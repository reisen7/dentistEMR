<template>
    <div class="inter-content">
        <p class="cur-position">个 人 信 息</p>
        <div class="form-content">
            <label for="change-user-img">
                <img id="userImg" :src="profile">
                <div class="user-img-text">编辑头像</div>
            </label>
            <input id="change-user-img" type="file" @change="changeImg" accept="image/*" style="display: none;" />
            <el-form :model="userData" label-width="10%" size="large" label-position="left">
                <el-form-item label="姓名" style="margin-bottom: 4vh;">
                    <el-input v-model="userData.name" />
                </el-form-item>
                <el-form-item label="密码" style="margin-bottom: 4vh;">
                    <el-input type="password" show-password v-model="userData.password" />
                </el-form-item>
                <el-form-item label="年龄" style="margin-bottom: 4vh;">
                    <el-input v-model="userData.age" />
                </el-form-item>
                <el-form-item label="地址" style="margin-bottom: 4vh;">
                    <el-input v-model="userData.address" />
                </el-form-item>
                <el-form-item label="性别" style="margin-bottom: 4vh;">
                    <el-radio-group v-model="userData.sex">
                        <el-radio label="男" />
                        <el-radio label="女" />
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="是否军人" style="margin-bottom: 4vh;">
                    <el-switch v-model="userData.classify" />
                </el-form-item>
                <el-button type="primary" @click="confirm" style="margin-left: 40%;">确定</el-button>
            </el-form>
        </div>
    </div>
</template>

<script>
import { reactive, ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useRoute, useRouter } from 'vue-router'
import headImg from '@/assets/imgs/headImg.png'
import axios from "axios"
export default {
    setup() {
        const route = useRoute()
        const router = useRouter()

        const userData = reactive({
            id: 0,
            account:"",
            password: "",
            name: "",
            age: "",
            address: "",
            classify: false,
            sex: ""
        })

        //接收路由跳转信息,并发起数据请求
        const patientId = JSON.parse(decodeURIComponent(route.query.data))
        const PatientSelectInfo = {
            id: patientId,
        }
        const url = "http://localhost:8909/Patient/PatientSelect"
        axios({
            method: "post",
            url: url,
            data: PatientSelectInfo,
        }).then(res => {
            if (res.data.message === "Success") {
                userData.id = res.data.data.id
                userData.account = res.data.data.account
                userData.password = res.data.data.password
                userData.name = res.data.data.name
                userData.age = res.data.data.age
                userData.address = res.data.data.address
                userData.sex = res.data.data.sex
                if (res.data.data.classify === "军人") {
                    userData.classify = true
                } else {
                    userData.classify = false
                }
            } else {
                ElMessage({
                    message: '查询失败!',
                    type: 'warning',
                })
            }
        }).catch(error => {
            ElMessage({
                message: '数据库错误！',
                type: 'error',
            })
        })

        const profile = ref(headImg)
        const changeImg = (val) => {
            // 获取选择的文件
            const files = val.target.files;
            console.log(val.fileInput)
            // 如果选择了文件
            if (files && files.length > 0) {
                // 获取第一个文件,用于传输到后端
                const file = files[0];
                console.log(file)
                // 检查文件类型是否为图片
                if (/^image\//.test(file.type)) {
                    // 创建 FileReader 实例
                    const reader = new FileReader();
                    // 当读取完成时更新图片预览
                    reader.onload = () => {
                        profile.value = reader.result;
                    };
                    // 读取文件
                    reader.readAsDataURL(file);
                }
            }
        }


        //确定按钮
        const confirm = () => {
            const userUpdateInfo = {
                id: userData.id,
                password: userData.password,
                name: userData.name,
                age: userData.age,
                address: userData.address,
                classify: userData.classify == true ? "军人" : "普通",
                sex: userData.sex
            }
            const url = "http://localhost:8909/Patient/PatientUpdate"
            axios({
                method: "post",
                url: url,
                data: userUpdateInfo,
            }).then(res => {
                if (res.data.message === "Success") {
                    ElMessage({
                        message: '保存成功！',
                        type: 'success',
                    })
                    //路由跳转
                    router.push({ path: '/pat/user', query: { data: encodeURIComponent(JSON.stringify(res.data.data)) } })
                } else {
                    ElMessage({
                        message: '编辑失败！',
                        type: 'warning',
                    })
                }
            }).catch(error => {
                ElMessage({
                    message: '数据库错误！',
                    type: 'error',
                })
            })

        }

        return {
            confirm,
            changeImg,
            profile,
            userData
        }
    }
}

</script>

<style scoped>
.inter-content {
    float: left;
    width: 85%;
    height: 87vh;
}

.cur-position {
    margin-left: 2%;
    margin-top: 3vh;
    font-size: 1.3vw;
    font-weight: 600;
}

.form-content {
    width: 70%;
    margin-left: 15%;
}

#userImg {
    margin-left: 40%;
    width: 10%;
}

.user-img-text {
    display: flex;
    justify-content: center;
    /*水平居中*/
    align-items: center;
    /*垂直居中*/
    width: 10%;
    height: 9vh;
    margin-left: 40%;
    margin-top: -10vh;
    margin-bottom: 5vh;
}
</style>
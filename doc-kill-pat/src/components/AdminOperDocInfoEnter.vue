<template>
    <div class="addDocPanel">
        <h3>新增医生信息</h3> <button class="returnBtn" @click="returnAndQuery">返回</button>
        <div class="infoInput">
            <div class="proFile">
                <input type="file" ref="fileInput" @change="previewImage" style="opacity:0;" accept="image/*" />
                <img :src="img" class="selectPro" />
            </div>
            <el-select v-model="department" class="m-2" placeholder="选择疾病" size="large">
                <el-option v-for="item in options" :key="item" :label="item" :value="item" />
            </el-select>
            <label style="margin-left: 34vw;"><input value="男" type="radio" v-model="sex" checked>男</label>
            <label style="margin-left: 3vw;"><input value="女" type="radio" v-model="sex">女</label>
            <div class="inputs">

                <input type="text" v-model="name" placeholder="请输入医生姓名">
                <input type="text" v-model="age" placeholder="请输入医生姓年龄">
                <input type="text" v-model="title" placeholder="请输入医生职称">
                <label style="margin-left: 3vw;">权限：&nbsp;<input type="checkbox" value="维护" v-model="authority">维护</label>
                <label style="margin-left: 2vw;"><input type="checkbox" value="查看" v-model="authority">查看</label>
                <label style="margin-left: 2vw;"><input type="checkbox" value="修改" v-model="authority">修改</label>
                <input type="text" v-model="account" placeholder="请输入医生账号">
                <input type="text" v-model="password" placeholder="请输入医生密码">
                <textarea cols="39" rows="6" v-model="introdution" placeholder="这里输入简介"></textarea>
                <el-button type="primary" @click="insertDoc">新增</el-button>
            </div>
        </div>

    </div>
</template>
<script>
import axios from 'axios';
import { ElMessage } from 'element-plus'
export default {
    data() {
        return {
            img: '',
            department: '',
            name: "",
            age: "",
            sex: "男",
            title: "",
            account: "",
            password: "",
            authority: [],
            introdution: "",
            options: "",
        }
    },
    created() {
        const options = JSON.parse(decodeURIComponent(this.$route.query.select))
        this.options = options,
            console.log(options);

    },
    computed: {
        selectedCount() {
            return this.authority.length;
        },
        selectedString() {
            return this.authority.join(', ');
        }
    },
    methods: {
        previewImage(event) {
            // 获取 input 元素
            const fileInput = this.$refs.fileInput;
            // 获取选择的文件
            const files = event.target.files;
            // 如果选择了文件
            if (files && files.length > 0) {
                // 获取第一个文件
                const file = files[0];
                // 检查文件类型是否为图片
                if (/^image\//.test(file.type)) {
                    // 创建 FileReader 实例
                    const reader = new FileReader();
                    // 当读取完成时更新图片预览
                    reader.onload = () => {
                        this.img = reader.result;
                    };
                    // 读取文件
                    reader.readAsDataURL(file);
                }
            }
        },
        returnAndQuery() {
            axios({
                method: "post",
                url: "http://localhost:8909/Admin/gotoOperDoctor",
            }).then(res => {
                if (res.data.message === "noData") {
                    console.log(res.data.data);
                    this.$router.push({ path: "/admin/adminOperDoc", query: { data: encodeURIComponent(JSON.stringify(res.data.data)) } });
                }
                if (res.data.message === "success") {
                    console.log(res.data.data);
                    this.$router.push({ path: "/admin/adminOperDoc", query: { data: encodeURIComponent(JSON.stringify(res.data.data)) } });
                }
            }).catch(error => {
                ElMessage({
                    message: 'G!',
                    type: 'error',
                })
            })
        },
        insertDoc() {
            const authorityList = this.authority;
            const myStringAuthority = authorityList.join(",");
            const doc = new FormData();
            doc.append("img", this.$refs.fileInput.files[0]);
            doc.append("department", this.department);
            doc.append("name", this.name);
            doc.append("age", this.age);
            doc.append("sex", this.sex);
            doc.append("title", this.title);
            doc.append("account", this.account);
            doc.append("password", this.password);
            doc.append("authority", myStringAuthority);
            doc.append("introduction", this.introdution);
            console.log(doc.get("img"));
            axios({
                method: "post",
                url: "http://localhost:8909/Admin/addDoctor",
                data: doc,
                headers: {
                    processData: false,
                    contentType: false,
                }
            }).then(res => {
                if (res.data.message === "hasRegisterAccount") {
                    ElMessage({
                        message: '该手机号已注册！',
                        type: 'error',
                    })
                }
                if (res.data.message === "RegisterFailed") {
                    ElMessage({
                        message: '注册失败！',
                        type: 'error',
                    })
                }
                if (res.data.message === "RegisterSuccess") {
                    ElMessage({
                        message: '注册成功！',
                        type: 'success',
                    })
                    this.img = '',
                    this.department = '',
                    this.name = "",
                    this.age = "",
                    this.sex = "男",
                    this.title = "",
                    this.account = "",
                    this.password = "",
                    this.authority = [],
                    this.introdution = ""
                }
            }).catch(error => {
                if (error.data.message === "ERROR") {
                    ElMessage({
                        message: 'G!',
                        type: 'error',
                    })
                }
            })
        }
    }
}
</script>
<style scoped>
.addDocPanel {
    float: left;
    width: 85%;
    height: 87vh;
}

h3 {
    margin-left: 2%;
    margin-top: 3vh;
    font-size: 1.3vw;
    font-weight: 600;
}

.returnBtn {
    width: 10vw;
    height: 5vh;
    border: none;
    background-color: #dee6f8;
    border-radius: 5vw;
    float: right;
    margin-right: 5vw;
    margin-top: -6vh;
}

.infoInput {
    width: 50vw;
    margin-top: 7vh;
    margin-left: 17vw;
    padding-top: 1vh;
    height: 70vh;
    background-color: #edf0f7;
    border-radius: 5px;
}

.proFile {
    margin-left: auto;
    margin-right: auto;
    margin-top: 30px;
    border-radius: 50%;
    background-color: white;
    background-image: url('../assets/imgs/selectProFile.png');
    background-size: 60%;
    background-repeat: no-repeat;
    background-position: center;
    padding: -20px;
    height: 130px;
    width: 130px;
}

.proFile input {
    position: absolute;
    height: 130px;
    width: 130px;
    border-radius: 50%;
}

.proFile img {
    position: relative;
    border-radius: 50%;
    height: 100%;
    width: 100%;
    pointer-events: none;
}

.m-2 {
    width: 10vw;
    float: right;
    margin-right: 7vw;
    margin-top: -10vh;
}

.inputs {
    width: 60vw;
    height: 45vh;
    margin-top: -1vh;
    margin-left: 3vw;
    /* border: 1px solid gray; */
}

input:focus,
textarea:focus {
    outline: none;
}

.inputs input[type="text"] {
    width: 20vw;
    margin-left: 1vw;
    height: 5vh;
    margin-top: 4vh;
    border-radius: 3px;
    border: 0;
    padding-left: 20px;
    font-size: 1vw;
}

textarea {
    border: 0;
    margin-left: 1vw;
    margin-top: 2vh;
    font-size: 1vw;
}

button {
    margin-left: 4vw;
    margin-top: -10vh;
    width: 15vw;
    height: 5vh;
    border-radius: 10px;
    font-size: 1vw;
}
</style>
<template>
    <div class="Register">
        <div class="proFile">
            <input type="file" ref="fileInput" @change="previewImage" style="opacity:0;" accept="image/*" />
            <img :src="profile" class="selectPro" />
        </div>
        <div class="messageInput">
            <input type="text" v-model="name" placeholder="请输入真实姓名">
            <input type="text" v-model="age" placeholder="请输入年龄">
            <input type="text" v-model="account" placeholder="请输入手机号绑定登录">
            <input type="password" v-model="password" placeholder="请输入登录密码至少八位数">
            <input type="text" v-model="address" placeholder="请输入通信地址">
            <p>性别：<label><input value="男" type="radio" v-model="sex" checked>男</label>
                <label><input value="女" type="radio" v-model="sex">女</label>
            </p>

            <p>类别：<label><input value="普通" type="radio" v-model="classify" checked>普通</label>
                <label><input value="军人" type="radio" v-model="classify">军人</label>
                <label> <input value="特殊" type="radio" v-model="classify">特殊</label>
            </p>
            <p><textarea style="margin-left: -0.5vw;" placeholder="既往病史描述：没有填无" cols="35" rows="10" v-model="anamnesis"
                    required></textarea>
            </p>
            <el-button :plain="true" @click="register" class="registerBtn">注册</el-button>
        </div>

    </div>
</template>
<script>
import axios from "axios"
import { ElMessage } from 'element-plus'
export default {
    data() {
        return {
            profile: '',
            name: '',
            age: '',
            account: '',
            password: "",
            sex: "男",
            classify: "普通",
            anamnesis: '',
            address: "",
            regName: /^[\u4e00-\u9fa5·]{2,20}$/,
            regAge: /^([1-9]|[1-9][0-9]|1[01][0-9]|120)$/,
            regAccount: /^1[3-9]\d{9}$/,
            regPassword: /.{8,}/,
        }
    },
    methods: {
        previewImage(event) {
            // 获取 input 元素
            // const fileInput = this.$refs.fileInput;
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
                        this.profile = reader.result;
                    };
                    // 读取文件
                    reader.readAsDataURL(file);
                }
            }
        },
        register() {
            if (!this.regName.test(this.name.trim())) {
                ElMessage({
                    message: '请输入正确姓名！',
                    type: 'error',
                })
                return;
            }
            if (!this.regAge.test(this.age.trim())) {
                ElMessage({
                    message: '请输入正确年龄！',
                    type: 'error',
                })
                return;
            }
            if (!this.regAccount.test(this.account.trim())) {
                ElMessage({
                    message: '请输入正确手机号！',
                    type: 'error',
                })
                return;
            }
            if (!this.regPassword.test(this.password.trim())) {
                ElMessage({
                    message: '请输入至少8位的密码！',
                    type: 'error',
                })

                return;
            }
            if (this.address.trim() === "") {
                ElMessage({
                    message: '请输入通信地址！',
                    type: 'error',
                })
                return;
            }
            if (this.anamnesis.trim() === "") {
                ElMessage({
                    message: '请输入既往病史！',
                    type: 'error',
                })
                return;
            }
            const Patient = new FormData();
            Patient.append("account", this.account);
            Patient.append("password", this.password);
            Patient.append("name", this.name);
            Patient.append("age", this.age);
            Patient.append("sex", this.sex);
            Patient.append("address", this.address);
            Patient.append("classify", this.classify);
            Patient.append("anamnesis", this.anamnesis);
            Patient.append("profile", this.$refs.fileInput.files[0]);
            console.log(Patient.get("profile"));
            axios({
                method: "post",
                url: "http://localhost:8909/Patient/Register",
                data: Patient,
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
                    this.$router.push('/Login');
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
.Register {
    float: right;
    margin-right: 20px;
    margin-top: 5vh;
    width: 35%;
    height: 90vh;
    box-sizing: border-box;
    background-color: rgba(154, 241, 251, 0.811);
    border-radius: 15px;
    overflow-y: auto;
}

.Register::-webkit-scrollbar {
    display: none;
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

.messageInput {
    margin: auto;
    width: 300px;
}

input[type="text"],
input[type="password"] {
    width: 300px;
    outline: none;
    border: 0;
    border-radius: 5px;
    margin-top: 30px;
    width: 300px;
    height: 40px;
    padding-left: 20px;
}

.messageInput p {
    margin-left: 2vw;
}

.registerBtn {
    margin-top: 5vh;
    width: 300px;
    margin-left: 15px;
    margin-bottom: 5vh;
    border: 0;
    height: 40px;
    border-radius: 5px;
    background-color: rgb(22, 153, 224);
    color: white;
    font-size: 1rem;
    font-weight: bold;
}
</style>
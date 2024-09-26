<template>
    <div class="aside">
        <h3>登录</h3>
        <div class="form">
            <div>
                <img src="../assets/imgs/user.png" id="ico-account">
                <input type="text" id="account" placeholder="请输入账号/手机号" v-model="account" @input="checkAccount">
                <div style="color: red;font-family:幼圆"> {{ checkAccount() ? "" : "请输入正确的账号!" }} </div>
            </div>
            <div>
                <img src="../assets/imgs/block.png" id="ico-password">
                <input type="password" placeholder="请输入密码" id="password" v-model="password" @input="checkPass">
                <div style="color: red;font-family:幼圆"> {{ checkPass() ? "" : "请输入正确格式的密码!" }} </div>
            </div>
            <div class="selects">
                <label><input value="普通" type="radio" v-model="classify" checked>普通</label>
                <label><input value="医生" type="radio" v-model="classify">医生</label>
                <label> <input value="管理员" type="radio" v-model="classify">管理员</label>
                <router-link id="router" to="/Register">去注册</router-link>&nbsp;
            </div>

            <el-button :plain="true" @click="Login" class="LoginBtn">登录</el-button>
        </div>
    </div>
</template>
<script>
import axios from "axios"
import { ElMessage,ElMessageBox } from 'element-plus'
export default {
    data() {
        return {
            account: "",
            password: "",
            classify: "普通",
            valid: true,
            regAccount: /^1[3456789]\d{9}$/,
            regPass: /.{8,}/,
        }
    },
    methods: {
        checkAccount() {
            if (!this.regAccount.test(this.account)) {
                this.valid = false
            } else {
                this.valid = true
            }
            return this.valid;
        },
        checkPass() {
            if (!this.regPass.test(this.password)) {
                this.valid = false
            } else {
                this.valid = true
            }
            return this.valid;
        },
        Login() {
            if (!this.valid) {
                return;
            }

            const data = {
                account: this.account,
                password: this.password,
                classify: this.classify,
            }
            console.log(data);
            var url = ""
            if (data.classify == "普通") {
                url = "http://localhost:8909/Patient/Login"
            } else if (data.classify == "医生") {
                url = "http://localhost:8909/Doctor/Login"
            } else {
                url = "http://localhost:8909/Admin/Login"
            }
            axios({
                method: "post",
                url: url,
                data: data,
            }).then(res => {
                // if (data.classify == "管理员") {
                //     if (res.data.message === "LoginSuccess") {
                //         ElMessage({
                //             message: '管理登录成功！',
                //             type: 'success',
                //         })
                //         this.$router.push('/admin/adminPanel');
                //     } else if (res.data.message === "LoginError") {
                //         ElMessage({
                //             message: '管理登录失败！',
                //             type: 'error',
                //         })
                //     }
                // }

                // if (data.classify == "管理员") {
                //     if (res.data.message === "LoginSuccess") {
                //         //人脸识别
                //         const adminId = res.data.data.id
                //         const data1 = {
                //             id: adminId
                //         }
                //         console.log("已经入人脸识别步骤")
                //         ElMessageBox.confirm(
                //             '管理员登录需要进行人脸识别认证，是否继续?',
                //             '提示',
                //             {
                //                 confirmButtonText: '确定',
                //                 cancelButtonText: '取消',
                //                 type: 'info',
                //             }
                //         ).then(() => {
                //             const url1 = "http://localhost:8909/Admin/faceLogin"
                //             axios({
                //                 method: "post",
                //                 url: url1,
                //                 data: data1,
                //             }).then(res => {
                //                 if (res.data.message === "Success") {
                //                     ElMessage({
                //                         message: '人脸识别认证成功!',
                //                         type: 'success',
                //                     })
                //                     this.$router.push('/admin/adminPanel');
                //                 } else {
                //                     ElMessage({
                //                         message: '人脸识别失败!',
                //                         type: 'warning',
                //                     })
                //                 }
                //             }).catch(error => {
                //                 ElMessage({
                //                     message: '数据库错误！',
                //                     type: 'error',
                //                 })
                //             })
                //         }).catch(() => {
                //             ElMessage({
                //                 type: 'info',
                //                 message: '已取消',
                //             })
                //         })
                //     } else if (res.data.message === "LoginError") {
                //         ElMessage({
                //             message: '管理登录失败！',
                //             type: 'error',
                //         })
                //     }
                // }

                if (data.classify == "普通") {
                    if (res.data.message === "LoginSuccess") {
                        ElMessage({
                            message: '用户登录成功！',
                            type: 'success',
                        })
                        this.$router.push({ path: '/pat/user', query: { data: encodeURIComponent(JSON.stringify(res.data.data.id)) } })
                    } else if (res.data.message === "LoginError") {
                        ElMessage({
                            message: '用户登录失败！',
                            type: 'error',
                        })
                    }
                }

                if (data.classify == "管理员") {
                    if (res.data.message === "LoginSuccess") {
                        ElMessage({
                            message: '用户登录成功！',
                            type: 'success',
                        })
                        this.$router.push({ path: '/admin/adminPanel', query: { data: encodeURIComponent(JSON.stringify(res.data.data.id)) } })
                    } else if (res.data.message === "LoginError") {
                        ElMessage({
                            message: '用户登录失败！',
                            type: 'error',
                        })
                    }
                }

                if (data.classify == "医生") {
                    if (res.data.message === "LoginSuccess") {
                        ElMessage({
                            message: '用户登录成功！',
                            type: 'success',
                        })
                        this.$router.push({ path: '/doctor/Panel', query: { data: encodeURIComponent(JSON.stringify(res.data.data.id)) } })
                    } else if (res.data.message === "LoginError") {
                        ElMessage({
                            message: '用户登录失败！',
                            type: 'error',
                        })
                    }
                }


            }).catch(error => {
                ElMessage({
                    message: 'G!!!!!',
                    type: 'error',
                })
            })
        }

    }
}
</script>

<style scoped>
.aside {
    float: right;
    margin-top: 10px;
    width: 30%;
    height: 65vh;
    border-radius: 10px;
    box-sizing: border-box;
    background-color: rgba(219, 230, 239, 0.652);
}

h3 {
    margin-top: 60px;
    text-align: center;
    font-size: 30px;
    font-family: "幼圆";
}

.form {
    margin: auto;
    width: 300px;
    height: 500px;
}

input:focus {
    outline: none;
}

#account {
    outline: none;
    border-radius: 5px;
    border: 0;
    margin-top: 30px;
    width: 300px;
    height: 40px;
    padding-left: 40px;
    /* 图标宽度 + padding */
}

#password {
    outline: none;
    border: 0;
    border-radius: 5px;
    margin-top: 30px;
    width: 300px;
    height: 40px;
    padding-left: 40px;
    /* 图标宽度 + padding */
}

#ico-account {
    position: absolute;
    height: 20px;
    width: 20px;
    margin-top: 40px;
    margin-left: 10px;
}

#ico-password {
    position: absolute;
    height: 20px;
    width: 20px;
    margin-top: 40px;
    margin-left: 10px;
}

.selects {
    margin-top: 40px;
    margin-left: 40px;
    width: 300px;
}

label {
    margin-left: 10px;
    font-family: "幼圆";
    font-weight: bold;
}

.LoginBtn {
    margin-top: 5vh;
    width: 300px;
    margin-left: 15px;
    border: 0;
    height: 40px;
    border-radius: 5px;
    background-color: rgb(22, 153, 224);
    color: white;
    font-size: 1rem;
    font-weight: bold;
}

#router {
    position: absolute;
    margin-top: 120px;
    font-size: 14px;
    color: rgb(245, 104, 10);
    font-family: "幼圆";
    font-weight: 700;
}
</style>
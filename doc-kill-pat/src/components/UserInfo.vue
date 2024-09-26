<template>
    <div :="interContent">
        <p :="curPosition">个 人 信 息</p>
        <div class="left-content">
            <img :="userImg" src="../assets/imgs/headImg.png">
            <button id="update-info-btn" type="button" @click="changeUserInfo">编辑个人信息</button>
        </div>
        <div class="right-content">
            <div class="row-info">
                <div class="row-key">账&nbsp;号&nbsp;:</div>
                <div id="zh-value" class="row-value">{{ userData.account }}</div>
            </div>
            <div class="row-info">
                <div class="row-key">姓&nbsp;名&nbsp;:</div>
                <div id="xm-value" class="row-value">{{ userData.name }}</div>
            </div>
            <div class="row-info">
                <div class="row-key">性&nbsp;别&nbsp;:</div>
                <div id="xb-value" class="row-value">{{ userData.sex }}</div>
            </div>
            <div class="row-info">
                <div class="row-key">年&nbsp;龄&nbsp;:</div>
                <div id="nl-value" class="row-value">{{ userData.age }}</div>
            </div>
            <div class="row-info">
                <div class="row-key">地&nbsp;址&nbsp;:</div>
                <div id="dz-value" class="row-value">{{ userData.address }}</div>
            </div>
            <div class="row-info">
                <div class="row-key">是否军人&nbsp;:</div>
                <div id="jr-value" class="row-value">{{ userData.classify }}</div>
            </div>
        </div>
    </div>
</template>

<script>
import { reactive } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
import axios from "axios"

export default {
    setup() {
        const interContent = reactive({
            id: "inter-content",
            class: "inter-content"
        })
        const curPosition = reactive({
            id: "cur-position",
            class: "cur-position"
        })
        const userImg = reactive({
            id: "user-img",
            class: "user-img"
        })

        const userData = reactive({
            id: 0,
            account: "",
            password: "",
            name: "",
            age: "",
            address: "",
            classify: false,
            sex: ""
        })

        //接收路由数据
        const route = useRoute()
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
                    userData.classify = "是"
                } else {
                    userData.classify = "否"
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

        //路由跳转
        const router = useRouter()
        const changeUserInfo = () => {
            router.push({ path: '/pat/userupdate', query: { data: encodeURIComponent(JSON.stringify(userData.id)) } })
        }

        return {
            interContent,
            curPosition,
            userImg,
            userData,
            changeUserInfo
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

.left-content {
    float: left;
    width: 15%;
    height: 30vh;
    margin-left: 15%;
    margin-top: 15vh;
}

.user-img {
    width: 100%;
}

#update-info-btn {
    width: 80%;
    margin-top: 3vh;
    margin-left: 10%;
    background: #f8f8f8;
    border: 1px solid #ccc;
    border-radius: 3px;
    color: #333;
    cursor: pointer;
    font-size: 1.2vw;
    padding: 8px 16px;
    text-align: center;
    text-decoration: none;
    transition: all .2s ease-in-out;
}

#update-info-btn:hover,
#update-info-btn:focus {
    background: #eee;
}

.right-content {
    float: left;
    width: 40%;
    margin-left: 10%;
    margin-top: 5vh;
}

.row-info {
    height: 4vh;
    margin-top: 5vh;
    border-bottom-style: solid;
    border-bottom-width: 2px;
    border-bottom-color: #e7e7e7;
}

.row-key {
    float: left;
    color: #1F1439;
    font-size: 1.3vw;
    width: 30%;
    margin-left: 2%;
}

.row-value {
    color: #1F1439;
    font-size: 1.3vw;
}
</style>
<template>
    <div :="topBar">牙 医 病 历 系 统</div>
    <div :="leftBar">
        <div :="leftBarNavi"><img src="../assets/imgs/navi.png" style="width:2vw">&nbsp;导&nbsp;航</div>
        <div :="leftBarCon1" @click="caseInfo"><img src="../assets/imgs/case.png" style="width:1.6vw;margin-right: 1vw;"><p class="left-bar-text">个人病历</p></div>
        <!-- <div :="leftBarCon2" @click="adviceInfo"><img src="../assets/imgs/advice.png" style="width:1.3vw;margin-right: 1vw;"><p class="left-bar-text">医嘱建议</p></div> -->
        <div :="leftBarCon3" @click="diagnoseInfo"><img src="../assets/imgs/diagnose.png" style="width:1.3vw;margin-right: 1vw;"><p class="left-bar-text">诊断信息</p></div>
        <!-- <div :="leftBarCon4" @click="appointInfo"><img src="../assets/imgs/appoint.png" style="width:1.5vw;margin-right: 1vw;"><p class="left-bar-text">挂号预约</p></div> -->
        <div :="leftBarCon5" @click="aboutInfo"><img src="../assets/imgs/about.png" style="width:1.7vw;margin-right: 1vw;"><p class="left-bar-text">关于&帮助</p></div>
    </div>
    <div :="rightUser" @click="patientInfo">
        <img src="../assets/imgs/headImg.png" style="width:2.5vw;margin-right: 1vw;">
        <p class="right-user-text">患者</p>
    </div>
    <div :="rightUser">
        <router-link to="/"  :="rightUserLoginOut">登出</router-link>
    </div>
</template>
  
<script>
import { reactive } from 'vue';
import { useRoute, useRouter } from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
import axios from "axios"

export default {
    setup() {
        const route = useRoute()
        const router = useRouter()

        const topBar = reactive({
            id: "top-bar",
            class: "top-bar"
        })
        const leftBar = reactive({
            id: "left-bar",
            class: "left-bar"
        })
        const leftBarNavi = reactive({
            id: "left-bar-navi",
            class: "left-bar-navi"
        })
        const leftBarCon1 = reactive({
            id: "left-bar-con1",
            class: "left-bar-con"
        })
        const leftBarCon2 = reactive({
            id: "left-bar-con2",
            class: "left-bar-con"
        })
        const leftBarCon3 = reactive({
            id: "left-bar-con3",
            class: "left-bar-con"
        })
        const leftBarCon4 = reactive({
            id: "left-bar-con4",
            class: "left-bar-con"
        })
        const leftBarCon5 = reactive({
            id: "left-bar-con5",
            class: "left-bar-con"
        })
        const rightUser = reactive({
            id: "right-user",
            class: "right-user"
        })
        const leftBottomBar = reactive({
            id: "left-bottom-bar",
            class: "left-bottom-bar"
        })
        const rightUserLoginOut = reactive({
                id: "left-bottom-loginOut",
                class: "left-bottom-bar-loginOut"
        })

        //点击用户信息，接收路由数据并跳转
        const patientInfo = () => {
            const patientId = JSON.parse(decodeURIComponent(route.query.data))
            router.push({ path: '/pat/user', query: { data: encodeURIComponent(JSON.stringify(patientId)) } })
        }

        //点击个人病历
        const caseInfo = () => {
            const patientId = JSON.parse(decodeURIComponent(route.query.data))
            router.push({ path: '/pat/case', query: { data: encodeURIComponent(JSON.stringify(patientId)) } })
        }

        //点击医嘱建议
        const adviceInfo = () => {
            const patientId = JSON.parse(decodeURIComponent(route.query.data))
            router.push({ path: '/pat/advice', query: { data: encodeURIComponent(JSON.stringify(patientId)) } })
        }

        //点击诊断信息
        const diagnoseInfo = () => {
            const patientId = JSON.parse(decodeURIComponent(route.query.data))
            router.push({ path: '/pat/diagnose', query: { data: encodeURIComponent(JSON.stringify(patientId)) } })
        }

        //点击挂号预约
        const appointInfo = () => {
            const patientId = JSON.parse(decodeURIComponent(route.query.data))
            router.push({ path: '/pat/appoint', query: { data: encodeURIComponent(JSON.stringify(patientId)) } })
        }

        //点击关于帮助
        const aboutInfo = () => {
            const patientId = JSON.parse(decodeURIComponent(route.query.data))
            router.push({ path: '/pat/about', query: { data: encodeURIComponent(JSON.stringify(patientId)) } })
        }

        return {
            topBar,
            leftBar,
            leftBarNavi,
            leftBarCon1,
            leftBarCon2,
            leftBarCon3,
            leftBarCon4,
            leftBarCon5,
            rightUser,
            leftBottomBar,
            rightUserLoginOut,
            patientInfo,
            caseInfo,
            adviceInfo,
            diagnoseInfo,
            appointInfo,
            aboutInfo
        }
    }
}

</script>

<style scoped>
.top-bar {
    display: flex;
    justify-content: center;
    /*水平居中*/
    align-items: center;
    /*垂直居中*/
    background-color: #F4F6FB;
    width: 100%;
    height: 10vh;
    font-size: 2vw;
    font-weight: 600;
    color: #347CCF;
    border-bottom-style: solid;
    border-bottom-width: 2px;
    border-bottom-color: #e7e7e7;
}

.left-bar {
    position: relative;
    float: left;
    background-color: #F4F6FB;
    width: 15%;
    height: 97vh;
    margin-top: -10vh;
}

.left-bar-navi {
    display: flex;
    justify-content: center;
    /*水平居中*/
    align-items: center;
    /*垂直居中*/
    background-color: #F4F6FB;
    widows: 100%;
    height: 10vh;
    font-size: 1.5vw;
    font-weight: 500;
    color: #1F1439;
    border-bottom-style: solid;
    border-bottom-width: 2px;
    border-bottom-color: #e7e7e7;
}

.icon-img {
    width: 2vw;
}

.left-bar-con {
    display: flex;
    justify-content: center;
    /*水平居中*/
    align-items: center;
    /*垂直居中*/
    width: 100%;
    height: 13vh;
    cursor: pointer;
}

.left-bar-text {
    color: #1F1439;
    text-decoration: none;
    font-size: 1.3vw;
}

.right-user {
    display: flex;
    justify-content: center;
    /*水平居中*/
    align-items: center;
    /*垂直居中*/
    float: right;
    margin-top: -10vh;
    margin-right: 5%;
    width: 12%;
    height: 10vh;
    cursor: pointer;
}

.right-user-text {
    color: #1F1439;
    text-decoration: none;
    font-size: 1.3vw;
    display: inline-block;
    max-width: 50%;
    /* 最大宽度 */
    overflow: hidden;
    /* 超出部分隐藏 */
    white-space: nowrap;
    /* 不换行 */
    text-overflow: ellipsis;
    /* 显示省略号 */
}

.left-bottom-bar {
    position: absolute;
    display: flex;
    justify-content: center;
    /*水平居中*/
    align-items: center;
    /*垂直居中*/
    color: #1F1439;
    font-size: 0.8vw;
    border-top-style: solid;
    border-top-width: 2px;
    border-top-color: #c1c1c1;
    width: 100%;
    height: 17vh;
    bottom: 0;
}
.left-bottom-bar-loginOut {
    position: absolute;
    display: flex;
    justify-content: center;
    /*水平居中*/
    align-items: center;
    /*垂直居中*/
    color: #1F1439;
    right: 4vw;
    font-size: 1vw;
    border-top-style: solid;
    border-top-width: 2px;
    border-top-color: #c1c1c1;
}
</style>
<template>
    <div :="topBar">牙 医 病 历 系 统</div>
    <div :="leftBar">
        <div :="leftBarNavi" ><img src="../assets/imgs/navi.png" style="width:2vw">&nbsp;导&nbsp;航
        </div>

        <div :="leftBarCon3"><img src="../assets/imgs/adminPatLogo.png" style="width:1.8vw;margin-right: 1vw;"><router-link
                to="/doctor/DoctorOperPat" :="leftBarText3">病人管理</router-link></div>
      
    </div>
    <div :="rightUser" >
        <img src="../assets/imgs/headImg.png" style="width:2.5vw;margin-right: 1vw;pointer-events: none;">
        <p :="rightUserText" @click="adminInfo">医生</p>
        <!-- <input> -->
    </div>
    <div :="rightUser" >
        <router-link to="/"  :="rightUserLoginOut">登出</router-link>
    </div>
</template>
  
<script>
import axios from 'axios'
import { ElMessage, ElMessageBox } from 'element-plus'
export default {
    data() {
        return {
            topBar: {
                id: "top-bar",
                class: "top-bar"
            },
            leftBar: {
                id: "left-bar",
                class: "left-bar"
            },
            leftBarNavi: {
                id: "left-bar-navi",
                class: "left-bar-navi"
            },
            leftBarCon1: {
                id: "left-bar-con1",
                class: "left-bar-con"
            },
            leftBarCon2: {
                id: "left-bar-con2",
                class: "left-bar-con"
            },
            leftBarCon3: {
                id: "left-bar-con3",
                class: "left-bar-con"
            },
            leftBarCon4: {
                id: "left-bar-con4",
                class: "left-bar-con"
            },
            leftBarCon5: {
                id: "left-bar-con5",
                class: "left-bar-con"
            },
            leftBarText1: {
                id: "left-bar-text1",
                class: "left-bar-text"
            },
            leftBarText2: {
                id: "left-bar-text2",
                class: "left-bar-text"
            },
            leftBarText3: {
                id: "left-bar-text3",
                class: "left-bar-text"
            },
            leftBarText4: {
                id: "left-bar-text4",
                class: "left-bar-text"
            },
            leftBarText5: {
                id: "left-bar-text5",
                class: "left-bar-text"
            }, rightUser: {
                id: "right-user",
                class: "right-user"
            }, rightUserText: {
                id: "right-user-text",
                class: "right-user-text"
            }, leftBottomBar: {
                id: "left-bottom-bar",
                class: "left-bottom-bar"
            }, rightUserLoginOut:{
                id: "left-bottom-loginOut",
                class: "left-bottom-bar-loginOut"
            }
        }
    },
    methods: {
        gotoOperDepart() {
            axios({
                method: "post",
                url: "http://localhost:8909/Admin/gotoOperDepart",
            }).then(res => {
                if (res.data.message === "noData") {
                    this.$router.push("/admin/adminOperDepart");
                }
                if (res.data.message === "success") {
                    console.log(res.data.data);
                    this.$router.push({ path: "/admin/adminOperDepart", query: { data: encodeURIComponent(JSON.stringify(res.data.data)) } });
                }
            }).catch(error => {
                ElMessage({
                    message: 'G!',
                    type: 'error',
                })
            })
        },

        adminInfo () {
            console.log(1)
            this.$router.push({ path: '/admin/adminPanel'} )
        },

        gotoOperDoctor() {
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
    pointer-events: none;
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
}

.left-bar-text {
    cursor: pointer;
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
<template>
    <div class="adminEnterDepart">
        <h3>新增疾病</h3>
        <button class="returnBtn" @click="returnQurey">返回</button>
        <div class="mainPanel">
            <h4>录入疾病</h4>
            <input type="text" v-model="name" placeholder="请输入疾病名称">
            <input type="text" v-model="introduction" placeholder="请输入疾病简介">
            <input type="text" v-model="tel" placeholder="请输入疾病联系电话">
            <el-button :plain="true" class="Insertbtn" @click="Enter">录入</el-button>
        </div>
    </div>
</template>
<script>
import { ElMessage } from 'element-plus'
import axios from "axios"
import { reactive } from 'vue'
import { useRouter } from 'vue-router'
export default {
    data() {
        return {
            name: "",
            introduction: "",
            tel: "",
        }
    },
    methods: {
        Enter() {
            const department = {
                name: this.name,
                introduction: this.introduction,
                tel: this.tel,
            }
            axios({
                method: "post",
                url: "http://localhost:8909/Admin/newDepart",
                data: department,
            }).then(res => {
                if (res.data.message == "failed") {
                    ElMessage({
                        message: '添加错误！',
                        type: 'error',
                    })
                }
                if (res.data.message == "success") {
                    ElMessage({
                        message: '添加成功！',
                        type: 'success',
                    })
                    this.name = "",
                        this.introduction = "",
                        this.tel = ""
                }
            }).catch(error => {
                ElMessage({
                    message: 'G!',
                    type: 'error',
                })
            })
        },
        returnQurey() {
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
        }
    }
}
</script>
<style scoped>
.adminEnterDepart {
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

h4 {
    font-size: 1.5vw;
    margin-left: 9vw;
    font-family: "站酷文艺体";
    font-weight: 500;
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

.mainPanel {
    float: left;
    margin-left: 25vw;
    margin-top: 6vh;
    width: 25vw;
    height: 50vh;
    background-color: #f3f0f0;
    border-radius: 5px;
    box-shadow: 0px 0px 10px rgba(14, 14, 14, 0.5);
}

input:focus {
    outline: none;
}

.mainPanel input {
    width: 20vw;
    margin-left: 2vw;
    margin-top: 3.5vh;
    height: 4vh;
    border: none;
    border-radius: 5px;
    padding-left: 1vw;
}

.Insertbtn {
    width: 14vw;
    height: 4vh;
    margin-top: 8vh;
    margin-left: 5.5vw;
    border: none;
    background-color: #5ac1f8;
    border-radius: 5px;
    padding-left: 1vw;
    font-family: "站酷文艺体";
    font-weight: 500;
    font-size: 1.1vw;
    color: white;
}
</style>
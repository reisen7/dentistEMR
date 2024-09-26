<template>
    <div class="docInfoDetail">
        <h3>医生信息详情</h3> <button class="returnBtn" @click="returnQuery">返回</button>

        <div class="infoShow">
            <img :src="docInfo.img" class="proFile">
            <table
                style="position: absolute;margin-top: -20vh;margin-left: 20vw;width: 20vw;height: 15vh;text-align: center;font-size: 1.3vw; ">
                <tr>
                    <td style=" border-bottom: 1px solid #ddd;">id:</td>
                    <td style=" border-bottom: 1px solid #ddd;">{{ docInfo.id }}</td>
                </tr>
                <tr>
                    <td style=" border-bottom: 1px solid #ddd;">姓名:</td>
                    <td style=" border-bottom: 1px solid #ddd;">{{ docInfo.name }}</td>
                </tr>
            </table>
            <table style="border-collapse: collapse;" class="allTable">
                <tr>
                    <td style="width: 4vw;">性别：</td>
                    <td>{{ docInfo.sex }}</td>
                    <td style="width: 4vw;">年龄</td>
                    <td>{{ docInfo.age }}</td>
                </tr>
                <tr>
                    <td>职称：</td>
                    <td>{{ docInfo.title }}</td>
                    <td>主治：</td>
                    <td>{{ docInfo.department }}</td>
                </tr>
                <tr>
                    <td>账号：</td>
                    <td>{{ docInfo.account }}</td>
                    <td>密码：</td>
                    <td>{{ docInfo.password }}</td>

                </tr>
                <tr>
                    <td>权限：</td>
                    <td>{{ docInfo.authority }}</td>
                    <td>简介：</td>
                    <td>{{ docInfo.introduction }}</td>

                </tr>
            </table>
        </div>


    </div>
</template>
<script>
import { useRoute,useRouter } from 'vue-router';
import { ElMessage, ElMessageBox } from 'element-plus'
import axios from 'axios';
export default {
    setup() {
        const route = useRoute();
        const router = useRouter();
        const docInfo = JSON.parse(decodeURIComponent(route.query.data))
        const returnQuery = () => {
            axios({
                method: "post",
                url: "http://localhost:8909/Admin/gotoOperDoctor",
            }).then(res => {
                if (res.data.message === "noData") {
                    console.log(res.data.data);
                    router.push({ path: "/admin/adminOperDoc", query: { data: encodeURIComponent(JSON.stringify(res.data.data)) } });
                }
                if (res.data.message === "success") {
                    console.log(res.data.data);
                    router.push({ path: "/admin/adminOperDoc", query: { data: encodeURIComponent(JSON.stringify(res.data.data)) } });
                }
            }).catch(error => {
                ElMessage({
                    message: 'G!',
                    type: 'error',
                })
            })
        }
        return { docInfo,returnQuery }
    }
}
</script>

<style scoped>
.docInfoDetail {
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

.infoShow {
    width: 50vw;
    height: 70vh;
    border: 0;
    margin-left: 15vw;
    margin-top: 5vh;
    border-radius: 5px;
    box-shadow: 0 0 5px 1px gray;
}

.proFile {
    margin-left: 5vw;
    margin-top: 3vh;
    width: 10vw;
    height: 23vh;
}

.allTable {
    width: 40vw;
    height: 40vh;
    margin-left: 5vw;
    text-align: left;
}

.allTable td {
    border-bottom: 1px solid #ddd;
    padding: 8px;
    text-align: left;
}</style>
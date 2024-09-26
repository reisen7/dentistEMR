<template>
    <div class="docInfoDetail">
        <h3>病人信息详情</h3> <router-link to="/admin/adminOperPat"><button class="returnBtn">返回</button></router-link>

        <div class="infoShow">
            <table style="border-collapse: collapse;" class="allTable">
                <tr>
                    <td style="width: 6vw;">id</td>
                    <td>{{ patInfo.id }}</td>
                    <td style="width: 6vw;">姓名</td>
                    <td>{{ patInfo.name }}</td>
                </tr>
                <tr>
                    <td >性别：</td>
                    <td>{{ patInfo.sex }}</td>
                    <td >年龄</td>
                    <td>{{ patInfo.age }}</td>
                </tr>
                <tr>
                    <td>既往病史：</td>
                    <td>{{ patInfo.anamnesis }}</td>
                    <td>地址：</td>
                    <td>{{ patInfo.address }}</td>
                </tr>
                <tr>
                    <td>账号：</td>
                    <td>{{ patInfo.account }}</td>
                    <td>密码：</td>
                    <td>{{ patInfo.password }}</td>

                </tr>
                <tr>
                    <td>类别</td>
                    <td>{{ patInfo.classify }}</td>
                    <td></td>
                    <td></td>

                </tr>
            </table>
        </div>


    </div>
</template>
<script>
import { useRoute } from 'vue-router';
export default {
    setup() {
        const route = useRoute();
        const patInfo = JSON.parse(decodeURIComponent(route.query.data))
        return { patInfo }
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
    height: 55vh;
    border: 0;
    margin-left: 15vw;
    margin-top: 5vh;
    border-radius: 5px;
    padding-top: 10vh;
    box-shadow: 0 0 5px 1px gray;
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
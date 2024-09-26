<template>
    <div class="adminDrugInfoShow">
        <h3>药品信息详情</h3><button class="returnBtn" @click="returnQuery">返回</button>
        <table>
            <tr>
                <td style="width: 5vw;">序号</td>
                <td>{{drugInfo.id}}</td>
                <td style="width: 5vw;">名称</td>
                <td colspan="3" style="text-align: center;">{{drugInfo.name}}</td>
            </tr>
            <tr>
                <td >剂型</td>
                <td>{{drugInfo.type}}</td>
                <td>适用</td>
                <td>{{drugInfo.Indications}}</td>
                <td style="width: 5vw;">禁忌</td>
                <td>{{drugInfo.taboo}}</td>
            </tr>
            <tr>
                <td>成分</td>
                <td>{{drugInfo.comcon}}</td>
                <td>用法</td>
                <td>{{drugInfo.use}}</td>
                <td>规格</td>
                <td>{{drugInfo.standards}}</td>
            </tr>
            <tr>
                <td>生产厂商</td>
                <td>{{drugInfo.producter}}</td>
                <td>产地</td>
                <td>{{drugInfo.proplace}}</td>
                <td>价格</td>
                <td>{{drugInfo.price}}</td>
            </tr>
        </table>
    </div>
</template>
<script>
import { useRoute, useRouter } from 'vue-router';
import { ref } from 'vue';
export default {
    setup() {
        const router = useRouter();
        const route = useRoute();
        const returnQuery = () => {
            router.push("/admin/adminOperDrug");
        }
        const drugInfo = JSON.parse(decodeURIComponent(route.query.data))
        return {
            returnQuery,
            drugInfo
        }
    }
}
</script>

<style scoped>
.adminDrugInfoShow {
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
table {
    border-collapse: collapse;
    box-shadow: 0 0 5px 5px rgb(230, 230, 230);
    width: 60vw;
    height: 40vh;
    margin-left: 10vw;
    margin-top: 15vh;
    font-size: 1.1vw;
  }

  td, th {
    border: 2px solid rgb(188, 188, 188);
    padding: 5px;
  }
</style>
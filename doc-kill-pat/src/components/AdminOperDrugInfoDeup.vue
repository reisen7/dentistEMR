<template>
    <div class="adminDrugInfoShow">
        <h3>药品信息操作</h3><button class="returnBtn" @click="returnQuery">返回</button>
        <table>
            <tr>
                <td style="width: 5vw;">序号</td>
                <td>{{ drugInfo.id }}</td>
                <td style="width: 5vw;">名称</td>
                <td colspan="3" style="text-align: center;"><input type="text" v-model="drugInfo.name"></td>
            </tr>
            <tr>
                <td>剂型</td>
                <td><input type="text" v-model="drugInfo.type"></td>
                <td>适用</td>
                <td><textarea cols="26" rows="3" v-model="drugInfo.indications"></textarea></td>
                <td style="width: 5vw;">禁忌</td>
                <td><textarea cols="26" rows="3" v-model="drugInfo.taboo"></textarea></td>
            </tr>
            <tr>
                <td>成分</td>
                <td><textarea cols="26" rows="3" v-model="drugInfo.comcon"></textarea></td>
                <td>用法</td>
                <td><textarea cols="26" rows="3" v-model="drugInfo.use"></textarea></td>
                <td>规格</td>
                <td><textarea cols="26" rows="3" v-model="drugInfo.standards"></textarea></td>
            </tr>
            <tr>
                <td>生产厂商</td>
                <td><input type="text" v-model="drugInfo.producter"></td>
                <td>产地</td>
                <td><input type="text" v-model="drugInfo.proplace"></td>
                <td>价格</td>
                <td><input type="text" v-model="drugInfo.price"></td>
            </tr>
        </table>
        <el-button type="primary" class="updateBtn" @click="updateDrug">修改药品信息</el-button>
        <el-button  class="deleteBtn" @click="deleteDrug">删除药品信息</el-button>
    </div>
</template>
<script>
import { useRoute, useRouter } from 'vue-router';
import { ref } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus'
import axios from 'axios';

export default {
    setup() {
        const router = useRouter();
        const route = useRoute();
        const returnQuery = () => {
            router.push("/admin/adminOperDrug");
        }
        const drug = ref({
            id: '',
            name: '',
            type: '',
            indications: '',
            taboo: '',
            comcon: '',
            use: '',
            standards: '',
            producter: '',
            proplace: '',
            price: ''
        })

        const drugInfo = JSON.parse(decodeURIComponent(route.query.data))
        const updateDrug = () => {
            console.log(drugInfo.id);
            console.log(drugInfo.name);
            console.log(drugInfo.type);
            console.log(drugInfo.indications);
            console.log(drugInfo.taboo);
            console.log(drugInfo.comcon);
            console.log(drugInfo.use);
            console.log(drugInfo.standards);
            console.log(drugInfo.producter);
            console.log(drugInfo.proplace);
            console.log(drugInfo.price);

            drug.value.id = drugInfo.id
            drug.value.name = drugInfo.name
            drug.value.type = drugInfo.type
            drug.value.indications = drugInfo.indications
            drug.value.taboo = drugInfo.taboo
            drug.value.comcon = drugInfo.comcon
            drug.value.use = drugInfo.use
            drug.value.standards = drugInfo.standards
            drug.value.producter = drugInfo.producter
            drug.value.proplace = drugInfo.proplace
            drug.value.price = drugInfo.price

            console.log(drug.value)

            axios({
                method: "post",
                url: "http://localhost:8909/drug/update",
                data: drug.value,
                headers: {
                    processData: false,
                    contentType: false,
                }
            }).then(
                router.push({ path: "/admin/adminOperDrug" })
            ).catch(error => {
                if (error.data.message == "DBERROR") {
                    ElMessage({
                        message: '修改失败！',
                        type: 'error',
                    })
                }
            })

            ElMessage({
                message: '修改成功！',
                type: 'success',
            })


        }
        const deleteDrug = () => {
            console.log(drugInfo.id);
            console.log(drugInfo.name);
            console.log(drugInfo.type);
            console.log(drugInfo.Indications);
            console.log(drugInfo.taboo);
            console.log(drugInfo.comcon);
            console.log(drugInfo.use);
            console.log(drugInfo.standards);
            console.log(drugInfo.producter);
            console.log(drugInfo.proplace);
            console.log(drugInfo.price);



            ElMessageBox.confirm('是否确认删除药品?', '提示!', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
            }).then(async () => {
                try {
                    await axios.post("http://localhost:8909/drug/delete", null, {
                        params: { id: drugInfo.id },
                        headers: {
                            processData: false,
                            contentType: false,
                        }
                    });
                    ElMessage({
                        type: 'success',
                        message: '删除成功!',
                    });

                    router.push("/admin/adminOperDrug");
                } catch (error) {
                    ElMessage({
                        message: '删除失败!',
                        type: 'error',
                    });
                }
            }).catch(() => {
                ElMessage({
                    type: 'info',
                    message: '删除已取消',
                });
            });

        }
        return {
            returnQuery,
            drugInfo,
            updateDrug,
            deleteDrug
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

td,
th {
    border: 2px solid rgb(188, 188, 188);
    padding: 5px;
}

.updateBtn {
    margin-left: 20vw;
    margin-top: 5vh;
    width: 15vw;
    height: 5vh;
    border-radius: 10px;
}
.deleteBtn {
    margin-left: 10vw;
    margin-top: 5vh;
    width: 15vw;
    height: 5vh;
    border-radius: 10px;
}
input:focus , textarea:focus{
    outline: none;
}
input{
    width: 13vw;
    height: 5vh;
    padding-left: 10px;
    border: 0;
    font-size: 0.9vw;
}
textarea{
    border: 0;
    padding-left: 10px;
    font-size: 0.9vw;
}
</style>
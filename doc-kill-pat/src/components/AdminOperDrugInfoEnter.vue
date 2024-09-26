<template>
    <div class="adminDrugInfoEnter">
        <h3>药品信息添加</h3><button class="returnBtn" @click="returnAndQuery">返回</button>
        <table>
            <tr>
                <td>名称</td>
                <td><input type="text" v-model="name"></td>
                <td>药品类型</td>
                <td><input type="text" v-model="type"></td>
            </tr>
            <tr>
                <td>规格</td>
                <td><input type="text" v-model="standards"></td>
                <td>生产厂家</td>
                <td><input type="text" v-model="producter"></td>
            </tr>
            <tr>
                <td>适用症</td>
                <td><input type="text" v-model="indications"></td>
                <td>用法用量</td>
                <td><input type="text" v-model="use"></td>
            </tr>
            <tr>
                <td>禁忌</td>
                <td><input type="text" v-model="taboo"></td>
                <td>成分含量</td>
                <td><input type="text" v-model="comcon"></td>
            </tr>
            <tr>
                <td>产地</td>
                <td><input type="text" v-model="proplace"></td>
                <td>价格</td>
                <td><input type="text" v-model="price"></td>
            </tr>
        </table>
        <el-button type="primary" class="addBtn" @click="addDrug">添加药品</el-button>
    </div>
</template>
<script>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus'
import axios from 'axios';
export default {
    setup() {
        const router = useRouter();
        const returnAndQuery = () => {
            router.push("/admin/adminOperDrug");
        }
        const name = ref('');
        const type = ref("");
        const standards = ref("");
        const producter = ref("");
        const indications = ref("");
        const use = ref("");
        const taboo = ref("");
        const comcon = ref("");
        const proplace = ref("");
        const price = ref("");

        const drug = ref({
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

        const addDrug = () => {
            console.log(name.value);
            console.log(type.value);
            console.log(standards.value);
            console.log(producter.value);
            console.log(indications.value);
            console.log(use.value);
            console.log(taboo.value);
            console.log(comcon.value);
            console.log(proplace.value);
            console.log(price.value);

            drug.value.name = name.value
            drug.value.type = type.value
            drug.value.indications = indications.value
            drug.value.taboo = taboo.value
            drug.value.comcon = comcon.value
            drug.value.use = use.value
            drug.value.standards = standards.value
            drug.value.producter = producter.value
            drug.value.proplace = proplace.value
            drug.value.price = price.value

            axios.post();

            axios({
                method: "post",
                url: "http://localhost:8909/drug/insert",
                data: drug.value,
                headers: {
                    processData: false,
                    contentType: false,
                }
            }).then((res)=>{
                setTimeout(() => {
                    console.log('暂停1秒钟后执行...');
                    // 在这里编写你希望在暂停后执行的代码  
                }, 1000)
                router.push({ path: "/admin/adminOperDrug" })
            }   
            ).catch(error => {
                if (error.data.message == "DBERROR") {
                    ElMessage({
                        message: '添加失败！',
                        type: 'error',
                    })
                }
            })

            ElMessage({
                message: '药品添加成功！',
                type: 'success',
            })
        }

        return {
            name,
            type,
            standards,
            producter,
            indications,
            use,
            taboo,
            comcon,
            proplace,
            price,
            returnAndQuery,
            addDrug,
        }
    }
}
</script>

<style scoped>
.adminDrugInfoEnter {
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
    width: 55vw;
    height: 50vh;
    margin-left: 13vw;
    margin-top: 8vh;
    font-size: 1.3vw;
    font-family: "楷体";
    font-weight: 500;
}

input:focus {
    outline: none;
}

input {
    width: 20vw;
    height: 5vh;
    border: 0;
    border-radius: 5px;
    background-color: rgb(215, 219, 218);
    padding-left: 1vw;
    font-size: 1vw;
}

.addBtn {
    margin-left: 34vw;
    margin-top: 5vh;
    width: 15vw;
    height: 5vh;
    border-radius: 10px;
}
</style>
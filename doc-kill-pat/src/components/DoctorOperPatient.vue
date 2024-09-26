<template>
    <div class="adminOperPatient">
        <h3>病人管理</h3>
        <el-input class="searchInput" v-model="searchInput" maxlength="10" placeholder="请输入想查找的病人的名字/类别" show-word-limit
            type="text" />
        <el-table :data="currentPageData" class="tableShow">
            <el-table-column fixed prop="id" label="Id" width="150" />
            <el-table-column prop="name" label="病人姓名" width="120" />
            <el-table-column prop="classify" label="类别" width="120" />
            <el-table-column prop="age" label="年龄" width="120" />
            <el-table-column prop="address" label="地址" width="600" />
            <el-table-column fixed="right" label="操作" width="150">
                <template #default="{ row }">
                    <el-button link type="primary" size="small" @click="enterDetail(row)">病历详情</el-button>
                    <!-- <el-button link type="primary" size="small" @click="enterEdit(row)">病历编辑</el-button> -->
                </template>
            </el-table-column>
        </el-table>
        <el-pagination :page-size="10" :pager-count="10" @current-change="handleCurrentChange" layout="prev, pager, next"
            :total="1000" class="pagination" />
    </div>
</template>
<script>
import { ref, computed, onMounted } from 'vue'
import { useRouter } from "vue-router"
import { ElMessage, ElMessageBox } from 'element-plus'
import axios from 'axios';
export default {
    setup() {
        const router = useRouter();
        const pat = ref({
            id: '',
            name: '',
            sex: '',
            age: '',
            anamnesis: '',
            address: '',
            account: '',
            password: '',
            classify: ''
        })

        const enterDetail = (row) => {

            pat.value.id = row.id
            const patientId = JSON.parse(decodeURIComponent(row.id))
            router.push({ path: '/doctor/case', query: { data: encodeURIComponent(JSON.stringify(patientId)) } })


        }
        // const enterEdit = (row) => {

            
        //     console.log(row.id);
        //     const data = {
        //         id: 1,
        //         name: '王某',
        //         age: 30,
        //         sex: '男',
        //         profile: "a.jpg",
        //         account: 12314,
        //         password: "GGGGG",
        //         address: "重庆市高新区",
        //         anamnesis: "先天性心脏病",
        //         classify: "VIP"
        //     }
        //     router.push({
        //         path: '/admin/adminOperPatDeUp', query: {
        //             data: encodeURIComponent(JSON.stringify(data))
        //         }
        //     })
        // }


        const enterEdit = async (row) => {


            pat.value.id = row.id

            try {
                const response = await axios.post("http://localhost:8909/drug/getOne?id="+row.id, null, {
                    params: { id: row.id },
                    headers: {
                        processData: false,
                        contentType: false,
                    }
                });
                router.push({
                    path: '/admin/adminOperDrugDeUp', query: {
                        data: encodeURIComponent(JSON.stringify(response.data.data))
                    }
                });
            } catch (error) {
                if (error.response && error.response.data.message === "DBERROR") {
                    ElMessage({
                        message: '编辑失败！',
                        type: 'error',
                    });
                }
            }

        };
        


        const enterAdd = () => {
            router.push("/admin/adminOperDocEnter");
        }
        const searchInput = ref('')
        const currentPage = ref(1);
        const pageSize = ref(10);
        const handleCurrentChange = (val) => {
            currentPage.value = val;
        };
        const tableData = ref([])

        const fetchTableData = async () => {
            try {
                const response = await axios.post("http://localhost:8909/Patient/PatientGet");
                tableData.value = response.data.data;
                console.log(tableData.value); // Corrected spelling error
            } catch (error) {
                ElMessage({
                    message: 'Failed to fetch data!',
                    type: 'error',
                });
            }
        };

        onMounted(fetchTableData);



        const filteredTableData = computed(() => {
            const keyword = searchInput.value.trim().toLowerCase()
            if (!keyword) {
                return tableData.value
            } else {
                return tableData.value.filter(item => {
                    return Object.values(item).some(val =>
                        String(val).toLowerCase().includes(keyword)
                    )
                })
            }
        })
        const currentPageData = computed(() => {
            const start = (currentPage.value - 1) * pageSize.value;
            const end = currentPage.value * pageSize.value;
            return filteredTableData.value.slice(start, end);
        });
        return {
            searchInput,
            filteredTableData,
            currentPage,
            pageSize,
            handleCurrentChange,
            enterAdd,
            enterDetail,
            enterEdit,
            tableData,
            currentPageData,
        }
    }
}

</script>

<style scoped>
.adminOperPatient {
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

.searchInput {
    margin-left: 10vw;
    width: 60vw;
    height: 6vh;
}



.tableShow {
    margin-top: 3vh;
    width: 60vw;
    margin-left: 10vw;
}

.pagination {
    margin-top: 1vh;
    margin-left: 27vw;
    height: 5vh;
}
</style>
<template>
    <div class="adminOperDrug">
        <h3>药品管理</h3>
        <el-input class="searchInput" v-model="searchInput" maxlength="10" placeholder="请输入想查找的药品的名字/适用" show-word-limit
            type="text" />
        <el-button class="AddDrug" type="default" @click="enterAdd" circle>+</el-button>
        <el-table :data="currentPageData" class="tableShow">
            <el-table-column fixed prop="id" label="Id" width="150" />
            <el-table-column prop="name" label="药名" width="120" />
            <el-table-column prop="type" label="剂型" width="120" />
            <el-table-column prop="indications" label="适用" width="200" />
            <el-table-column prop="taboo" label="禁忌" width="600" />
            <el-table-column fixed="right" label="操作" width="120">
                <template #default="{ row }">
                    <el-button link type="primary" size="small" @click="enterDetail(row)">详情</el-button>
                    <el-button link type="primary" size="small" @click="enterEdit(row)">编辑</el-button>
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
        const enterDetail = async (row) => {
            console.log(row.id);
            const data = {
                id: 1,
                name: '阿莫西林',
                Indications: '适用于感冒发烧，风寒湿热',
                taboo: '不能和为维C同时食用',
                comcon: "羊开口、金沙藤、金樱根、玉米淀粉",
                use: "饭前,成人3~5片,未成年2片",
                price: 213,
                type: "片剂",
                standards: "54片",
                producter: "哈药制业",
                proplace: "莆田"
            }

            const response = await axios.post("http://localhost:8909/drug/getOne?id="+row.id, null, {
                    params: { id: row.id },
                    headers: {
                        processData: false,
                        contentType: false,
                    }
                });

            router.push({
                path: '/admin/adminOperDrugInfoShow', query: {
                    data: encodeURIComponent(JSON.stringify(response.data.data))
                }
            })
        }
        const enterEdit = async (row) => {
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

        }
        const enterAdd = () => {
            router.push("/admin/adminOperDrugEnter");
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
                const response = await axios.post("http://localhost:8909/drug/get");
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
.adminOperDrug {
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

.AddDrug {
    position: absolute;
    width: 1.5vw;
    height: 3vh;
    font-size: 1.3vw;
    color: gray;
    margin-top: 10vh;
    font-weight: 500;
    margin-left: -4vw;
    z-index: 100;
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
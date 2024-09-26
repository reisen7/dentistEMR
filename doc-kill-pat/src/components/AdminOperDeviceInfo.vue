<template>
    <div class="adminOperDevice">
        <h3>设备管理</h3>
        <el-input class="searchInput" v-model="searchInput" maxlength="10" placeholder="请输入想查找的设备的名称" show-word-limit
            type="text" />
        <el-button class="AddDevice" type="default" @click="enterAdd" circle>+</el-button>
        <el-table :data="currentPageData" class="tableShow">
            <el-table-column fixed prop="id" label="Id" width="100" />
            <el-table-column prop="name" label="设备名称" width="120" />
            <el-table-column prop="chargmethod" label="收费方式" width="120" />
            <el-table-column prop="standard" label="收费标准" width="120" />
            <el-table-column prop="illustrate" label="费用说明" width="600" />
            <el-table-column fixed="right" label="操作" width="120">
                <template #default="{ row }">
                    <el-button link type="danger" size="small" @click="deleteRow(row)">删除</el-button>
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
        const tableData = ref([]);
        const searchInput = ref('');
        const currentPage = ref(1);
        const pageSize = ref(10);

        const fetchTableData = async () => {
            try {
                const response = await axios.post("http://localhost:8909/dev/get");
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
            const keyword = searchInput.value.trim().toLowerCase();
            if (!keyword) {
                return tableData.value;
            } else {
                return tableData.value.filter(item => 
                    Object.values(item).some(val => 
                        String(val).toLowerCase().includes(keyword)
                    )
                );
            }
        });

        const currentPageData = computed(() => {
            const start = (currentPage.value - 1) * pageSize.value;
            const end = currentPage.value * pageSize.value;
            return filteredTableData.value.slice(start, end);
        });

        const deleteRow = async (row) => {
            ElMessageBox.confirm('是否确认删除设备?', '提示!', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
            }).then(async () => {
                try {
                    await axios.post("http://localhost:8909/dev/delete", null, {
                        params: { id: row.id },
                        headers: {
                            processData: false,
                            contentType: false,
                        }
                    });
                    await fetchTableData();
                    ElMessage({
                        type: 'success',
                        message: '删除成功!',
                    });
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
        };

        const enterEdit = async (row) => {
            try {
                const response = await axios.post("http://localhost:8909/dev/getOne?id="+row.id, null, {
                    params: { id: row.id },
                    headers: {
                        processData: false,
                        contentType: false,
                    }
                });
                router.push({
                    path: '/admin/adminOperDeviceEdit', query: {
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
            router.push("/admin/adminOperDeviceEnter");
        };

        const handleCurrentChange = (val) => {
            currentPage.value = val;
        };

        return {
            searchInput,
            filteredTableData,
            currentPage,
            pageSize,
            handleCurrentChange,
            enterAdd,
            deleteRow,
            enterEdit,
            tableData,
            currentPageData,
        }
    }
}
</script>

<style scoped>
.adminOperDevice {
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

.AddDevice {
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
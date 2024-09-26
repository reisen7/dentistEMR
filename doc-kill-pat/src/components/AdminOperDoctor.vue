<template>
    <div class="adminOperDoctor">
        <h3>医生管理</h3>
        <el-input class="searchInput" v-model="searchInput" maxlength="10" placeholder="请输入想查找的医生的名字/疾病" show-word-limit
            type="text" />
        <el-button class="AddDoc" type="default" @click="enterAdd" circle>+</el-button>
        <el-table :data="currentPageData" class="tableShow">
            <el-table-column fixed prop="id" label="Id" width="150" />
            <el-table-column prop="name" label="医生姓名" width="120" />
            <el-table-column prop="department" label="主治疾病" width="120" />
            <el-table-column prop="title" label="职称" width="120" />
            <el-table-column prop="sex" label="性别" width="600" />
            <el-table-column fixed="right" label="操作" width="120">
                <template #default="{ row }">
                    <el-button link type="primary" size="small" @click="enterDetail(row)">详情</el-button>
                    <el-button link type="primary" size="small" @click="enterEdit(row)">编辑</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination :page-size="10" :pager-count="10" @current-change="handleCurrentChange" layout="prev, pager, next"
            :total="totalrow" class="pagination" />
    </div>
</template>
<script>
import axios from 'axios';
import { ref, computed } from 'vue'
import { useRouter, useRoute } from "vue-router"
export default {
    setup() {
        const router = useRouter();
        const route = useRoute();
        const enterDetail = (row) => {
            console.log(row);
            const data = row;
            router.push({
                path: '/admin/adminOperDocInfoShow', query: {
                    data: encodeURIComponent(JSON.stringify(data))
                }
            })
        }
        const enterEdit = (row) => {
            axios({
                method: "post",
                url: "http://localhost:8909/Admin/gotoEditDoctor",
            }).then(res => {
                if (res.data.message === "success") {
                    console.log(res.data.data);
                    const data = row;
                    router.push({
                        path: '/admin/adminOperDocDeUp', query: {
                            data: encodeURIComponent(JSON.stringify(data)),
                            select: JSON.stringify(res.data.data)
                        }
                    })
                }

            }).catch(error => {
                ElMessage({
                    message: 'G!',
                    type: 'error',
                })
            })
        }
        const enterAdd = () => {
            axios({
                method: "post",
                url: "http://localhost:8909/Admin/gotoEditDoctor",
            }).then(res => {
                if (res.data.message === "success") {
                    console.log(res.data.data);
                    router.push({
                        path: '/admin/adminOperDocEnter', query: { select: encodeURIComponent(JSON.stringify(res.data.data)) }
                    })
                }

            }).catch(error => {
                ElMessage({
                    message: 'G!',
                    type: 'error',
                })
            })
        }
        
        const searchInput = ref('')
        const currentPage = ref(1);
        const pageSize = ref(10);
        const handleCurrentChange = (val) => {
            currentPage.value = val;
        };
        console.log(JSON.parse(decodeURIComponent(route.query.data)));
        const tableData = ref(JSON.parse(decodeURIComponent(route.query.data)))
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
        const totalrow = ref(tableData.value.length);
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
            totalrow,
        }
    }
}

</script>

<style scoped>
.adminOperDoctor {
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

.AddDoc {
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
    /* margin-top: 1vh;
    margin-left: auto;
    height: 5vh; */
    position: absolute;
    top: 95%;
    left: 50%;
    transform: translateX(-50%) translateY(-50%);
}
</style>
<template>
    <div class="inter-content">
        <p class="cur-position">医 嘱 建 议</p>
        <div class="form-content">
            <el-input v-model="text" maxlength="10" placeholder="输入病名或医师姓名来搜索相关医嘱" show-word-limit type="text"
                style="width:90%;margin-top: 2vh;" size="large">
                <template #prefix>
                    <el-icon class="el-input__icon">
                        <img src="../assets/imgs/search.png" style="width: 4vh;">
                    </el-icon>
                </template>
            </el-input>
            <el-button type="primary" size="large" style="width:10%;margin-top: 2vh;" @click="clickSearch">搜索</el-button>
            <div style="margin: 20px 0"></div>
            <el-table :data="currentTableData" style="width: 100%;" size="large">
                <el-table-column prop="id" label="编号" align="center" />
                <el-table-column prop="name" label="病名" align="center" />
                <el-table-column prop="doctor" label="医师" align="center" />
                <el-table-column prop="content" label="内容" align="center" show-overflow-tooltip />
                <el-table-column fixed="right" label="操作" align="center">
                    <template #default="{ row }">
                        <el-button link type="primary" size="large" @click="detailClick(row)">详情</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="table-pagi-div">
                <el-pagination background layout="prev, pager, next" :pager-count="pagerCount" :page-size="pageSize"
                    :total="pageTotal" @current-change="handlePageChange" class="table-pagi" />
            </div>
        </div>
    </div>
</template>

<script>
import { reactive, ref, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
import axios from "axios"
export default {

    setup() {
        const text = ref('')
        const pagerCount = ref(15)  //页码按钮的数量，当总页数超过该值时会折叠
        const pageSize = ref(7)  //每页显示条目个数
        const pageTotal = ref(500)  //总条目数
        const currentPage = ref(1) //当前页
        const tableData = ref("")


        //接收路由数据
        const route = useRoute()
        const patientId = JSON.parse(decodeURIComponent(route.query.data))
        const url = "http://localhost:8909/Patient/PatientTellSelect"
        const tellInfo = {
            name: null,
        }
        axios({
            method: "post",
            url: url,
            data: tellInfo,
        }).then(res => {
            if (res.data.message === "Success") {
                tableData.value = res.data.data
                pageTotal.value = tableData.value.length
            } else {
                ElMessage({
                    message: '查询失败!',
                    type: 'warning',
                })
            }
        }).catch(error => {
            ElMessage({
                message: '数据库错误！',
                type: 'error',
            })
        })

        const clickSearch = () => {
            const tellInfo1 = {
                name: text.value,
            }
            axios({
                method: "post",
                url: url,
                data: tellInfo1,
            }).then(res => {
                if (res.data.message === "Success") {
                    tableData.value = res.data.data
                } else {
                    ElMessage({
                        message: '查询失败!',
                        type: 'warning',
                    })
                }
            }).catch(error => {
                ElMessage({
                    message: '数据库错误！',
                    type: 'error',
                })
            })
        }

        // pageTotal.value = tableData.value.length
        const handlePageChange = (val) => {
            currentPage.value = val
            console.log('当前页：' + currentPage.value)
        }

        const currentTableData = computed(() => {  //当前页的表格数据
            const start = (currentPage.value - 1) * pageSize.value
            const end = start + pageSize.value
            if(end<=tableData.value.length){
                return tableData.value.slice(start, end)
            }else{
                return tableData.value.slice(start, tableData.value.length)
            }
        });

        //路由跳转
        const router = useRouter()
        const detailClick = (row) => {
            router.push({ path: '/pat/advicedetail', query: {
                data: encodeURIComponent(JSON.stringify(patientId)),
                adviceid: encodeURIComponent(JSON.stringify(row.id))
            } })
        }

        return { text, currentTableData, clickSearch, detailClick, handlePageChange, pageTotal, pageSize, pagerCount, }
    }
}

</script>

<style scoped>
.inter-content {
    float: left;
    width: 85%;
    height: 87vh;
}

.cur-position {
    margin-left: 2%;
    margin-top: 3vh;
    font-size: 1.3vw;
    font-weight: 600;
}

.form-content {
    position: relative;
    width: 70%;
    margin-left: 15%;
}

.table-pagi-div {
    position: absolute;
    display: flex;
    justify-content: center;
    /*水平居中*/
    align-items: center;
    /*垂直居中*/
    top: 70vh;
    width: 100%;
}
</style>
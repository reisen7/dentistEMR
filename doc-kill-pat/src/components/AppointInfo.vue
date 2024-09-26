<template>
    <div class="inter-content">
        <p class="cur-position">挂 号 预 约</p>
        <div class="form-content">
            <el-button type="primary" style="margin-left: 85%;margin-bottom: 2vh;">新 建 预 约</el-button>
            <el-table :data="currentTableData" style="width: 100%;" size="large">
                <el-table-column prop="name" label="姓名" align="center" />
                <el-table-column prop="department" label="疾病" align="center" />
                <el-table-column prop="doctor" label="医生" align="center" />
                <el-table-column prop="time" label="预约时间" align="center" show-overflow-tooltip />
                <el-table-column prop="reason" label="预约原因" align="center" show-overflow-tooltip />
                <el-table-column prop="status" label="状态" align="center" />
                <el-table-column fixed="right" label="操作" align="center">
                    <template #default>
                        <el-button type="warning" plain size="small" @click="detailClick">修改</el-button>
                        <el-button type="info" plain size="small" @click="detailClick">取消</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="table-pagi-div">
                <el-pagination background layout="prev, pager, next" :pager-count=pagerCount :page-size="pageSize"
                    :total="pageTotal" @current-change="handlePageChange" class="table-pagi" />
            </div>
        </div>
    </div>
</template>

<script>
import { ref, reactive, computed } from "vue"
import { useRouter } from 'vue-router'
export default {

    setup() {
        const text = ref('')
        const pagerCount = ref(15)  //页码按钮的数量，当总页数超过该值时会折叠
        const pageSize = ref(7)  //每页显示条目个数
        const pageTotal = ref(1000)  //总条目数
        const currentPage = ref(1) //当前页
        const tableData = ref(
            [{
                name: '张三',
                department: '内科',
                doctor: '张医生',
                time: '2020/2/12 10:00',
                reason: '胃痛胃痛胃痛胃痛胃痛胃痛',
                status: '等候就诊',
            },
            {
                name: '张三',
                department: '内科',
                doctor: '张医生',
                time: '2020/2/12 10:00',
                reason: '胃痛胃痛胃痛胃痛胃痛胃痛',
                status: '等候就诊',
            },
            {
                name: '张三',
                department: '内科',
                doctor: '张医生',
                time: '2020/2/12 10:00',
                reason: '胃痛胃痛胃痛胃痛胃痛胃痛',
                status: '等候就诊',
            },
            {
                name: '张三',
                department: '内科',
                doctor: '张医生',
                time: '2020/2/12 10:00',
                reason: '胃痛胃痛胃痛胃痛胃痛胃痛',
                status: '等候就诊',
            },
            {
                name: '张三',
                department: '内科',
                doctor: '张医生',
                time: '2020/2/12 10:00',
                reason: '胃痛胃痛胃痛胃痛胃痛胃痛',
                status: '等候就诊',
            },
            {
                name: '张三',
                department: '内科',
                doctor: '张医生',
                time: '2020/2/12 10:00',
                reason: '胃痛胃痛胃痛胃痛胃痛胃痛',
                status: '等候就诊',
            },
            {
                name: '张三',
                department: '内科',
                doctor: '张医生',
                time: '2020/2/12 10:00',
                reason: '胃痛胃痛胃痛胃痛胃痛胃痛',
                status: '等候就诊',
            },
            {
                name: '张三',
                department: '内科',
                doctor: '张医生',
                time: '2020/2/12 10:00',
                reason: '胃痛胃痛胃痛胃痛胃痛胃痛',
                status: '等候就诊',
            },
            {
                name: '张三',
                department: '内科',
                doctor: '张医生',
                time: '2020/2/12 10:00',
                reason: '胃痛胃痛胃痛胃痛胃痛胃痛',
                status: '等候就诊',
            },
            {
                name: '张三',
                department: '内科',
                doctor: '张医生',
                time: '2020/2/12 10:00',
                reason: '胃痛胃痛胃痛胃痛胃痛胃痛',
                status: '等候就诊',
            },
            {
                name: '张三',
                department: '内科',
                doctor: '张医生',
                time: '2020/2/12 10:00',
                reason: '胃痛胃痛胃痛胃痛胃痛胃痛',
                status: '等候就诊',
            },
            {
                name: '张三',
                department: '内科',
                doctor: '张医生',
                time: '2020/2/12 10:00',
                reason: '胃痛胃痛胃痛胃痛胃痛胃痛',
                status: '等候就诊',
            },
            {
                name: '张三',
                department: '内科',
                doctor: '张医生',
                time: '2020/2/12 10:00',
                reason: '胃痛胃痛胃痛胃痛胃痛胃痛',
                status: '等候就诊',
            },
            {
                name: '张三',
                department: '内科',
                doctor: '张医生',
                time: '2020/2/12 10:00',
                reason: '胃痛胃痛胃痛胃痛胃痛胃痛',
                status: '等候就诊',
            },
            {
                name: '张三',
                department: '内科',
                doctor: '张医生',
                time: '2020/2/12 10:00',
                reason: '胃痛胃痛胃痛胃痛胃痛胃痛',
                status: '等候就诊',
            },
            {
                name: '张三',
                department: '内科',
                doctor: '张医生',
                time: '2020/2/12 10:00',
                reason: '胃痛胃痛胃痛胃痛胃痛胃痛',
                status: '等候就诊',
            },
            {
                name: '张三',
                department: '内科',
                doctor: '张医生',
                time: '2020/2/12 10:00',
                reason: '胃痛胃痛胃痛胃痛胃痛胃痛',
                status: '等候就诊',
            }]
        )
        pageTotal.value = tableData.value.length

        const handlePageChange = (val) => {
            currentPage.value = val
            console.log('当前页：' + currentPage.value)
        }

        const currentTableData = computed(() => {  //当前页的表格数据
            const start = (currentPage.value - 1) * pageSize.value
            const end = start + pageSize.value
            return tableData.value.slice(start, end)
        });

        //路由跳转
        const router = useRouter()
        const detailClick = () => {
            // router.push({ path: '/pat/advicedetail', query: { data: encodeURIComponent(JSON.stringify(currentTableData.value)) } })
        }

        return { text, pagerCount, pageSize, pageTotal, currentTableData, handlePageChange, detailClick }
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
    width: 90%;
    margin-left: 5%;
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
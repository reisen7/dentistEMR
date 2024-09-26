<template>
    <div class="inter-content">
        <p class="cur-position">医 嘱 建 议</p>
        <div class="form-content">
            <el-descriptions :column="3" size="large" border>
                <el-descriptions-item label="病名" align="center" label-align="center">
                    {{ adviceData.name }}
                </el-descriptions-item>
                <el-descriptions-item label="建议医师" align="center" label-align="center">
                    {{ adviceData.doctor }}
                </el-descriptions-item>
                <el-descriptions-item label="疾病" align="center" label-align="center">
                    {{ adviceData.department }}
                </el-descriptions-item>
            </el-descriptions>
            <el-descriptions :column="1" size="large" border>
                <el-descriptions-item label="医嘱内容" align="center" label-align="center">
                    {{ adviceData.content }}
                </el-descriptions-item>
                <el-descriptions-item label="治疗建议" align="center" label-align="center">
                    {{ adviceData.advice }}
                </el-descriptions-item>
            </el-descriptions>
            <el-descriptions direction="vertical" :column="1" size="large" border>
                <el-descriptions-item label="药品信息" align="center" label-align="center">
                    <el-table :data="tableData" stripe style="width: 100%" height="40vh">
                        <el-table-column prop="name" label="药品名称" align="center" />
                        <el-table-column prop="type" label="药剂类型" align="center" />
                        <el-table-column prop="standards" label="规格" align="center" show-overflow-tooltip/>
                        <el-table-column prop="usefor" label="用法用量" align="center" show-overflow-tooltip/>
                        <el-table-column prop="price" label="价格" align="center" />
                    </el-table>
                </el-descriptions-item>
            </el-descriptions>
        </div>
    </div>
</template>

<script>
import { reactive, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
import axios from "axios"

export default {
    setup() {
        const adviceData = reactive({
            name: '',
            doctor: '',
            department: '',
            content: '',
            advice: ''
        })
        const tableData = ref()

        const router = useRouter()
        const route = useRoute()
        const patientId = JSON.parse(decodeURIComponent(route.query.data))
        const adviceId = JSON.parse(decodeURIComponent(route.query.adviceid))

        const url = "http://localhost:8909/Patient/PatientTellDetail"
        const tellInfo = {
            id: adviceId,
        }
        axios({
            method: "post",
            url: url,
            data: tellInfo,
        }).then(res => {
            if (res.data.message === "Success") {
                adviceData.name = res.data.data[0].name
                adviceData.doctor = res.data.data[0].doctor
                adviceData.department = res.data.data[0].department
                adviceData.content = res.data.data[0].content
                adviceData.advice = res.data.data[0].advice
                tableData.value = res.data.data[1]
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

        return { adviceData, tableData }

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
    width: 70%;
    margin-left: 15%;
}
</style>
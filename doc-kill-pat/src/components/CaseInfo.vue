<template>
    <div class="inter-content">
        <p class="cur-position">个 人 病 历</p>
        <div class="descrip-content">
            <el-descriptions :column="3" size="large" border>
                <el-descriptions-item label="姓名" align="center">
                    {{ caseData.name }}
                </el-descriptions-item>
                <el-descriptions-item label="性别" align="center">
                    {{ caseData.sex }}
                </el-descriptions-item>
                <el-descriptions-item label="年龄" align="center">
                    {{ caseData.age }}
                </el-descriptions-item>
                <el-descriptions-item label="既往病史" align="center">
                    {{ caseData.anamnesis }}
                </el-descriptions-item>
            </el-descriptions>
            <el-descriptions direction="vertical" :column="1" size="large" border>
                <el-descriptions-item label="现病史" align="center">
                    <el-table :data="tableData" stripe style="width: 100%" height="50vh">
                        <el-table-column prop="date" label="检查日期" align="center" />
                        <el-table-column prop="diagnosticinfo" label="病名" align="center" />
                    </el-table>
                </el-descriptions-item>
            </el-descriptions>
        </div>
    </div>
</template>

<script>
import { reactive,ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
import axios from "axios"
export default {

    setup() {

        const caseData = reactive({
            name: '',
            sex: '',
            age: '',
            anamnesis: ''
        })

        const tableData = ref()

        //接收路由数据
        const route = useRoute()
        const patientId = JSON.parse(decodeURIComponent(route.query.data))
        const PatientSelectInfo = {
            id: patientId,
        }
        const url = "http://localhost:8909/Patient/PatientCaseSelect"
        axios({
            method: "post",
            url: url,
            data: PatientSelectInfo,
        }).then(res => {
            if (res.data.message === "Success") {
                console.log(res.data.data[1])
                caseData.name=res.data.data[0].name
                caseData.sex=res.data.data[0].sex
                caseData.age=res.data.data[0].age
                caseData.anamnesis=res.data.data[0].anamnesis
                tableData.value=res.data.data[1]
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

        return { caseData, tableData }
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

.descrip-content {
    width: 60%;
    margin-left: 20%;
}

.cell-item {
    display: flex;
    align-items: center;
}
</style>
<template>
    <div class="inter-content">
        <p class="cur-position">诊 断 信 息</p>
        <div class="form-content">
            <el-timeline>
                <el-timeline-item placement="top" v-for="(diagnoseData, index) in diagnoseData" :key="index"
                    :timestamp="diagnoseData.date">
                    <el-card style="cursor: pointer;" @click="cardClick(diagnoseData.id)">
                        <el-tag class="ml-2" :type="diagnoseData.typeTag">{{ diagnoseData.type }}</el-tag>
                        <h4>{{ diagnoseData.diagnosticinfo }}</h4>
                        <p>{{ diagnoseData.department }}</p>
                    </el-card>
                </el-timeline-item>
            </el-timeline>
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
        const diagnoseData = ref("")

        const router = useRouter()
        const route = useRoute()
        const patientId = JSON.parse(decodeURIComponent(route.query.data))

        const url = "http://localhost:8909/Patient/PatientDiagSelect"
        const diagnoseInfo = {
            patid: patientId,
        }
        axios({
            method: "post",
            url: url,
            data: diagnoseInfo,
        }).then(res => {
            if (res.data.message === "Success") {
                ElMessage({
                    message: '查询成功!',
                    type: 'success',
                })
                diagnoseData.value = res.data.data
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

        //路由跳转
        const cardClick = (id) => {
            router.push({ path: '/pat/diagnosedetail', query: {
                data: encodeURIComponent(JSON.stringify(patientId)),
                diagId:encodeURIComponent(JSON.stringify(id))
            } })
        }

        return {
            diagnoseData,
            cardClick
        }
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
    height: 76vh;
    margin-left: 15%;
    overflow-y: auto;
}
</style>
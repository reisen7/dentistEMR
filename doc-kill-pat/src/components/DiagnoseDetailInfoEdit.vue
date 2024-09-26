<template>
    <div class="inter-content">
        <p class="cur-position">诊 断 信 息</p>
        <div class="form-content">
            <el-descriptions :column="3" size="large" border>
                <el-descriptions-item label="姓名" align="center" label-align="center">
                    {{ diagData.name }}
                </el-descriptions-item>
                <el-descriptions-item label="性别" align="center" label-align="center">
                    {{ diagData.sex }}
                </el-descriptions-item>
                <el-descriptions-item label="年龄" align="center" label-align="center">
                    {{ diagData.age }}
                </el-descriptions-item>
                <el-descriptions-item label="就诊方式" align="center" label-align="center">
                    {{ diagData.diagType }}
                </el-descriptions-item>
                <el-descriptions-item label="疾病" align="center" label-align="center">
                    {{ diagData.department }}
                </el-descriptions-item>
                <el-descriptions-item label="总费用" align="center" label-align="center">
                    {{ diagData.cost }}
                </el-descriptions-item>
            </el-descriptions>
            <el-descriptions :column="1" size="large" border>
                <el-descriptions-item label="体格检查" label-align="center">
                    <el-input v-model="diagData.physicalcheck" class="is-borderless"/>
                </el-descriptions-item>
                <el-descriptions-item label="辅助检查" label-align="center">
                    <el-input v-model="diagData.auxiliarycheck" class="is-borderless"/>
                </el-descriptions-item>
                <el-descriptions-item label="诊断结果" label-align="center">
                    <el-input v-model="diagData.diagnosticinfo" class="is-borderless"/>
                </el-descriptions-item>
                <el-descriptions-item label="治疗信息" label-align="center">
                    <el-input v-model="diagData.therapyinfo" class="is-borderless"/>
                </el-descriptions-item>
                <el-descriptions-item label="主治医生" label-align="center">
                    {{ diagData.doctor }}
                </el-descriptions-item>
            </el-descriptions>
            <el-descriptions direction="vertical" :column="1" size="large" border>
                <el-descriptions-item label="药品信息" align="center" label-align="center">
                    <el-table :data="diagData.drugData" stripe style="width: 100%" height="40vh">
                        <el-table-column prop="name" label="药品名称" align="center" />
                        <el-table-column prop="type" label="药剂类型" align="center" />
                        <el-table-column prop="standards" label="规格" align="center" />
                        <el-table-column prop="usefor" label="用法用量" align="center" />
                        <el-table-column prop="price" label="价格" align="center" />
                    </el-table>
                </el-descriptions-item>
            </el-descriptions>
        </div>
        <el-button class="updateBtn" @click="updateOp">修改</el-button>
    </div>
    
</template>

<script>
import { ref, reactive, computed } from "vue"
import { useRoute, useRouter } from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
import axios from "axios"
export default {

    setup() {
        const diagData = reactive({
            name: '',
            sex: '',
            age: '',
            diagType: '',
            department: '',
            cost: '',
            physicalcheck: '',
            auxiliarycheck: '',
            diagnosticinfo: '',
            therapyinfo: '',
            doctor: '',
            drugData: ""
        })

        const diagData1 = ref({
            id:'',
            patid: '',
            docid: '',
            date: '',
            physicalcheck: '',
            auxiliarycheck: '',
            diagnosticinfo: '',
            therapyinfo: '',
            cost: '',
            drug: '',
            device: '',
            type: '',
        })

        //接收路由跳转信息
        const route = useRoute()
        const router = useRouter()
        const patientId = JSON.parse(decodeURIComponent(route.query.data))
        const diagId = JSON.parse(decodeURIComponent(route.query.diagId))

        const url = "http://localhost:8909/Patient/PatientDiagDetail"
        const diagnoseInfo = {
            id: diagId
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
                diagData.name = res.data.data[0].patName
                diagData.sex = res.data.data[0].patSex
                diagData.age = res.data.data[0].patAge
                diagData.diagType = res.data.data[0].type
                diagData.department = res.data.data[0].department
                diagData.cost = res.data.data[0].cost
                diagData.physicalcheck = res.data.data[0].physicalcheck
                diagData.auxiliarycheck = res.data.data[0].auxiliarycheck
                diagData.diagnosticinfo = res.data.data[0].diagnosticinfo
                diagData.therapyinfo = res.data.data[0].therapyinfo
                diagData.doctor = res.data.data[0].doctor
                diagData.drugData = res.data.data[1]
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

        const updateOp = () => {
            // console.log(patInfo.name);
            // console.log(patInfo.sex);
            // console.log(patInfo.age);
            // console.log(patInfo.anamnesis);
            // console.log(patInfo.address);
            // console.log(patInfo.account);
            // console.log(patInfo.password);
            // console.log(patInfo.classify);

            // pat.value.id = patInfo.id
            // pat.value.name = patInfo.name
            // pat.value.sex = patInfo.sex
            // pat.value.age = patInfo.age
            // pat.value.anamnesis = patInfo.anamnesis
            // pat.value.address = patInfo.address
            // pat.value.account = patInfo.account
            // pat.value.password = patInfo.password
            // pat.value.classify = patInfo.classify

            diagData1.value.id = diagId
            diagData1.value.physicalcheck = diagData.physicalcheck
            diagData1.value.auxiliarycheck = diagData.auxiliarycheck
            diagData1.value.diagnosticinfo = diagData.diagnosticinfo
            diagData1.value.therapyinfo = diagData.therapyinfo


            console.log(diagData1.value)
            axios({
                method: "post",
                url: "http://localhost:8909/Patient/PatientCaseUpdate",
                data: diagData1.value,
                headers: {
                    processData: false,
                    contentType: false,
                }
            }).then((res)=>{
                ElMessage({
                message: '修改成功！',
                type: 'success',
            })
                window.location.reload();
            }
                
            ).catch(error => {
                if (error.data.message == "DBERROR") {
                    ElMessage({
                        message: '修改失败！',
                        type: 'error',
                    })
                }
            })

            ElMessage({
                message: '修改成功！',
                type: 'success',
            })


        }



        return { diagData,updateOp }
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
    width: 75%;
    height: 76vh;
    margin-left: 15%;
    overflow-y: auto;
}
:deep(.el-input__wrapper) {
box-shadow: none;
font-size: 0.9vw;
}

.updateBtn {
    position: absolute;
    margin-top: -80vh;
    margin-left: 160vh;
    width: 1vw;
    font-size: 1vw;
    height: 10vh;
    border: 0;
    background-color: #98b4f5;
    color: white;
    writing-mode: vertical-rl;
    text-orientation: mixed;
}

</style>
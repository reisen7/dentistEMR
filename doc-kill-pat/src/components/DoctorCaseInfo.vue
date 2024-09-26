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
                    <el-button class="AddDoc" type="default" @click="enterAdd" circle>+</el-button>
                    <el-table :data="tableData" stripe style="width: 100%" height="50vh">
                            <el-table-column prop="date" label="检查日期" align="center" />
                                <el-table-column prop="diagnosticinfo" label="病名" align="center" />
                                <el-table-column fixed="right" label="操作" width="120">
                <template #default="{ row }">
                    <el-button link type="primary" size="small" @click="enterEdit(row)">编辑</el-button>
                    <el-button link type="danger" size="small" @click="deleteRow(row)">删除</el-button>
                </template>
            </el-table-column>


                            </el-table>
                        </el-descriptions-item>
                </el-descriptions>
        </div>

        <!-- <el-button class="updateBtn" @click="updateOp">修改</el-button> -->
        <!-- <el-button class="deleteBtn" @click="deleteOp">删除</el-button> -->
    </div>
</template>

<script>
import { reactive,ref,computed, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
import axios from "axios"

export default {

    setup() {

        const router = useRouter();

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

        const enterEdit = async (row) => {

            router.push({ path: '/doctor/DiagnoseDetailEdit', query: {
                data: encodeURIComponent(JSON.stringify(patientId)),
                diagId: encodeURIComponent(JSON.stringify(row.id))
            } })

        };

        
        const deleteRow = (row) => {
            
            ElMessageBox.confirm('是否确认删除诊断?', '提示!', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
            }).then(async () => {
                try {
                    await axios.post("http://localhost:8909/Patient/PatientCaseDelete", null, {
                        params: { id: row.id },
                        headers: {
                            processData: false,
                            contentType: false,
                        }
                    });
                    ElMessage({
                        type: 'success',
                        message: '删除成功!',
                    });

                    window.location.reload();
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

        }


        const enterAdd = () => {
            router.push({ path: '/doctor/DiagnoseDetailAdd', query: {
                data: encodeURIComponent(JSON.stringify(patientId)),
            } })

        }


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

        return { caseData, tableData, enterEdit,deleteRow,enterAdd }
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

.updateBtn {
    position: absolute;
    margin-top: -60vh;
    margin-left: 70vw;
    width: 1vw;
    font-size: 1vw;
    height: 10vh;
    border: 0;
    background-color: #98b4f5;
    color: white;
    writing-mode: vertical-rl;
    text-orientation: mixed;
}

.deleteBtn {
    position: absolute;
    margin-top: -45vh;
    margin-left: 65vw;
    width: 1vw;
    font-size: 1vw;
    height: 10vh;
    border: 0;
    background-color: #d4d6db;
    writing-mode: vertical-rl;
    text-orientation: mixed;
}

.AddDoc {
    position: absolute;
    width: 1.5vw;
    height: 3vh;
    font-size: 1.3vw;
    color: gray;
    margin-top: 1vh;
    font-weight: 500;
    margin-left: 21vw;
    z-index: 100;
}

</style>
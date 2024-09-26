<template>
    <div class="docInfoDetail">
        <h3>病人信息操作</h3> <button class="returnBtn" @click="returnQuery">返回</button>
        <div class="infoShow">
            <table style="border-collapse: collapse;" class="allTable">
                <tr>
                    <td style="width: 6vw;">id</td>
                    <td>{{ patInfo.id }}</td>
                    <td style="width: 6vw;">姓名</td>
                    <td><input type="text" v-model="patInfo.name"></td>
                </tr>
                <tr>
                    <td>性别：</td>
                    <td><input type="text" v-model="patInfo.sex"></td>
                    <td>年龄</td>
                    <td><input type="text" v-model="patInfo.age"></td>
                </tr>
                <tr>
                    <td>既往病史：</td>
                    <td><input type="text" v-model="patInfo.anamnesis"></td>
                    <td>地址：</td>
                    <td><input type="text" v-model="patInfo.address"></td>
                </tr>
                <tr>
                    <td>账号：</td>
                    <td><input type="text" v-model="patInfo.account"></td>
                    <td>密码：</td>
                    <td><input type="text" v-model="patInfo.password"></td>

                </tr>
                <tr>
                    <td>类别</td>
                    <td><input type="text" v-model="patInfo.classify"></td>
                    <td></td>
                    <td></td>

                </tr>
            </table>
        </div>
        <el-button class="updateBtn" @click="updateOp">修改</el-button>
        <el-button class="deleteBtn" @click="deleteOp">删除</el-button>

    </div>
</template>
<script>
import { useRoute, useRouter } from 'vue-router';
import { ref } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus'
import axios from 'axios';

export default {
    setup() {
        const route = useRoute();
        const router = useRouter();
        const returnQuery = () => {
            router.push("/admin/adminOperPat");
        }
        const patInfo = JSON.parse(decodeURIComponent(route.query.data))
        const pat = ref({
            id: '',
            name: '',
            sex: '',
            age: '',
            anamnesis: '',
            address: '',
            account: '',
            password: '',
            classify: ''
        })
        const updateOp = () => {
            console.log(patInfo.name);
            console.log(patInfo.sex);
            console.log(patInfo.age);
            console.log(patInfo.anamnesis);
            console.log(patInfo.address);
            console.log(patInfo.account);
            console.log(patInfo.password);
            console.log(patInfo.classify);

            pat.value.id = patInfo.id
            pat.value.name = patInfo.name
            pat.value.sex = patInfo.sex
            pat.value.age = patInfo.age
            pat.value.anamnesis = patInfo.anamnesis
            pat.value.address = patInfo.address
            pat.value.account = patInfo.account
            pat.value.password = patInfo.password
            pat.value.classify = patInfo.classify

            console.log(pat.value)
            axios({
                method: "post",
                url: "http://localhost:8909/Patient/PatientUpdate",
                data: pat.value,
                headers: {
                    processData: false,
                    contentType: false,
                }
            }).then(
                router.push({ path: "/admin/adminOperPat" })
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
        
        const deleteOp = () => {
            console.log(patInfo.id);


            ElMessageBox.confirm('是否确认删除患者?', '提示!', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
            }).then(async () => {
                try {
                    await axios.post("http://localhost:8909/Patient/PatientDelete", null, {
                        params: { id: patInfo.id },
                        headers: {
                            processData: false,
                            contentType: false,
                        }
                    });
                    ElMessage({
                        type: 'success',
                        message: '删除成功!',
                    });

                    router.push("/admin/adminOperPat");
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
        return { returnQuery, patInfo, updateOp, deleteOp }
    }
}
</script>

<style scoped>
.docInfoDetail {
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

.returnBtn {
    width: 10vw;
    height: 5vh;
    border: none;
    background-color: #dee6f8;
    border-radius: 5vw;
    float: right;
    margin-right: 5vw;
    margin-top: -6vh;
}

.infoShow {
    width: 50vw;
    height: 55vh;
    border: 0;
    margin-left: 15vw;
    margin-top: 5vh;
    border-radius: 5px;
    padding-top: 10vh;
    box-shadow: 0 0 5px 1px gray;
}

.allTable {
    width: 40vw;
    height: 40vh;
    margin-left: 5vw;
    text-align: left;
}

.allTable td {
    border-bottom: 1px solid #ddd;
    padding: 8px;
    text-align: left;
}

input:focus {
    outline: none;
}

input {
    width: 13vw;
    height: 4vh;
    font-size: 1vw;
    border: 0;
}

.updateBtn {
    position: absolute;
    margin-top: -60vh;
    margin-left: 65vw;
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
</style>
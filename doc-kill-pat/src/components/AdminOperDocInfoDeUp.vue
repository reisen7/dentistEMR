<template>
    <div class="docInfoDetail">
        <h3>医生信息操作</h3> <button class="returnBtn" @click="returnQuery">返回</button>

        <div class="infoShow">
            <img :src="docInfo.img" class="proFile">
            <table
                style="position: absolute;margin-top: -20vh;margin-left: 20vw;width: 20vw;height: 15vh;text-align: center;font-size: 1.3vw; ">
                <tr>
                    <td style=" border-bottom: 1px solid #ddd;">id:</td>
                    <td style=" border-bottom: 1px solid #ddd;">{{ docInfo.id }}</td>
                </tr>
                <tr>
                    <td style=" border-bottom: 1px solid #ddd;">姓名:</td>
                    <td style=" border-bottom: 1px solid #ddd;"><input type="text" v-model="docInfo.name"
                            style="text-align: center;"></td>
                </tr>
            </table>
            <table style="border-collapse: collapse;" class="allTable">
                <tr>
                    <td style="width: 4vw;">性别：</td>
                    <td><input type="text" v-model="docInfo.sex"></td>
                    <td style="width: 4vw;">年龄</td>
                    <td><input type="text" v-model="docInfo.age"></td>
                </tr>
                <tr>
                    <td>职称：</td>
                    <td><input type="text" v-model="docInfo.title"></td>
                    <td>疾病：</td>
                    <td>
                        <!-- <input type="text" v-model="docInfo.department"> -->
                        <select v-model="docInfo.department">
                            <option v-for="(option, index) in options" :key="index" :value="option">{{ option }}</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>账号：</td>
                    <td><input type="text" v-model="docInfo.account"></td>
                    <td>密码：</td>
                    <td><input type="text" v-model="docInfo.password"></td>

                </tr>
                <tr>
                    <td>权限：</td>
                    <td><input type="text" v-model="docInfo.authority"></td>
                    <td>简介：</td>
                    <td><input type="text" v-model="docInfo.introduction"></td>
                </tr>
            </table>
        </div>
        <el-button class="updateBtn" @click="updateOp">修改</el-button>
        <el-button class="deleteBtn" @click="deleteOp">删除</el-button>
    </div>
</template>
<script>
import { useRoute, useRouter } from 'vue-router';
import { ref,h } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus'
import axios from 'axios';
export default {
    setup() {
        const route = useRoute();
        const router = useRouter();

        const returnQuery = () => {
            axios({
                method: "post",
                url: "http://localhost:8909/Admin/gotoOperDoctor",
            }).then(res => {
                if (res.data.message === "noData") {
                    console.log(res.data.data);
                    router.push({ path: "/admin/adminOperDoc", query: { data: encodeURIComponent(JSON.stringify(res.data.data)) } });
                }
                if (res.data.message === "success") {
                    console.log(res.data.data);
                    router.push({ path: "/admin/adminOperDoc", query: { data: encodeURIComponent(JSON.stringify(res.data.data)) } });
                }
            }).catch(error => {
                ElMessage({
                    message: 'G!',
                    type: 'error',
                })
            })
        }
        let docInfo = JSON.parse(decodeURIComponent(route.query.data))
        let options = JSON.parse(route.query.select)
        const updateOp = () => {
            const doctor = {
                id: docInfo.id,
                name: docInfo.name,
                sex: docInfo.sex,
                age: docInfo.age,
                title: docInfo.title,
                department: docInfo.department,
                account: docInfo.account,
                password: docInfo.password,
                authority: docInfo.authority,
                introduction: docInfo.introduction,
            }
            console.log(doctor);
            axios({
                method: "post",
                url: "http://localhost:8909/Admin/reNewDoctor",
                data: doctor,
            }).then(res => {
                if (res.data.message === "failed") {
                    ElMessage({
                        message: '修改失败！',
                        type: 'error',
                    })
                    docInfo = res.data.data;
                }
                if (res.data.message === "success") {
                    ElMessage({
                        message: '修改成功！',
                        type: 'success',
                    })
                    docInfo = res.data.data;
                }
            }).catch(error => {
                if (error.data.message === "DBERROR") {
                    ElMessage({
                        message: 'G!',
                        type: 'error',
                    })
                }

            })
        }
        const deleteOp = () => {
            const data = new FormData();
            data.append("id", docInfo.id);
            ElMessageBox({
                title: '提示！',
                message: h('p', null, [
                    h('span', null, '是否确认删除该 '),
                    h('i', { style: 'color: teal' }, '医生信息！'),
                ]),
                showCancelButton: true,
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                beforeClose: (action, instance, done) => {
                    if (action === 'confirm') {
                        axios({
                            method: "post",
                            url: "http://localhost:8909/Admin/deleteDoctor",
                            data: data,
                            headers: {
                                processData: false,
                                contentType: false,
                            }
                        }).then(res => {
                            if (res.data.message == "success") {
                                ElMessage({
                                    message: '删除成功！',
                                    type: 'success',
                                })
                                axios({
                                    method: "post",
                                    url: "http://localhost:8909/Admin/gotoOperDoctor",
                                }).then(res => {
                                    if (res.data.message === "noData") {
                                        console.log(res.data.data);
                                        router.push({ path: "/admin/adminOperDoc", query: { data: encodeURIComponent(JSON.stringify(res.data.data)) } });
                                    }
                                    if (res.data.message === "success") {
                                        console.log(res.data.data);
                                        router.push({ path: "/admin/adminOperDoc", query: { data: encodeURIComponent(JSON.stringify(res.data.data)) } });
                                    }
                                }).catch(error => {
                                    ElMessage({
                                        message: 'G!',
                                        type: 'error',
                                    })
                                })
                            }
                        }).catch(error => {
                            if (error.data.message == "DBERROR") {
                                ElMessage({
                                    message: '删除失败！',
                                    type: 'error',
                                })
                            }
                        })
                        done()
                    } else {
                        done()
                    }
                },
            }).then((action) => {
                ElMessage({
                    type: 'success',
                    message: `action: ${action}`,
                })
            })
        }
        return { returnQuery, docInfo, options, updateOp, deleteOp }
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
    height: 70vh;
    border: 0;
    margin-left: 15vw;
    margin-top: 5vh;
    border-radius: 5px;
    box-shadow: 0 0 5px 1px gray;
}

.proFile {
    margin-left: 5vw;
    margin-top: 3vh;
    width: 10vw;
    height: 23vh;
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
    margin-top: -65vh;
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
    margin-top: -50vh;
    margin-left: 65vw;
    width: 1vw;
    font-size: 1vw;
    height: 10vh;
    border: 0;
    background-color: #d4d6db;
    writing-mode: vertical-rl;
    text-orientation: mixed;
}

select:focus {
    outline: none;
}

select {
    width: 10vw;
    height: 4vh;
    font-size: 0.9vw;
    box-shadow: 0 0 3px 1px rgb(212, 212, 212);
    border: 0;
    border-radius: 3px;
}
</style>
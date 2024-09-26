<template>
    <div class="adminDeUpdate">
        <h3>疾病操作</h3>
        <button class="returnBtn" @click="returnQurey">返回</button>
        <table>
            <tr>
                <th>疾病id：</th>
                <td><input type="text" v-model="id" disabled></td>
            </tr>
            <tr>
                <th>疾病名：</th>
                <td><input type="text" v-model="name"></td>
            </tr>
            <tr>
                <th>疾病简介：</th>
                <td><textarea rows="8" cols="68" v-model="introduction"> </textarea></td>
            </tr>

        </table> <el-button type="primary" class="updateBtn" @click="updateSend">修改</el-button>
        <el-button class="deleteBtn" @click="deleteSend">删除</el-button>
    </div>
</template>
<script>
import { ElMessage, ElMessageBox } from 'element-plus'
import axios from "axios"
import { h } from 'vue'
export default {
    data() {
        return {
            id: "",
            name: "",
            introduction: "",
            tel: '',
            data: Object,
        }
    },
    created() {
        const dataStr = decodeURIComponent(this.$route.query.data)
        const dataObject = JSON.parse(dataStr);
        this.data = dataObject;
        this.id = this.data.id;
        this.name = this.data.name;
        this.introduction = this.data.introduction;
        this.tel = this.data.tel;

    },
    methods: {
        returnQurey() {
            axios({
                method: "post",
                url: "http://localhost:8909/Admin/gotoOperDepart",
            }).then(res => {
                if (res.data.message === "noData") {
                    this.$router.push("/admin/adminOperDepart");
                }
                if (res.data.message === "success") {
                    console.log(res.data.data);
                    this.$router.push({ path: "/admin/adminOperDepart", query: { data: encodeURIComponent(JSON.stringify(res.data.data)) } });
                }
            }).catch(error => {
                ElMessage({
                    message: 'G!',
                    type: 'error',
                })
            })
        },
        updateSend() {
            if (this.name === "") {
                return;
            }
            if (this.introduction === "") {
                return;
            }
            if (this.tel === "") {
                return;
            }
            const department = {
                id: this.id,
                name: this.name,
                introduction: this.introduction,
                tel: this.tel,
            }
            console.log(department);
            axios({
                method: "post",
                url: "http://localhost:8909/Admin/reNewDepart",
                data: department,
            }).then(res => {
                if (res.data.message === "failed") {
                    ElMessage({
                        message: '修改失败！',
                        type: 'error',
                    })
                    this.id = res.data.data.id;
                    this.name = res.data.data.name;
                    this.introduction = res.data.data.introduction;
                    this.tel = res.data.data.tel;
                }
                if (res.data.message === "success") {
                    ElMessage({
                        message: '修改成功！',
                        type: 'success',
                    })
                    this.id = res.data.data.id;
                    this.name = res.data.data.name;
                    this.introduction = res.data.data.introduction;
                    this.tel = res.data.data.tel;
                }
            }).catch(error => {
                ElMessage({
                        message: '修改失败！',
                        type: 'G!',
                    })
            })
        },
        deleteSend() {
            const data = new FormData();
            data.append("id", this.id);
            console.log(data.get("id"));
            ElMessageBox({
                title: '提示！',
                message: h('p', null, [
                    h('span', null, '是否确认删除该 '),
                    h('i', { style: 'color: teal' }, '疾病！'),
                ]),
                showCancelButton: true,
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                beforeClose: (action, instance, done) => {
                    if (action === 'confirm') {
                        axios({
                            method: "post",
                            url: "http://localhost:8909/Admin/deleteDepart",
                            data: data,
                            headers: {
                                processData: false,
                                contentType: false,
                            }
                        }).then(res => {
                            if (res.data.message === "failed") {
                                ElMessage({
                                    message: '删除失败！',
                                    type: 'error',
                                })
                            }
                            if (res.data.message === "success") {
                                ElMessage({
                                    message: '删除成功！',
                                    type: 'success',
                                })
                                axios({
                                    method: "post",
                                    url: "http://localhost:8909/Admin/gotoOperDepart",
                                }).then(res => {
                                    if (res.data.message === "noData") {
                                        this.$router.push("/admin/adminOperDepart");
                                    }
                                    if (res.data.message === "success") {
                                        console.log(res.data.data);
                                        this.$router.push({ path: "/admin/adminOperDepart", query: { data: encodeURIComponent(JSON.stringify(res.data.data)) } });
                                    }
                                }).catch(error => {
                                    ElMessage({
                                        message: 'G!',
                                        type: 'error',
                                    })
                                })
                            }

                        }).catch(error => {
                            ElMessage({
                                message: 'G!',
                                type: 'error',
                            })
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
    }
}
</script>

<style scoped>
.adminDeUpdate {
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

table {
    border-collapse: collapse;
    width: 41vw;
    margin-left: 17vw;
    margin-top: 10vh;

}

th,
td {
    text-align: left;
    padding: 8px;
    border: 1px solid #ddd;
}

th {
    background-color: #f2f2f2;
}

input:focus {
    outline: none;
}

input {
    width: 33vw;
    height: 3vh;
    border: none;
    font-size: 0.9vw;
}

textarea:focus {
    outline: none;
}

textarea {
    border: none;
}

.updateBtn {
    margin-top: 8vh;
    margin-left: 20vw;
    width: 12vw;
    height: 5vh;
    border: none;
    font-size: 1vw;
    border-radius: 5px;
}

.deleteBtn {
    margin-top: 8vh;
    margin-left: 10vw;
    height: 5vh;
    width: 12vw;
    font-size: 1vw;
    border-radius: 5px;

}
</style>
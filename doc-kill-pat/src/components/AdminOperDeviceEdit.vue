<template>
    <div class="editDeviceInfo">
        <h3>设备信息修改</h3><button class="returnBtn" @click="returnQuery">返回</button>
        <div class="inputs">
            <table>
                <tr>
                    <td style="padding-top: 3vh;">设备名称</td>
                    <td><input type="text" v-model="deviceInfo.name"></td>
                </tr>
                <tr>
                    <td style="padding-top: 3vh;">设备费用</td>
                    <td><input type="text" v-model="deviceInfo.cost"></td>
                </tr>
                <tr>
                    <td style="padding-top: 3vh;">收费方式</td>
                    <td><input type="text" v-model="deviceInfo.chargmethod"></td>
                </tr>
                <tr>
                    <td style="padding-top: 3vh;">收费标准</td>
                    <td><input type="text" v-model="deviceInfo.standard"></td>
                </tr>
                <tr>
                    <td style="padding-top: 3vh;">费用说明</td>
                    <td><textarea cols="38" rows="5" v-model="deviceInfo.illustrate"></textarea></td>
                </tr>
            </table>
            <el-button type="primary" class="editBtn" @click="edit">修改设备信息</el-button>
        </div>

    </div>
</template>
<script>
import { useRoute, useRouter } from 'vue-router';
import { ref } from 'vue';
import { ElMessage } from 'element-plus' 
import axios from 'axios';
export default {
    setup() {
        const route = useRoute();
        const router = useRouter();
        const returnQuery = () => {

            router.push("/admin/adminOperDevice");
        }
        const deviceInfo = JSON.parse(decodeURIComponent(route.query.data))
        const device = ref({
            id: '',
            name: '',
            cost: '',
            chargmethod: '',
            standard: '',
            illustrate: ''
        })

        const edit = () => {
            console.log(deviceInfo.name);
            console.log(deviceInfo.cost);
            console.log(deviceInfo.chargmethod);
            console.log(deviceInfo.standard);
            console.log(deviceInfo.illustrate);

            device.value.id = deviceInfo.id
            device.value.name = deviceInfo.name
            device.value.cost = deviceInfo.cost
            device.value.chargmethod = deviceInfo.chargmethod
            device.value.standard = deviceInfo.standard
            device.value.illustrate = deviceInfo.illustrate

            console.log(device.value)

            axios({
                method: "post",
                url: "http://localhost:8909/dev/update",
                data: device.value,
                headers: {
                    processData: false,
                    contentType: false,
                }
            }).then(
                router.push({ path: "/admin/adminOperDevice" })
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
        return {
            returnQuery,
            deviceInfo,
            edit,
        }
    }
}
</script>
<style scoped>
.editDeviceInfo {
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
.inputs{
    background-color: rgb(237, 236, 236);
    width: 36vw;
    height: 65vh;
    margin-left: 25vw;
    margin-top: -5vh;
    border-radius: 5px;
    box-shadow: 0 0 5px 2px rgb(240, 240, 240);
}
table {
    margin-left: 5vw;
    margin-top: 10vh;
    padding-top: 3vh;
    font-size: 1.4vw;
    font-family: "楷体";
}

input:focus,
textarea:focus {
    outline: none;
}

input {
    width: 18vw;
    height: 5svh;
    margin-left: 20px;
    margin-top: 3vh;
    padding-left: 10px;
    border-radius: 5px;
    border: 0;
    font-size: 1vw;
    background-color: #f2f3f7;
}

textarea {
    margin-left: 20px;
    margin-top: 3vh;
    font-size: 0.95vw;
    font-family: "等线";
    border: 0;
    border-radius: 5px;
    background-color: #f2f3f7;
    padding-left: 20px;
}
.editBtn{
    margin-left: 14vw;
    margin-top: 5vh;
}
</style>
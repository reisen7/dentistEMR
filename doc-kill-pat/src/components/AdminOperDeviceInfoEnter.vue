<template>
    <div class="adminEnterDeviceInfo">
        <h3>新增设备</h3><button class="returnBtn" @click="returnQuery">返回</button>
        <div class="operPanel">
            <h4>设备录入</h4>
            <div class="inputs">
                <input type="text" v-model="name" placeholder="请输入设备名称">
                <input type="text" v-model="cost" placeholder="请输入设备费用">
                <input type="text" v-model="chargmethod" placeholder="请输入设备收费方式">
                <input type="text" v-model="standard" placeholder="请输入设备收费标准">
                <input type="text" v-model="illustrate" placeholder="请输入设备费用说明">
            </div>

            <el-button type="primary" class="addDevice" @click="addDevice">添加设备</el-button>
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
        const router = useRouter();
        const returnQuery = () => {
            router.push("/admin/adminOperDevice");
        }
        const name = ref('');
        const cost = ref('');
        const chargmethod = ref('');
        const standard = ref('');
        const illustrate = ref('');
        const device = ref({
            name: '',
            cost: '',
            chargmethod: '',
            standard: '',
            illustrate: ''
        })
        const addDevice = () => {
            console.log(name.value);
            console.log(cost.value);
            console.log(chargmethod.value);
            console.log(standard.value);
            console.log(illustrate.value);

            device.value.name = name.value
            device.value.cost = cost.value
            device.value.chargmethod = chargmethod.value
            device.value.standard = standard.value
            device.value.illustrate = illustrate.value

            console.log(device.value);

            axios({
                method: "post",
                url: "http://localhost:8909/dev/insert",
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
                        message: '添加失败！',
                        type: 'error',
                    })
                }
            })

            ElMessage({

                message: '设备添加成功！',
                type: 'success',
            })
        }
        return {
            returnQuery,
            name,
            cost,
            chargmethod,
            standard,
            illustrate,
            addDevice,
            device
        }
    }
}
</script>
<style scoped>
.adminEnterDeviceInfo {
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

.operPanel {
    width: 30vw;
    height: 60vh;
    margin-left: 25vw;
    margin-top: 8vh;
    background-color: rgb(243, 243, 243);
    border-radius: 10px;
}

h4 {
    float: left;
    font-size: 1.5vw;
    font-weight: 1000;
    margin-top: 3vh;
    width: 6vw;
    margin-left: 12vw;
    margin-right: 12vw;
    font-family: "幼圆";
    margin-bottom: 1vh;
}

.inputs {
    width: 18vw;
    height: 40vh;
    float: left;
    margin-left: 6vw;
}

input:focus {
    outline: none;
}

input {
    width: 18vw;
    margin-top: 3vh;
    height: 5vh;
    border: none;
    padding-left: 15px;
    font-size: 1.1vw;
}

.addDevice {
    float: left;
    margin-top: 45vh;
    margin-left: -13.5vw;
    width: 10vw;
    height: 4.5vh;
    border-radius: 8px;
}
</style>
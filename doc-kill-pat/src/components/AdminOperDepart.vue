<template>
    <div class="adminOperDepart">
        <h3>疾病管理</h3>
        <div class="btns">
            <img src="../assets/imgs/search.png" id="ico-seach">
            <input type="text" v-model="searchDepartment"  placeholder="查询疾病">
        </div>
        <div class="container">
            <div class="child"><router-link to="/admin/adminOperDepartEnter">
                    <img src="../assets/imgs/selectProFile.png">
                </router-link>
            </div>
            <p class="searchTip">{{filteredDepartments.length==0?"没有相关疾病信息！":""}}</p>
            <div v-for="(department, index) in filteredDepartments" :key="index" class="child"
                @click="goDetail(index)">
                <p class="name">{{ department.name }}</p>
                <p class="introduction">{{ department.introduction }}</p>
                <p class="tel">{{ department.tel }}</p>
            </div>
        </div>

    </div>
</template>
<script>
export default {
    data() {
        return {
            // departments: List,
            //     id: 1,
            //     name: "精神科",
            //     introduction: "精神科是管理病人和治疗恢复的,心理治疗：通过与患者进行谈话、咨询等方式来帮助患者缓解压力和焦虑，促进情绪稳定。",
            //     tel: "联系电话：" + 42929094,
            // }, {
            //     id: 2,
            //     name: "手术外科",
            //     introduction: "精神科是管理病人和治疗恢复的,药物治疗：通过使用药物来调节神经系统功能，从而减轻或消除相关症状。",
            //     tel: "联系电话：" + 42929094,
            // }, {
            //     id: 3,
            //     name: "消化内科",
            //     introduction: "精神科是管理病人和治疗恢复的,康复治疗：通过提供各种康复项目，如职业疗法、体育运动等，帮助患者逐步恢复正常生活和社交功能。",
            //     tel: "联系电话：" + 42929094,
            // }, {
            //     id: 4,
            //     name: "消化内科",
            //     introduction: "精神科是管理病人和治疗恢复的,康复治疗：通过提供各种康复项目，如职业疗法、体育运动等，帮助患者逐步恢复正常生活和社交功能。",
            //     tel: "联系电话：" + 42929094,
            // }, {
            //     id: 5,
            //     name: "消化内科",
            //     introduction: "精神科是管理病人和治疗恢复的,康复治疗：通过提供各种康复项目，如职业疗法、体育运动等，帮助患者逐步恢复正常生活和社交功能。",
            //     tel: "联系电话：" + 42929094,
            // }, {
            //     id: 6,
            //     name: "消化内科",
            //     introduction: "精神科是管理病人和治疗恢复的,康复治疗：通过提供各种康复项目，如职业疗法、体育运动等，帮助患者逐步恢复正常生活和社交功能。",
            //     tel: "联系电话：" + 42929094,
            // }, {
            //     id: 7,
            //     name: "消化内科",
            //     introduction: "精神科是管理病人和治疗恢复的,康复治疗：通过提供各种康复项目，如职业疗法、体育运动等，帮助患者逐步恢复正常生活和社交功能。",
            //     tel: "联系电话：" + 42929094,
            // }, {
            //     id: 8,
            //     name: "消化内科",
            //     introduction: "精神科是管理病人和治疗恢复的,康复治疗：通过提供各种康复项目，如职业疗法、体育运动等，帮助患者逐步恢复正常生活和社交功能。",
            //     tel: "联系电话：" + 42929094,
            // }, {
            //     id: 9,
            //     name: "消化内科",
            //     introduction: "精神科是管理病人和治疗恢复的,康复治疗：通过提供各种康复项目，如职业疗法、体育运动等，帮助患者逐步恢复正常生活和社交功能。",
            //     tel: "联系电话：" + 42929094,
            // }, {
            //     id: 10,
            //     name: "消化内科",
            //     introduction: "精神科是管理病人和治疗恢复的,康复治疗：通过提供各种康复项目，如职业疗法、体育运动等，帮助患者逐步恢复正常生活和社交功能。",
            //     tel: "联系电话：" + 42929094,
            // }, {
            //     id: 11,
            //     name: "消化内科",
            //     introduction: "精神科是管理病人和治疗恢复的,康复治疗：通过提供各种康复项目，如职业疗法、体育运动等，帮助患者逐步恢复正常生活和社交功能。",
            //     tel: "联系电话：" + 42929094,
            // }, {
            //     id: 12,
            //     name: "消化内科",
            //     introduction: "精神科是管理病人和治疗恢复的,康复治疗：通过提供各种康复项目，如职业疗法、体育运动等，帮助患者逐步恢复正常生活和社交功能。",
            //     tel: "联系电话：" + 42929094,
            // }, {
            //     id: 13,
            //     name: "消化内科",
            //     introduction: "精神科是管理病人和治疗恢复的,康复治疗：通过提供各种康复项目，如职业疗法、体育运动等，帮助患者逐步恢复正常生活和社交功能。",
            //     tel: "联系电话：" + 42929094,
            // }],
            searchDepartment: "",
        }
    },
    created() {
        const data = JSON.parse(decodeURIComponent(this.$route.query.data));
        console.log(data);
        this.departments = data;

    },
    computed: {
        filteredDepartments() {
            if (!this.searchDepartment) {
                return this.departments;
            }
            return this.departments.filter(department =>
                department.name.toLowerCase().includes(this.searchDepartment.toLowerCase())
            );
        }
    },
    methods: {
        goDetail(index) {
            // alert(this.departments[index].id);
            this.$router.push({path:"/admin/adminOperDepInfo",query:{data:encodeURIComponent(JSON.stringify(this.departments[index]))}})
        },
    }

}
</script>
<style scoped>
.child{
    cursor: pointer;
}
.adminOperDepart {
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

.searchTip {
    color: red;
    position: absolute;
    margin-left: 20vw;
    padding-top: 2vh;
    font-size: 1.1rem;
    z-index: -1;
}

.container {
    display: flex;
    flex-wrap: wrap;
    width: 80.5vw;

    height: 77vh;
    float: left;
    margin-left: 2vw;
    overflow-y: auto;
}

.child {
    width: 14.5vw;
    height: 35vh;
    margin: 10px;
    background-color: white;
    border-radius: 10px;
    box-shadow: 0px 0px 10px rgba(149, 202, 208, 0.5);
}

.child img {
    margin-left: 4.5vw;
    margin-top: 10vh;
    width: 6vw;
}

.child .name {
    font-size: 22px;
    text-align: center;
    font-family: "幼圆";
    font-weight: 1000;
    color: rgb(0, 0, 0);
}

.child .introduction {
    font-size: 15px;
    float: left;
    padding-left: 15px;
    padding-right: 15px;
    margin-top: -1vh;
    color: rgb(0, 0, 0);
    text-indent: 2em;
    line-height: 1.5em;
}

.child .tel {
    font-size: 13px;
    float: right;
    margin-right: 10px;
}

.btns {
    float: right;
    margin-top: -7vh;
    margin-right: 5vw;
    width: 65vw;
    height: 6vh;

}

.btns img {
    position: absolute;
    width: 1vw;
    height: 2vh;
    margin-left: 62.5vw;
    margin-top: 1.2vh;
}

.btns input {
    width: 17vw;
    height: 4vh;
    border: none;
    float: right;
    border-radius: 16vw;
    box-shadow: 0px 0px 5px rgba(25, 25, 25, 0.5);
    padding-left: 20px;
}

input:focus {
    outline: none;
}
</style>
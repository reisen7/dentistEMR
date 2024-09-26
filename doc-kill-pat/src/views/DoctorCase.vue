<template>
    <LeftTopBar />
    <CaseInfo />
</template>
<script>
    import LeftTopBar from '../components/DoctorLeftTopBar.vue';
    import CaseInfo from '../components/DoctorCaseInfo.vue';
    export default{
        components:{
            LeftTopBar,
            CaseInfo
        }
    }
</script>
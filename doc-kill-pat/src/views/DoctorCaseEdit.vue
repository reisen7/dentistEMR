<template>
    <LeftTopBar />
    <CaseInfo />
</template>
<script>
    import LeftTopBar from '../components/DoctorLeftTopBar.vue';
    import CaseInfo from '../components/DoctorCaseInfoEdit.vue';
    export default{
        components:{
            LeftTopBar,
            CaseInfo
        }
    }
</script>
<template>
    <LeftTopBar />
    <DiagnoseDetailInfo />
</template>
<script>
    import LeftTopBar from '../components/DoctorLeftTopBar.vue';
    import DiagnoseDetailInfo from '../components/DiagnoseDetailInfoEdit.vue';
    export default{
        components:{
            LeftTopBar,
            DiagnoseDetailInfo
        }
    }
</script>
<template>
    <LeftTopBar />
    <AdminOperDepartInfo />
</template>

<script>
import LeftTopBar from '../components/AdminLeftTopBar.vue';
import AdminOperDepartInfo from "../components/AdminOperDepartInfo.vue";
export default {
    components: {
        LeftTopBar,
        AdminOperDepartInfo,
    }
}
</script>
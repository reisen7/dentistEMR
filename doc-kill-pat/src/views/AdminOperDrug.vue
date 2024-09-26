<template>
    <LeftTopBar />
    <AdminOperDrugInfo/>
</template>

<script>
import LeftTopBar from '../components/AdminLeftTopBar.vue';
import AdminOperDrugInfo from '../components/AdminOperDrugInfo.vue';
export default {
    components: {
        LeftTopBar,
        AdminOperDrugInfo,
    }
}
</script>
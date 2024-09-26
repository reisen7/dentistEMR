<template>
    <LeftTopBar />
    <AdminOperDrugDetailShow/>
</template>

<script>
import LeftTopBar from '../components/AdminLeftTopBar.vue';
import AdminOperDrugDetailShow from '../components/AdminOperDrugDetailShow.vue';
export default {
    components: {
        LeftTopBar,
        AdminOperDrugDetailShow,
    }
}
</script>
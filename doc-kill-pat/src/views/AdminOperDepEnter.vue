<template>
    <LeftTopBar />
    <AdminEnterDepart />
</template>

<script>
import LeftTopBar from '../components/AdminLeftTopBar.vue';
import AdminEnterDepart from "../components/AdminDepEnter.vue";
export default {
    components: {
        LeftTopBar,
        AdminEnterDepart,
    }
}
</script>
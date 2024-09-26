<template>
    <LeftTopBar />
    <AdminOperDepart/>
</template>

<script>
import LeftTopBar from '../components/AdminLeftTopBar.vue';
import AdminOperDepart from '../components/AdminOperDepart.vue';
export default {
    components: {
        LeftTopBar,
        AdminOperDepart,
    }
}
</script>
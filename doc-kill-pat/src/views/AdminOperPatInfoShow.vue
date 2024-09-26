<template>
    <LeftTopBar />
    <AdminOperPatDetailShow/>
</template>

<script>
import LeftTopBar from '../components/AdminLeftTopBar.vue';
import AdminOperPatDetailShow from '../components/AdminOperPatDetailShow.vue';
export default {
    components: {
        LeftTopBar,
        AdminOperPatDetailShow,
    }
}
</script>
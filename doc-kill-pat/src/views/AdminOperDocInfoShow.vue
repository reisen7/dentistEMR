<template>
    <LeftTopBar />
    <AdminOperDocDetailShow/>
</template>

<script>
import LeftTopBar from '../components/AdminLeftTopBar.vue';
import AdminOperDocDetailShow from '../components/AdminOperDocDetailShow.vue';
export default {
    components: {
        LeftTopBar,
        AdminOperDocDetailShow,
    }
}
</script>
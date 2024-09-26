<template>
    <LeftTopBar />
    <AdminOperDoctor/>
</template>

<script>
import LeftTopBar from '../components/AdminLeftTopBar.vue';
import AdminOperDoctor from '../components/AdminOperDoctor.vue';
export default {
    components: {
        LeftTopBar,
        AdminOperDoctor,
    }
}
</script>
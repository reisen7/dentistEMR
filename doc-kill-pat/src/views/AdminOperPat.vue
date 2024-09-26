<template>
    <LeftTopBar />
    <AdminOperPatient/>
</template>

<script>
import LeftTopBar from '../components/AdminLeftTopBar.vue';
import AdminOperPatient from '../components/AdminOperPatient.vue';
export default {
    components: {
        LeftTopBar,
        AdminOperPatient,
    }
}
</script>
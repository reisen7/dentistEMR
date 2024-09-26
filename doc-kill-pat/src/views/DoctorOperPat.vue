<template>
    <LeftTopBar />
    <AdminOperPatient/>
</template>

<script>
import LeftTopBar from '../components/DoctorLeftTopBar.vue';
import AdminOperPatient from '../components/DoctorOperPatient.vue';
export default {
    components: {
        LeftTopBar,
        AdminOperPatient,
    }
}
</script>
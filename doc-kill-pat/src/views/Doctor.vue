<template>
    <LeftTopBar />
    <AdminAbout/>
</template>

<script>
import LeftTopBar from '../components/DoctorLeftTopBar.vue';
import AdminAbout from '../components/DoctorAbout.vue';
export default {
    components: {
        LeftTopBar,
        AdminAbout,
    }
}
</script>
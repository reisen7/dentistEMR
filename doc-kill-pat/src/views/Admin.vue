<template>
    <LeftTopBar />
    <AdminAbout/>
</template>

<script>
import LeftTopBar from '../components/AdminLeftTopBar.vue';
import AdminAbout from '../components/AdminAbout.vue';
export default {
    components: {
        LeftTopBar,
        AdminAbout,
    }
}
</script>
<template>
    <LeftTopBar />
    <AdminOperDeviceInfo/>
</template>

<script>
import LeftTopBar from '../components/AdminLeftTopBar.vue';
import AdminOperDeviceInfo from '../components/AdminOperDeviceInfo.vue';
export default {
    components: {
        LeftTopBar,
        AdminOperDeviceInfo,
    }
}
</script>
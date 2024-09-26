<template>
    <LeftTopBar />
    <AdminOperDeviceEdit/>
</template>

<script>
import LeftTopBar from '../components/AdminLeftTopBar.vue';
import AdminOperDeviceEdit from '../components/AdminOperDeviceEdit.vue';
export default {
    components: {
        LeftTopBar,
        AdminOperDeviceEdit,
    }
}
</script>
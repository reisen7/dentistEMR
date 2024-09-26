<template>
    <LeftTopBar />
    <UserInfoUpdate />
</template>

<script>
    import LeftTopBar from '../components/LeftTopBar.vue';
    import UserInfoUpdate from '../components/UserInfoUpdate.vue';
    export default {
        components: {
            LeftTopBar,
            UserInfoUpdate
        }
    }
</script>
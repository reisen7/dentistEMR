<template>
    <div class="background">
        <Main />
        <Aside />
    </div>
</template>
<script>
import Main from '../components/RegisterMain.vue';
import Aside from '../components/RegisterAside.vue';
export default {
    components: {
        Main,
        Aside,
    }
}
</script>

<style scoped>
.background {
    margin: -8px;
    background-image: url("../assets/imgs/mainBg.jpg");
    background-size: cover;
    object-fit: fill;
    height: 100vh;
}
</style>
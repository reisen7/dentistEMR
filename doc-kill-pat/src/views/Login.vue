<template>
    <Header />
    <div class="background">
        <Main />
        <Aside />
    </div>
    <Footer />
</template>
<script>
import Header from '../components/LoginHeader.vue';
import Main from '../components/LoginMain.vue';
import Aside from '../components/LoginAside.vue';
import Footer from '../components/LoginFooter.vue';
export default {
    components: {
        Header,
        Main,
        Aside,
        Footer,
    }
}
</script>

<style scoped>
* {
    margin: 0;
    padding: 0;
}

.background {
    margin: -8px -8px auto;
    background-image: url("../assets/imgs/mainBg.jpg");
    background-size: cover;
    object-fit: fill;
    height: 70vh;
}
</style>
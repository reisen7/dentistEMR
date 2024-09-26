<template>
    <div class="swiper-container">
        <div class="title">电子病历平台</div>
        <router-link id="router" to="/Login">统一登录入口</router-link>
        <div class="swiper-wrapper">
            <div class="swiper-slide"><img src="../assets/imgs/swOne.jpg" /></div>
            <div class="swiper-slide"><img src="../assets/imgs/swTwo1.png" /></div>
            <div class="swiper-slide"><img src="../assets/imgs/swTc1.png" /></div>
        </div>
        <div class="swiper-pagination"></div>
    </div>
</template>
<script>
import Swiper from 'swiper/bundle';
import 'swiper/swiper.css';

export default {
    mounted() {
        const mySwiper = new Swiper('.swiper-container', {
            // Swiper参数
            slidesPerView: 1,
            spaceBetween: 100,
            loop: true,
            autoplay: {
                delay: 5000,
                disableOnInteraction: false,
            },
            pagination: {
                el: '.swiper-pagination',
                clickable: true,
            },
        });
    }
};
</script>
<style scoped>

.swiper-container {
    margin: -8px -8px -8px -8px;
    overflow-x: hidden;
    overflow-y: hidden;
    width: 100vw;
    height: 100vh;

}
.swiper-container img{
    height: 100%;
    width: 100%;
}

.title{
    position: absolute;
    font-size: 5rem;
    z-index: 100;
    float: left;
    margin-left: 10vw;
    margin-top: 8vh;
    width: 2vw;
    font-family: "站酷庆科黄油体";
    color: rgb(45, 107, 240);
}
.swiper-container #router{
    position: absolute;
    margin-left: 9vw;
    margin-top: 85vh;
    font-size: 1rem;
    color: rgb(57, 10, 245);
    font-family: "幼圆";
    font-weight: 700;
    z-index: 999;
} 
</style>
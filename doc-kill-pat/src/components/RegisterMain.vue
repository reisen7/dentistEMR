<template>
    <div class="agreeMent">
        <h1>电子病历平台使用协议</h1>

        <h2>定义</h2>
        <p>“电子病历平台”指提供在线服务的医疗机构。</p>

        <h2>服务内容</h2>
        <ul>
            <li>病历查看</li>
            <li>病历更改</li>
            <li>病历编辑</li>
        </ul>

        <h2>用户责任</h2>
        <ol>
            <li>提供真实准确信息</li>
            <li>不得发布不良信息</li>
            <li>不得干扰正常运行</li>
            <li>不得商业活动</li>
            <li>遵守法律法规和协议义务</li>
        </ol>

        <h2>知识产权</h2>
        <p>电子病历平台享有所有内容的版权和知识产权。</p>

        <h2>免责声明</h2>
        <p>因互联网特殊性，导致损失不负责任。</p>


    </div>
</template>
<script>

</script>

<style scoped>
.agreeMent {
    float: left;
    margin-left: 20px;
    width: 60%;
    height: 100vh;
    box-sizing: border-box;
    background-color: rgba(236, 224, 215, 0.466);
    border-radius: 10px;
    padding-left: 15vw;
    overflow-y: auto;
}

h1 {
    font-size: 20px;
    font-weight: bold;
    margin-bottom: 10px;
}

h2 {
    font-size: 15px;
    font-weight: bold;
    margin-top: 20px;
    margin-bottom: 10px;
}

p {
    margin-top: 10px;
    margin-bottom: 10px;
}

ul {
    list-style: none;
    margin-left: 20px;
}

li:before {
    content: "";
    margin-right: 5px;
}

ol {
    list-style-type: decimal;
    margin-left: 20px;
}
</style>
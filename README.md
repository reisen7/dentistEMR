# 电子病历管理系统

#### 介绍
信息化时代，为了更好的管理病历医嘱以及预约医生，团队开发了电子病历平台
电子病历平台，三个用户子系统，分别为普通用户、院方（医生）、维护子系统
普通用户为一般病人用户使用，现只做了网页端，用于查询病历、挂号、等等。
院方（医生）为医院使用，权限管理严格，涉及使用医院的器材，药品安排，人手安排，值班等，以及相关病人的病历查看以及对病人病症的叮嘱留言等。
维护子系统需要特定数据库账号登录使用，涉及数据库底层数据，登录需要人脸识别加密码登录双重认证才可以登录，涉及病人和医院的信息管理，主要是给医生账号信息、药品信息、器材管理等信息维护。

#### 软件架构
软件架构说明



DocKillThePat为后端springboot
doc-kill-pat为前端vue3做的ui
imgIdentifypy是cv2训练的管理人员面部特征数据

#### 安装教程

1.  node.js 安装 打开官方网站：https://nodejs.org 下载node.js， 配置环境变量
2.  执行以下命令来安装Vue 3的包 npm install vue@next

#### 使用说明

1.  前端 在doc-kill-pat目录下 使用命令npm install axios， npm install element-plus ，npm install swiper，下载相关依赖库  npm run dev ，将前端地址在网页中打开
2.  后端 配置jdk环境和本地Maven ，用IDE打开DocKillThePat文件夹，等待相关配置加载启动即可
3.  管理员人脸识别 将 imgIdentifypy文件夹下的py文件中文件路径和数据库设置为自己的，并且自己用cv2训练一个特征数据集放于trainer.yml中，对人脸数据特征对比外加密码才能登录。
#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)

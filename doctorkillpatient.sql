/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : localhost:3306
 Source Schema         : doctorkillpatient

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 27/09/2024 00:18:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for adminuser
-- ----------------------------
DROP TABLE IF EXISTS `adminuser`;
CREATE TABLE `adminuser`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `account` bigint NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of adminuser
-- ----------------------------
INSERT INTO `adminuser` VALUES (5, 19122542886, '12345678');
INSERT INTO `adminuser` VALUES (6, 17723018045, '12345678');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `introduction` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `tel` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '菌斑', '牙菌斑（又称牙垢）是在牙齿表面逐渐沉积的生物薄膜，由食物残渣、脱落的口腔上皮细胞、唾液和细菌构成。牙菌斑中的细菌主要是正常口腔内存在的链球菌、厌氧菌等', 42192928);
INSERT INTO `department` VALUES (2, '龋齿', '龋齿俗称“蛀牙”或“虫牙”，是最常见的一种牙病。龋齿是牙体组织被龋蚀，逐渐毁坏崩解，形成龋洞的一种疾病。', 42948592);
INSERT INTO `department` VALUES (4, '牙龈炎', '医学上将围绕并覆盖在牙齿周围的软组织称为牙龈，发生于牙龈组织的急慢性炎症称为牙龈炎。牙龈炎表现为牙龈肿痛发炎、出血、红肿，而且特别容易在刷牙时流血。', 42737729);
INSERT INTO `department` VALUES (5, '牙周炎', '牙周炎是一种比牙龈炎更为严重的口腔疾病，一旦发病是不能完全治愈的。当齿垢在牙龈线积聚并发生感染，便会破坏支持牙齿的纤维和牙槽骨。牙龈会与牙齿分离，并开始萎缩，令齿根外露，牙齿松动，直至最后不得不将其拔掉。口腔专家认为，治疗可使牙周炎停止恶化，但不能令其痊愈，所以必须及早预防，养成良好的口腔保健习惯，避免此类牙病的发生。', 42928875);

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `cost` bigint NOT NULL,
  `chargmethod` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `standard` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `departid` int NULL DEFAULT NULL,
  `docid` int NULL DEFAULT NULL,
  `illustrate` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `dev-dep`(`departid` ASC) USING BTREE,
  INDEX `dev-doc`(`docid` ASC) USING BTREE,
  CONSTRAINT `depart-id` FOREIGN KEY (`departid`) REFERENCES `department` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `doc-dev` FOREIGN KEY (`docid`) REFERENCES `doctor` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES (2, '牙医设备', 1000, '500/次', '599~1000', NULL, NULL, '用了两次,收两倍');
INSERT INTO `device` VALUES (5, '数字化口腔扫描仪', 150000, '套餐', '会员', NULL, NULL, '高精度口腔扫描，快速生成3D模型');

-- ----------------------------
-- Table structure for diagnose
-- ----------------------------
DROP TABLE IF EXISTS `diagnose`;
CREATE TABLE `diagnose`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `patid` int NOT NULL COMMENT '病历id',
  `docid` int NOT NULL COMMENT '医生id',
  `date` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '日期',
  `physicalcheck` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '体格检查',
  `auxiliarycheck` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '辅助检查',
  `diagnosticinfo` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '诊断信息',
  `therapyinfo` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '治疗信息',
  `cost` double NOT NULL COMMENT '门诊费用',
  `drug` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '药品',
  `device` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '设备',
  `type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '门诊，住院，急诊',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `resu-cas`(`patid` ASC) USING BTREE,
  INDEX `resu-doc`(`docid` ASC) USING BTREE,
  CONSTRAINT `CASEID` FOREIGN KEY (`patid`) REFERENCES `patient` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `cod-dig` FOREIGN KEY (`docid`) REFERENCES `doctor` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of diagnose
-- ----------------------------
INSERT INTO `diagnose` VALUES (1, 1, 100, '2023/10/12', '血压：120/80 mmHg  脉搏：72 次/分  呼吸率：16 次/分', '白细胞计数：6.2 × 10^9/L  红细胞计数：5.0 × 10^12/L  血红蛋白浓度：150 g/L', '初步诊断为确诊为锯齿', '心理、药物治疗', 2300, '1-2-3', '1', '住院');
INSERT INTO `diagnose` VALUES (2, 1, 103, '2022/9/1', '身高180cm，体重75kg，BMI为23.1', '牙齿有出血', '初步诊断为牙龈出血', '建议继续保持良好的生活习惯，进行定期体检，以确保身体健康。', 300, '1-2-3-4-5', '2', '急诊');
INSERT INTO `diagnose` VALUES (6, 1, 100, '2024/09/17', '正常', '正常', '牙齿不齐', '不到', 100, '', NULL, '门诊');
INSERT INTO `diagnose` VALUES (7, 10, 100, '2024/09/17', '1', '1', '1', '1', 100, '', NULL, '急诊');

-- ----------------------------
-- Table structure for doctor
-- ----------------------------
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE `doctor`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `age` int NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `departmentid` int NOT NULL,
  `introduction` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `account` bigint NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `authority` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `doc-dep`(`departmentid` ASC) USING BTREE,
  CONSTRAINT `derpat-id` FOREIGN KEY (`departmentid`) REFERENCES `department` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 116 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of doctor
-- ----------------------------
INSERT INTO `doctor` VALUES (100, '月城雪兔', '男', 21, '高级医师', 4, '月城雪兔一直是医院的中流砥柱，总是在危急时刻救医院于危难之中', 'http://localhost:8909/proFile/OIP-C.jpg', 19138284992, '12345678', '查看,维护');
INSERT INTO `doctor` VALUES (101, '大岛寺知世', '女', 20, '高级医师', 5, '知世一直是木之本医师的得力助手，在多次重大手术中做出贡献', 'http://localhost:8909/proFile/OIP-C (3).jpg', 15528390121, '12345678', '维护,查看');
INSERT INTO `doctor` VALUES (102, '木之本樱', '男', 20, '高级医师', 4, '木之本医师一直在致力于医院的研究工作，是医院新技术的得力主干', 'http://localhost:8909/proFile/20200624004509_xYJFu.jpeg', 18128312342, '12345678', '维护,查看');
INSERT INTO `doctor` VALUES (103, '小可', '男', 10, '高级医师', 5, '小可是一个高级医师，是木之本的得力助手', 'http://localhost:8909/proFile/ke.png', 18329829839, '12345678', '维护,查看');
INSERT INTO `doctor` VALUES (104, '大岛寺之樱', '男', 30, '高级药师', 4, '这是一个很稳的药师，在对药处方上颇有研究', 'http://localhost:8909/proFile/OIP-C (3).jpg', 18098729812, '12345678', '维护,查看');
INSERT INTO `doctor` VALUES (105, '知世', '女', 20, '高级医师', 4, '大岛寺之家，知世医师', 'http://localhost:8909/proFile/OIP-C (5).jpg', 18092819283, '12345678', '维护,查看');
INSERT INTO `doctor` VALUES (106, '大岛知世', '女', 20, '高级医师', 2, '大岛寺的医师，真的好', 'http://localhost:8909/proFile/OIP-C (7).jpg', 17398213823, '12345678', '维护,查看');
INSERT INTO `doctor` VALUES (107, '三三', '男', 98, '高级医师', 2, '三三是高级医师中的顶流，是王者医师', 'http://localhost:8909/proFile/u=569350009,4041112873&fm=253&fmt=auto&app=138&f=JPEG.webp', 15089012392, '12345678', '查看,维护');
INSERT INTO `doctor` VALUES (108, '斯比', '男', 100, '高级药师', 1, '这是小可医师的辅助医师', 'http://localhost:8909/proFile/v2-2c5fd8e6fac4cc807074621ef731c89c_r.jpg', 18212312312, '12345678', '维护,查看');
INSERT INTO `doctor` VALUES (111, '樱樱', '男', 20, '高级医师', 4, '樱樱是一个超级牛的医师', 'http://localhost:8909/proFile/OIP-C (6).jpg', 19221231239, '12345678', '查看');
INSERT INTO `doctor` VALUES (112, '小小樱', '女', 20, '高级医师', 2, '高级医师小小樱', 'http://localhost:8909/proFile/R-C (9).jpg', 191283942991, '12345678', '维护,查看');

-- ----------------------------
-- Table structure for drug
-- ----------------------------
DROP TABLE IF EXISTS `drug`;
CREATE TABLE `drug`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `standards` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `producter` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `indications` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `usefor` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `taboo` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `comcon` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `price` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `proplace` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of drug
-- ----------------------------
INSERT INTO `drug` VALUES (6, '氟化钠凝胶', '凝胶状', '每支凝胶3g或5g', 'XX口腔健康制药有限公司', '用于预防和治疗牙齿敏感、龋齿早期病变（如白垩斑）的辅助治疗，以及作为口腔保健措施之一，增强牙齿抗酸能力', NULL, '对氟化物过敏者禁用。', '主要成分为氟化钠，辅以适量甘油、羧甲基纤维素钠等作为基质和稳定剂。', '10', '中国·XX省XX市');
INSERT INTO `drug` VALUES (7, '丁硼乳膏', '乳膏', '10克/支', 'XX口腔健康用品公司', '用于牙龈炎、牙周炎等引起的牙龈出血、肿痛等症状的辅助治疗。', NULL, '对硼砂、丁香酚等成分过敏者禁用；牙齿敏感者慎用。', '硼砂、丁香酚等', '20', '中国·XX市');
INSERT INTO `drug` VALUES (8, '复方氯己定含漱液', '含漱液', '100毫升/瓶', 'XX药业集团', '用于口腔疾病的辅助治疗，如牙龈炎、口腔溃疡等，具有杀菌消炎作用。', NULL, '对本品任何成分过敏者禁用；长期使用可能导致牙齿着色，使用时应注意。', '氯己定、甲硝唑等', '200', '中国·XX省XX市');
INSERT INTO `drug` VALUES (9, '人工牛黄甲硝唑胶囊', '胶囊', '每粒含甲硝唑XX毫克，人工牛黄XX毫克', '中国·XX省', '用于急性智齿冠周炎、局部牙槽脓肿、牙髓炎、根尖周炎等口腔疾病的辅助治疗。', NULL, '对甲硝唑或牛黄过敏者禁用；孕妇及哺乳期妇女禁用。', '人工牛黄、甲硝唑', '200', 'XX制药厂');

-- ----------------------------
-- Table structure for patient
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account` bigint NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '病人名字',
  `age` int NOT NULL COMMENT '年龄',
  `sex` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '性别',
  `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '联系地址',
  `classify` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '门诊，住院等',
  `anamnesis` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '既往病史描述',
  `profile` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of patient
-- ----------------------------
INSERT INTO `patient` VALUES (1, 15012329482, '12345678', '刘大', 22, '女', '重庆122222', '军人', '有病', NULL);
INSERT INTO `patient` VALUES (2, 17723018045, '12345678', '刘老大', 21, '女', '重庆q', '军人', '大病啊', NULL);
INSERT INTO `patient` VALUES (4, 17723018099, '12312312132', '刘某人', 20, '男', '重庆', '普通', '有大病', 'http://localhost:8909/proFile/ke.png');
INSERT INTO `patient` VALUES (5, 17723018088, '12312312132', '刘某人', 20, '男', '重庆', '普通', '有大病', 'http://localhost:8909/proFile/ke.png');
INSERT INTO `patient` VALUES (6, 17723928422, '12312312312', '刘某人', 12, '男', '重庆师范大学大学城校区和风苑b4070', '普通', '没有', 'http://localhost:8909/proFile/OIP-C (5).jpg');
INSERT INTO `patient` VALUES (10, 17723018048, '12345678', '明世隐', 12, '男', '19370794832', '普通', '有病', 'http://localhost:8909/proFile/config3.jpg');

-- ----------------------------
-- Table structure for reservation
-- ----------------------------
DROP TABLE IF EXISTS `reservation`;
CREATE TABLE `reservation`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `patid` int NOT NULL,
  `depatid` int NOT NULL,
  `docid` int NOT NULL,
  `date` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `time` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `reason` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `way` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `status` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `res-pat`(`patid` ASC) USING BTREE,
  INDEX `res-dep`(`depatid` ASC) USING BTREE,
  INDEX `res-doc`(`docid` ASC) USING BTREE,
  CONSTRAINT `depart-idd` FOREIGN KEY (`depatid`) REFERENCES `department` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `doc-re` FOREIGN KEY (`docid`) REFERENCES `doctor` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `patid` FOREIGN KEY (`patid`) REFERENCES `patient` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of reservation
-- ----------------------------

-- ----------------------------
-- Table structure for tell
-- ----------------------------
DROP TABLE IF EXISTS `tell`;
CREATE TABLE `tell`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `docid` int NOT NULL,
  `content` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `advice` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `druginfo` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `tel-doc`(`docid` ASC) USING BTREE,
  CONSTRAINT `doc-tall` FOREIGN KEY (`docid`) REFERENCES `doctor` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tell
-- ----------------------------
INSERT INTO `tell` VALUES (1, 100, '定期复查血糖水平', '糖尿病', '每月前来医院进行血糖监测，遵循医生的饮食控制和药物治疗方案。', '1-2-3');
INSERT INTO `tell` VALUES (2, 102, '接受放射治疗', '肺癌', '每周到放射科接受放射治疗，按照医生的安排完成治疗周期，同时注意保持良好的营养和休息。', '1-2');
INSERT INTO `tell` VALUES (3, 101, '使用抗生素治疗', '细菌感染', '按时服用医生开具的抗生素药物，每日剂量和用法依照医嘱，完成全程治疗，并注意药物的可能副作用和饮食调整。', '2-3-4-5');
INSERT INTO `tell` VALUES (4, 105, '进行肠镜检查', '结肠炎', '预约肠镜检查，并按照医生的指导进行术前准备和饮食控制。', '1-2-3');
INSERT INTO `tell` VALUES (5, 107, '接受物理治疗', '腰椎间盘突出症', '到物理治疗科进行物理疗法，如热敷、理疗等，按照医生的安排进行康复训练。', '4-5');
INSERT INTO `tell` VALUES (6, 108, '佩戴牙套', '牙列不齐', '到正畸科进行检查，如果适合，佩戴牙套并按时复诊调整。\r\n\r\n', '1-2-5');
INSERT INTO `tell` VALUES (7, 105, '接受心脏手术', '冠心病', '接受冠状动脉搭桥手术，在手术前进行必要的检查和准备。\r\n\r\n', '2-3-4');
INSERT INTO `tell` VALUES (8, 104, '进行眼压检查', '青光眼', '定期到眼科进行眼压检查，按医生的建议使用降眼压药物。', '1-4-5');
INSERT INTO `tell` VALUES (9, 106, '接受化疗', '乳腺癌', '按照医生的方案接受化疗治疗周期，同时注意饮食调整和身体护理。', '1-2');
INSERT INTO `tell` VALUES (10, 100, '实施手术切除肿瘤', '流感', '按照医生的处方用药，每日服用抗病毒药物，同时注意休息和补充充足的水分。', '1-2-3');
INSERT INTO `tell` VALUES (11, 111, '佩戴矫形鞋垫', '扁平足', '到骨科进行检查，如有需要则佩戴矫形鞋垫，并参加适当的足部锻炼。', '1-4-5');
INSERT INTO `tell` VALUES (12, 112, '进行心脑血管手术', '动脉瘤', '接受心脑血管手术，术前进行必要的检查和准备。', '2-3');
INSERT INTO `tell` VALUES (13, 107, '接受肠道洗净治疗', '慢性便秘', '在医生指导下进行肠内洗净治疗，同时调整饮食结构和增加运动量。\r\n\r\n', '4-5');
INSERT INTO `tell` VALUES (14, 102, '进行关节置换手术', '髋关节退行性疾病', '接受人工关节置换手术，术前进行相关检查和准备。\r\n\r\n', '2-3-4-5');
INSERT INTO `tell` VALUES (15, 105, '进行化学去皮治疗', '色斑', '到美容科接受化学去皮治疗，按医生的指导进行护肤和注意防晒。\r\n\r\n', '1-2-3');
INSERT INTO `tell` VALUES (16, 101, '使用口服避孕药', '月经不调', '根据医生的处方用药，每日定时服用口服避孕药，同时注意月经周期和身体状况。', '3-4-5');
INSERT INTO `tell` VALUES (17, 102, '进行激光手术修复视力', '近视', '在眼科接受激光手术进行视力矫正，术前需要做相应检查和术后康复护理。', '1-2-3-4-5');

SET FOREIGN_KEY_CHECKS = 1;

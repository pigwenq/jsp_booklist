/*
SQLyog Ultimate v12.4.3 (64 bit)
MySQL - 5.7.36-log : Database - book
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`book` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `book`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `admin` */

insert  into `admin`(`id`,`name`,`password`) values 
(9,'zhu','123'),
(10,'12','12'),
(11,'13','13'),
(12,'14','14');

/*Table structure for table `booklist` */

DROP TABLE IF EXISTS `booklist`;

CREATE TABLE `booklist` (
  `bookId` int(11) NOT NULL AUTO_INCREMENT,
  `bookname` char(50) NOT NULL,
  `price` double DEFAULT NULL,
  `bookmsg` varchar(1000) DEFAULT NULL,
  `typeid` int(11) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bookId`),
  KEY `typeid` (`typeid`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

/*Data for the table `booklist` */

insert  into `booklist`(`bookId`,`bookname`,`price`,`bookmsg`,`typeid`,`photo`) values 
(34,'夏洛的网',18.9,'在朱克曼家的谷仓里，快乐地生活着一群动物，其中小猪威尔伯和蜘蛛夏洛建立了*真挚的友谊。然而，一个*丑恶的消息打破了谷仓的平静：威尔伯未来的命运竞是成为熏肉火腿。作为一只小猪，悲痛绝望的威尔伯似乎只能接受任人宰割的命运了，然而，看似渺小的夏洛却说：“我救你。”于是，夏洛用自己的丝在猪栏上织出了被人类视为奇迹的网上文字，彻底逆转了威尔伯的命运，终于让它在集市的大赛中赢得特别奖，和一个安享天命的未来。但，这时，蜘蛛夏洛的生命却走到了尽头……\r\n在E·B 怀特的笔下，夏洛用蜘蛛丝编织了一张爱的大网，这网挽救了威尔伯的生命，更激起你我心中无尽的爱与温情。',1,'1639401071470.jpg'),
(36,'从你的全世界路过',28.8,'《从你的全世界路过》是微博上*会写故事的人张嘉佳的一部短篇小说集。书中讲述了发生在我们身边 的很多爱情故事，有念念不忘的美好，有爱而不得的疼痛，有生离死别的遗憾，有一再错过的宿命，也有喧嚣之后的回归和温暖。总有那么一些瞬间，你会在张嘉佳 的故事里看到自己，也总有那么一些瞬间，你会因为这些故事，而想到某个人，某段爱情。《从你的全世界路过》，注定会成为你今年读过的*温暖的书，因为这本 书，是关于你的故事。',1,'1639400044260.jpg'),
(37,'解忧杂货店',1,'日本作家东野圭吾的《解忧杂货店》，出版当年即获中央公论文艺奖。作品超越推理小说的范围，却比推理小说更加扣人心弦。\r\n僻静的街道旁有一家杂货店，只要写下烦恼投进店前门卷帘门的投信口，第二天就会在店后的牛奶箱里得到回答：因男友身患绝症，年轻女孩静子在爱情与梦想间徘徊；克郎为了音乐梦想离家漂泊，却在现实中寸步难行；少年浩介面临家庭巨变，挣扎在亲情与未来的迷茫中……\r\n他们将困惑写成信投进杂货店，奇妙的事情随即不断发生。生命中的一次偶然交会，将如何演绎出截然不同的人生？',1,'1639400103808.jpg'),
(42,'小王子',19.9,'这是一本足以让人永葆童心的不朽经典，被全球亿万读者誉为*值得收藏的书。\r\n遥远星球上的小王子，与美丽而骄傲的玫瑰吵架负气出走，在各星球漫游中，小王子遇到了傲慢的国王、酒鬼、惟利是图的商人，死守教条的地理学家，*后来到地球上，试图找到治愈孤独和痛苦的良方。\r\n这时，他遇到一只奇怪的狐狸，于是奇妙而令人惊叹的事情发生了……\r\n《小王子》犹如透亮的镜子，照出了荒唐的成人世界。她在提醒我们，只有爱，才是*的哲学，才是我们活下去的*理由。',1,'1639401169214.jpg'),
(43,'我不喜欢这个世界',19.8,'本书与市面上的家庭教育及学习方法指导书有很大的不同。这是因为，本书既不是某一个家长个人的育儿经验，也不是就某一具体学科让家长对孩子的学习进行指导。本书立足于心理科学的视角，让家长从本质上了解孩子“学习这件事儿”背后的心理学规律，走出对孩子学习的指导误区。 作者将30多年的心理学专业知识和实践经验融会于一个个案例、实验、方法、技巧中，分析入理、“招数”实用，帮助家长科学指导孩子学习。家长朋友们如能真正掌握，让孩子爱上学习就不是难事。',1,'1639401391360.jpg'),
(44,'奇幻梦境',20.96,'★引爆优选购买狂潮的经典填色书！\r\n★刷爆朋友圈的减压力器，这个夏季，涂色书让你放下手机，做个涂色艺术家。\r\n★这本书让现代人可以关闭WiFi，放下手机，拔掉插头，不看电脑屏幕，暂时摆脱紧张节奏，回归轻松简单的生活,不做电子产品的奴隶。\r\n★全书手绘而成，图案精致复杂，风格唯美清新。取材自经典童话《爱丽丝梦游仙境》，更加灵动，富有生机。很好适合亲子活动，边读故事边涂色，同时快来找找看华丽线条中隐藏着小惊喜。\r\n★为了方便读者创作，本书采用140g金太阳胶，......',4,'1639401460558.jpg'),
(45,'把精彩寄给你',64,'见设计之美，享心意之暖。\r\n\r\n遇见一枚心仪的明信片，就遇见了一个新的世界。\r\n\r\n让明信片触摸指掌的温度，带着这样的温暖放到另一个人的手里。\r\n\r\n你见多一些明信片，天南海北，却错过了更多的精彩，于是，以这样一本书的形式盛放在你的眼前。去看见更多的，去寻找你喜欢的，那些薄薄的身躯充满了新鲜的活力，与设计者们意蕴相通，与寄送者们心意相连，每一张，都是满满的承载。让丫们，带你进入一个更加广阔的世界。\r\n\r\n经历时光雕刻的千挑万选；\r\n\r\n你没有遇见过的千姿百态；\r\n\r\n充满创意灵感的千变万化；\r\n\r\n那些跨越时空的千山万水；\r\n\r\n都在这浓缩了千言万语的小小纸片上，慢慢浸润进了千家万户。\r\n\r\n用眼睛去品味，与你重温握笔的心动。',4,'1639401547248.jpg'),
(46,'佐藤大的设计减法',50.6,'佐藤大用诙谐幽默的语言，将获奖无数的nendo的设计理念、创意灵感和推进工作的方法一举公开，并以插图+注文的形式讲述了nendo背后的点点滴滴。你会发现，没有哪一本设计书可以这样使你颔首称赞，又开怀大笑！ 无论你是讲究设计方法的设计师，还是喜欢nendo作品的粉丝，抑或是想了解在全球工作的“空中飞人”背后故事的人士，阅读本书，你就可以了解nendo的世界观和设计，以及它为何在海内外如此广受欢迎和追捧。',4,'1639401608173.jpg'),
(47,'光透过缝隙.散落在水彩里',29.95,'水彩着色没技巧，浓淡晕染一团糟。 遮罩留白头都大，一看成果全白画。 涂色苦练终变好，光影无神又烦恼。 大师经验全收纳，搭配视频不再怕。 在本书中，你能收获什么？ 1、专业工具解析：纸、笔、颜料、裱纸大比拼，按需挑选很贴心。 2、水彩绘制技巧：遮罩、晕染 叠加，萌新基础练习，上手快。 3、色彩调试 搭配：颜色短缺难下手？自己动手调成功。\r\n\r\n水彩着色没技巧，浓淡晕染一团糟。\r\n\r\n遮罩留白头都大，一看成果全白画。\r\n\r\n涂色苦练终变好，光影无神又烦恼。\r\n\r\n大师经验全收纳，搭配视频不再怕。',4,'1639401662639.jpg'),
(48,'从异教徒到基督徒',29.4,'本书主要记述了一代国学大师林语堂的生平及信仰之旅，是解读林语堂的珍贵资料。正文部分是林语堂多年探求宗教经验的记录，记述其在信仰上的探险、疑难和迷惘，与其他哲学和宗教的磋研，对往圣先哲言论的探讨等。附录部分包括“林语堂自传”和“八十自叙”，主要记述了林语堂的生平经历，信笔挥洒，豁达、从容的智者形象跃然纸上。',4,'1639401714040.jpg'),
(49,'资治通鉴全套',118,'全书共294卷，记述了上至战国周威烈王二十三年，下至五肛全周世宗显德六年共1362年间的史事。司马光在史学方百造诣极深，从发起凡例到拟定提纲都亲自动手，再加上协助他修书的刘恕、范祖禹等都是当时有名的史学家国。书稿编成后，又反复校订，数次删增，最后才定稿成书。《资治通鉴》体例严整，网罗宏富，取材丰富的历史资料，对了解中国古代的政治、军事、经济、文化等历史状况有极高的参考价值，同时也对现今执政实务和经济实践等各行各业都有重要的指导意义。《四库全书总目》称之为：“前古未有”之书，梁启超誉之为“天地一大文”，一代伟人毛泽东也曾阅读圈点《资治通鉴》达十七遍之多。司马光之后，后世史学家甚至形成了专门的“通鉴学”、面《资治通鉴》也一直被后人推崇。',2,'1639401843347.jpg'),
(50,'山海经',156,'对《山海经》一书记载，最早见于司马迁《史记·大宛传》。\r\n古代典籍中首次明确指出《山海经》的作者是在西汉刘秀的《上山海经表》中，刘秀认为《山海经》是上古治水的大禹、伯益。\r\n在《吴越春秋》中：“禹巡行四渎，与益、夔共谋，行到名山大泽，招其神而问之：山川脉理金玉所有鸟兽昆虫之类，及八方之民族，殊国异域土地里数。使益疏而记之，命曰《山海经》。”\r\n其后，东汉时期的王充、赵晔等也都在其著作中将《山海经》的作者定为伯益，在流传过程中，经后人增删修改。\r\n明代学者胡应麟《少室山房笔丛》载：“战国好奇之士，本《穆天子传》之文与事，而奢侈大博级之，杂傅以汲冢、纪年之异闻，周书、王会之诡物，离骚、天问之遐旨，南华郑花圃之寓言，以成此书”。\r\n清朝毕沅在总结前代诸家研究成果的基础上，进而提出《山经》是大禹、伯益创作，《海外经》、《海内经》为秦人所作，《大荒经》则在刘秀修订时产生。',2,'1639401955538.jpg'),
(51,'史记资治通鉴孙子兵法',62.8,'他受吴王阖闾重用，在吴国为将，辅助吴治军强国，为吴王的霸业作出了贡献 。. 《孙子兵法》的内容，包括对战争、军队的基本问题的论述和战略、策略、作战原则、方法等。. 它深刻地指出了战争与政治、经济的关系，提出决定战争胜负的五个基本因素是政治、天时、地利、将帅、法制，而首要的是政治因素；它提出许多杰出的命题，如\"知彼知己，百战不殆\",\"攻其无备，出其不意\",\"不战而屈人之兵，善之善者也\"等，许多问题上反映了战争的一般规律，不仅为中国历代兵家所重视，也为各国军事家所重视。.',2,'1639402078745.jpg'),
(52,'Python语言程序设计',59.5,'本书以问题驱动的方式对Python程序设计进行综合介绍。全书共分三个部分，首先介绍基本程序设计技术以及数据类型、变量、常量、简单函数等内容，随后利用抽象、封装和多态性来了解面向对象程序设计，很后通过对数据结构和算法的讲解，循序渐进，掌握Python程序设计。书中引入实例解释基本概念，同时提供大量不同难度的编程题，帮助读者理解和巩固。本书可作为高等院校相关专业的基础教材，也可作为Python语言及编程开发爱好者的参考资料。',3,'1639402139658.jpg'),
(53,'高等数学',33.2,'《高等数学》是面向文科类专业学生的高等数学课程教材。 \r\n\r\n　　《高等数学》由三部分组成，分别是微积分、线性代数初步和概率统计初步，内容涉及实数与函数、极限与连续、导数与应用、不定积分、定积分、矩阵、线性方程组、随机事件概率、随机变量及其概率分布、数理统计简介等。 \r\n\r\n　　《高等数学》力求内容的广度和深度合理结合，数学文化和数学知识有机融合，在介绍数学知识的同时，强调培养学生的数学思维能力，引导学生体会数学文化和数学思想。',3,'1639402188254.jpg'),
(54,'偏微分方程',33,'本书是与“爱课程”网上厦门大学谭忠教授主讲的“偏微分方程MOOC”配套使用的教材。全书通过介绍偏微分方程产生的历史源头问题以及在当今世界的应用，使学生感受课程的理论价值和实际应用，主要内容包括现象与偏微分方程建模，偏微分方程一般概论，求解波动方程的柯西问题 (达朗贝尔公式)，分离变量法，傅里叶变换法，能量方法、极值原理与格林函数法。全书纸质内容与数字课程一体化设计，紧密配合。数字课程包含微视频、PPT 课件等内容，为学生的学习提供思维与探索的空间。本书可作为数学类各专业本科生的偏微分方程教材或参考书，也可供相关科技工作者参考使用。',3,'1639402226926.jpg'),
(55,'线性代数 高等教育出版社',23.9,'本书是根据教育部高等学校大学数学课程教学指导委员会制定的《大学数学课程教学基本要求》（2014年版）和教育部考试中心制定的“全国硕士研究生入学统一考试数学考试大纲”以及各学科专业对线性代数的基本要求，并结合作者多年的教学经验编写的。本书分为行列式、矩阵及其运算、向量组、线性方程组、相似矩阵与二次型等五章。每章配有应用实例和用MATLAB进行计算的简单例子。全书结构严谨、条理清楚、语言通俗易懂、论述简明扼要、例题与习题难度适中且题型丰富。本书纸质内容与数字化资源一体化设计，紧密配合。数字课程涵盖微视频、电子教案、概念解析、典型例题分析、方法总结、背景阅读、自测题等栏目，在提升课程教学效果的同时，为学生学习提供思维与探索的空间，便于学生\r\n\r\n自主学习。\r\n\r\n本书可作为高等学校非数学类专业的线性代数教材，也可作为科技工作者学习线性代数知识的参考书。',3,'1639402288198.jpg'),
(56,'仪器分析',32.5,'本书共分为十五章，主要内容包括：绪论；光谱分析法概论；紫外-可见分光光度法；分子荧光分光光度法；原子吸收分光光度法；红外分光光度法；核磁共振氢谱；核磁共振碳谱和二维核磁共振谱等',3,'1639402338046.jpg');

/*Table structure for table `booktype` */

DROP TABLE IF EXISTS `booktype`;

CREATE TABLE `booktype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `msg` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `booktype` */

insert  into `booktype`(`id`,`name`,`msg`) values 
(1,'人文类','人文'),
(2,'历史类','历史'),
(3,'科技类','科技'),
(4,'艺术类','艺术');

/*Table structure for table `cart` */

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
  `bookid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  PRIMARY KEY (`bookid`,`userid`),
  KEY `userid` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `cart` */

insert  into `cart`(`bookid`,`userid`,`num`) values 
(1,7,1),
(1,8,1),
(1,11,4),
(2,2,11),
(2,3,9),
(3,7,1),
(3,11,5),
(6,11,2),
(8,11,1),
(19,7,4),
(37,16,7),
(43,16,1),
(45,16,3),
(49,16,1);

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  `state` varchar(20) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sumprice` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `goods` */

insert  into `goods`(`id`,`bookid`,`userid`,`num`,`state`,`time`,`sumprice`) values 
(1,10,7,3,'已提交','2021-12-07 22:58:47',NULL),
(2,10,7,3,'已提交','2021-12-07 23:06:22',NULL),
(3,10,7,3,'已提交','2021-12-07 23:06:36',NULL),
(4,10,7,3,'已提交','2021-12-07 23:07:36',NULL),
(5,10,7,3,'已提交','2021-12-07 23:10:03',NULL),
(6,10,7,3,'已发货','2021-12-08 17:39:24',NULL),
(7,10,7,3,'已提交','2021-12-07 23:10:40',NULL),
(8,1,7,39,'已提交','2021-12-07 23:11:12',NULL),
(9,6,7,36,'已提交','2021-12-07 23:13:42',NULL),
(10,9,7,4,'已提交','2021-12-07 23:13:42',NULL),
(11,1,7,2,'已提交','2021-12-07 23:14:40',NULL),
(12,2,7,7,'已提交','2021-12-07 23:14:40',NULL),
(13,1,7,6,'已提交','2021-12-07 23:20:12',NULL),
(14,1,7,0,'已提交','2021-12-08 16:46:26',0),
(15,8,7,3,'已提交','2021-12-08 16:47:41',135.89999999999998),
(16,5,7,1,'已提交','2021-12-08 17:25:27',22.7),
(17,3,15,4,'已提交','2021-12-11 09:15:01',315.2),
(18,54,16,2,'已提交','2021-12-13 21:37:43',66),
(19,56,16,2,'已提交','2021-12-13 21:37:43',65),
(20,52,16,1,'已提交','2021-12-13 21:37:50',59.5),
(21,53,16,8,'已提交','2021-12-13 21:37:50',265.6),
(22,56,16,1,'已提交','2021-12-16 14:34:11',32.5);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `power` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`,`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=gbk;

/*Data for the table `user` */

insert  into `user`(`id`,`userName`,`password`,`power`) values 
(16,'zhu','zhu',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
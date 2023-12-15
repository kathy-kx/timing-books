CREATE table t_book(
	`id` INT PRIMARY KEY auto_increment,
	`name` VARCHAR(100),
	`price` DECIMAL(11,2),
	`author` VARCHAR(100),
	`sales` int,
	`stock` int,
	`img_path` VARCHAR(200)
);
DESC t_book;



#插入初始化数据 
INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '富爸爸穷爸爸', '罗伯特清崎',44.5, 800, 59, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '被讨厌的勇气', '岸健一郎',27.5, 10000, 108, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '朝花夕拾', '鲁迅',17.1, 8000, 77, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '白夜行', '东野圭吾',29.8, 5000, 79, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '没头脑和不高兴', '任溶溶',12.4, 3000, 39, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '西游记', '吴承恩',49.35, 1000, 49, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '水浒传', '施耐庵',61.85, 500, 38, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '城南旧事', '林海音',13.8, 1000, 67, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '雾都孤儿', '查尔斯狄更斯', 23.2, 300, 29, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '罗生门', '芥川龙之介', 29.9, 308, 86, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '飘', '玛格丽特米切尔', 56.9, 103, 29, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '边城', '沈从文', 19.7, 530, 129, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '简爱', '夏洛蒂勃朗特', 19.9, 94, 39, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '金银岛', '史蒂文森', 14, 8, 3, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '三体', '刘慈欣', 37.85, 6087, 432, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '八次危机', '温铁军', 37, 1094, 376, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '爱的教育', '亚米契斯', 14, 1034, 237, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '思维方式', '稻盛和夫', 18, 104, 9, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '局外人', '加缪', 19.8, 4, 6, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '当生命陷落时', '佩玛丘卓', 19.8, 4, 7, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '夏洛的网', '怀特', 25.5, 1084, 434, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '活着', '余华', 31, 3004, 79, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '围城', '钱钟书', 17.5, 2048, 173, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '查理和巧克力工厂', '罗尔德达尔', 8.8, 2024, 297, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '杀死一只知更鸟', '哈珀李', 28.8, 1014, 237, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '晚熟的人', '莫言',29.5, 1987, 364, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '俗世奇人', '冯骥才',10.44, 1724, 271, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '在细雨中呼喊', '余华', 27.2, 985, 182, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '绝叫', '叶真中显', 29, 592, 85, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '简谱视唱', '赵方幸', 12.6, 792, 251, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '射雕英雄传', '金庸', 88, 421, 91, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '理想国', '柏拉图', 28, 462, 79, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '苏格拉底的申辩', '柏拉图', 29.4, 420, 51, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '消失的13级台阶', '宫部美雪', 21, 110, 37, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '赎罪', '伊恩麦克尤恩', 29.4, 102, 49, 'static/img/default.jpg');

INSERT INTO t_book(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`)
VALUES(null, '如何阅读一本书', '艾德勒', 37.5, 640, 83, 'static/img/default.jpg');

SELECT * FROM t_book;

SELECT * FROM t_book LIMIT 0,4;

SELECT * from t_book where price BETWEEN 10 and 20 ORDER BY `price` LIMIT 0,4;

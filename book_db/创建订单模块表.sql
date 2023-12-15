use book;

CREATE TABLE t_order(
	`order_id` VARCHAR(50) PRIMARY KEY,
	`create_time` datetime, #年月日时分秒
	`price` DECIMAL(11,2),
	`status` int,
	`user_id` int,
	FOREIGN KEY(`user_id`) REFERENCES t_user(`id`)
);

CREATE TABLE t_order_item(
	`id` int PRIMARY KEY auto_increment,
	`name` VARCHAR(100),
	`count` INT,
	`price` DECIMAL(11,2),
	`total_price` DECIMAL(11,2),
	`order_id` VARCHAR(50),
	FOREIGN KEY(`order_id`) REFERENCES t_order(`order_id`)
	);
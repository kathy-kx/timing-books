DROP DATABASE IF EXISTS book;

create database book;
use book;

create table t_user(
`id` INT PRIMARY KEY auto_increment,
`username` VARCHAR(20) NOT NULL UNIQUE,
`password` VARCHAR(32) not null,
`email` VARCHAR(200)
);
ALTER TABLE t_user MODIFY `username` VARCHAR(20) NOT NULL UNIQUE;

INSERT INTO t_user(`username`,`password`,`email`)
VALUES ('admin','admin','admin@qq.com');

SELECT * FROM t_user;

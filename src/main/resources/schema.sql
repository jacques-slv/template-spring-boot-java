
DROP TABLE IF EXISTS user;
CREATE TABLE user(
id int NOT NULL AUTO_INCREMENT,
role char(30) not null,
username char(30) not null,
firstname char(30) not null,
lastname char(30) not null,
PRIMARY KEY (id));

DROP TABLE IF EXISTS product;
CREATE TABLE product (
id int NOT NULL AUTO_INCREMENT,
name char(30) not null,
price numeric(10, 2),
description char(255),
PRIMARY KEY (id)
);





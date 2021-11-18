CREATE TABLE USER(id int not null identity primary key, role varchar(64) not null, user_name varchar not null, first_name varchar not null, last_name varchar not null);

CREATE TABLE PRODUCT (id int not null identity primary key, name varchar not null, price numeric(10, 2), description varchar);




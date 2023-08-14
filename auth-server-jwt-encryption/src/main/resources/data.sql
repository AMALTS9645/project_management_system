drop table if exists user_db CASCADE;
create table user_db (id bigint not null,password varchar(255),role varchar(255),username varchar(255),primary key(id));

insert into user_db values(101,'pass1234','USER','rahul');
insert into user_db values(102,'amal1234','ADMIN','amal');
insert into user_db values(103,'akshara1234','USER','akshara');
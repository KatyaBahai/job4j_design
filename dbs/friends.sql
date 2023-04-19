create table friends(
id serial primary key,
name text,
job varchar,
age integer
);
insert into friends(name, job, age) values ('Chandler', 'data analyst', 32);
select * from friends;
update friends set age = 33;
select * from friends;
delete from friends;
update friends set age = 33;
select * from friends;
delete from friends;
select * from friends;
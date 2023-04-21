create table town(
id serial primary key,
name text,
population int
);

create table person(
id serial primary key,
name varchar(255),
hometown int references town(id)
);

insert into town(name, population) values('Kazan', 1200000);
insert into town(name, population) values('Penza', 600000);
insert into town(name, population) values('Saransk', 350000);

insert into person(name, hometown) values('Vadim', 1);
insert into person(name, hometown) values('Elena', 2);
insert into person(name, hometown) values('Zhenya', 1);
insert into person(name, hometown) values('Zina', 3);
insert into person(name, hometown) values('Igor', 3);

select * from person;
select * from town where id in (select hometown from person);
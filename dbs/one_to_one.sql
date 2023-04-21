create table husband(
id serial primary key,
name varchar(255)
);

create table wife(
id serial primary key,
name varchar(255)
);

create table family(
id serial primary key,
	wife_id int references wife(id) unique,
	husband_id int references husband(id) unique
);

insert into husband(name) values('Boris');
insert into husband(name) values('James');
insert into husband(name) values('Jiovanni');

insert into wife(name) values('Elena');
insert into wife(name) values('Ruth');
insert into wife(name) values('Alessandra');

insert into family(husband_id, wife_id) values(1, 1);
insert into family(husband_id, wife_id) values(2, 2);
insert into family(husband_id, wife_id) values(3, 3);

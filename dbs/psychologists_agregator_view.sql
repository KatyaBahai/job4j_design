create table types(
id serial primary key,
name varchar(255)
);

create type therapist_gender as enum('female', 'male');

create table psychologists(
id serial primary key,
name varchar(255),
age int,
gender therapist_gender 
);

create table psychologists_types(
id serial primary key,
type_id int references types(id),
psy_name_id int references psychologists(id)
);

create table clients(
id serial primary key,
name varchar(255),
psychologist_id int references psychologists(id)
);

insert into types(name) 
values('cognitive'), ('geshtalt'), ('psychoanalisis'), ('positive'), ('behavioristic');

insert into psychologists(name, age, gender)
values('Ivan', 30, 'male'), ('Sonay', 36, 'female'), ('Ada', 28, 'female'), 
('Fedor', 45, 'male'), ('Anton', 50, 'male'), ('Emma', 37, 'female');

update psychologists
set age = 37
where id = 1;

insert into psychologists_types(type_id, psy_name_id)
values(1, 1), (1, 2), (2, 1), (3, 2), (4, 3), (5, 4);

insert into clients(name, psychologist_id)
values('AEE', 1), ('FBF', 2), ('ERT', 3), 
('YUI', 4), ('TGB', 5), ('DFG', 3), 
('WER', 3), ('DFG', 2), ('YUJ', 1), ('BNM', 5);

create view psychologists_filter as 
select p.name from psychologists_types pt 
left join types t on t.id = pt.type_id
left join psychologists p on p.id = pt.psy_name_id
where p.age >= 35 and 
t.name = 'geshtalt'; 

select * from psychologists_filter;


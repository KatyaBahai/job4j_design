create type gender as enum('female', 'male', 'other');

create table teens(
id serial primary key,
name varchar(255),
gender gender
);

insert into teens(name, gender) 
values('Victoria', 'female'), ('Sasha', 'female'), ('Edward', 'male'), ('Marshall', 'male'), 
('Maria', 'female'), ('Leonid', 'male'), ('Victor', 'male'), ('Sava', 'male');

select * from teens where gender = 'female';

select girls.name girl, boys.name boy 
from teens girls
cross join teens boys 
where girls.gender = 'female' and boys.gender = 'male';
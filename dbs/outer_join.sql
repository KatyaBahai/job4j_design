create table departments(
id serial primary key,
name varchar(255)
);

create table employees(
id serial primary key,
name varchar(255),
department int references departments(id)
);

insert into departments(name) 
values('animation'), ('art'), ('programmers'), ('sound');
insert into departments(name)
values('devops'), ('testing');

insert into employees(name, department) 
values('Denis', 1), ('James', 2), ('Arianna', 3), ('Emma', 3), ('Sam', 3), ('Henry', 4);
insert into employees(name)
values('Sabrina'), ('George');

select d.name, e.name 
from departments d left join employees e
on d.id = e.department;

select d.name, e.name 
from departments d right join employees e
on d.id = e.department;

select d.name, e.name
from departments d full join employees e
on d.id = e.department;

select d.name, e.name
from departments d join employees e
on d.id = e.department;

select d.name, e.name 
from departments d left join employees e
on d.id = e.department
where e.name is null;

select e.name, d.name 
from departments d left join employees e
on d.id = e.department;
select e.name, d.name 
from employees e right join departments d
on d.id = e.department;





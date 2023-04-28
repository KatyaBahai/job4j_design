create table types(
id serial primary key,
name varchar(255)
);

create table products(
id serial primary key,
name varchar(255),
type_id int references types(id),
expiration_date date,
price float
);

insert into types(name) 
values ('cheese'), ('meat'), ('ice cream'), ('milk');

insert into products(name, type_id, expiration_date, price) 
values
('cheddar', 1, '2024-05-09', 450), ('emmental', 1, '2023-12-09', 550), ('colber jack', 1, '2023-05-30', 400), 
('lamb', 2, '2023-04-09', 500), ('pork', 2, '2023-04-30', 550), ('beaf', 2, '2023-05-01', 350), 
('Barack Obama', 3, '2023-10-09', 65), ('Lakomka', 3, '2023-09-09', 70), ('Cookie dough', 3, '2022-05-30', 110), 
('milk 2,5%', 4, '2023-05-09', 80), ('milk 6%', 4, '2023-04-09', 100), ('skim milk', 4, '2023-05-30', 90);

update products
set name = 'Lakomka ice cream'
where price = 70;

select * from products where type_id in (select id from types where name = 'cheese');
select * from products where name like '%ice cream%';
select name, expiration_date from products where expiration_date < current_date; 

select name
from products
where price = (select max(price) from products)
group by name;

select t.name as "Type of product", string_agg(p.name, ', ') as "Products" 
from products p 
join types t on t.id = p.type_id
group by t.name;

select t.name, count(p.name) 
from products p 
join types t on t.id = p.type_id
group by t.name;

select p.name, t.name
from products p join types t on p.type_id = t.id
where t.name = 'cheese' or t.name = 'milk';

select t.name
from products p 
join types t on p.type_id = t.id 
group by t.name 
having count(t.name) < 10;

select p.name, t.name 
from products p 
join types t on p.type_id = t.id
order by p.id;
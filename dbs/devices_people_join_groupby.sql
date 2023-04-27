create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('smartphone Samsung', 16000), ('smartphone Apple', 70000), 
('ipod', 30000), ('laptop Honor', 45000), ('laptop Lenovo', 50000);

insert into people(name) values ('Emma'), ('Andrew'), ('Sonya'), ('Emma'), ('Jordan');

insert into devices_people(device_id, people_id) values (1, 2), (1, 3), (1, 4);
insert into devices_people(device_id, people_id) values (2, 1);
insert into devices_people(device_id, people_id) values (3, 1), (3, 2), (3, 3), (3, 5);
insert into devices_people(device_id, people_id) values (4, 1), (4, 2), (4, 3), (4, 5);
insert into devices_people(device_id, people_id) values (5, 1), (5, 2), (5, 3), (5, 5);

select avg(price) 
from devices d 
join devices_people dp 
on d.id = dp.device_id;

select p.name, avg(price) 
from devices_people dp join people p on p.id = dp.people_id 
join devices d on d.id = dp.device_id
group by p.name;

select p.name, avg(price) 
from devices_people dp join people p on p.id = dp.people_id 
join devices d on d.id = dp.device_id
group by p.name
having avg(price) > 40000;
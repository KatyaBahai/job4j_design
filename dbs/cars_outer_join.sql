create table car_bodies(
id serial primary key,
name varchar(255)
);

create table car_engines(
id serial primary key,
name varchar(255)
);

create table car_transmissions(
id serial primary key,
name varchar(255)
);

create table cars(
id serial primary key,
name varchar(255),
body_id int references car_bodies(id),
engine_id int references car_engines(id),
transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values('sedan'), ('hatchback'), ('pickup'), ('convertible');
insert into car_engines(name) values('four-cylinder'), ('boxer-four'), ('straight-six'), ('V6');
insert into car_transmissions(name) values('manual'), ('automatic');

insert into cars(name, body_id, engine_id, transmission_id) 
values('red car', 1, 1, 1), ('small car', 2, 2, 1), ('black car', 3, 3, 1), ('big car', 1, 3, 1);

insert into cars(name, body_id, engine_id)
values('red car', 1, 1);

select cars.id id, cars.name car_name, cb.name body_name, ce.name engine_name, ct.name transmission_name
from cars
left join car_bodies cb on cb.id = cars.body_id
left join car_engines ce on ce.id = cars.engine_id
left join car_transmissions ct on ct.id = cars.transmission_id;

select cb.name 
from cars c right join car_bodies cb
on cb.id = c.body_id
where c.name is null;

select ce.name 
from cars c right join car_engines ce
on ce.id = c.engine_id
where c.name is null;

select ct.name 
from cars c right join car_transmissions ct
on ct.id = c.transmission_id
where c.name is null;

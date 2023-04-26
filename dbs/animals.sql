create table fauna(
id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values('Tiger', 10000, null);
insert into fauna(name, avg_age, discovery_date) values('Himalayan thrush', 1200, '2016-01-01');
insert into fauna(name, avg_age, discovery_date) values('Okapi ', 6200, '1901-01-01');
insert into fauna(name, avg_age, discovery_date) values('Red kangaroo Osphanter rufus', 3000, '1822-01-01');
insert into fauna(name, avg_age, discovery_date) values('Crucian carp fish', 3600, null);

select * from fauna where name like '%fish%';
select name, avg_age from fauna where avg_age >= 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';
select * from fauna order by discovery_date asc;
select * from fauna order by avg_age desc;
select * from fauna order by id;
select distinct discovery_date from fauna;
select * from fauna limit 3;
select name, avg_age from fauna;

create table dance_school(
id serial primary key,
name varchar(255)
);

create table styles(
id serial primary key,
name text
);

create table style_dance_school(
id serial primary key,
style_id int references styles(id),
school_id int references dance_school(id)
);


insert into dance_school(name) values('DanceHouse');
insert into dance_school(name) values('Studio_25.5');
insert into dance_school(name) values('Full_Swing');

insert into styles(name) values('tango');
insert into styles(name) values('strip_plastic');
insert into styles(name) values('high_heels');
insert into styles(name) values('belly-dance');

insert into style_dance_school(style_id, school_id) values(1, 1);
insert into style_dance_school(style_id, school_id) values(3, 1);
insert into style_dance_school(style_id, school_id) values(1, 2);
insert into style_dance_school(style_id, school_id) values(2, 2);
insert into style_dance_school(style_id, school_id) values(1, 3);
insert into style_dance_school(style_id, school_id) values(2, 3);
insert into style_dance_school(style_id, school_id) values(3, 3);
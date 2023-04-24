create table role(
id serial primary key,
name text
);

create table category(
id serial primary key,
name text
);

create table rule(
id serial primary key,
name text
);

create table state(
id serial primary key,
name text
);

create table users(
id serial primary key,
name text,
role_id int references role(id)
);

create table role_rule(
id serial primary key,
name text,
role_id int references role(id),
rules_id int references rule(id)
);

create table item(
id serial primary key,
name text,
users_id int references users(id),
category_id int references category(id),
state_id int references state(id)
);

create table attachment(
id serial primary key,
name text,
item_id int references item(id)
);

create table comment(
id serial primary key,
name text,
item_id int references item(id)
);

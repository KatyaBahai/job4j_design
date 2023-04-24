insert into role(name) values('administrator');
insert into role(name) values('user');
insert into role(name) values('guest');

insert into category(name) values('high priority');
insert into category(name) values('low priority');
insert into category(name) values('urgent');

insert into rule(name) values('read a file');
insert into rule(name) values('do anything');
insert into rule(name) values('download a file');

insert into role_rule(role_id, rules_id) values(1, 2);
insert into role_rule(role_id, rules_id) values(2, 1);
insert into role_rule(role_id, rules_id) values(2, 3);
insert into role_rule(role_id, rules_id) values(3, 1);

insert into state(name) values('error');
insert into state(name) values('working');
insert into state(name) values('idle');

insert into users(name, role_id) values('Sabrina', 1);
insert into users(name, role_id) values('Byron', 2);
insert into users(name, role_id) values('Eric', 2);

insert into item(name, users_id, category_id, state_id) values('post1', 1, 2, 3);
insert into item(name, users_id, category_id, state_id) values('post2', 1, 1, 3);
insert into item(name, users_id, category_id, state_id) values('post3', 3, 2, 1);

insert into attachment(name, item_id) values('file1', 2);
insert into attachment(name, item_id) values('file2', 2);
insert into attachment(name, item_id) values('file3', 3);

insert into comment(name, item_id) values('comment1', 3);
insert into comment(name, item_id) values('comment2', 3);
insert into comment(name, item_id) values('comment3', 1);


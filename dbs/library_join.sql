create table genres(
id serial primary key,
genre varchar(255) 
);

create table writers(
id serial primary key,
name varchar(255),
country varchar(255)
);

create table books(
id serial primary key,
name varchar(255),
number_of_pages int,
author int references writers(id),
genre int references genres(id)
);

insert into genres(genre) values('detective');
insert into genres(genre) values('sci-fi');
insert into genres(genre) values('fantasy');

insert into writers(name, country) values('J.K.Rowling', 'Great Britain');
insert into writers(name, country) values('Arkady and Boris Strugatsky', 'Russia');
insert into writers(name, country) values('Agatha Christie', 'Great Britain');
insert into writers(name, country) values('Stephen King', 'the USA');

insert into books(name, number_of_pages, author, genre) 
values('Firestarter ', 426, 4, 2);
insert into books(name, number_of_pages, author, genre) 
values('Murder on the Orient Express', 256, 3, 1);
insert into books(name, number_of_pages, author, genre) 
values('Harry Potter and the Sorcerer"s stone', 223, 1, 3);
insert into books(name, number_of_pages, author, genre) 
values('Harry Potter and the Chamber of Secrets', 251, 1, 3);
insert into books(name, number_of_pages, author, genre) 
values('Harry Potter and the Prisoner of Azkaban', 317, 1, 3);
insert into books(name, author) 
values('Hard to Be a God', 2);
insert into books(name, author) 
values('Monday Begins on Saturday', 2);


select w.name as "Author", w.country as "Country of origin", b.name as "Book name" from writers as w
join books as b on b.author = w.id;

select b.name, b.number_of_pages, g.genre from books as b
join genres as g on b.genre = g.id;

select b.name as "Название книги", w.name "Автор", g.genre as "Жанр" from books as b 
join  writers as w on b.author = w.id
join genres as g on g.id = b.genre;

select b.name as "Book", w.name as "Author" from writers as w
join books as b on b.author = w.id;




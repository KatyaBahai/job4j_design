create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function tax_sample()
    returns trigger 
	as
$$
    BEGIN
        NEW.price = NEW.price * 1.2;
        return new;
    END;
$$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS tax_trigger ON products;
CREATE TRIGGER tax_trigger
  BEFORE INSERT
  ON products
  FOR EACH ROW
  EXECUTE PROCEDURE tax_sample();

insert into products(name, producer, count, price) 
values('lego house', 'lego company', 100, 3500), ('lego castle', 'lego company', 100, 4000), 
('lego Hogwarts', 'lego company', 100, 6000), ('lego shop', 'lego company', 100, 3000);

select * from products;

create or replace function tax_after()
	returns trigger as
	$$
		begin
		update products
		set price = price * 1.2
		where id = (select id from inserted);
		return new;
		end;
	$$
language 'plpgsql';

create trigger set_tax
	after insert on products
	referencing new table as inserted
	for each statement
	execute procedure tax_after();
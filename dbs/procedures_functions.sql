create or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
    insert into products (name, producer, count, price)
    values (i_name, prod, i_count, i_price);
    END
$$;

create or replace procedure p_delete_data()
language plpgsql 
as $$
		begin
		delete from products 
		where count = 0;
		end
$$;

call insert_data('puzzle', 'puzzle&co', 0, 1000);
select * from products;
call p_delete_data();
select * from products;

create or replace function f_delete_data(f_id integer)
returns integer
language plpgsql
as
$$
	declare 
	deleted integer;
	begin
		delete from products 
		where id = f_id;
		select into deleted id from products where id = f_id;
		return deleted;
	end;
$$;

call insert_data('coloring book', 'books&co', 200, 1000);
call insert_data(' roly-poly', 'Matryoshka&co', 50, 10000);
select * from products;
select f_delete_data(1); 
select * from products;
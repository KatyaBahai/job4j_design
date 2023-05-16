CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

INSERT INTO customers(first_name, last_name, age, country) 
VALUES ('Ivan', 'Alekseev', 37, 'Hungary'), ('Egor', 'Zyryanov', 32, 'Russia'), 
('Ida', 'Efimova', 30, 'Russia'), ('Ivan', 'Grey', 37, 'Russia');

INSERT INTO orders(amount, customer_id) 
VALUES (20, 1), (20, 1), (240, 2), (80, 3), (12, 3), (82, 3), (55, 1), (10, 2);

INSERT INTO customers(first_name, last_name, age, country) 
VALUES ('Aleksandr', 'Alekseev', 30, 'France');

SELECT * FROM customers 
GROUP BY id
HAVING age = (SELECT min(age) FROM customers);

SELECT * FROM customers
WHERE id NOT IN (SELECT customer_id FROM orders);

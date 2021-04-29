BEGIN;

SET search_path TO products;

DROP TABLE IF EXISTS manufacturers CASCADE;
CREATE TABLE manufacturers (id serial PRIMARY KEY, title VARCHAR(255));
INSERT INTO manufacturers (title) VALUES ('iDidAss'), ('Reboke'), ('Nuke'), ('Rita');


DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (
    id serial PRIMARY KEY,
    title VARCHAR(255),
    price numeric(8, 2),
    manufacturer_id int,
    FOREIGN KEY (manufacturer_id) REFERENCES manufacturers(id)
);
INSERT INTO products (title, manufacturer_id, price)
VALUES ('Trousers', 1, 20.00),
       ('Shoes', 1, 60.00),
       ('Skechers', 4, 100.00),
       ('T_Shirt', 4, 10.00),
       ('Hoodie', 2, 50.00),
       ('Woodie', 2, 55.00),
       ('Chute', 3, 1000.00),
       ('Swimming Hat', 3, 10.00);


DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE customers (id serial PRIMARY KEY, name VARCHAR(255));
INSERT INTO customers (name) VALUES ('Athos'), ('Portos'), ('Aramis'), ('Dart Agnan');


DROP TABLE IF EXISTS purchases CASCADE;
CREATE TABLE purchases (
    id serial PRIMARY KEY,
    customer_id int,
    product_id int,
    price numeric(8, 2),
    FOREIGN KEY (customer_id) REFERENCES customers(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);
INSERT INTO purchases (customer_id, product_id, price)
VALUES (1, 1, 25.00),
       (2, 3, 100.00),
       (3, 5, 55.00),
       (4, 7, 990.00),
       (4, 4, 15.00);

COMMIT;
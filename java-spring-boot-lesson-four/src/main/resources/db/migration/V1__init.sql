DROP TABLE IF EXISTS products;
CREATE TABLE products (
                          id SERIAL PRIMARY KEY,
                          title VARCHAR(255) UNIQUE NOT NULL,
                          description TEXT,
                          price BIGINT
);

INSERT INTO products (title, description, price)
VALUES
('MacBook', 'Ultra low and Great Power', 3000),
('iPhone', 'The most expensive phone by credit', 1000),
('iPad', 'More size - more cost', 1500),
('iMac', 'More size - more cost', 4000),
('MacBook Pro', 'One usb port is enough', 3500),
('MacBook Air', 'Weights like feather', 2500),
('Mac Mini', 'Notebook power at desktop size', 1500),
('LaCie drive', 'Can hold your grandmothers library', 2000),
('Leika Camera', 'Can shot flowers but costs like a car', 10000),
('Apple TV', 'Simply another online TV', 500),
('Air Pods', 'Not an ordinary earphones', 100),
('iPod', 'Old fashioned portable player', 350),
('iWatch', 'Not a Tissot but price is the same', 1250);


-- тестовая таблица

DROP TABLE IF EXISTS items;
CREATE TABLE items (
                       id SERIAL,
                       title VARCHAR(40)
);

INSERT INTO items (title)
VALUES
('stone'), ('knife'), ('spoon');
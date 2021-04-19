CREATE TABLE products.products (product_id bigserial primary key, 
					   product_name varchar(255), 
					   product_description varchar(255), 
					   product_price money);
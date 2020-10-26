DROP TABLE IF EXISTS product;

CREATE TABLE product(
	id serial not null
constraint product_pk unique ,
	name VARCHAR(128),
	slug VARCHAR(128),
	description TEXT,
	price FLOAT,
	category_id int
	);

DROP TABLE IF EXISTS category;

CREATE TABLE category(
	id serial not null
constraint category_pk unique ,
	category_name VARCHAR(128),
	slug VARCHAR(128)
	);
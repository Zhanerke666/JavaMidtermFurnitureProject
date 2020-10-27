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

DROP TABLE IF EXISTS Customer;
create table Customer(
  id serial not null
constraint  customer_pk unique,
  name VARCHAR (128),
  phone VARCHAR(128) ,
  address VARCHAR(128)
  );

DROP TABLE IF EXISTS auth;
create table auth (
  id serial not null constraint  auth_pk unique,
  customer_id bigint constraint auth_customer_id_fk references Customer(id),
  login VARCHAR (128),
  password VARCHAR (128),
  token varchar(255)
);
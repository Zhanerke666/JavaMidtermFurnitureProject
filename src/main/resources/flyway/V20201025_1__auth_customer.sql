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
  id serial not null
constraint  auth_pk unique,
  customer_id bigint constraint auth_customer_id_fk references Customer(id),
  login VARCHAR (128),
  password VARCHAR (128)
);
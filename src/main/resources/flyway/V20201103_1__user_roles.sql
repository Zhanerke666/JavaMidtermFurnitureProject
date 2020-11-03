drop table if exists user_role;

create table user_role(
    id serial not null constraint user_role_pk unique,
    user_id bigint constraint user_role_customer_id_fk references Customer(id),
    rolle varchar (255) default('customer')
)
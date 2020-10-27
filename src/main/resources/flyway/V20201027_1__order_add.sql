DROP table if exists orderr;
 create table orderr(
    id serial not null constraint order_pk unique,
    customer_id bigint constraint order_customer_id_fk references Customer(id),
    total_price float,
    order_date varchar default(32)
);

DROP table if exists order_list;
create table order_list(
    id serial not null constraint order_list_pk unique,
    product_id bigint constraint order_list_product_id_fk references Product(id),
    order_id bigint constraint order_list_order_id_fk references Orderr(id),
    quantity int,
    price float,
    total_sum float
);
create table product_specifications
(
    specification_id serial
        constraint product_specifications_pk
            primary key,
    product_id       integer      not null
        constraint product_specifications_product_product_id_fk
            references product,
    key              varchar(255) not null,
    value            varchar(255) not null
);

alter table product_specifications
    owner to postgres;



create table product_image
(
    id         serial
        constraint product_image_pk
            primary key,
    product_id integer       not null
        constraint product_image_product_product_id_fk
            references product,
    image_url  varchar(1000) not null
);

alter table product_image
    owner to postgres;


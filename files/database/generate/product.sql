create table product
(
    product_id  integer generated by default as identity
        constraint product_pk
            primary key,
    user_id     integer                                not null
        constraint product_users_user_id_fk
            references users,
    created_at  timestamp with time zone default now() not null,
    title       varchar(255)                           not null,
    description varchar(2000),
    price       double precision,
    category_id integer                                not null
        constraint product_product_category_product_category_id_fk
            references product_category,
    popularity  integer                                not null
);

alter table product
    owner to postgres;

create index idx_product_user_id
    on product (user_id);

create index idx_product_creation_date
    on product (created_at);

create index idx_product_title
    on product (title);

create index idx_product_price
    on product (price);

create index idx_product_category_id
    on product (category_id);

create index idx_product_popularity
    on product (popularity);


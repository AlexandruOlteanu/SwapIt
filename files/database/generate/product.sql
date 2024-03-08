create table product
(
    product_id  serial
        constraint product_pk
            primary key,
    user_id     integer                                not null
        constraint product_users_user_id_fk
            references users,
    created_at  timestamp with time zone default now() not null,
    title       varchar(255)                           not null,
    description varchar(2000),
    price       double precision,
    category    varchar(255)                           not null,
    subcategory varchar(255)
);

alter table product
    owner to postgres;



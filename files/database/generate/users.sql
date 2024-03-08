create table users
(
    user_id   serial
        primary key,
    username  varchar(255) not null,
    name      varchar(255),
    surname   varchar(255),
    join_date timestamp with time zone default now(),
    email     varchar(255) not null,
    password  varchar(255) not null,
    role      varchar(50)  not null
);

alter table users
    owner to postgres;



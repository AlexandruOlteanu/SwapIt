create table registration_code
(
    id         serial,
    email      varchar(256) not null,
    code       varchar(256) not null,
    created_at timestamp with time zone default now()
);

alter table registration_code
    owner to postgres;


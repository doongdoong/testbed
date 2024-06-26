create table testbed.users
(
    id           bigint auto_increment,
    name         varchar(255) not null,
    email        varchar(255) not null,
    date_created DATETIME     not null,
    date_updated DATETIME     not null,
    date_deleted DATETIME     null,
    constraint users_pk
        primary key (id)
);

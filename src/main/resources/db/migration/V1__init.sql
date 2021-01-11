CREATE TABLE products
(
    id    serial,
    title varchar(100),
    price int
);

INSERT INTO products (title, price)
VALUES ('Bread', 40),
       ('Milk', 80);

create table users
(
    id              serial,
    username        varchar(50)  not null,
    password        varchar(80)  not null,
    name            varchar(100) not null,
    email           varchar(50)  not null,
    PRIMARY KEY (id)
);

create table roles
(
    id              serial,
    name            varchar(50) default null,
    PRIMARY KEY (id)
);

create table users_roles
(
    user_id         int not null,
    role_id         int not null,

    PRIMARY key (user_id, role_id),

    constraint FK_USER_ID FOREIGN KEY (user_id)
        references users (id)
        on delete no action on update no action,

    constraint FK_ROLE_ID FOREIGN KEY (role_id)
        references roles (id)
        on delete no action on update no action
);

insert into roles (name)
values
('ROLE_USER'),('ROLE_ADMIN');

insert into users(username, password, name, email)
values
('admin', '$2a$10$gVwvIifTZGKTISkq4SHqyOWBCd.TdGJ0TtLzPGNPxABP5cp6x2nAu','Roma','roma@gmail.com'),
('user', '$2a$10$wCx2ypzy8v6IS8MIiSS/7OKaHE6fDa9hSj.iEXjRs4PWDqL1pz.Sm','Egor','egor@gmail.com');

insert into users_roles(user_id, role_id)
values
(1,1),
(1,2),
(2,1);
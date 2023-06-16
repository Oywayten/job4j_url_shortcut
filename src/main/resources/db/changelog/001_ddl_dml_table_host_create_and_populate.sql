CREATE TABLE host (
    id          bigserial PRIMARY KEY NOT NULL,
    site        varchar(256) NOT NULL UNIQUE,
    login       varchar(16),
    password    varchar(256)
);

insert into host (site, login, password)values ('job4j.ru', '111', '222');
insert into host (site, login, password)values ('yandex.ru', '333', '444');
insert into host (site, login, password)values ('google.com', '555', '666');
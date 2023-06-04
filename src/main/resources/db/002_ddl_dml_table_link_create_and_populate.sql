CREATE TABLE link
(
    id            bigserial PRIMARY KEY NOT NULL,
    path          text          NOT NULL UNIQUE,
    host_id       BIGINT                NOT NULL REFERENCES host (id),
    short_code    varchar(10)            NOT NULL UNIQUE,
    request_count bigint DEFAULT 0
);

insert into link (path, host_id, short_code)
values ('https://job4j.ru/profile/', '1', '11111111');
insert into link (path, host_id, short_code)
values ('yandex.ru/dev', '2', '22222222');
insert into link (path, host_id, short_code)
values ('https://www.google.ru/search?q=%D0%BF%D0%BE%D0%B3%D0%BE%D0%B4%D0%B0', '3', '33333333');
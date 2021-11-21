create table film
(
    id     int auto_increment primary key,
    name   varchar(128) not null,
    length smallint     not null
);

create table seance
(
    id       int auto_increment primary key,
    film_id  int            not null,
    price    decimal(19, 2) not null,
    dt_start timestamp      not null,
    constraint seance_id_film_id
        foreign key (film_id) references film (id)
            on update cascade on delete cascade
);

create index seance_id_film_id_idx
    on seance (film_id);

create table ticket
(
    id        int not null primary key,
    seance_id int not null,
    constraint ticket_id_seance_id
        foreign key (seance_id) references seance (id)
);

create index ticket_id_seance_id_idx on ticket (seance_id);


insert into film (name, length)
values ('back to future 1', 90),
       ('back to future 2', 120),
       ('back to future 3', 80),
       ('rocky 1', 60),
       ('rocky 2', 80),
       ('rocky 3', 90),
       ('rocky 4', 120),
       ('rocky 5', 70)
;


insert into seance (film_id, price, dt_start)
values (1, 200, '2021-11-20 10:00:00.000'),
       (2, 200, '2021-11-20 11:00:00.000'),
       (3, 200, '2021-11-20 12:00:00.000'),
       (4, 200, '2021-11-20 13:00:00.000'),
       (5, 200, '2021-11-20 14:00:00.000'),
       (6, 200, '2021-11-20 15:00:00.000'),
       (7, 200, '2021-11-20 16:00:00.000'),
       (8, 200, '2021-11-20 17:00:00.000'),
       (1, 200, '2021-11-20 18:00:00.000'),
       (2, 200, '2021-11-20 19:00:00.000'),
       (3, 200, '2021-11-20 20:00:00.000'),
       (4, 200, '2021-11-20 10:30:00.000'),
       (5, 200, '2021-11-20 11:30:00.000'),
       (6, 200, '2021-11-20 12:30:00.000'),
       (7, 200, '2021-11-20 13:30:00.000'),
       (8, 200, '2021-11-20 14:30:00.000');



select f.name, s.dt_start, f.length
from film f
         left join seance s on s.film_id = f.id
order by f.name, s.dt_start, f.length
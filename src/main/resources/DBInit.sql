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
    id        int auto_increment primary key,
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
values (1, 200, '2021-11-20 09:00:00.000'),
       (2, 250, '2021-11-20 10:40:00.000'),
       (3, 300, '2021-11-20 12:00:00.000'),
       (4, 350, '2021-11-20 13:30:00.000'),
       (5, 400, '2021-11-20 14:20:00.000'),
       (6, 450, '2021-11-20 16:00:00.000'),
       (7, 500, '2021-11-20 16:40:00.000'),
       (8, 550, '2021-11-20 17:00:00.000'),
       (1, 600, '2021-11-20 18:30:00.000'),
       (2, 650, '2021-11-20 19:00:00.000'),
       (3, 700, '2021-11-20 20:00:00.000'),
       (4, 750, '2021-11-20 21:30:00.000'),
       (5, 800, '2021-11-20 22:20:00.000'),
       (6, 150, '2021-11-20 23:30:00.000'),
       (7, 100, '2021-11-20 05:00:00.000'),
       (8, 140, '2021-11-20 07:30:00.000');

insert into ticket (seance_id)
values (1), (1),(1), (1),(1), (2),(2), (2),(2), (2),(2), (2),(2), (2),(2), (2),(2), (3),(3), (3),(3), (3),(3),
       (3),(3), (3),(3), (3),(4), (4),(4), (4),(4), (4),(4), (4),(4), (4),(4), (4),(4), (4),(4), (5),(5), (5),(5),
       (5),(5), (5),(5), (5),(5), (5),(5), (5),(5), (5),(5), (6),(6), (6),(6), (6),(6), (6),(6), (6),(6), (6),(6), (6),
       (6), (6), (6), (6),(7), (7), (7), (7), (7), (7), (7), (8), (8), (8), (9), (9), (9),
       (9), (9), (9), (9), (9), (9), (9), (9), (9), (9), (9), (9), (9), (10), (10), (10),
       (10), (10), (10), (10), (10), (10), (10), (11),
       (11), (11), (11), (11), (11), (11), (11), (12),
       (12), (12), (12), (12), (12), (12), (12), (12),
       (12), (12), (13), (13), (13), (13), (13), (13),
       (13), (13), (13), (13), (13), (13), (13), (13),
       (13), (14), (14), (14), (14), (14), (14), (14),
       (14), (14), (14), (14), (14), (14), (14), (15),
       (15), (15), (15), (15), (15), (15), (15), (15),
       (15), (15), (15), (15), (15), (15), (15), (15),
       (15), (15), (15), (15), (15), (15), (15), (15);


-- чисто для удобства чтения запроса ниже
CREATE VIEW v_film_seance AS
select f.id                                                    as film_id
     , s.id                                                    as seance_id
     , f.name
     , s.dt_start
     , f.length
     , (SELECT DATE_ADD(s.dt_start, INTERVAL f.length MINUTE)) as dt_end
from film f
         left join seance s on s.film_id = f.id
;

-- ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени.
-- Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность»;
select vfs1.name                                     as film_1
     , vfs1.dt_start                                 as f1_start
     , vfs1.length                                   as f1_length
     , (SELECT TIMEDIFF(vfs1.dt_end, vfs2.dt_start)) as diff
     , vfs2.name                                     as film_2
     , vfs2.dt_start                                 as f2_start
     , vfs2.length                                   as f2_tength
from v_film_seance vfs1,
     v_film_seance vfs2
where vfs1.dt_start < vfs2.dt_start
  and vfs1.dt_end > vfs2.dt_start
  and vfs1.film_id != vfs2.film_id
ORDER BY f1_start
;


-- перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва.
-- Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва»;
select film_1, f1_start, f1_length, break, f2_start, f2_tength
FROM (select vfs1.name                                                              as film_1
           , vfs1.dt_start                                                          as f1_start
           , vfs1.length                                                            as f1_length
           , (SELECT ROUND(TIME_TO_SEC(TIMEDIFF(vfs2.dt_start, vfs1.dt_end)) / 60)) as break
           , vfs2.dt_start                                                          as f2_start
           , vfs2.length                                                            as f2_tength
           , vfs2.seance_id                                                         as f2_seance_id
      from v_film_seance vfs1,
           v_film_seance vfs2
      where 1 = 1
        AND vfs1.dt_start <= vfs2.dt_end
        AND vfs1.dt_end <= vfs2.dt_start
        and vfs1.film_id != vfs2.film_id
        and vfs1.seance_id != vfs2.seance_id
      GROUP BY vfs1.seance_id
      ORDER BY break) as t
Group BY f2_seance_id
HAVING break > 30
order BY break desc
;

-- список фильмов, для каждого — с указанием общего числа посетителей за все время,
-- среднего числа зрителей за сеанс и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли).
-- Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;
select  IFNULL(film,'ИТОГО') as film
     , sum(tickets)                       as visitors
     , sum(seances)                       as all_seances
     , sum(money)                         as all_money
     , round(sum(tickets) / sum(seances)) as average_visitors
from (select s.film_id
           , s.price
           , f.name                       as film
           , count(t.seance_id)           as tickets
           , (select count(*)
              from seance s1
              where s1.film_id = s.film_id
              group by s1.film_id)        as seances
           , s.price * count(t.seance_id) as money
      from seance s
               left join ticket t on t.seance_id = s.id
               left join film f on f.id = s.film_id
      group by f.id
     ) as v_t
GROUP BY film
WITH ROLLUP
order by all_money
;

-- число посетителей и кассовые сборы, сгруппированные по времени начала фильма:
-- с 9 до 15, с 15 до 18, с 18 до 21,
-- с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.).

SELECT IF(GROUPING(period), 'ИТОГО', period) AS period
     , IFNULL(tickets, 'Подитог')            AS tickets
     , sum(tickets)                          AS visitors
     , sum(money)                            AS all_money
FROM (
         SELECT CASE
                    -- BETWEEN лучше избегать по этому так
                    WHEN HOUR(dt_start) >= 9 AND HOUR(dt_start) <= 15 THEN '9-15'
                    WHEN HOUR(dt_start) >= 15 AND HOUR(dt_start) <= 18 THEN '15-18'
                    WHEN HOUR(dt_start) >= 18 AND HOUR(dt_start) <= 21 THEN '18-21'
                    WHEN HOUR(dt_start) >= 21 AND HOUR(dt_start) <= 24 THEN '21-24'
                    ELSE "another time"
             END                             AS period
              , s.price
              , count(t.seance_id)           AS tickets
              , s.dt_start
              , s.price * count(t.seance_id) AS money
         from seance s
                  LEFT JOIN ticket t ON t.seance_id = s.id
                  LEFT JOIN film f ON f.id = s.film_id
         WHERE HOUR(dt_start) >= 9 AND HOUR(dt_start) <= 24
         GROUP BY s.id
     ) AS v_t
GROUP BY period, tickets
WITH ROLLUP
;
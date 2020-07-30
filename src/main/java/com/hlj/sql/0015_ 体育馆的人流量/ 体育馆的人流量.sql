
# 1、 体育馆的人流量
# X 市建了一个新的体育馆，每日人流量信息被记录在这三列信息中：序号 (id)、日期 (visit_date)、 人流量 (people)。
#
# 请编写一个查询语句，找出人流量的高峰期。高峰期时，至少连续三行记录中的人流量不少于100。
#
# 例如，表 stadium：
#
# +------+------------+-----------+
# | id   | visit_date | people    |
# +------+------------+-----------+
# | 1    | 2017-01-01 | 10        |
# | 2    | 2017-01-02 | 109       |
# | 3    | 2017-01-03 | 150       |
# | 4    | 2017-01-04 | 99        |
# | 5    | 2017-01-05 | 145       |
# | 6    | 2017-01-06 | 1455      |
# | 7    | 2017-01-07 | 199       |
# | 8    | 2017-01-08 | 188       |
# +------+------------+-----------+
# 对于上面的示例数据，输出为：
#
# +------+------------+-----------+
# | id   | visit_date | people    |
# +------+------------+-----------+
# | 5    | 2017-01-05 | 145       |
# | 6    | 2017-01-06 | 1455      |
# | 7    | 2017-01-07 | 199       |
# | 8    | 2017-01-08 | 188       |
# +------+------------+-----------+

# 2、数据准备
drop table stadium;
create table stadium(
                        id int,
                        visit_date date,
                        people int
);
select * from stadium;
INSERT INTO stadium (id, visit_date, people) VALUES (1, '2017-01-01', 10);
INSERT INTO stadium (id, visit_date, people) VALUES (2, '2017-01-02', 109);
INSERT INTO stadium (id, visit_date, people) VALUES (3, '2017-01-03', 150);
INSERT INTO stadium (id, visit_date, people) VALUES (4, '2017-01-04', 99);
INSERT INTO stadium (id, visit_date, people) VALUES (5, '2017-01-05', 145);
INSERT INTO stadium (id, visit_date, people) VALUES (6, '2017-01-06', 1455);
INSERT INTO stadium (id, visit_date, people) VALUES (7, '2017-01-07', 199);
INSERT INTO stadium (id, visit_date, people) VALUES (8, '2017-01-08', 188);



# 答案
# 答案1
-- 解析：join方式
select * from
(select a.*
-- t1 t2 t3
 from stadium a
          join stadium b on b.id = a.id + 1
          join stadium c on c.id = a.id + 2 where a.people >= 100 and b.people >= 100 and c.people >= 100
 union
-- t2 t1 t3
 select a.*
 from stadium a
          join stadium b on b.id = a.id - 1
          join stadium c on c.id = a.id + 1 where a.people >= 100 and b.people >= 100 and c.people >= 100
 union
-- t2 t3 t1
 select a.*
 from stadium a
          join stadium b on b.id = a.id - 2
          join stadium c on c.id = a.id - 1 where a.people >= 100 and b.people >= 100 and c.people >= 100) m
order by id
;



# 答案2
-- 解析：多个表同时查询方式
select distinct t1.*
from stadium t1
where t1.people >= 100
;

select distinct t1.*
from stadium t1,
     stadium t2,
     stadium t3
where t1.people >= 100
  and t2.people >= 100
  and t3.people >= 100
;


select distinct t1.*
from stadium t1,
     stadium t2,
     stadium t3
where t1.people >= 100
  and t2.people >= 100
  and t3.people >= 100
  and (
        (t1.id + 1 = t2.id and t2.id + 1 = t3.id)    -- t1 t2 t3
        or (t1.id - 1 = t2.id and t2.id + 2 = t3.id) -- t2 t1 t3
        or (t1.id - 2 = t2.id and t2.id + 1 = t3.id) -- t2 t3 t1
    ) order by id
;



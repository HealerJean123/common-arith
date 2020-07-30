# 1、题目：求出不同班级中男生和女生的数量

# 2、数据准备
drop table if exists student;
create table student
(
    Id        int,
    className varchar(32),
    sex       varchar(8),
    score     int
)
select * from student;


INSERT INTO student (Id, className, sex, score) VALUES (1, 'A', '男', 40);
INSERT INTO student (Id, className, sex, score) VALUES (2, 'A', '女', 36);
INSERT INTO student (Id, className, sex, score) VALUES (3, 'A', '男', 90);
INSERT INTO student (Id, className, sex, score) VALUES (4, 'A', '男', 77);
INSERT INTO student (Id, className, sex, score) VALUES (5, 'A', '女', 40);
INSERT INTO student (Id, className, sex, score) VALUES (6, 'B', '女', 35);
INSERT INTO student (Id, className, sex, score) VALUES (7, 'B', '女', 35);
INSERT INTO student (Id, className, sex, score) VALUES (8, 'C', '男', 90);
INSERT INTO student (Id, className, sex, score) VALUES (9, 'C', '女', 35);


# 3、答案
select className,
       sum(case when sex = '男' then 1 else 0 end) as numOfMan,
       sum(case when sex = '女' then 1 else 0 end) as numOfWoman
from student
group by className;

# 1、题目 求男生的平均分大于女的班级

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
INSERT INTO student (Id, className, sex, score) VALUES (5, 'A', '女', 30);
INSERT INTO student (Id, className, sex, score) VALUES (6, 'B', '女', 35);
INSERT INTO student (Id, className, sex, score) VALUES (7, 'B', '女', 35);
INSERT INTO student (Id, className, sex, score) VALUES (8, 'C', '男', 90);
INSERT INTO student (Id, className, sex, score) VALUES (9, 'C', '女', 35);






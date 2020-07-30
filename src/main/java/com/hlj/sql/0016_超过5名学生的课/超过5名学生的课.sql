# 1、题目：超过5名学生的课
# 有一个courses 表 ，有: student (学生) 和 class (课程)。
#
# 请列出所有超过或等于5名学生的课。
#
# 例如,表:
#
# +---------+------------+
# | student | class      |
# +---------+------------+
# | A       | Math       |
# | B       | English    |
# | C       | Math       |
# | D       | Biology    |
# | E       | Math       |
# | F       | Computer   |
# | G       | Math       |
# | H       | Math       |
# | I       | Math       |
# +---------+------------+
# 应该输出:
#
# +---------+
# | class   |
# +---------+
# | Math    |
# +---------+

# 2、数据准备
drop table if exists courses;
create table courses (
    student varchar(32),
    class varchar(32)
);
select * from courses;
insert into courses(student, class) values ('A', 'Math');
insert into courses(student, class) values ('B', 'English');
insert into courses(student, class) values ('C', 'Math');
insert into courses(student, class) values ('D', 'Biology');
insert into courses(student, class) values ('E', 'Math');
insert into courses(student, class) values ('F', 'Computer');
insert into courses(student, class) values ('G', 'Math');
insert into courses(student, class) values ('H', 'Math');
insert into courses(student, class) values ('I', 'Math');


# 3、答案
-- 解析，可能会有重复的  student 所以使用了 distinct
select class from courses group by class having count(distinct student) >= 5 ;

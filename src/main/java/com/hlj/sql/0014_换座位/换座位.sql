# 1、题目：换座位
# 小美是一所中学的信息科技老师，她有一张 seat 座位表，平时用来储存学生名字和与他们相对应的座位 id。
#
# 其中纵列的 id 是连续递增的
#
# 小美想改变相邻俩学生的座位。
#
# 你能不能帮她写一个 SQL query 来输出小美想要的结果呢？
#
#  
#
# 示例：
#
# +---------+---------+
# |    id   | student |
# +---------+---------+
# |    1    | Abbot   |
# |    2    | Doris   |
# |    3    | Emerson |
# |    4    | Green   |
# |    5    | Jeames  |
# +---------+---------+
# 假如数据输入的是上表，则输出结果如下：
#
# +---------+---------+
# |    id   | student |
# +---------+---------+
# |    1    | Doris   |
# |    2    | Abbot   |
# |    3    | Green   |
# |    4    | Emerson |
# |    5    | Jeames  |
# +---------+---------+
# 注意：
#
# 如果学生人数是奇数，则不需要改变最后一个同学的座位。

# 2、数据准备
drop table if exists seat;
create table seat (
  id int,
  student varchar(32)
);
select * from seat;
INSERT INTO seat (id, student) VALUES (1, 'Abbot');
INSERT INTO seat (id, student) VALUES (2, 'Doris');
INSERT INTO seat (id, student) VALUES (3, 'Emerson');
INSERT INTO seat (id, student) VALUES (4, 'Green');
INSERT INTO seat (id, student) VALUES (5, 'Jeames');

# 3、答案
# 解析，if判断 select if( 1 > 0 ,1 ,0 ) ; 第二个if要判断末尾是存在 a.id + 1
select a.id,
       if(a.id % 2 = 0, (select b.student from seat b where b.id = a.id - 1),
          if((select d.id from seat d where d.id = a.id + 1) is not null,
             (select c.student from seat c where c.id = a.id + 1),
             a.student)) as student
from seat a;


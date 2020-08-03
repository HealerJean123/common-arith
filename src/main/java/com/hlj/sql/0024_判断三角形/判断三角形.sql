# 1、题目：判断三角形

# 一个小学生 Tim 的作业是判断三条线段是否能形成一个三角形。
# 然而，这个作业非常繁重，因为有几百组线段需要判断。
# 假设表 triangle 保存了所有三条线段的三元组 x, y, z ，你能帮 Tim 写一个查询语句，来判断每个三元组是否可以组成一个三角形吗？
#
# | x  | y  | z  |
# |----|----|----|
# | 13 | 15 | 30 |
# | 10 | 20 | 15 |
# 对于如上样例数据，你的查询语句应该返回如下结果：
#
# | x  | y  | z  | triangle |
# |----|----|----|----------|
# | 13 | 15 | 30 | No       |
# | 10 | 20 | 15 | Yes      |

# 2、数据准备
drop table if exists triangle;
create table triangle  (
    x int,
    y int,
    z int
);


INSERT INTO triangle (x, y, z) VALUES (13, 15, 30);
INSERT INTO triangle (x, y, z) VALUES (10, 20, 15);
select * from triangle;


# 3、答案
select x, y, z, if((x + y <= z || x + z <= y || z + y <= x ) , 'No','Yes') as triangle     from triangle;

# 1、题目：平面上的最近距离
# 表 point_2d 保存了所有点（多于 2 个点）的坐标 (x,y) ，这些点在平面上两两不重合。
#
# 写一个查询语句找到两点之间的最近距离，保留 2 位小数。
#
# | x  | y  |
# |----|----|
# | -1 | -1 |
# | 0  | 0  |
# | -1 | -2 |
#
# 最近距离在点 (-1,-1) 和(-1,2) 之间，距离为 1.00 。所以输出应该为：
#
# | shortest |
# |----------|
# | 1.00     |

# 2、数据准备
drop table if exists point_2d ;
create table point_2d
(
    x int,
    y int
);

insert into point_2d (x, y) values (-1, -1);
insert into point_2d (x, y) values (0, 0);
insert into point_2d (x, y) values (-1, -2);
select * from point_2d;;

# 3、答案
-- 1、准备关联数据（除了自己以外的其他点进行关联，因为没有id，所以只能使用 x y 任意不相等就成立）
select p1.x, p1.y, p2.x, p2.y from point_2d p1   join point_2d p2    on  !(p1.x = p2.x and p1.y = p2.y);

-- 2、函数
-- power(value, 2); 平方    比如：select  power(3,2);  -> 9
-- sqrt(value)      开平方，比如：select sqrt(9);  -> 3
select min(
               round(
                       sqrt(power((p2.y - p1.y), 2) + power((p2.x - p1.x), 2)),
                       2)
           ) as shortest
from point_2d p1
         join point_2d p2 on !(p1.x = p2.x and p1.y = p2.y);



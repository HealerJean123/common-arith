# 1、题目：二级关注者
# 在 facebook 中，表 follow 会有 2 个字段： followee, follower ，分别表示被关注者和关注者。
#
# 请写一个 sql 查询语句，对每一个关注者，查询关注他的关注者的数目。
#
# 比方说：
#
# +-------------+------------+
# | followee    | follower   |
# +-------------+------------+
# |     A       |     B      |
# |     B       |     C      |
# |     B       |     D      |
# |     D       |     E      |
# +-------------+------------+
# 应该输出：
#
# +-------------+------------+
# | follower    | num        |
# +-------------+------------+
# |     B       |  2         |
# |     D       |  1         |
# +-------------+------------+
# 解释：
#
# B 和 D 都在在 follower 字段中出现，作为被关注者，B 被 C 和 D 关注，D 被 E 关注。A 不在 follower 字段内，所以A不在输出列表中。



# 2、准备数据
drop table if exists follow;
create table follow
(
    followee varchar(8),
    follower varchar(8)
);
INSERT INTO follow (followee, follower) VALUES ('A', 'B');
INSERT INTO follow (followee, follower) VALUES ('B', 'C');
INSERT INTO follow (followee, follower) VALUES ('B', 'D');
INSERT INTO follow (followee, follower) VALUES ('D', 'E');
select * from follow;

# 3、答案
-- 1、根据关注人进行分组
select f1.followee, count(f1.followee) as num from follow f1 group by f1.followee

-- 2、必须使用join进行关联（由于解释中的说法）
select f2.follower, f.num
from follow f2
         join (
    select f1.followee, count(f1.followee) as num from follow f1 group by f1.followee
) f on f.followee = f2.follower

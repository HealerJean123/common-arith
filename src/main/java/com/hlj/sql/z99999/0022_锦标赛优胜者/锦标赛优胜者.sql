# 1、竞标赛优胜者
# Players 玩家表
#
# +-------------+-------+
# | Column Name | Type  |
# +-------------+-------+
# | player_id   | int   |
# | group_id    | int   |
# +-------------+-------+
# 玩家 ID 是此表的主键。
# 此表的每一行表示每个玩家的组。
# Matches 赛事表
#
# +---------------+---------+
# | Column Name   | Type    |
# +---------------+---------+
# | match_id      | int     |
# | first_player  | int     |
# | second_player | int     |
# | first_score   | int     |
# | second_score  | int     |
# +---------------+---------+
# match_id 是此表的主键。
# 每一行是一场比赛的记录，第一名和第二名球员包含每场比赛的球员 ID。
# 第一个玩家和第二个玩家的分数分别包含第一个玩家和第二个玩家的分数。
# 你可以假设，在每一场比赛中，球员都属于同一组。

# 每组的获胜者是在组内得分最高的选手。如果平局，player_id 最小 的选手获胜。
#
# 编写一个 SQL 查询来查找每组中的获胜者。
#
# 查询结果格式如下所示
#
# Players 表:
# +-----------+------------+
# | player_id | group_id   |
# +-----------+------------+
# | 15        | 1          |
# | 25        | 1          |
# | 30        | 1          |
# | 45        | 1          |
# | 10        | 2          |
# | 35        | 2          |
# | 50        | 2          |
# | 20        | 3          |
# | 40        | 3          |
# +-----------+------------+
#
# Matches 表:
# +------------+--------------+---------------+-------------+--------------+
# | match_id   | first_player | second_player | first_score | second_score |
# +------------+--------------+---------------+-------------+--------------+
# | 1          | 15           | 45            | 3           | 0            |
# | 2          | 30           | 25            | 1           | 2            |
# | 3          | 30           | 15            | 2           | 0            |
# | 4          | 40           | 20            | 5           | 2            |
# | 5          | 35           | 50            | 1           | 1            |
# +------------+--------------+---------------+-------------+--------------+
#
# Result 表:
# +-----------+------------+
# | group_id  | player_id  |
# +-----------+------------+
# | 1         | 15         |
# | 2         | 35         |
# | 3         | 40         |
# +-----------+------------+

# 2、数据准备
drop table if exists Players;
create table Players
(
    player_id int,
    group_id      int
);
select * from Players;

drop table if exists Matches;
create table Matches
(
    match_id      int,
    first_player  int,
    second_player int,
    first_score   int,
    second_score  int

);
select * from Matches;


INSERT INTO players (player_id, group_id) VALUES (10, 2);
INSERT INTO players (player_id, group_id) VALUES (15, 1);
INSERT INTO players (player_id, group_id) VALUES (20, 3);
INSERT INTO players (player_id, group_id) VALUES (25, 1);
INSERT INTO players (player_id, group_id) VALUES (30, 1);
INSERT INTO players (player_id, group_id) VALUES (35, 2);
INSERT INTO players (player_id, group_id) VALUES (40, 3);
INSERT INTO players (player_id, group_id) VALUES (45, 1);
INSERT INTO players (player_id, group_id) VALUES (50, 2);

INSERT INTO matches (match_id, first_player, second_player, first_score, second_score) VALUES (1, 15, 45, 3, 0);
INSERT INTO matches (match_id, first_player, second_player, first_score, second_score) VALUES (2, 30, 25, 1, 2);
INSERT INTO matches (match_id, first_player, second_player, first_score, second_score) VALUES (3, 30, 15, 2, 0);
INSERT INTO matches (match_id, first_player, second_player, first_score, second_score) VALUES (4, 40, 20, 5, 2);
INSERT INTO matches (match_id, first_player, second_player, first_score, second_score) VALUES (5, 35, 50, 1, 1);


# 答案
# 答案1
-- 1、获取所有的选手所在组以及得分情况
select p1.group_id, p1.player_id, m1.first_score as score
from Players p1
         join Matches m1 on m1.first_player = p1.player_id
union
select p2.group_id, p2.player_id, m2.second_score as score
from Players p2
         join Matches m2 on m2.second_player = p2.player_id
;

-- 2、关键字需要包装一下
select m.group_id, m.player_id,m.score
from (
         select p1.group_id, p1.player_id, m1.first_score as score
         from Players p1
                  join Matches m1 on m1.first_player = p1.player_id
         union
         select p2.group_id, p2.player_id, m2.second_score as score
         from Players p2
                  join Matches m2 on m2.second_player = p2.player_id
     ) m order by group_id;

-- 3、通过组分组，找出每组中最大的得分
select m.group_id, max(score)
from (
         select p1.group_id, m1.first_score as score
         from Players p1
                  join Matches m1 on m1.first_player = p1.player_id
         union
         select p2.group_id, m2.second_score as score
         from Players p2
                  join Matches m2 on m2.second_player = p2.player_id
     ) m group by group_id;



select m1.group_id, m1.player_id, m1.score
from (
         select p1.group_id, p1.player_id, m1.first_score as score
         from Players p1
                  join Matches m1 on m1.first_player = p1.player_id
         union
         select p2.group_id, p2.player_id, m2.second_score as score
         from Players p2
                  join Matches m2 on m2.second_player = p2.player_id
     ) m1
         join (
    select m.group_id, max(score) as score
    from (
             select p1.group_id, m1.first_score as score
             from Players p1
                      join Matches m1 on m1.first_player = p1.player_id
             union
             select p2.group_id, m2.second_score as score
             from Players p2
                      join Matches m2 on m2.second_player = p2.player_id
         ) m
    group by group_id
) m2 on m1.group_id = m2.group_id and m1.score = m2.score;

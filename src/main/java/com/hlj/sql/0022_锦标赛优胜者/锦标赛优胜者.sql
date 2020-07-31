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


select p2.group_id, p2.player_id from Players p2 left join Matches m2 on m2.first_player = p2.player_id
 join (
select  p1.group_id, max(m1.first_score) maxScore   from Players p1  join Matches m1 on m1.first_player = p1.player_id  group by group_id
) m on p2.group_id = m.group_id where m2.first_score = m.maxScore ;



select m.group_id, m.player_id,m.score
from (
         select p1.group_id, p1.player_id, m1.first_score as score
         from Players p1
                  join Matches m1 on m1.first_player = p1.player_id
         union
         select p2.group_id, p2.player_id, m2.first_score as score
         from Players p2
                  join Matches m2 on m2.second_player = p2.player_id
     ) m group by  group_id;

select * from Players p3    join (
select m.group_id, max(m.score) as score
from (
         select p1.group_id, p1.player_id, m1.first_score as score
         from Players p1
                  join Matches m1 on m1.first_player = p1.player_id
         union
         select p2.group_id, p2.player_id, m2.first_score as score
         from Players p2
                  join Matches m2 on m2.second_player = p2.player_id
     ) m group by  group_id 
)f on f.group_id = p3.group_id



SELECT group_id, player_idorder by group_id, score desc , player_id
FROM (
         SELECT group_id, player_id, SUM(score) AS score
         FROM (
                  -- 每个用户总的 first_score
                  SELECT Players.group_id, Players.player_id, SUM(Matches.first_score) AS score
                  FROM Players JOIN Matches ON Players.player_id = Matches.first_player
                  GROUP BY Players.player_id

                  UNION ALL

                  -- 每个用户总的 second_score
                  SELECT Players.group_id, Players.player_id, SUM(Matches.second_score) AS score
                  FROM Players JOIN Matches ON Players.player_id = Matches.second_player
                  GROUP BY Players.player_id
              ) s
         GROUP BY player_id
         ORDER BY score DESC, player_id
     ) result
GROUP BY group_id

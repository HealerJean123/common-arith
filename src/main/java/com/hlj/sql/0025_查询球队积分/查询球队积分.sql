# # 1、题目：查询球队积分
# Table: Teams
#
# +---------------+----------+
# | Column Name   | Type     |
# +---------------+----------+
# | team_id       | int      |
# | team_name     | varchar  |
# +---------------+----------+
# 此表的主键是 team_id，表中的每一行都代表一支独立足球队。
# Table: Matches
#
# +---------------+---------+
# | Column Name   | Type    |
# +---------------+---------+
# | match_id      | int     |
# | host_team     | int     |
# | guest_team    | int     |
# | host_goals    | int     |
# | guest_goals   | int     |
# +---------------+---------+
# 此表的主键是 match_id，表中的每一行都代表一场已结束的比赛，比赛的主客队分别由它们自己的 id 表示，他们的进球由 host_goals 和 guest_goals 分别表示。
#  
#
# 积分规则如下：
#
# 赢一场得三分；
# 平一场得一分；
# 输一场不得分。
# 写出一条SQL语句以查询每个队的 team_id，team_name 和 num_points。结果根据 num_points 降序排序，如果有两队积分相同，那么这两队按 team_id  升序排序。
#
# 查询结果格式如下：
#
# Teams table:
# +-----------+--------------+
# | team_id   | team_name    |
# +-----------+--------------+
# | 10        | Leetcode FC  |
# | 20        | NewYork FC   |
# | 30        | Atlanta FC   |
# | 40        | Chicago FC   |
# | 50        | Toronto FC   |
# +-----------+--------------+
#
# Matches table:
# +------------+--------------+---------------+-------------+--------------+
# | match_id   | host_team    | guest_team    | host_goals  | guest_goals  |
# +------------+--------------+---------------+-------------+--------------+
# | 1          | 10           | 20            | 3           | 0            |
# | 2          | 30           | 10            | 2           | 2            |
# | 3          | 10           | 50            | 5           | 1            |
# | 4          | 20           | 30            | 1           | 0            |
# | 5          | 50           | 30            | 1           | 0            |
# +------------+--------------+---------------+-------------+--------------+
#
# Result table:
# +------------+--------------+---------------+
# | team_id    | team_name    | num_points    |
# +------------+--------------+---------------+
# | 10         | Leetcode FC  | 7             |
# | 20         | NewYork FC   | 3             |
# | 50         | Toronto FC   | 3             |
# | 30         | Atlanta FC   | 1             |
# | 40         | Chicago FC   | 0             |
# +------------+--------------+---------------+

# 2、数据准备

drop table if exists Teams;
create table Teams
(
    team_id   int,
    team_name varchar(32)
);
drop table if exists Matches;
create table Matches
(
    match_id    int,
    host_team   int,
    guest_team  int,
    host_goals  int,
    guest_goals int
);
INSERT INTO teams (team_id, team_name) VALUES (10, 'Leetcode FC');
INSERT INTO teams (team_id, team_name) VALUES (20, 'NewYork FC');
INSERT INTO teams (team_id, team_name) VALUES (30, 'Atlanta FC');
INSERT INTO teams (team_id, team_name) VALUES (40, 'Chicago FC');
INSERT INTO teams (team_id, team_name) VALUES (50, 'Toronto FC');
select * from Teams;

INSERT INTO matches (match_id, host_team, guest_team, host_goals, guest_goals) VALUES (1, 10, 20, 30, 0);
INSERT INTO matches (match_id, host_team, guest_team, host_goals, guest_goals) VALUES (2, 30, 10, 2, 2);
INSERT INTO matches (match_id, host_team, guest_team, host_goals, guest_goals) VALUES (3, 10, 50, 5, 1);
INSERT INTO matches (match_id, host_team, guest_team, host_goals, guest_goals) VALUES (4, 20, 30, 1, 0);
INSERT INTO matches (match_id, host_team, guest_team, host_goals, guest_goals) VALUES (5, 50, 30, 1, 0);
select * from Matches;



# 3、答案
-- 解析
-- 1、根据比赛的球队分组，然后计算每个球队的得分
(select host_team           as team_id,
       sum(case
               when (host_goals > guest_goals) then 3
               when host_goals = guest_goals then 1
               else 0 end) as score
from Matches
group by host_team)
union all
(select guest_team          as team_id,
    sum(case
    when (host_goals > guest_goals) then 0
    when host_goals = guest_goals then 1
    else 3 end) as score
from Matches
group by guest_team);



-- 2、将关联起来的球队再进行分组，这个时候的分组，还是以球队进行分组的
select team_id, sum(score) as num_points
from (
         (select host_team           as team_id,
                 sum(case
                         when (host_goals > guest_goals) then 3
                         when host_goals = guest_goals then 1
                         else 0 end) as score
          from Matches
          group by host_team)
         union all
         (select guest_team          as team_id,
                 sum(case
                         when (host_goals > guest_goals) then 0
                         when host_goals = guest_goals then 1
                         else 3 end) as score
          from Matches
          group by guest_team)
     ) m
group by team_id;



-- 3、left join 查询
select t.team_id, t.team_name, ifnull(num_points, 0) as num_points
from Teams t
         left join (
    select team_id, sum(score) as num_points
    from (
             (select host_team           as team_id,
                     sum(case
                             when (host_goals > guest_goals) then 3
                             when host_goals = guest_goals then 1
                             else 0 end) as score
              from Matches
              group by host_team)
             union all
             (select guest_team          as team_id,
                     sum(case
                             when (host_goals > guest_goals) then 0
                             when host_goals = guest_goals then 1
                             else 3 end) as score
              from Matches
              group by guest_team)
         ) m
    group by team_id
) n on t.team_id = n.team_id
order by num_points desc, team_id


# 1、不同性别每日分数总计
# 表: Scores
#
# +---------------+---------+
# | Column Name   | Type    |
# +---------------+---------+
# | player_name   | varchar |
# | gender        | varchar |
# | day           | date    |
# | score_points  | int     |
# +---------------+---------+
# (gender, day)是该表的主键
# 一场比赛是在女队和男队之间举行的
# 该表的每一行表示一个名叫 (player_name) 性别为 (gender) 的参赛者在某一天获得了 (score_points) 的分数
# 如果参赛者是女性，那么 gender 列为 'F'，如果参赛者是男性，那么 gender 列为 'M'
#  
#
# 写一条SQL语句查询每种性别在每一天的总分，并按性别和日期对查询结果排序
#
# 下面是查询结果格式的例子：
#
# Scores表:
# +-------------+--------+------------+--------------+
# | player_name | gender | day        | score_points |
# +-------------+--------+------------+--------------+
# | Aron        | F      | 2020-01-01 | 17           |
# | Alice       | F      | 2020-01-07 | 23           |
# | Bajrang     | M      | 2020-01-07 | 7            |
# | Khali       | M      | 2019-12-25 | 11           |
# | Slaman      | M      | 2019-12-30 | 13           |
# | Joe         | M      | 2019-12-31 | 3            |
# | Jose        | M      | 2019-12-18 | 2            |
# | Priya       | F      | 2019-12-31 | 23           |
# | Priyanka    | F      | 2019-12-30 | 17           |
# +-------------+--------+------------+--------------+
# 结果表:
# +--------+------------+-------+
# | gender | day        | total |
# +--------+------------+-------+
# | F      | 2019-12-30 | 17    |
# | F      | 2019-12-31 | 40    |
# | F      | 2020-01-01 | 57    |
# | F      | 2020-01-07 | 80    |
# | M      | 2019-12-18 | 2     |
# | M      | 2019-12-25 | 13    |
# | M      | 2019-12-30 | 26    |
# | M      | 2019-12-31 | 29    |
# | M      | 2020-01-07 | 36    |
# +--------+------------+-------+
# 女性队伍:
# 第一天是 2019-12-30，Priyanka 获得 17 分，队伍的总分是 17 分
# 第二天是 2019-12-31, Priya 获得 23 分，队伍的总分是 40 分
# 第三天是 2020-01-01, Aron 获得 17 分，队伍的总分是 57 分
# 第四天是 2020-01-07, Alice 获得 23 分，队伍的总分是 80 分
# 男性队伍：
# 第一天是 2019-12-18, Jose 获得 2 分，队伍的总分是 2 分
# 第二天是 2019-12-25, Khali 获得 11 分，队伍的总分是 13 分
# 第三天是 2019-12-30, Slaman 获得 13 分，队伍的总分是 26 分
# 第四天是 2019-12-31, Joe 获得 3 分，队伍的总分是 29 分
# 第五天是 2020-01-07, Bajrang 获得 7 分，队伍的总分是 36 分


# 2、数据准备
drop table if exists Scores;
create table Scores
(
    player_name  varchar(32),
    gender       varchar(1),
    day          date,
    score_points int
);
INSERT INTO scores (player_name, gender, day, score_points) VALUES ('Aron', 'F', '2020-01-01', 17);
INSERT INTO scores (player_name, gender, day, score_points) VALUES ('Alice', 'F', '2020-01-07', 23);
INSERT INTO scores (player_name, gender, day, score_points) VALUES ('Bajrang', 'M', '2020-01-07', 7);
INSERT INTO scores (player_name, gender, day, score_points) VALUES ('Khali', 'M', '2019-12-25', 11);
INSERT INTO scores (player_name, gender, day, score_points) VALUES ('Slaman', 'M', '2019-12-30', 13);
INSERT INTO scores (player_name, gender, day, score_points) VALUES ('Joe', 'M', '2019-12-31', 3);
INSERT INTO scores (player_name, gender, day, score_points) VALUES ('Jose', 'M', '2019-12-18', 2);
INSERT INTO scores (player_name, gender, day, score_points) VALUES ('Priya', 'F', '2019-12-31', 23);
INSERT INTO scores (player_name, gender, day, score_points) VALUES ('Priyanka', 'F', '2019-12-30', 17);
select * from Scores;


# 3、答案
-- 3.1、答案1
-- 解析
--- 1、找出性别对应日期的顺序
select sum(s2.score_points)
from Scores s2
where s2.day <= '2020-01-07'
  and s2.gender = 'F';

-- 2、条件查询
select s1.gender,
       s1.day,
       (select sum(s2.score_points)
        from Scores s2
        where s2.day <= s1.day
          and s2.gender = s1.gender) as total
from Scores s1
order by s1.gender, s1.day;


# 2、答案2
select s1.gender,
       s1.day,
       sum(s2.score_points) as total
from Scores s1
         left join Scores s2 on s2.gender = s1.gender and  s1.day >= s2.day
group by s1.day, s1.gender
order by s1.gender, s1.day;
;

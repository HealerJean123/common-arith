
# 1、题目
# 如果两个分数相同，则两个分数排名（Rank）相同。请注意，平分后的下一个名次应该是下一个连续的整数值。换句话说，名次之间不应该有“间隔”。
#
# +----+-------+
# | Id | Score |
# +----+-------+
# | 1  | 3.50  |
# | 2  | 3.65  |
# | 3  | 4.00  |
# | 4  | 3.85  |
# | 5  | 4.00  |
# | 6  | 3.65  |
# +----+-------+
# 例如，根据上述给定的 Scores 表，你的查询应该返回（按分数从高到低排列）：
#
# +-------+------+
# | Score | Rank |
# +-------+------+
# | 4.00  | 1    |
# | 4.00  | 1    |
# | 3.85  | 2    |
# | 3.65  | 3    |
# | 3.65  | 3    |
# | 3.50  | 4    |
# +-------+------+


# 2、数据准备

drop table if exists  Scores ;
create table Scores
(

    Id     int(11) ,
    Score  decimal(16,2)
);

truncate Scores;
insert into Scores (Id, Score) values (1, 3.50);
insert into Scores (Id, Score) values (2, 3.65);
insert into Scores (Id, Score) values (3, 4.00);
insert into Scores (Id, Score) values (4, 3.85);
insert into Scores (Id, Score) values (5, 4.00);
insert into Scores (Id, Score) values (6, 3.65);

# 3、答案

# 答案1
# 解析：子查询查询大于A中等于（唯一，因为会有重复的分数比它大，重复的分数看做是一个排名）分数的的个数 也就是排名
select A.Score as score,
       (select COUNT(distinct B.Score) from Scores B where B.Score >= A.Score) as Rank
from Scores A
order by Score
desc;



# 答案2  用户变量的方式
SELECT
    Score,
    case
        when  @curScore = Score then @curRank
        when  @curScore := Score THEN 	@curRank := @curRank + 1
        end as Rank
FROM
    Scores,
    (SELECT @curRank := 0 , @curScore) r
ORDER BY
    Score DESC;



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




select * from Scores where Score > 3 order by Score desc ;

# 第一种 A是主题，子查询查询大于A中等于（唯一）分数的的个数 也就是排名
select A.Score as  score,
     (select COUNT(distinct B.Score) from Scores B where B.Score >= A.Score) as Rank
from Scores A order by  Score   DESC;


# 第二种  用户变量的方式
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

+-------+------+
| score | Rank |
+-------+------+
| 4.00  |    1 |
| 4.00  |    1 |
| 3.85  |    2 |
| 3.65  |    3 |
| 3.65  |    3 |
| 3.50  |    4 |


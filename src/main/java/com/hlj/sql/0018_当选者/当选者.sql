# 1、题目
# 表: Candidate
# +-----+---------+
# | id  | Name    |
# +-----+---------+
# | 1   | A       |
# | 2   | B       |
# | 3   | C       |
# | 4   | D       |
# | 5   | E       |
# +-----+---------+
#
# 表: Vote ，id 是自动递增的主键，CandidateId 是 Candidate 表中的 id.
# +-----+--------------+
# | id  | CandidateId  |
# +-----+--------------+
# | 1   |     2        |
# | 2   |     4        |
# | 3   |     3        |
# | 4   |     2        |
# | 5   |     5        |
# +-----+--------------+
#
# 请编写 sql 语句来找到当选者的名字，上面的例子将返回当选者 B.
#
# +------+
# | Name |
# +------+
# | B    |
# +------+
#
# 注意：你可以假设没有平局，换言之，最多只有一位当选者。

# 2、数据准备
drop table if exists Candidate;
create table Candidate
(
    id   int,
    Name varchar(32)
);
insert into Candidate(id, Name)  values (1, 'A');
insert into Candidate(id, Name)  values (2, 'B');
insert into Candidate(id, Name)  values (3, 'C');
insert into Candidate(id, Name)  values (4, 'D');
insert into Candidate(id, Name)  values (5, 'E');

select * from Candidate;;


drop table if exists Vote;
create table Vote
(
    id          int,
    CandidateId int
);
insert into Vote(id, CandidateId)  values (1, 2);
insert into Vote(id, CandidateId)  values (2, 4);
insert into Vote(id, CandidateId)  values (3, 3);
insert into Vote(id, CandidateId)  values (4, 2);
insert into Vote(id, CandidateId)  values (5, 5);
select * from Vote;;



# 答案
# 1、答案1
select  c.Name   from Candidate c join  Vote v on c.id = v.CandidateId  group by c.Name order by count(*)  desc  limit 1;

# 2、答案2
select Name
from Candidate c
         join (select v.CandidateId from Vote v group by v.CandidateId order by count(*) desc limit 1
) r on r.CandidateId = c.id;

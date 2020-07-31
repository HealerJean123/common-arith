# 1、题目
# 编写一个 SQL 查询，来删除 Person 表中所有重复的电子邮箱，重复的邮箱里只保留 Id 最小 的那个。
#
# +----+------------------+
# | Id | Email            |
# +----+------------------+
# | 1  | john@example.com |
# | 2  | bob@example.com  |
# | 3  | john@example.com |
# +----+------------------+
# Id 是这个表的主键。
# 例如，在运行你的查询语句之后，上面的 Person 表应返回以下几行:
#
# +----+------------------+
# | Id | Email            |
# +----+------------------+
# | 1  | john@example.com |
# | 2  | bob@example.com  |
# +----+------------------+

# 2、数据准备
drop table if exists Person;
create table Person(
    Id int,
    Email varchar(64)
);

insert into Person(id, email) values (1, 'john@example.com');
insert into Person(id, email) values (2, 'bob@example.com');
insert into Person(id, email) values (3, 'john@example.com');
select * from Person;


# 3、答案
-- 解析 土方法
delete p.*
from Person p
where (p.Id) not in (select id from (select min(m.id) as id from Person m group by m.Email) r);

# 答案2 join查询
-- 解析：有点类似于删除id比它大的
select p1.id
from Person p1
           join Person p2 on p1.Email = p2.Email
where p1.id > p2.Id;


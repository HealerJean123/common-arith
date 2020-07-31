# 1、题目
# +----------------+---------+
# | Column Name    | Type    |
# +----------------+---------+
# | book_id        | int     |
# | name           | varchar |
# | available_from | date    |
# +----------------+---------+
# book_id 是这个表的主键。
# 订单表 Orders：
#
# +----------------+---------+
# | Column Name    | Type    |
# +----------------+---------+
# | order_id       | int     |
# | book_id        | int     |
# | quantity       | int     |
# | dispatch_date  | date    |
# +----------------+---------+
# order_id 是这个表的主键。
# book_id  是 Books 表的外键。
#
#
# 你需要写一段 SQL 命令，筛选出过去一年中订单总量 少于10本 的 书籍 。
# 注意：不考虑 上架（available from）距今 不满一个月 的书籍。并且 假设今天是 2019-06-23 。
#
#
# 下面是样例输出结果：
#
# Books 表：
# +---------+--------------------+----------------+
# | book_id | name               | available_from |
# +---------+--------------------+----------------+
# | 1       | "Kalila And Demna" | 2010-01-01     |
# | 2       | "28 Letters"       | 2012-05-12     |
# | 3       | "The Hobbit"       | 2019-06-10     |
# | 4       | "13 Reasons Why"   | 2019-06-01     |
# | 5       | "The Hunger Games" | 2008-09-21     |
# +---------+--------------------+----------------+
#
# Orders 表：
# +----------+---------+----------+---------------+
# | order_id | book_id | quantity | dispatch_date |
# +----------+---------+----------+---------------+
# | 1        | 1       | 2        | 2018-07-26    |
# | 2        | 1       | 1        | 2018-11-05    |
# | 3        | 3       | 8        | 2019-06-11    |
# | 4        | 4       | 6        | 2019-06-05    |
# | 5        | 4       | 5        | 2019-06-20    |
# | 6        | 5       | 9        | 2009-02-02    |
# | 7        | 5       | 8        | 2010-04-13    |
# +----------+---------+----------+---------------+
#
# Result 表：
# +-----------+--------------------+
# | book_id   | name               |
# +-----------+--------------------+
# | 1         | "Kalila And Demna" |
# | 2         | "28 Letters"       |
# | 5         | "The Hunger Games" |
# +-----------+--------------------+

# 2、数据准备
drop table if exists Books;
create table  Books(
    book_id int,
    name varchar(32),
    available_from date
);
insert into Books(book_id, name, available_from) values (1, 'Kalila And Demna' ,'2010-01-01');
insert into Books(book_id, name, available_from) values (2, '28 Letters' ,'2012-05-12');
insert into Books(book_id, name, available_from) values (3, 'The Hobbit' ,'2019-06-10');
insert into Books(book_id, name, available_from) values (4, '13 Reasons Why' ,'2019-06-01');
insert into Books(book_id, name, available_from) values (5, 'The Hunger Games' ,'2008-09-21');
select * from Books;


drop table if exists Orders;
create table  Orders(
    order_id int,
    book_id int,
    quantity int,
    dispatch_date date
);
insert into Orders(order_id, book_id, quantity, dispatch_date) values (1,1,2,'2018-07-26');
insert into Orders(order_id, book_id, quantity, dispatch_date) values (2,1,1,'2018-11-05');
insert into Orders(order_id, book_id, quantity, dispatch_date) values (3,3,8,'2019-06-11');
insert into Orders(order_id, book_id, quantity, dispatch_date) values (4,4,6,'2019-06-05');
insert into Orders(order_id, book_id, quantity, dispatch_date) values (5,4,5,'2019-06-20');
insert into Orders(order_id, book_id, quantity, dispatch_date) values (6,5,9,'2009-02-02');
insert into Orders(order_id, book_id, quantity, dispatch_date) values (7,6,8,'2010-04-13');
select * from Orders;

# 答案
-- 解析
-- 1、先筛选符合基本条件的
select b.book_id, b.Name from Books b where b.available_from < date_sub('2019-06-23', INTERVAL 1  MONTH);

-- 2、根据书籍分组选择 订单量的 (注意反向思考，如果1年内没有订单将不会出现在订单表中，这样就很难join关联查)
select o.book_id from Orders o  where o.dispatch_date > date_sub('2019-06-23', INTERVAL 1 YEAR) group by o.book_id having sum(quantity) >= 10 ;

-- 3、使用left join连接，因为可能会有一个订单都没有的书籍，所以使用left join
select b.book_id, b.Name
from Books b
where b.available_from < date_sub('2019-06-23', INTERVAL 1 MONTH)
  and b.book_id  not in (select o.book_id from Orders o  where o.dispatch_date > date_sub('2019-06-23', INTERVAL 1 YEAR) group by o.book_id having sum(quantity) >= 10);
;




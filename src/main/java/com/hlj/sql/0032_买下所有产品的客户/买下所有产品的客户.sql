# 1、买下所有产品的客户
# Customer 表：
#
# +-------------+---------+
# | Column Name | Type    |
# +-------------+---------+
# | customer_id | int     |
# | product_key | int     |
# +-------------+---------+
# product_key 是 Customer 表的外键。
# Product 表：
#
# +-------------+---------+
# | Column Name | Type    |
# +-------------+---------+
# | product_key | int     |
# +-------------+---------+
# product_key 是这张表的主键。
#  
#
# 写一条 SQL 查询语句，从 Customer 表中查询购买了 Product 表中所有产品的客户的 id。
#
# 示例：
#
# Customer 表：
# +-------------+-------------+
# | customer_id | product_key |
# +-------------+-------------+
# | 1           | 5           |
# | 2           | 6           |
# | 3           | 5           |
# | 3           | 6           |
# | 1           | 6           |
# +-------------+-------------+
#
# Product 表：
# +-------------+
# | product_key |
# +-------------+
# | 5           |
# | 6           |
# +-------------+
#
# Result 表：
# +-------------+
# | customer_id |
# +-------------+
# | 1           |
# | 3           |
# +-------------+
# 购买了所有产品（5 和 6）的客户的 id 是 1 和 3 。

# 2、数据准备
drop table if exists Customer
create table Customer(
    customer_id int,
    product_key int
    );

drop table if exists Product
create table Product (
    product_key  int
);
INSERT INTO customer (customer_id, product_key) VALUES (1, 5);
INSERT INTO customer (customer_id, product_key) VALUES (2, 6);
INSERT INTO customer (customer_id, product_key) VALUES (3, 5);
INSERT INTO customer (customer_id, product_key) VALUES (3, 6);
INSERT INTO customer (customer_id, product_key) VALUES (1, 6);
select * from Customer;

INSERT INTO product (product_key) VALUES (5);
INSERT INTO product (product_key) VALUES (6);
select * from Product;


# 3、答案
-- 1、找到所有不重复产品的个数
select count(distinct p.product_key)  from Product p;


-- 2、使用用户分组，找到不重复产品个数和产品总数相同的用户
select c.customer_id
from Customer c
group by c.customer_id
having count(distinct c.product_key) = (select count(distinct p.product_key)  from Product p);

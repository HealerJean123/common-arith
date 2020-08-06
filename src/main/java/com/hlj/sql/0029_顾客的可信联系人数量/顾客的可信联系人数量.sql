# 1、题目：顾客的可信联系人数量
# 顾客表：Customers
#
# +---------------+---------+
# | Column Name   | Type    |
# +---------------+---------+
# | customer_id   | int     |
# | customer_name | varchar |
# | email         | varchar |
# +---------------+---------+
# customer_id 是这张表的主键。
# 此表的每一行包含了某在线商店顾客的姓名和电子邮件。
#  
#
# 联系方式表：Contacts
#
# +---------------+---------+
# | Column Name   | Type    |
# +---------------+---------+
# | user_id       | id      |
# | contact_name  | varchar |
# | contact_email | varchar |
# +---------------+---------+
# (user_id, contact_email) 是这张表的主键。
# 此表的每一行表示编号为 user_id 的顾客的某位联系人的姓名和电子邮件。
# 此表包含每位顾客的联系人信息，但顾客的联系人不一定存在于顾客表中。
#  
#
# 发票表：Invoices
#
# +--------------+---------+
# | Column Name  | Type    |
# +--------------+---------+
# | invoice_id   | int     |
# | price        | int     |
# | user_id      | int     |
# +--------------+---------+
# invoice_id 是这张表的主键。
# 此表的每一行分别表示编号为 user_id 的顾客拥有有一张编号为 invoice_id、价格为 price 的发票。
#  
#
# 为每张发票 invoice_id 编写一个SQL查询以查找以下内容：
#
# customer_name：与发票相关的顾客名称。
# price：发票的价格。
# contacts_cnt：该顾客的联系人数量。
# trusted_contacts_cnt：可信联系人的数量：既是该顾客的联系人又是商店顾客的联系人数量（即：可信联系人的电子邮件存在于客户表中）。
# 将查询的结果按照 invoice_id 排序。
#
# 查询结果的格式如下例所示：
#
# Customers table:
# +-------------+---------------+--------------------+
# | customer_id | customer_name | email              |
# +-------------+---------------+--------------------+
# | 1           | Alice         | alice@leetcode.com |
# | 2           | Bob           | bob@leetcode.com   |
# | 13          | John          | john@leetcode.com  |
# | 6           | Alex          | alex@leetcode.com  |
# +-------------+---------------+--------------------+
# Contacts table:
# +-------------+--------------+--------------------+
# | user_id     | contact_name | contact_email      |
# +-------------+--------------+--------------------+
# | 1           | Bob          | bob@leetcode.com   |
# | 1           | John         | john@leetcode.com  |
# | 1           | Jal          | jal@leetcode.com   |
# | 2           | Omar         | omar@leetcode.com  |
# | 2           | Meir         | meir@leetcode.com  |
# | 6           | Alice        | alice@leetcode.com |
# +-------------+--------------+--------------------+
# Invoices table:
# +------------+-------+---------+
# | invoice_id | price | user_id |
# +------------+-------+---------+
# | 77         | 100   | 1       |
# | 88         | 200   | 1       |
# | 99         | 300   | 2       |
# | 66         | 400   | 2       |
# | 55         | 500   | 13      |
# | 44         | 60    | 6       |
# +------------+-------+---------+
# Result table:
# +------------+---------------+-------+--------------+----------------------+
# | invoice_id | customer_name | price | contacts_cnt | trusted_contacts_cnt |
# +------------+---------------+-------+--------------+----------------------+
# | 44         | Alex          | 60    | 1            | 1                    |
# | 55         | John          | 500   | 0            | 0                    |
# | 66         | Bob           | 400   | 2            | 0                    |
# | 77         | Alice         | 100   | 3            | 2                    |
# | 88         | Alice         | 200   | 3            | 2                    |
# | 99         | Bob           | 300   | 2            | 0                    |
# +------------+---------------+-------+--------------+----------------------+
# Alice 有三位联系人，其中两位(Bob 和 John)是可信联系人。
# Bob 有两位联系人, 他们中的任何一位都不是可信联系人。
# Alex 只有一位联系人(Alice)，并是一位可信联系人。
# John 没有任何联系人。

# 2、数据准备
drop table if exists Customers;
create table Customers
(
    customer_id   int,
    customer_name varchar(32),
    email         varchar(32)
);

drop table if exists Contacts;
create table Contacts
(
    user_id       int,
    contact_name  varchar(32),
    contact_email varchar(32)
);

drop table if exists Invoices;
create table Invoices
(
    invoice_id int,
    price      int,
    user_id    int
);

INSERT INTO customers (customer_id, customer_name, email) VALUES (1, 'Alice', 'alice@leetcode.com');
INSERT INTO customers (customer_id, customer_name, email) VALUES (2, 'Bob', 'bob@leetcode.com');
INSERT INTO customers (customer_id, customer_name, email) VALUES (13, 'John', 'john@leetcode.com');
INSERT INTO customers (customer_id, customer_name, email) VALUES (6, 'Alex', 'alex@leetcode.com');
select * from Customers;

INSERT INTO contacts (user_id, contact_name, contact_email) VALUES (1, 'Bob', 'bob@leetcode.com');
INSERT INTO contacts (user_id, contact_name, contact_email) VALUES (1, 'John', 'john@leetcode.com');
INSERT INTO contacts (user_id, contact_name, contact_email) VALUES (1, 'Jal', 'jal@leetcode.com');
INSERT INTO contacts (user_id, contact_name, contact_email) VALUES (2, 'Omar', 'omar@leetcode.com');
INSERT INTO contacts (user_id, contact_name, contact_email) VALUES (2, 'Meir', 'meir@leetcode.com');
INSERT INTO contacts (user_id, contact_name, contact_email) VALUES (6, 'Alice', 'alice@leetcode.com');
select * from Contacts;

INSERT INTO invoices (invoice_id, price, user_id) VALUES (77, 100, 1);
INSERT INTO invoices (invoice_id, price, user_id) VALUES (88, 200, 1);
INSERT INTO invoices (invoice_id, price, user_id) VALUES (99, 300, 2);
INSERT INTO invoices (invoice_id, price, user_id) VALUES (66, 400, 2);
INSERT INTO invoices (invoice_id, price, user_id) VALUES (55, 500, 13);
INSERT INTO invoices (invoice_id, price, user_id) VALUES (44, 60, 6);
select * from Invoices;


# 3、答案
# 3.1、答案1
-- 操 ，这题问的有问题，要不是看案例，根本看不懂
-- 1、查询基本属性
select n1.invoice_id, cus.customer_name, n1.price
from Invoices n1
         join Customers cus on cus.customer_id = n1.user_id
order by invoice_id;

-- 2、查询客户所有的联系人数量
select con1.user_id,
       count(con1.user_id) as contacts_cnt
from Contacts con1 group by con1.user_id;

-- 3、查询客户可信联系人数量
select cus2.customer_id, count(con1.user_id) as trusted_contacts_cnt
from Contacts con1
         right join Customers cus2 on cus2.customer_id = con1.user_id
group by cus2.customer_id;

-- 4、整合
select n1.invoice_id,
       cus.customer_name,
       n1.price,
       ifnull(contacts_cnt, 0)         as contacts_cnt,
       ifnull(trusted_contacts_cnt, 0) as trusted_contacts_cnt
from Invoices n1
         join Customers cus on cus.customer_id = n1.user_id
         left join (select con1.user_id, count(con1.user_id) as contacts_cnt
                    from Contacts con1
                    group by con1.user_id) m on m.user_id = n1.user_id
         left join (select con1.user_id, count(con1.user_id) as trusted_contacts_cnt
                    from Contacts con1
                    where con1.contact_name in (select cus3.customer_name from Customers cus3)
                    group by con1.user_id) n on n.user_id = n1.user_id

order by invoice_id
;


# 3.2、答案2
select i.invoice_id,
       cus1.customer_name,
       i.price,
       count(con1.contact_name) contacts_cnt,
       count(cus2.customer_name) trusted_contacts_cnt
from invoices i
         join customers cus1 on i.user_id = cus1.customer_id
         left join contacts con1 on i.user_id = con1.user_id
         left join customers cus2 on con1.contact_name  = cus2.customer_name
group by i.invoice_id,customer_name,price
order by i.invoice_id




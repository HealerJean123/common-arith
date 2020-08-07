# 1、指定日期的产品价格
# 产品数据表: Products
#
# +---------------+---------+
# | Column Name   | Type    |
# +---------------+---------+
# | product_id    | int     |
# | new_price     | int     |
# | change_date   | date    |
# +---------------+---------+
# 这张表的主键是 (product_id, change_date)。
# 这张表的每一行分别记录了 某产品 在某个日期 更改后 的新价格。
#  
#
# 写一段 SQL来查找在 2019-08-16 时全部产品的价格，假设所有产品在修改前的价格都是 10。
#
# 查询结果格式如下例所示：
#
# Products table:
# +------------+-----------+-------------+
# | product_id | new_price | change_date |
# +------------+-----------+-------------+
# | 1          | 20        | 2019-08-14  |
# | 2          | 50        | 2019-08-14  |
# | 1          | 30        | 2019-08-15  |
# | 1          | 35        | 2019-08-16  |
# | 2          | 65        | 2019-08-17  |
# | 3          | 20        | 2019-08-18  |
# +------------+-----------+-------------+
#
# Result table:
# +------------+-------+
# | product_id | price |
# +------------+-------+
# | 2          | 50    |
# | 1          | 35    |
# | 3          | 10    |
# +------------+-------+

# 2、准备数据
drop table if exists Products;
create table Products
(
    product_id  int,
    new_price   int,
    change_date date
);
INSERT INTO products (product_id, new_price, change_date) VALUES (1, 20, '2019-08-14');
INSERT INTO products (product_id, new_price, change_date) VALUES (2, 50, '2019-08-14');
INSERT INTO products (product_id, new_price, change_date) VALUES (1, 30, '2019-08-15');
INSERT INTO products (product_id, new_price, change_date) VALUES (1, 35, '2019-08-16');
INSERT INTO products (product_id, new_price, change_date) VALUES (2, 65, '2019-08-17');
INSERT INTO products (product_id, new_price, change_date) VALUES (3, 20, '2019-08-18');
select * from Products;


# 3、答案

-- 1、选择产品价格出现的最早日期

select p1.product_id,   min(p1.change_date) as minDate  from Products p1  where p1.product_id not in
(select p1.product_id  from Products p1  where p1.change_date = '2019-08-16' group by p1.product_id)
group by p1.product_id
union
select p1.product_id,    '2019-08-16' as minDate  from Products p1  where p1.change_date = '2019-08-16' group by p1.product_id


select p2.product_id from Products p2 group by p2.product_id;



-- 1、找到 产品被修改的日期
select product_id, max(change_date)
from products
where change_date <= '2019-08-16'
group by product_id;

-- 2、找到被产品日期所在的价格
select m.product_id, max_date, p.new_price from (
                                                    select product_id, max(change_date) max_date
                                                    from products
                                                    where change_date <= '2019-08-16'
                                                    group by product_id
                                                ) m  join products p on m.product_id = p.product_id and m.max_date = p.change_date
-- 2、还可以写成
select product_id, new_price
from products
where (product_id, change_date) in (
    select product_id, max(change_date)
    from products
    where change_date <= '2019-08-16'
    group by product_id;


-- 3、找到所有的产品的价格，包括没有修改的 left join
select distinct  p2.product_id ,ifnull(n.new_price, 10)  as price  from Products  p2 left join
(
select m.product_id, max_date, p.new_price from (
                                     select product_id, max(change_date) max_date
                                     from products
                                     where change_date <= '2019-08-16'
                                     group by product_id
                                 ) m  join products p on m.product_id = p.product_id and m.max_date = p.change_date
)n on p2.product_id = n.product_id;


-- 官方答案
select p1.product_id, ifnull(p2.new_price, 10) as price
from (
         select distinct product_id
         from products
     ) as p1 -- 所有的产品
         left join (
            select product_id, new_price
            from products
            where (product_id, change_date) in (
                select product_id, max(change_date)
                from products
                where change_date <= '2019-08-16'
                group by product_id
    )
) as p2 -- 在 2019-08-16 之前有过修改的产品和最新的价格
                   on p1.product_id = p2.product_id



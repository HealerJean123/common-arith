# 1、题目：按年度列出销售总额
#  Product 表：
#
# +---------------+---------+
# | Column Name   | Type    |
# +---------------+---------+
# | product_id    | int     |
# | product_name  | varchar |
# +---------------+---------+
# product_id 是这张表的主键。
# product_name 是产品的名称。
#  
#
# Sales 表：
#
# +---------------------+---------+
# | Column Name         | Type    |
# +---------------------+---------+
# | product_id          | int     |
# | period_start        | varchar |
# | period_end          | date    |
# | average_daily_sales | int     |
# +---------------------+---------+
# product_id 是这张表的主键。
# period_start 和 period_end 是该产品销售期的起始日期和结束日期，且这两个日期包含在销售期内。
# average_daily_sales 列存储销售期内该产品的日平均销售额。
#  
#
# 编写一段SQL查询每个产品每年的总销售额，并包含 product_id, product_name 以及 report_year 等信息。
#
# 销售年份的日期介于 2018 年到 2020 年之间。你返回的结果需要按 product_id 和 report_year 排序。
#
# 查询结果格式如下例所示：
#
# Product table:
# +------------+--------------+
# | product_id | product_name |
# +------------+--------------+
# | 1          | LC Phone     |
# | 2          | LC T-Shirt   |
# | 3          | LC Keychain  |
# +------------+--------------+
#
# Sales table:
# +------------+--------------+-------------+---------------------+
# | product_id | period_start | period_end  | average_daily_sales |
# +------------+--------------+-------------+---------------------+
# | 1          | 2019-01-25   | 2019-02-28  | 100                 |
# | 2          | 2018-12-01   | 2020-01-01  | 10                  |
# | 3          | 2019-12-01   | 2020-01-31  | 1                   |
# +------------+--------------+-------------+---------------------+
#
# Result table:
# +------------+--------------+-------------+--------------+
# | product_id | product_name | report_year | total_amount |
# +------------+--------------+-------------+--------------+
# | 1          | LC Phone     |    2019     | 3500         |
# | 2          | LC T-Shirt   |    2018     | 310          |
# | 2          | LC T-Shirt   |    2019     | 3650         |
# | 2          | LC T-Shirt   |    2020     | 10           |
# | 3          | LC Keychain  |    2019     | 31           |
# | 3          | LC Keychain  |    2020     | 31           |
# +------------+--------------+-------------+--------------+
# LC Phone 在 2019-01-25 至 2019-02-28 期间销售，该产品销售时间总计35天。销售总额 35*100 = 3500。
# LC T-shirt 在 2018-12-01 至 2020-01-01 期间销售，该产品在2018年、2019年、2020年的销售时间分别是31天、365天、1天，2018年、2019年、2020年的销售总额分别是31*10=310、365*10=3650、1*10=10。
# LC Keychain 在 2019-12-01 至 2020-01-31 期间销售，该产品在2019年、2020年的销售时间分别是：31天、31天，2019年、2020年的销售总额分别是31*1=31、31*1=31。



# 2、数据准备

drop table if exists Product;
create table Product
(
    product_id   int,
    product_name varchar(32)

);

drop table if exists Sales;
create table Sales
(
    product_id          int,
    period_start        varchar(32),
    period_end          date,
    average_daily_sales int
);
INSERT INTO product (product_id, product_name) VALUES (1, 'LC Phone ');
INSERT INTO product (product_id, product_name) VALUES (2, 'LC T-Shirt');
INSERT INTO product (product_id, product_name) VALUES (3, 'LC Keychain');
select * from Product;

INSERT INTO sales (product_id, period_start, period_end, average_daily_sales) VALUES (1, '2019-01-25', '2019-02-28', 100);
INSERT INTO sales (product_id, period_start, period_end, average_daily_sales) VALUES (2, '2018-12-01', '2020-01-01', 10);
INSERT INTO sales (product_id, period_start, period_end, average_daily_sales) VALUES (3, '2019-12-01', '2020-01-31', 1);
select * from Sales;


# 3、答案
-- 1、简单查询
select p.product_id, p.product_name from Product p ;

-- 2、日销售额转年销售额
select s.product_id   from Sales s;


select date('2020-01-01');

# 如果起始日期小于等于2019-01-01， 此时如果截止日期比 2018-12-31大的话，则end就是 2018-12-31
(select Sales.product_id,
        product_name,
        '2018'                                                         as report_year,
        if(period_start <= '2018-12-31', (datediff(if(period_end > '2018-12-31', '2018-12-31', period_end),
                                                   if(period_start < '2018-01-01', '2018-01-01' , period_start)) +
                                          1) * average_daily_sales, 0) as total_amount
 from Product
          join Sales on Sales.product_id = Product.product_id
      # # 防止计算后有负数以及不存在的请
 having total_amount > 0
)
union
(
    select Sales.product_id,
           product_name,
           '2019'                                                         as report_year,
           if(period_start <= '2019-12-31', (datediff(if(period_end > '2019-12-31', '2019-12-31', period_end),
                                                      if(period_start < '2019-01-01', '2019-01-01', period_start)) +
                                             1) * average_daily_sales, 0) as total_amount
    from Product
             join Sales on Sales.product_id = Product.product_id
    having total_amount > 0
)
union
(
    select Sales.product_id,
           product_name,
           '2020'                                                         as report_year,
           if(period_start <= '2020-12-31', (datediff(if(period_end > '2020-12-31', '2020-12-31', period_end),
                                                      if(period_start < '2020-01-01','2020-01-01', period_start)) +
                                             1) * average_daily_sales, 0) as total_amount
    from Product
             join Sales on Sales.product_id = Product.product_id
    having total_amount > 0
)
order by product_id, report_year;



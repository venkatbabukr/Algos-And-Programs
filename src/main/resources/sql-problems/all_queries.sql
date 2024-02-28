------
--- List top 3 brands in Information technology based on profitability
---
select * from industry where industry.type='Information Technology' order by profit desc limit 3;

------
--- Sales, Member & Product tables: Find the first product each consumer ordered after enrolling for membership.
---
select s.customer_id, p.name
 from sales s
 join product p on s.product_id=p.id
 join (select s.customer_id, min(s.order_date) as req_order_date
        from sales s join member m on s.customer_id=m.customer_id and s.order_date > m.enroll_date
        group by s.customer_id) fo_data on s.customer_id=fo_data.customer_id and s.order_date=fo_data.req_order_date
 order by s.customer_id;

------
--- Select NPV of each query
--- https://leetcode.ca/all/1421.html
---
select q.id, coalesce(n.npv, 0) from queries q left join npv n on q.id=n.id and q.year=n.year order by q.id;
 
  
------
--- Find number of food items sales and non-food items sales from sales table.
--- Food items = Fruits & Vegetables, Eggs & Meat
--- 
select fs_count.sales_count as food_sales_cnt, (s_count.sales_count - fs_count.sales_count) as non_food_sales_cnt
  from
  (select 1 as id, count(*) as sales_count from sales) s_count
  join (select 1 as id, count(*) as sales_count from sales where item in ('Fruits & Vegetables', 'Eggs & Meat')) fs_count
  on s_count.id = fs_count.id;

------
--- Find median in data
---
/* --------------------
   Case Study Questions
   --------------------*/

-- 1. What is the total amount each customer spent at the restaurant?

select
    s.customer_id, sum(m.price)
from
    dannys_diner.sales s join dannys_diner.menu m on s.product_id=m.product_id
group by s.customer_id
order by s.customer_id;

-- 2. How many days has each customer visited the restaurant?
select
    s.customer_id, count(distinct s.order_date)
from
    dannys_diner.sales s
group by s.customer_id
order by s.customer_id;

-- 3. What was the first item from the menu purchased by each customer?

-- Option #1 using ROW_NUMBER()
WITH RankedProducts AS (
    SELECT
        s.customer_id,
        s.order_date,
        m.product_name,
        ROW_NUMBER() OVER (PARTITION BY s.customer_id ORDER BY s.order_date) AS row_num
    FROM
        dannys_diner.sales s join dannys_diner.menu m on s.product_id=m.product_id
)
SELECT
    customer_id,
    order_date as first_order_date,
    product_name as first_order
FROM
    RankedProducts
WHERE
    row_num = 1;

-- Option #2 using DENSE_RANK()
WITH ranked_sales AS (
  SELECT 
    sales.customer_id, 
    sales.order_date, 
    menu.product_name,
    DENSE_RANK() OVER(
      PARTITION BY sales.customer_id 
      ORDER BY sales.order_date) AS rank
  FROM dannys_diner.sales
  JOIN dannys_diner.menu
    ON sales.product_id = menu.product_id
)
SELECT 
  customer_id,
  order_date as first_order_date,
  product_name as first_order
FROM ranked_sales
WHERE rank = 1;

-- Option #3: Same as DENSE_RANK but doesn't use CTE.
select
  s.customer_id, s.order_date as first_order_date, m.product_name as first_order
from dannys_diner.sales s
join dannys_diner.menu m on s.product_id=m.product_id
join (select s.customer_id, min(s.order_date) as fo_date
        from dannys_diner.sales s group by s.customer_id) fo_table on s.customer_id=fo_table.customer_id and s.order_date = fo_table.fo_date
order by s.customer_id;

-- 4. What is the most purchased item on the menu and how many times was it purchased by all customers?

WITH MaxPurchasedProduct AS (
  SELECT
      s.product_id, COUNT(*) as purchase_count
  FROM
      dannys_diner.sales s
  GROUP BY s.product_id
  ORDER BY purchase_count desc
  LIMIT 1
)
SELECT
  s.customer_id, count(*) as purchase_count
FROM
  dannys_diner.sales s JOIN MaxPurchasedProduct mpp on s.product_id=mpp.product_id
GROUP BY s.customer_id
ORDER BY s.customer_id;

-- 5. Which item was the most popular for each customer?
WITH RankedProductPurchase AS (
  select
    s.customer_id, m.product_name, count(*) as purchase_count,
    DENSE_RANK() OVER(PARTITION BY s.customer_id ORDER BY COUNT(s.customer_id) DESC) as rank_by_purchase_count
  from
    dannys_diner.sales s join dannys_diner.menu m on s.product_id=m.product_id
  group by s.customer_id, m.product_name
)
SELECT
  customer_id, product_name, purchase_count, rank_by_purchase_count
FROM
  RankedProductPurchase
WHERE
  rank_by_purchase_count = 1;

-- 6. Which item was purchased first by the customer after they became a member?
select
  s.customer_id, s.order_date, m.product_name
from
  dannys_diner.sales s
  join dannys_diner.menu m on s.product_id=m.product_id
  join (
    select s.customer_id, min(s.order_date) as fo_date
    from
      dannys_diner.sales s join dannys_diner.members m on s.customer_id=m.customer_id
    where
      s.order_date > m.join_date
    group by s.customer_id) fo_table on s.customer_id=fo_table.customer_id and s.order_date = fo_table.fo_date;
    
-- 7. Which item was purchased just before the customer became a member?
select
  s.customer_id, s.order_date, m.product_name
from
  dannys_diner.sales s
  join dannys_diner.menu m on s.product_id=m.product_id
  join (
    select s.customer_id, max(s.order_date) as pfo_date
    from
      dannys_diner.sales s join dannys_diner.members m on s.customer_id=m.customer_id
    where
      s.order_date < m.join_date
    group by s.customer_id) pfo_table on s.customer_id=pfo_table.customer_id and s.order_date = pfo_table.pfo_date;

-- 8. What is the total items and amount spent for each member before they became a member?
select
  s.customer_id, count(s.product_id) as total_products_purchased, sum(m.price) as total_amount_spent
from
  dannys_diner.sales s
  join dannys_diner.menu m on s.product_id=m.product_id
  join dannys_diner.members mm on s.customer_id=mm.customer_id
where
  s.order_date < mm.join_date
group by s.customer_id
order by s.customer_id;

-- 9.  If each $1 spent equates to 10 points and sushi has a 2x points multiplier - how many points would each customer have?

select
    s.customer_id,
    sum(case when m.product_name = 'sushi' then 20 * m.price else 10 * m.price end) as total_points
from
  dannys_diner.sales s join dannys_diner.menu m on s.product_id=m.product_id
group by s.customer_id;

-- 10. In the first week after a customer joins the program (including their join date) they earn 2x points on all items, not just sushi - how many points do customer A and B have at the end of January?

WITH offer_dates_cte AS (
  SELECT 
    customer_id, 
    join_date, 
    join_date + 6 AS valid_date
  FROM dannys_diner.members)
SELECT
  s.customer_id, 
  SUM(CASE
    WHEN m.product_name = 'sushi' THEN 2 * 10 * m.price
    WHEN s.order_date BETWEEN od.join_date AND od.valid_date THEN 2 * 10 * m.price
    ELSE 10 * m.price END) AS points
FROM
  dannys_diner.sales s
  join offer_dates_cte od on s.customer_id=od.customer_id
  join dannys_diner.menu m on s.product_id=m.product_id
WHERE
  s.order_date >= '2021-01-01' and s.order_date < '2021-02-01'
GROUP BY s.customer_id
ORDER BY s.customer_id;

-- Join All The Things
-- Create the raw excel data
select
  s.customer_id,
  s.order_date,
  m.product_name,
  m.price,
  case when s.order_date >= mm.join_date then 'Y' else 'N' END as member
from
  dannys_diner.sales s
  join dannys_diner.menu m on s.product_id = m.product_id
  left join dannys_diner.members mm on s.customer_id = mm.customer_id
order by s.customer_id, s.order_date, m.product_name;

-- Create the ranked excel data

-----
--- References
---

--- 1. https://medium.com/analytics-vidhya/8-week-sql-challenge-case-study-week-1-dannys-diner-2ba026c897ab
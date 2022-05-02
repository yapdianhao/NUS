with maxPizzaPrice as 
    (select rname, max(price) as maxPrice
     from Sells
     group by rname
    )

select m1.rname, m2.rname
from maxPizzaPrice m1, maxPizzaPrice m2
where  m1.maxPrice > m2.maxPrice;
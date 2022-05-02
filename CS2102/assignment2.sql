-- lum wei boon CS2102 Assignment answers
CREATE OR REPLACE VIEW v1 (pizza) AS
	SELECT distinct pizza
	FROM Restaurants r, Sells s
	WHERE
        r.rname = s.rname and  
        area = (
        select area
        from Customers
        where cname = 'Bob'
    );

-- Question 2 Find all customers who like at least two different pizzas.
CREATE OR REPLACE VIEW v2 (cname) AS 
    SELECT distinct cname
        FROM Likes l
        GROUP BY cname
        having COUNT(DISTINCT pizza) >= 2;

CREATE OR REPLACE VIEW v3 (rname1, rname2) AS 
    select distinct s1.rname, s2.rname 
    from Sells s1, Sells s2
    where s1.rname <> s2.rname
    and s1.pizza = s2.pizza
    except 
    select distinct s1.rname, s2.rname 
    from Sells s1, Sells s2
    where s1.rname <> s2.rname
    and s1.pizza = s2.pizza
    and s1.price <= s2.price;

CREATE OR REPLACE VIEW v4 (rname) AS 
    select rname
    from Restaurants
    where area = 'Central'
    union
    select rname
    from Sells
    GROUP BY rname
    having count(distinct pizza) >= 10
    union
    select rname
    from Sells s1
    where not exists (
        select 1
        from Sells s2
        where s1.rname = s2.rname
        and s2.price > 20
    )
;

CREATE OR REPLACE VIEW v5 (rname) AS 
    select distinct rname
    from Sells s
    where exists (
        select 1
        from Sells s1, Sells s2
        where s1.rname = s2.rname
        and s.rname = s1.rname
        and s1.pizza <> s2.pizza
        and s1.price + s2.price <= 40
    );


CREATE OR REPLACE VIEW v6 (rname, pizza1, pizza2, pizza3, totalcost) AS 
    with validPizzas as (
        select LC.pizza as Cpizza, LL.pizza as Lpizza, LM.pizza as Mpizza
        from Likes LM, Likes LL, Likes LC
        where LM.cname = 'Moe'
        and LL.cname = 'Larry'
        and LC.cname = 'Curly'
        and LM.pizza <> LL.pizza
        and LM.pizza <> LC.pizza
        and LL.pizza <> LC.pizza
        and ((LM.pizza in (select pizza from Likes where cname = 'Larry')) or (LM.pizza in (select pizza from Likes where cname = 'Curly')))
        and ((LC.pizza in (select pizza from Likes where cname = 'Moe')) or (LC.pizza in (select pizza from Likes where cname = 'Larry')))
        and ((LL.pizza in (select pizza from Likes where cname = 'Moe')) or (LL.pizza in (select pizza from Likes where cname = 'Curly')))
    )

    select distinct s1.rname,
    case 
    when (validPizzas.Cpizza < validPizzas.Lpizza) and (validPizzas.Cpizza < validPizzas.Mpizza) then validPizzas.Cpizza
    when (validPizzas.Lpizza < validPizzas.Mpizza) and (validPizzas.Lpizza < validPizzas.Cpizza) then validPizzas.Lpizza
    else validPizzas.Mpizza
    end as pizza1,
    case 
    when ((validPizzas.Cpizza < validPizzas.Lpizza) and (validPizzas.Lpizza < validPizzas.Mpizza))
        or ((validPizzas.Mpizza < validPizzas.Lpizza) and (validPizzas.Lpizza < validPizzas.Cpizza)) then validPizzas.Lpizza
    when ((validPizzas.Lpizza < validPizzas.Cpizza) and (validPizzas.Cpizza < validPizzas.Mpizza))
        or ((validPizzas.Mpizza < validPizzas.Cpizza) and (validPizzas.Cpizza < validPizzas.Lpizza)) then validPizzas.Cpizza
    else validPizzas.Mpizza
    end as pizza2,
    case 
    when ((validPizzas.Cpizza < validPizzas.Lpizza) and (validPizzas.Mpizza < validPizzas.Lpizza)) then validPizzas.Lpizza
    when ((validPizzas.Mpizza < validPizzas.Cpizza) and (validPizzas.Lpizza < validPizzas.Cpizza)) then validPizzas.Cpizza
    else validPizzas.Mpizza
    end as pizza3,
    s1.price + s2.price + s3.price
    from Sells s1, Sells s2, Sells s3, validPizzas
    where s1.rname = s2.rname
    and s1.rname = s3.rname
    and s1.pizza = validPizzas.Cpizza
    and s2.pizza = validPizzas.Lpizza
    and s3.pizza = validPizzas.Mpizza
    and s1.price + s2.price + s3.price <= 80
;

CREATE OR REPLACE VIEW v7 (rname) AS 
    with restaurantStats as (
        select distinct rname, count(distinct pizza) as numPizza, max(price) - min(price) as priceRange
        from Sells
        group by rname
        union 
        select r.rname as rname, 0 as numPizza, 0 as priceRange
        from Restaurants r
        where not exists(
            select 1
            from Sells
            where Sells.rname = r.rname
        )
    )
    select distinct rs0.rname
    from restaurantStats rs0
    where not exists (
        select 1
        from restaurantStats rs1, restaurantStats rs2 
        where rs0.rname = rs1.rname 
        and rs1.rname <> rs2.rname
        and (
            (rs2.numPizza > rs1.numPizza and rs2.priceRange >= rs1.priceRange)
            or 
            (rs2.numPizza >= rs1.numPizza and rs2.priceRange > rs1.priceRange)
        )

    )
;

CREATE OR REPLACE VIEW v8 (area, numCust, numRest, maxPrice) AS 
    select C.area as area, count(distinct C.cname) as numCust, count(distinct R.rname) as numRest,
    case 
        when max(S.price) is null then 0
        else max(S.price)
    end as maxPrice
    from Customers C natural left outer join
    (Restaurants R natural left outer join Sells S)
    group by C.area
;

CREATE OR REPLACE VIEW v9 (cname) AS 
    select distinct cname
    from Likes
    except
    select distinct cname
    from (Likes natural left outer join Customers) natural left outer join 
        (Sells natural left outer join Restaurants)
    group by (pizza, cname)
    having count(distinct rname) <= 1;


CREATE OR REPLACE VIEW v10 (pizza) AS 
    with areaPopularPizzas as (
        select r.area, s.pizza, count(distinct r.rname) as popularCount
        from Sells s, Restaurants r
        WHERE s.rname = r.rname
        group by (r.area, s.pizza)
    ),
    areaPopularOnly as (
        select a0.pizza, count(distinct area) as popularAreaCount
        from areaPopularPizzas a0
        where popularCount = 
        (
            select max(popularCount)
            from areaPopularPizzas a1
            where a1.area = a0.area
        )
        group by a0.pizza
    )
    select pizza 
    from areaPopularOnly
    where popularAreaCount = (
        select max(popularAreaCount)
        from areaPopularOnly
    );
   
-- q1
CREATE OR REPLACE VIEW v1 (pizza) AS
    SELECT DISTINCT pizza
    FROM Customers C, Restaurants R, Sells S
    WHERE C.cname = 'Bob' AND C.area = R.area and R.rname = S.rname;

-- q2
CREATE OR REPLACE VIEW v2 (cname) AS
    SELECT DISTINCT cname
    FROM Likes
    GROUP BY cname
    HAVING COUNT(*) >= 2;

-- q3
CREATE OR REPLACE VIEW v3 (rname1, rname2) AS
    SELECT DISTINCT S1.rname, S2.rname
    FROM Sells S1, Sells S2
    WHERE S1.rname <> S2.rname AND S1.pizza = S2.pizza
    EXCEPT
    SELECT DISTINCT S1.rname, S2.rname
    FROM Sells S1, Sells S2
    WHERE S1.rname <> S2.rname AND S1.pizza = S2.pizza AND S1.price <= S2.price;

-- q4
CREATE OR REPLACE VIEW v4(rname) AS
    SELECT DISTINCT rname
    FROM (
            SELECT rname
            FROM Restaurants
            WHERE area = 'Central'
            UNION
            SELECT rname
            FROM Sells
            GROUP BY rname
            HAVING count(*) >= 10
            UNION
            SELECT rname
            FROM Sells S1
            WHERE NOT EXISTS (
                SELECT 1
                FROM Sells S2
                WHERE S1.rname = S2.rname AND S2.price > 20
                )
        ) AS X;

-- q5
CREATE OR REPLACE VIEW v5 (rname) AS
    SELECT DISTINCT S1.rname
    FROM Sells S1, Sells S2
    WHERE S1.rname = S2.rname AND S1.pizza <> S2.pizza AND S1.price + S2.price <= 40;

-- q6
CREATE OR REPLACE VIEW v6 (rname, pizza1, pizza2, pizza3, totalcost) AS
    SELECT DISTINCT S1.rname, S1.pizza, S2.pizza, S3.pizza, S1.price + S2.price + S3.price
    FROM Sells S1, Sells S2, Sells S3
    WHERE S1.rname = S2.rname AND
          S2.rname = S3.rname AND
          S1.price + S2.price + S3.price <= 80 AND
          S1.pizza < S2.pizza AND S2.pizza < S3.pizza AND
          EXISTS(
              SELECT 1
              FROM Likes L
              WHERE (L.cname = 'Moe' OR L.cname = 'Larry' OR L.cname = 'Curly') AND L.pizza = S1.pizza
              ) AND
          EXISTS (
              SELECT 1
              FROM Likes L
              WHERE (L.cname = 'Moe' OR L.cname = 'Larry' OR L.cname = 'Curly') AND L.pizza = S2.pizza
              ) AND
          EXISTS (
              SELECT 1
              FROM Likes L
              WHERE (L.cname = 'Moe' OR L.cname = 'Larry' OR L.cname = 'Curly') AND L.pizza = S3.pizza
              ) AND
          EXISTS (
              SELECT 1
              FROM Likes L
              WHERE L.pizza = S1.pizza AND (L.cname = 'Moe' OR L.cname = 'Larry' OR L.cname = 'Curly')
              HAVING COUNT(*) >= 2
              ) AND
          EXISTS (
              SELECT 1
              FROM Likes L
              WHERE L.pizza = S2.pizza AND (L.cname = 'Moe' OR L.cname = 'Larry' OR L.cname = 'Curly')
              HAVING COUNT(*) >= 2
              ) AND
          EXISTS (
              SELECT 1
              FROM Likes L
              WHERE L.pizza = S3.pizza AND (L.cname = 'Moe' OR L.cname = 'Larry' OR L.cname = 'Curly')
              HAVING COUNT(*) >= 2
              );

-- q7
CREATE OR REPLACE VIEW v7 (rname) AS
    WITH
        pizzaCount AS
        (
            SELECT rname, COUNT(*) AS numPizza
            FROM Sells
            GROUP BY rname
            UNION
            SELECT rname, 0
            FROM Restaurants
            WHERE rname NOT IN (
                SELECT rname
                FROM Sells
                )
        ),
        pizzaRange AS
        (
            SELECT rname, MAX(price) - MIN(price) AS priceRange
            FROM Sells
            GROUP BY rname
            UNION
            SELECT rname, numPizza
            FROM pizzaCount
            WHERE numPizza = 0
        )
    SELECT DISTINCT R0.rname
    FROM Restaurants R0
    EXCEPT
    SELECT DISTINCT R1.rname
    FROM Restaurants R1, Restaurants R2
    WHERE R1.rname <> R2.rname AND (
            (
                        (
                            SELECT P.numPizza
                            FROM pizzaCount P
                            WHERE P.rname = R2.rname
                        ) >
                        (
                            SELECT P.numPizza
                            FROM pizzaCount P
                            WHERE P.rname = R1.rname
                        ) AND
                        (
                            SELECT P.priceRange
                            FROM pizzaRange P
                            WHERE P.rname = R2.rname
                        ) >=
                        (
                            SELECT P.priceRange
                            FROM pizzaRange P
                            WHERE P.rname = R1.rname
                        )
                ) OR
            (
                        (
                            SELECT P.numPizza
                            FROM pizzaCount P
                            WHERE P.rname = R2.rname
                        ) >=
                        (
                            SELECT P.numPizza
                            FROM pizzaCount P
                            WHERE P.rname = R1.rname
                        ) AND
                        (
                            SELECT P.priceRange
                            FROM pizzaRange P
                            WHERE P.rname = R2.rname
                        ) >
                        (
                            SELECT P.priceRange
                            FROM pizzaRange P
                            WHERE P.rname = R1.rname
                        )
                )
        );

-- q8
CREATE OR REPLACE VIEW v8 (area, numCust, numRest, maxPrice) AS
    WITH areaTable AS
        (
            SELECT DISTINCT area
            FROM Customers
            EXCEPT
            SELECT DISTINCT area
            FROM Restaurants
        ),
         countCustomers AS
        (
            SELECT area, COUNT(*) AS numCustomers
            FROM Customers
            GROUP BY area
        ),
         countRestaurants AS
        (
            SELECT R.area, COUNT(*) AS numRestaurants
            FROM Restaurants R
            GROUP BY R.area
            UNION
            SELECT area, 0 AS numRestaurants
            FROM areaTable
        ),
         mostExpensive AS
        (
            SELECT area, MAX(price) AS maxPizzaPrice
            FROM Restaurants R, Sells S
            WHERE R.rname = S.rname
            GROUP BY area
            UNION
            SELECT area, 0 AS maxPizzaPrice
            FROM areaTable
            UNION
            SELECT area, 0
            FROM Sells S1 RIGHT JOIN Restaurants R1 ON R1.rname = S1.rname
            GROUP BY area
            HAVING COUNT(pizza) = 0
        )
    SELECT DISTINCT C1.area, numCustomers, numRestaurants, maxPizzaPrice
    FROM countCustomers C1, countRestaurants C2, mostExpensive M
    WHERE C1.area = C2.area and C2.area = M.area;

-- q9
CREATE OR REPLACE VIEW v9 (cname) AS
    WITH
          pizzaCnameRname AS
             (
                 SELECT DISTINCT C.cname AS cname, S.pizza AS pizza, R.rname AS rname
                 FROM Sells S, Customers C,Restaurants R, Likes L
                 WHERE S.rname = R.rname AND R.area = C.area AND L.pizza = S.pizza and C.cname = L.cname
             ),
          pizzaCname AS
             (
                 SELECT DISTINCT cname, pizza, COUNT(*) as numRestaurants
                 FROM pizzaCnameRname
                 GROUP BY (cname, pizza)
             ),
          satisfyPizzas AS
              (
                  SELECT cname, COUNT(*) as numPizzas
                  FROM pizzaCname
                  GROUP BY cname
                  HAVING MIN(numRestaurants) >= 2
              ),
          satisfyCustomers AS
              (
                  SELECT cname
                  FROM satisfyPizzas S
                  WHERE numPizzas = (
                        SELECT COUNT(*)
                        FROM Likes L
                        WHERE L.cname = S.cname
                        GROUP BY L.cname
                      )
              )
    SELECT DISTINCT cname
    FROM satisfyCustomers;

-- q10
CREATE OR REPLACE VIEW v10 (pizza) AS
    WITH pizzaRestaurantArea AS
        (
            SELECT S.pizza AS pizza, R.area as area, COUNT(*) AS numRestaurants
            FROM Sells S, Restaurants R
            WHERE S.rname = R.rname
            GROUP BY (S.pizza, R.area)
        ),
        popularPizzasInEachArea AS (
            SELECT pizza, COUNT(*) as areaNum
            FROM pizzaRestaurantArea P1
            WHERE numRestaurants >= ALL (
                    SELECT P2.numRestaurants
                    FROM pizzaRestaurantArea P2
                    WHERE P2.area = P1.area
                )
            GROUP BY pizza
        )
    SELECT pizza
    FROM popularPizzasInEachArea
    WHERE areaNum >= ALL (
            SELECT P2.areaNum
            FROM popularPizzasInEachArea P2
        );


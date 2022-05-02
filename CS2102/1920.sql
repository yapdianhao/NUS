CREATE VIEW v16(cid) AS
    WITH OrdersGreaterThan200 AS (
        SELECT o1.oid AS oid
        FROM Orders O1 INNER JOIN OrderItems O2 on O2.oid = O1.oid
        GROUP BY o1.oid
        HAVING SUM(O2.qty * (SELECT price FROM Items WHERE iid = O2.iid))
    )
    SELECT cid
    FROM (Customers C INNER JOIN Orders O ON O.cid = C.cid) AS CustomerOrderPairs
    WHERE EXISTS (
        SELECT 1 FROM OrdersGreaterThan200
        WHERE oid = CustomerOrderPairs.oid);


CREATE VIEW v17(cid) AS
    WITH SpendingByMonth AS (
        SELECT SUM(price * qty) AS MonthlySpend, cid, month
        FROM Orders NATURAL JOIN OrderItems NATURAL JOIN Customers
        WHERE cid = O.cid
        GROUP BY cid, month
        )
    )
    SELECT DISTINCT cid
    FROM Orders O
    WHERE 200 <= ALL (
        SELECT MonthlySpend
        FROM SpendingByMonth
        WHERE cid = O.cid
    )
    AND (SELECT MonthlySpend
        FROM SpendingByMonth S
        WHERE S.cid = O.cid AND S.month = O.month) >= ALL (
            SELECT MonthlySpend
            FROM SpendingByMonth S2
            WHERE S2.month = O.month
        );

WITH PizzaRestaurant AS (
    SELECT pizza, area, COUNT(*) AS numRest
    FROM Sells NATURAL JOIN Restaurants
    GROUP BY pizza, area
),
PopularPizzas AS (
    SELECT pizza, (SELECT COUNT(*)
        FROM ( SELECT PR.area
                     FROM PizzaRestaurant PR
                     WHERE PR.pizza = P.pizza
                       AND PR.numRest = (SELECT MAX(numRest) FROM PizzaRestaurant WHERE area = PR.area)
            ) AS SatisfyAreas
        ) AS popularAreas
    FROM Pizzas P
)
SELECT pizza
FROM PopularPizzas
WHERE popularAreas = (SELECT MAX(popularAreas) FROM PopularPizzas);

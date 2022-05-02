-- find restaurants that sell some pizza at a higher price than any pizza sell by Corleone Corner. Exclude Corleone Corner from the result.
SELECT DISTINCT S1.rname
FROM Sells as S1, Sells as S2
WHERE S1.rname <> 'Corleone Corner' and S2.rname = 'Corleone Corner' and S1.price > S2.price;

-- CS2102 Tutorial 2
-- Yap Dian Hao
-- Q3B

CREATE TABLE Customers (
    cust_id integer unique,
    name varchar(100) not null,
    e-mail varchar(100),
);

CREATE TABLE Purchase (
    pid integer,
    purchase_date timestamp,
    cust_id integer,
    unique(purchase_date, cust_id),
    foreign key (cust_id) references Customers(cust_id);
);


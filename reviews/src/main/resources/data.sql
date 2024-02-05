DROP TABLE IF EXISTS ratings;
CREATE TABLE ratings AS SELECT * FROM CSVREAD('classpath:ratings.csv');
--CREATE TABLE IF NOT EXISTS `customer` (
--  `customer_id` int AUTO_INCREMENT  PRIMARY KEY,
--  `name` varchar(100) NOT NULL,
--  `email` varchar(100) NOT NULL,
--  `mobile_number` varchar(20) NOT NULL,
--  `created_at` date NOT NULL,
--  `created_by` varchar(20) NOT NULL,
--  `updated_at` date DEFAULT NULL,
--    `updated_by` varchar(20) DEFAULT NULL
--);

CREATE TABLE IF NOT EXISTS `flight` (
  `id` int NOT NULL,
  `destination` varchar(100) NOT NULL,
  `origin` varchar(100) NOT NULL,
  `speed_series` varchar(100) ,
  `flight` varchar(200) NOT NULL,
  `created_at` date NOT NULL,
   `created_by` varchar(20) NOT NULL,
   `updated_at` date DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL,
    `capacity` int NOT NULL,
    `DEPARTURE_TIME` date NOT NULL,
    `flight_number` varchar(10) NOT NULL
);
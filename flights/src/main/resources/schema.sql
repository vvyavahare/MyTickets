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

CREATE TABLE IF NOT EXISTS `acc` (
  `id` int NOT NULL,
  `destination` varchar(100) NOT NULL,
  `origin` varchar(100) NOT NULL,
  `speed_series` varchar(100) NOT NULL,
  `flight` varchar(200) NOT NULL,
  `created_at` date NOT NULL,
   `created_by` varchar(20) NOT NULL,
   `updated_at` date DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL
);
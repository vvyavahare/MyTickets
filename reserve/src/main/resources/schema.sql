CREATE TABLE IF NOT EXISTS `passenger` (
  `passenger_id` int NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `prefix` varchar(100) ,
  `created_at` date NOT NULL,
   `created_by` varchar(20) NOT NULL,
   `updated_at` date DEFAULT NULL,
   `date_of_birth` date NOT NULL,
    `updated_by` varchar(20) DEFAULT NULL
);
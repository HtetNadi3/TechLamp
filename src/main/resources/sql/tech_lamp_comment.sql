CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(45) DEFAULT NULL,
  `post_id` int DEFAULT NULL,
  `delete_flag` tinyint DEFAULT NULL,
  `created_user_id` int DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
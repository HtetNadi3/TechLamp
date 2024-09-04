DROP TABLE IF EXISTS `users`;
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL unique,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL unique,
    role VARCHAR(20) NOT NULL
);
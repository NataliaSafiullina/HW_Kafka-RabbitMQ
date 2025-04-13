CREATE TABLE IF NOT EXISTS `netology`.`credit_requests` (
    `id`INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `amount` DECIMAL(9,2) NOT NULL,
    `term` INT NOT NULL CHECK (`term`>= 0),
    `income` DECIMAL(9,2),
    `credit_load` DECIMAL(9,2),
    `rating` INT,
    `status` VARCHAR(20) DEFAULT 'in processing',
    PRIMARY KEY (`id`)
);
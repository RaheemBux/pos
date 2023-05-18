/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.5.5-10.4.25-MariaDB : Database - battery_scrab_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`battery_scrab_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `battery_scrab_db`;

/*Table structure for table `customers` */

DROP TABLE IF EXISTS `customers`;

CREATE TABLE `customers` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `contact` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `customer_type` enum('CUSTOMER','VENDOR','CUSTOMER_VENDOR') NOT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `customer_id_UNIQUE` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `customers` */

insert  into `customers`(`customer_id`,`name`,`email`,`contact`,`address`,`customer_type`) values (1,'Test','test@gmail.com','78978789789','Habbibb','CUSTOMER');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `code` varchar(45) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `product_id_UNIQUE` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `product` */

insert  into `product`(`product_id`,`name`,`code`,`quantity`,`price`) values (1,'Product','2224',0,0);

/*Table structure for table `purchase` */

DROP TABLE IF EXISTS `purchase`;

CREATE TABLE `purchase` (
  `purchase_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `purchase_date` datetime DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `unit` enum('KG','MT') DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `amount_paid` int(11) DEFAULT NULL,
  `amount_remaining` int(11) DEFAULT NULL,
  `total_amount` int(11) DEFAULT NULL,
  `purchase_number` varchar(45) DEFAULT NULL,
  `payment_type` enum('CASH','CHEQUE','ONLINE') DEFAULT NULL,
  `is_taxable` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`purchase_id`),
  UNIQUE KEY `purchase_id_UNIQUE` (`purchase_id`),
  KEY `purchased_product_id` (`product_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `purchase_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`),
  CONSTRAINT `purchased_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `purchase` */

/*Table structure for table `sales` */

DROP TABLE IF EXISTS `sales`;

CREATE TABLE `sales` (
  `sales_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `sales_date` datetime DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `unit` enum('KG','MT') DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `sales_number` varchar(100) DEFAULT NULL,
  `amount_paid` int(11) DEFAULT NULL,
  `amount_remaining` int(11) DEFAULT NULL,
  `total_amount` int(11) DEFAULT NULL,
  `payment_type` enum('CASH','CHEQUE','ONLINE') DEFAULT NULL,
  `is_taxable` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`sales_id`),
  KEY `product_id` (`product_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `sales_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `sales_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sales` */

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `contact` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `users` */

insert  into `users`(`user_id`,`name`,`email`,`password`,`contact`,`address`) values (1,'Sanaullah','sana@gmail.com','saan123','+9731312313123','Palm Jumera beach Dubai');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
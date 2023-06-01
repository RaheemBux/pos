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
  `contact1` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `customer_type` enum('CUSTOMER','VENDOR','CUSTOMER_VENDOR') NOT NULL,
  `emirates_id` varchar(255) DEFAULT NULL,
  `expiry_Date` date DEFAULT NULL,
  `contact2` varchar(45) DEFAULT NULL,
  `contact3` varchar(45) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `customer_id_UNIQUE` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

/*Data for the table `customers` */

insert  into `customers`(`customer_id`,`name`,`email`,`contact1`,`address`,`customer_type`,`emirates_id`,`expiry_Date`,`contact2`,`contact3`,`created_date`,`created_by`,`last_modified_date`,`last_modified_by`) values (1,'Test','test@gmail.com','78978789789','Habbibb','CUSTOMER_VENDOR','EM435535','2023-05-31','4252557','25252255',NULL,NULL,NULL,NULL),(3,'Vendor1','vendor1@gmail.com','09242425','UAE','CUSTOMER_VENDOR','Em546','2023-05-31','325252','2525255',NULL,NULL,NULL,NULL),(4,'Vendor2','vendor2@gmail.com','09242425232','DUBAI','VENDOR',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'Sanaullah','sanaullah@gmail.com','23482782784','Abu Dhabi','VENDOR',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,'hello','hello@gmail.com','8972352253','Dubaiiii','CUSTOMER_VENDOR','EM3535','2023-05-31','4252557','25252255',NULL,NULL,NULL,NULL),(7,'Shakeel','shak@nisum.com','03131312442','Dubai','CUSTOMER_VENDOR','EM333231','2023-05-02','03444444422','034253523523','2023-05-29 22:19:31',NULL,NULL,NULL);

/*Table structure for table `expense_category` */

DROP TABLE IF EXISTS `expense_category`;

CREATE TABLE `expense_category` (
  `expense_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(45) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`expense_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `expense_category` */

/*Table structure for table `expenses` */

DROP TABLE IF EXISTS `expenses`;

CREATE TABLE `expenses` (
  `expense_id` int(11) NOT NULL AUTO_INCREMENT,
  `expense_category` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `ref_number` varchar(45) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `expense_date` date DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`expense_id`),
  KEY `expense_category` (`expense_category`),
  CONSTRAINT `expenses_ibfk_1` FOREIGN KEY (`expense_category`) REFERENCES `expense_category` (`expense_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `expenses` */

/*Table structure for table `ledger` */

DROP TABLE IF EXISTS `ledger`;

CREATE TABLE `ledger` (
  `ledger_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) DEFAULT NULL,
  `order_number` varchar(255) DEFAULT NULL,
  `amount_paid` double DEFAULT NULL,
  `amount_remaining` double DEFAULT NULL,
  `total_amount` double DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ledger_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `ledger_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

/*Data for the table `ledger` */

insert  into `ledger`(`ledger_id`,`customer_id`,`order_number`,`amount_paid`,`amount_remaining`,`total_amount`,`created_date`,`created_by`,`last_modified_date`,`last_modified_by`) values (7,3,'NM-20230521165429',4000,1250,5250,NULL,NULL,NULL,NULL),(8,4,'NM-20230521165616',2000,1150,3150,NULL,NULL,NULL,NULL),(9,5,'NM-20230521180018',3000,2250,5250,NULL,NULL,NULL,NULL);

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `code` varchar(45) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `product_id_UNIQUE` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `product` */

insert  into `product`(`product_id`,`name`,`code`,`quantity`,`price`,`created_date`,`created_by`,`last_modified_date`,`last_modified_by`) values (1,'Product','2224',0,0,NULL,NULL,NULL,NULL),(3,'Battery','1234',0,0,NULL,NULL,NULL,NULL);

/*Table structure for table `purchase` */

DROP TABLE IF EXISTS `purchase`;

CREATE TABLE `purchase` (
  `purchase_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `purchase_date` datetime DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `unit` enum('KG','MT') DEFAULT NULL,
  `price` double DEFAULT NULL,
  `amount_paid` double DEFAULT NULL,
  `amount_remaining` double DEFAULT NULL,
  `total_amount` double DEFAULT NULL,
  `purchase_number` varchar(45) DEFAULT NULL,
  `payment_type` enum('CASH','CHEQUE','ONLINE') DEFAULT NULL,
  `is_taxable` tinyint(1) DEFAULT NULL,
  `tax_amount` double DEFAULT 0,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`purchase_id`),
  UNIQUE KEY `purchase_id_UNIQUE` (`purchase_id`),
  KEY `purchased_product_id` (`product_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `purchase_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`),
  CONSTRAINT `purchased_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

/*Data for the table `purchase` */

insert  into `purchase`(`purchase_id`,`product_id`,`customer_id`,`purchase_date`,`quantity`,`unit`,`price`,`amount_paid`,`amount_remaining`,`total_amount`,`purchase_number`,`payment_type`,`is_taxable`,`tax_amount`,`created_date`,`created_by`,`last_modified_date`,`last_modified_by`) values (5,1,3,'2023-05-15 00:00:00',5,'KG',1000,3000,2000,5000,'NM-20230521154237','CASH',0,0,NULL,NULL,NULL,NULL),(6,1,4,'2023-05-15 00:00:00',5,'MT',10000,40000,10000,52500,'NM-20230521154300','ONLINE',1,2500,NULL,NULL,NULL,NULL),(7,1,4,'2023-05-10 00:00:00',3,'MT',1000,2000,1150,3150,'NM-20230521163036','CASH',1,150,NULL,NULL,NULL,NULL),(8,1,3,'2023-05-03 00:00:00',4,'KG',1000,3000,1200,4200,'NM-20230521163533','CASH',1,200,NULL,NULL,NULL,NULL),(9,1,3,'2023-05-03 00:00:00',5,'KG',1000,4000,1250,5250,'NM-20230521165429','CASH',1,250,NULL,NULL,NULL,NULL),(10,1,4,'2023-05-19 00:00:00',3,'MT',1000,2000,1150,3150,'NM-20230521165616','ONLINE',1,150,NULL,NULL,NULL,NULL),(11,3,5,'2023-05-15 00:00:00',5,'MT',1000,3000,2250,5250,'NM-20230521180018','CHEQUE',1,250,NULL,NULL,NULL,NULL);

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
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(45) DEFAULT NULL,
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
  `emirates_id` varchar(100) DEFAULT NULL,
  `expiry_date` date DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

/*Data for the table `users` */

insert  into `users`(`user_id`,`name`,`email`,`password`,`contact`,`address`,`emirates_id`,`expiry_date`,`created_date`,`created_by`,`last_modified_date`,`last_modified_by`) values (2,'Admin','admin@gmail.com','admin','009414124','UAE','EM43535','2023-05-31',NULL,NULL,NULL,NULL),(3,'Azeem','azeem@gmail.com','azeem','7897842323','Dubai','EM55554','2023-05-31',NULL,NULL,NULL,NULL),(5,'Rahim','rahim@gmail.com','admin','78789789789','Pakistan','EM345353','2023-05-31',NULL,NULL,NULL,NULL),(9,'Rahim','rahim44@gmail.com','admin','78789789789','Pakistan','EM6677777','2023-05-31',NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

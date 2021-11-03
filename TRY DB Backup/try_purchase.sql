CREATE DATABASE  IF NOT EXISTS `try` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `try`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: try
-- ------------------------------------------------------
-- Server version	5.7.8-rc-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase` (
  `product` varchar(25) NOT NULL,
  `purchasedate` date NOT NULL,
  `subgroup_name` varchar(25) DEFAULT NULL,
  `purchase_qty` float(15,2) DEFAULT '0.00',
  `stock_qty` float(15,2) DEFAULT '0.00',
  `uom` varchar(15) DEFAULT NULL,
  `price_unit` float(21,2) DEFAULT '0.00',
  `price_total` float(21,2) DEFAULT '0.00',
  `taxpercent_total` float(21,2) DEFAULT '0.00',
  `taxamt_total` float(21,2) DEFAULT '0.00',
  `comments` varchar(150) DEFAULT NULL,
  `changedby` varchar(20) DEFAULT NULL,
  `changeddate` date DEFAULT NULL,
  `changedtime` time DEFAULT NULL,
  PRIMARY KEY (`product`,`purchasedate`),
  KEY `product_fk_idx` (`product`),
  CONSTRAINT `product_fk` FOREIGN KEY (`product`) REFERENCES `product` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES ('20ml packet oil','2020-05-05','PACKET_OIL',2000.00,0.00,'Packet',15.00,30000.00,13.00,3900.00,'Test','Admin','2020-05-23','19:41:20'),('20ml packet oil','2020-05-06','PACKET_OIL',3000.00,1805.00,'Packet',14.00,42000.00,12.00,2900.00,'Test','Admin','2020-07-13','18:08:18'),('20ml packet oil','2020-05-09','PACKET_OIL',5000.00,4915.00,'Packet',15.00,10000.00,12.00,1200.00,'Test','Admin','2020-05-16','18:28:38'),('20ml packet oil','2020-05-12','PACKET_OIL',456.00,455.00,'Packet',10.97,5001.00,12.00,600.12,'','Admin','2020-05-12','22:02:58'),('40ml packet oil','2020-05-05','PACKET_OIL',500.00,380.00,'Packet',10.00,5000.00,13.00,650.00,'Test','Admin','2020-05-13','18:18:49'),('60ml packet oil','2021-08-06','PACKET_OIL',1000.00,870.00,'Packet',18.00,18000.00,10.00,1800.00,'','Admin','2021-08-06','14:54:14'),('800 NXT','2021-01-22','Engine_oil',100.00,56.00,'Can',150.00,15000.00,18.00,2700.00,'test','Admin','2021-01-22','13:35:37'),('900 HERO','2021-01-22','Engine_oil',300.00,0.00,'Can',120.00,36000.00,18.00,6480.00,'test','Admin','2021-01-22','13:37:51'),('900 HERO','2021-08-06','Engine_oil',100.00,84.00,'Can',600.00,60000.00,10.00,6000.00,'','Admin','2021-08-06','14:55:41'),('900 STALLION','2021-01-22','Engine_oil',200.00,172.00,'Can',150.00,30000.00,18.00,5400.00,'test','Admin','2021-01-22','13:36:48'),('900 STALLION','2021-01-23','Engine_oil',120.00,120.00,'Can',150.00,18000.00,18.00,3240.00,'test','Admin','2021-01-27','13:38:45'),('900NXT','2020-05-23','Engine_oil',50.00,45.00,'Can',100.00,5000.00,12.00,600.00,'','Admin','2020-05-23','19:56:04'),('BRAKE OIL','2021-01-22','Engine_oil',1000.00,992.00,'Can',20.00,20000.00,18.00,3600.00,'test','Admin','2021-01-22','13:38:11'),('Diesel(HSD)','2020-05-12','FUEL',500.00,0.00,'Litres',10.00,5000.00,12.00,600.00,'Test','Admin','2020-05-12','00:50:41'),('Diesel(HSD)','2020-06-01','FUEL',4000.00,0.00,'Litres',70.00,280000.00,18.00,50400.00,'','Admin','2020-06-04','18:07:16'),('Diesel(HSD)','2021-08-06','FUEL',8000.00,6570.00,'Litres',97.35,778800.00,18.00,140184.00,'','Admin','2021-10-20','18:58:06'),('MULTI LOOSE','2021-01-22','Engine_oil',500.00,496.00,'Can',15.00,7500.00,18.00,1350.00,'test','Admin','2021-01-22','13:38:49'),('Petrol(MS)','2020-06-01','FUEL',8000.00,455.00,'Litres',72.00,560000.00,18.00,100800.00,'','Admin','2020-06-04','17:53:39'),('Petrol(MS)','2020-06-02','FUEL',4000.00,4000.00,'Litres',70.00,280000.00,18.00,50400.00,'','Admin','2020-06-04','18:07:29'),('Petrol(MS)','2021-08-06','FUEL',8000.00,8000.00,'Litres',101.55,812400.00,18.00,146232.00,'','Admin','2021-10-20','18:59:24');
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-03 19:30:47

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
-- Table structure for table `saleprice`
--

DROP TABLE IF EXISTS `saleprice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `saleprice` (
  `date` date NOT NULL,
  `product` varchar(25) NOT NULL,
  `subgroup_name` varchar(25) DEFAULT NULL,
  `sell_price` float(21,2) DEFAULT '0.00',
  `uom` varchar(15) DEFAULT NULL,
  `taxpercent_total` float(21,2) DEFAULT '0.00',
  `taxamt_total` float(21,2) DEFAULT '0.00',
  `comments` varchar(150) DEFAULT NULL,
  `changedby` varchar(20) DEFAULT NULL,
  `changeddate` date DEFAULT NULL,
  `changedtime` time DEFAULT NULL,
  PRIMARY KEY (`date`,`product`),
  KEY `sp_product_fk_idx` (`product`),
  KEY `sp_uom_fk_idx` (`uom`),
  CONSTRAINT `sp_product_fk` FOREIGN KEY (`product`) REFERENCES `product` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `saleprice`
--

LOCK TABLES `saleprice` WRITE;
/*!40000 ALTER TABLE `saleprice` DISABLE KEYS */;
INSERT INTO `saleprice` VALUES ('2020-04-01','20ml packet oil','PACKET_OIL',8.00,'Packet',12.00,0.84,NULL,'Admin','2020-07-15','14:53:22'),('2020-05-12','900NXT','Engine_oil',150.00,'Can',18.00,27.00,'','Admin','2021-01-22','14:57:38'),('2020-05-20','20ml packet oil','PACKET_OIL',7.00,'Packet',12.00,0.84,'','Admin','2020-05-24','14:51:45'),('2020-05-20','40ml packet oil','PACKET_OIL',13.00,'Packet',19.00,2.81,NULL,'Admin','2020-07-15','14:53:23'),('2020-05-24','40ml packet oil','PACKET_OIL',12.00,'Packet',18.00,2.16,'','Admin','2020-05-24','14:53:22'),('2020-05-24','900NXT','Engine_oil',150.00,'Can',18.00,27.00,'test','Admin','2021-01-22','14:58:05'),('2020-05-24','Diesel(HSD)','FUEL',68.00,'Litres',18.00,12.24,'','Admin','2020-05-24','14:54:34'),('2020-05-24','Petrol(MS)','FUEL',72.00,'Litres',0.00,0.00,'','Admin','2020-05-24','14:50:54'),('2020-05-25','Diesel(HSD)','FUEL',64.00,'Litres',18.00,11.52,'','Admin','2020-05-27','20:37:46'),('2020-05-25','Petrol(MS)','FUEL',71.00,'Litres',18.00,12.78,'','Admin','2020-05-27','20:37:58'),('2020-05-27','Diesel(HSD)','FUEL',67.00,'Litres',18.00,12.06,'','Admin','2020-05-27','20:37:34'),('2020-05-27','Petrol(MS)','FUEL',76.00,'Litres',18.00,13.68,'','Admin','2020-05-27','20:37:24'),('2020-05-28','Diesel(HSD)','FUEL',60.00,'Litres',18.00,10.80,'','Admin','2020-05-28','00:31:36'),('2020-05-28','Petrol(MS)','FUEL',70.00,'Litres',18.00,12.60,'','Admin','2020-05-28','00:31:29'),('2020-06-05','60ml packet oil','PACKET_OIL',20.00,'Packet',18.00,3.15,'test','Admin','2020-05-24','14:53:22'),('2021-01-21','800 NXT','Engine_oil',200.00,'Can',18.00,36.00,'test','Admin','2021-01-22','14:58:47'),('2021-01-21','900 HERO','Engine_oil',150.00,'Can',18.00,27.00,'test','Admin','2021-01-22','14:59:32'),('2021-01-21','900 STALLION','Engine_oil',200.00,'Can',18.00,36.00,'test','Admin','2021-01-22','14:59:57'),('2021-01-22','BRAKE OIL','Engine_oil',25.00,'Can',18.00,4.50,'test','Admin','2021-01-22','13:40:38'),('2021-01-22','MULTI LOOSE','Engine_oil',20.00,'Can',18.00,3.60,'test','Admin','2021-01-22','15:00:44'),('2021-08-06','20ml packet oil','PACKET_OIL',10.00,'Packet',10.00,1.00,'','Admin','2021-08-06','14:49:29'),('2021-08-06','40ml packet oil','PACKET_OIL',15.00,'Packet',10.00,1.50,'','Admin','2021-08-06','14:49:40'),('2021-08-06','60ml packet oil','PACKET_OIL',20.00,'Packet',10.00,2.00,'','Admin','2021-08-06','14:49:48'),('2021-08-06','800 NXT','Engine_oil',350.00,'Can',10.00,35.00,'','Admin','2021-08-06','14:48:33'),('2021-08-06','900 HERO','Engine_oil',650.00,'Can',10.00,65.00,'','Admin','2021-08-06','14:48:54'),('2021-08-06','900 STALLION','Engine_oil',550.00,'Can',10.00,55.00,'','Admin','2021-08-06','14:48:43'),('2021-08-06','900NXT','Engine_oil',450.00,'Can',10.00,45.00,'','Admin','2021-08-06','14:48:23'),('2021-08-06','BRAKE OIL','Engine_oil',500.00,'Can',10.00,50.00,'','Admin','2021-08-06','14:49:02'),('2021-08-06','Diesel(HSD)','FUEL',90.00,'Litres',10.00,9.00,'','Admin','2021-08-06','14:47:57'),('2021-08-06','MULTI LOOSE','Engine_oil',200.00,'Can',10.00,20.00,'','Admin','2021-08-06','14:49:16'),('2021-08-06','Petrol(MS)','FUEL',100.00,'Litres',10.00,10.00,'','Admin','2021-08-06','14:47:46'),('2021-08-07','Diesel(HSD)','FUEL',99.50,'Litres',18.00,17.91,'','Admin','2021-10-20','19:00:36'),('2021-08-07','Petrol(MS)','FUEL',103.50,'Litres',18.00,18.63,'','Admin','2021-10-20','19:00:24'),('2021-08-08','Diesel(HSD)','FUEL',100.00,'Litres',18.00,18.00,'','Admin','2021-10-20','19:18:14'),('2021-08-08','Petrol(MS)','FUEL',104.00,'Litres',18.00,18.72,'','Admin','2021-10-20','19:17:54');
/*!40000 ALTER TABLE `saleprice` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-03 19:08:01

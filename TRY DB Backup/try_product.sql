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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  `group_name` varchar(25) DEFAULT NULL,
  `subgroup_name` varchar(25) DEFAULT NULL,
  `uom` varchar(15) DEFAULT NULL,
  `comments` varchar(200) DEFAULT NULL,
  `changedby` varchar(20) DEFAULT NULL,
  `changeddate` date DEFAULT NULL,
  `changedtime` time DEFAULT NULL,
  `createddate` date DEFAULT NULL,
  PRIMARY KEY (`product_id`,`name`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Petrol(MS)','Product','FUEL','Litres','Petrol','Admin','2020-05-06','20:22:44','2020-05-06'),(4,'Diesel(HSD)','Product','FUEL','Litres','Diesel','Admin','2020-05-06','21:05:41','2020-05-06'),(7,'20ml packet oil','Product','PACKET_OIL','Packet','jkhjkh 1','Admin','2020-05-13','18:07:04','2020-05-06'),(9,'40ml packet oil','Product','PACKET_OIL','Packet','','Admin','2020-05-06','20:31:03','2020-05-06'),(10,'900NXT','Product','Engine_oil','Can','Test','Admin','2020-05-22','18:30:46','2020-05-22'),(11,'60ml packet oil','Product','PACKET_OIL','Packet','test','Admin',NULL,NULL,NULL),(12,'800 NXT','Product','Engine_oil','Can','','Admin','2021-01-19','15:18:22','2021-01-19'),(13,'900 STALLION','Product','Engine_oil','Can','','Admin','2021-01-19','15:19:04','2021-01-19'),(14,'900 HERO','Product','Engine_oil','Can','','Admin','2021-01-19','15:19:18','2021-01-19'),(15,'BRAKE OIL','Product','Engine_oil','Can','','Admin','2021-01-19','15:19:48','2021-01-19'),(16,'MULTI LOOSE','Product','Engine_oil','Can','','Admin','2021-01-19','15:20:03','2021-01-19');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-03 19:08:00

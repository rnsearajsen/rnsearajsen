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
-- Table structure for table `vendor`
--

DROP TABLE IF EXISTS `vendor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vendor` (
  `vendor_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `group_name` varchar(25) DEFAULT NULL,
  `subgroup_name` varchar(25) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `mail` varchar(100) DEFAULT NULL,
  `contact1` varchar(15) DEFAULT NULL,
  `contact2` varchar(15) DEFAULT NULL,
  `idtype1` varchar(25) DEFAULT NULL,
  `idnum1` varchar(30) DEFAULT NULL,
  `idtype2` varchar(25) DEFAULT NULL,
  `idnum2` varchar(30) DEFAULT NULL,
  `comments` varchar(200) DEFAULT NULL,
  `joindate` date DEFAULT NULL,
  `lastdate` date DEFAULT NULL,
  `changedby` varchar(20) DEFAULT NULL,
  `changeddate` date DEFAULT NULL,
  `changedtime` time DEFAULT NULL,
  `createddate` date DEFAULT NULL,
  `createdtime` time DEFAULT NULL,
  PRIMARY KEY (`vendor_id`,`name`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `group_vendor_idx` (`group_name`),
  CONSTRAINT `group_vendor` FOREIGN KEY (`group_name`) REFERENCES `group` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendor`
--

LOCK TABLES `vendor` WRITE;
/*!40000 ALTER TABLE `vendor` DISABLE KEYS */;
INSERT INTO `vendor` VALUES (1,'sdfsd','Vendor','FUEL','ssdd1','1','2331','1','Aadhaar Card','dd1','PAN Card','dd1','1   ','2010-01-01',NULL,'Admin','2020-05-06','16:22:21','2020-05-05','18:48:44'),(4,'465456_1','Vendor','FUEL_SUP','dd','','2413434','','Aadhaar Card','daasd','Aadhaar Card','',' ','2020-05-05',NULL,'Admin','2020-05-13','16:59:48','2020-05-13','16:59:48'),(6,'gffgfg','Vendor','FUEL_SUP','hhjhj','','45456','','Aadhaar Card','fghfg','Aadhaar Card','',' ','2020-05-05',NULL,'Admin','2020-05-13','17:38:25','2020-05-13','17:38:25');
/*!40000 ALTER TABLE `vendor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-03 19:30:44

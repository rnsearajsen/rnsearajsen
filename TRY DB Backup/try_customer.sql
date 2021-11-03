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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
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
  PRIMARY KEY (`customer_id`,`name`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `customer_group_idx` (`group_name`,`subgroup_name`),
  KEY `customer_subgrp_idx` (`subgroup_name`),
  CONSTRAINT `group_customer` FOREIGN KEY (`group_name`) REFERENCES `group` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (41,'sas','Customer','Non-Credit','dd','','32','','Aadhaar Card','ads1','Aadhaar Card','','   ','2020-05-05',NULL,'Admin','2020-05-06','16:21:43','2020-05-05','15:01:18'),(53,'werw','Customer','Credit','rrr','','2332','','Aadhaar Card','eer','Aadhaar Card','',' ','2020-05-05',NULL,'Admin','2020-05-05','16:08:07','2020-05-05','16:08:07'),(57,'werw1','Customer','Credit','5551','','55','','Aadhaar Card','55','Aadhaar Card','','  ','2020-05-05',NULL,'Admin','2020-05-12','21:49:03','2020-05-05','16:12:34'),(58,'sadas','Customer','Credit','dasdas1','','322','','Aadhaar Card','sdsd','Aadhaar Card','','  ','2020-05-08',NULL,'Admin','2020-05-08','20:34:52','2020-05-08','20:26:51'),(59,'sadas1','Customer','Credit','dasdas','','322','','Aadhaar Card','sdsd','Aadhaar Card','',' ','2020-05-08',NULL,'Admin','2020-05-08','20:27:20','2020-05-08','20:26:51'),(61,'sadasd','Customer','Credit','dddd','','32341','','Aadhaar Card','ddd','Aadhaar Card','',' ','2020-05-05',NULL,'Admin','2020-05-08','20:32:42','2020-05-08','20:32:42'),(63,'fdg','Customer','Credit','jjj','','7787','','Aadhaar Card','jjj_1','Aadhaar Card','','  ','2020-05-05',NULL,'Admin','2020-05-13','16:54:55','2020-05-08','20:35:33'),(64,'fdg1','Customer','Credit','jjj','','7787','','Aadhaar Card','jjj','Aadhaar Card','',' ','2020-05-05',NULL,'Admin','2020-05-08','20:36:10','2020-05-08','20:36:10'),(65,'Tst','Customer','Credit','jjhjkh1','hjkhj1','45564651','1','Aadhaar Card','jhjhj1','Aadhaar Card','jhghjg1','jhjh  1','2001-05-05','2031-05-05','Admin','2020-05-12','21:48:04','2020-05-12','21:46:52');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-03 19:08:02

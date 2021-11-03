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
-- Table structure for table `billpurpay`
--

DROP TABLE IF EXISTS `billpurpay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `billpurpay` (
  `bill_no` int(11) NOT NULL,
  `bill_date` date NOT NULL,
  `bill_prdgrp` varchar(45) NOT NULL,
  `bill_amt` float(21,2) DEFAULT NULL,
  `discount` float(21,2) DEFAULT NULL,
  `interest` float(21,2) DEFAULT NULL,
  `paymnt_amt` float(21,2) DEFAULT NULL,
  `paymnt_date` date DEFAULT NULL,
  `paid` varchar(1) DEFAULT NULL,
  `changedby` varchar(25) DEFAULT NULL,
  `changeddate` date DEFAULT NULL,
  `changedtime` time DEFAULT NULL,
  PRIMARY KEY (`bill_no`,`bill_date`,`bill_prdgrp`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Maintained with both Purchase & Payment';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billpurpay`
--

LOCK TABLES `billpurpay` WRITE;
/*!40000 ALTER TABLE `billpurpay` DISABLE KEYS */;
INSERT INTO `billpurpay` VALUES (101,'2021-07-10','Bill_MS/HSD',10256.00,256.00,1000.00,11000.00,'2021-10-14','X','Admin','2021-10-14','17:08:56'),(102,'2021-10-05','Bill_MS/HSD',100000.00,0.00,0.00,0.00,'2021-10-18','','Admin','2021-10-18','15:17:10'),(103,'2021-10-06','Bill_Oil',20000.00,2000.00,1000.00,10000.00,'2021-10-18','','Admin','2021-10-18','15:17:10'),(104,'2021-08-05','Bill_MS/HSD',200000.00,2000.00,2000.00,30000.00,'2021-10-18','','Admin','2021-10-18','15:17:10');
/*!40000 ALTER TABLE `billpurpay` ENABLE KEYS */;
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

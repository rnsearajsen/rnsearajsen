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
-- Table structure for table `cashtxn`
--

DROP TABLE IF EXISTS `cashtxn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cashtxn` (
  `txndate` date NOT NULL,
  `labour_shift` varchar(25) NOT NULL,
  `aggramt` float(21,2) DEFAULT '0.00' COMMENT 'Aggregate Amount till the Txndate',
  `collection_amt` float(21,2) DEFAULT '0.00' COMMENT 'Collection Amount from Cashier',
  `difference` float(21,2) DEFAULT '0.00' COMMENT 'Collection Difference from Cashier',
  `changedby` varchar(25) DEFAULT NULL,
  `changeddate` date DEFAULT NULL,
  `changedtime` time DEFAULT NULL,
  PRIMARY KEY (`txndate`,`labour_shift`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cashtxn`
--

LOCK TABLES `cashtxn` WRITE;
/*!40000 ALTER TABLE `cashtxn` DISABLE KEYS */;
INSERT INTO `cashtxn` VALUES ('2021-08-01','SHIFT1',100000.00,100000.00,0.00,'Test','2021-08-18','00:00:00'),('2021-08-06','SHIFT1',469000.00,369000.00,-900.00,'Admin','2021-10-19','22:58:29'),('2021-08-06','SHIFT2',496500.00,27500.00,175.00,'Admin','2021-10-19','23:00:38'),('2021-08-07','SHIFT1',495500.00,185000.00,-740.00,'Admin','2021-10-20','21:16:46'),('2021-08-07','SHIFT2',765400.00,64400.00,-52.50,'Admin','2021-10-20','21:22:19');
/*!40000 ALTER TABLE `cashtxn` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-03 19:30:46

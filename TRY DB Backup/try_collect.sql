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
-- Table structure for table `collect`
--

DROP TABLE IF EXISTS `collect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `collect` (
  `txndate` date NOT NULL,
  `labour_shift` varchar(25) NOT NULL,
  `collect_type` varchar(25) NOT NULL,
  `collect_amt` float(21,2) DEFAULT '0.00',
  `comments` varchar(250) DEFAULT '0.00',
  `changedby` varchar(20) DEFAULT NULL,
  `changeddate` date DEFAULT NULL,
  `changedtime` time DEFAULT NULL,
  PRIMARY KEY (`txndate`,`labour_shift`,`collect_type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Collection Table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collect`
--

LOCK TABLES `collect` WRITE;
/*!40000 ALTER TABLE `collect` DISABLE KEYS */;
INSERT INTO `collect` VALUES ('2021-08-06','SHIFT1','Cash',200000.00,'','Admin','2021-10-19','22:55:39'),('2021-08-06','SHIFT1','Fino',169000.00,'','Admin','2021-10-19','22:55:39'),('2021-08-06','SHIFT2','Cash',2500.00,'','Admin','2021-10-19','23:00:11'),('2021-08-06','SHIFT2','UPI',25000.00,'','Admin','2021-10-19','23:00:11'),('2021-08-07','SHIFT1','Cash',100000.00,'','Admin','2021-10-20','21:16:31'),('2021-08-07','SHIFT1','UPI',85000.00,'','Admin','2021-10-20','21:16:31'),('2021-08-07','SHIFT2','Card_HDFC',34400.00,'','Admin','2021-10-20','21:21:34'),('2021-08-07','SHIFT2','Fino',30000.00,'','Admin','2021-10-20','21:21:34');
/*!40000 ALTER TABLE `collect` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-03 19:08:03

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
-- Table structure for table `oth_income`
--

DROP TABLE IF EXISTS `oth_income`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oth_income` (
  `txndate` date NOT NULL,
  `labour_shift` varchar(25) NOT NULL,
  `inctype` varchar(45) NOT NULL,
  `dbamount` float(21,2) DEFAULT '0.00' COMMENT 'If ''Cash Handle'' set, then adjust the amount in ''aggramt''(Aggregate amount) in table ''cashtxn'' table on same ''txndate'' & ''labour_shift''',
  `mop` varchar(25) DEFAULT NULL,
  `ch_dbamount` float(21,2) DEFAULT '0.00',
  `comments` varchar(150) DEFAULT NULL,
  `changedby` varchar(20) DEFAULT NULL,
  `changeddate` date DEFAULT NULL,
  `changedtime` time DEFAULT NULL,
  PRIMARY KEY (`txndate`,`labour_shift`,`inctype`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oth_income`
--

LOCK TABLES `oth_income` WRITE;
/*!40000 ALTER TABLE `oth_income` DISABLE KEYS */;
INSERT INTO `oth_income` VALUES ('2021-03-05','SHIFT1','dada',100.00,'CARD',0.00,'test','Admin','2021-08-06','12:49:44'),('2021-03-05','SHIFT1','dada1',1000.00,'CARD',0.00,'test1','Admin','2021-08-06','12:49:44'),('2021-03-05','SHIFT1','hhjk',1500.00,'CARD1',0.00,'Test','Admin','2021-08-06','12:49:44'),('2021-03-06','SHIFT1','DFSDF',399.00,'CARD',0.00,'DFSFS','Admin','2021-08-06','13:35:18'),('2021-03-06','SHIFT1','FSDF',345.00,'ONLINE',0.00,'DSFSDF','Admin','2021-08-06','13:35:18'),('2021-03-06','SHIFT1','HHG',55.00,'ONLINE',0.00,'','Admin','2021-08-06','13:35:18'),('2021-03-06','SHIFT1','HHJG',9877.00,'CARD',0.00,'JHJ','Admin','2021-08-06','13:35:18'),('2021-08-06','SHIFT1','ATM EB Bill',10000.00,'CARD',400.00,'test','Admin','2021-09-27','21:29:29'),('2021-08-06','SHIFT1','Test',500.00,'CARD',1000.00,'test','Admin','2021-09-27','21:29:29'),('2021-08-06','SHIFT2','income',500.00,'CARD',10000.00,'','Admin','2021-09-27','21:30:14'),('2021-08-06','SHIFT2','test',500.00,'CARD',0.00,'','Admin','2021-09-27','21:30:14'),('2021-08-07','SHIFT1','Miscellaneous',1000.00,'CARD',5000.00,'','Admin','2021-10-20','21:09:47'),('2021-08-07','SHIFT1','Test',0.00,'CARD',2000.00,'Test','Admin','2021-10-20','21:09:47'),('2021-08-07','SHIFT2','Test1',5000.00,'CARD',0.00,'','Admin','2021-10-20','21:10:20'),('2021-08-07','SHIFT2','Test2',0.00,'CARD',2000.00,'','Admin','2021-10-20','21:10:20');
/*!40000 ALTER TABLE `oth_income` ENABLE KEYS */;
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

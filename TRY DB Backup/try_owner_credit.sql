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
-- Table structure for table `owner_credit`
--

DROP TABLE IF EXISTS `owner_credit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `owner_credit` (
  `txndate` date NOT NULL,
  `labour_shift` varchar(25) NOT NULL,
  `name` varchar(45) NOT NULL,
  `crdamount` float(21,2) DEFAULT '0.00',
  `dbamount` float(21,2) DEFAULT '0.00',
  `mop` varchar(25) DEFAULT NULL,
  `ch_crdamount` float(21,2) DEFAULT NULL,
  `ch_dbamount` float(21,2) DEFAULT NULL,
  `pastdue` float(21,2) DEFAULT '0.00',
  `comments` varchar(150) DEFAULT NULL,
  `changedby` varchar(20) DEFAULT NULL,
  `changeddate` date DEFAULT NULL,
  `changedtime` time DEFAULT NULL,
  PRIMARY KEY (`txndate`,`labour_shift`,`name`),
  KEY `name_idx` (`name`),
  KEY `name_own_idx` (`name`),
  CONSTRAINT `name_own` FOREIGN KEY (`name`) REFERENCES `owner` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `owner_credit`
--

LOCK TABLES `owner_credit` WRITE;
/*!40000 ALTER TABLE `owner_credit` DISABLE KEYS */;
INSERT INTO `owner_credit` VALUES ('2021-03-04','SHIFT1','hjkh',300.00,0.00,' ',NULL,NULL,NULL,'','Admin','2021-06-25','16:00:19'),('2021-03-05','SHIFT1','dfdsf',1000.00,0.00,' ',NULL,NULL,NULL,'','Admin','2021-06-25','15:37:24'),('2021-03-05','SHIFT1','hjkh',500.00,0.00,' ',NULL,NULL,NULL,'','Admin','2021-06-25','15:37:24'),('2021-03-05','SHIFT1','Test',1000.00,100.00,'CARD',NULL,NULL,NULL,'Tets','Admin','2021-06-25','15:37:24'),('2021-03-06','SHIFT1','dfdsf',1000.00,100.00,'CARD',NULL,NULL,NULL,'Test','Admin','2021-06-25','15:57:24'),('2021-03-06','SHIFT1','hjkh',0.00,100.00,'ONLINE',NULL,NULL,NULL,'test','Admin','2021-06-25','15:57:24'),('2021-03-06','SHIFT1','Test',100.00,0.00,' ',NULL,NULL,NULL,'ghh','Admin','2021-06-25','15:57:24'),('2021-03-07','SHIFT1','hjkh',0.00,600.00,'CARD',NULL,NULL,NULL,'','Admin','2021-06-25','15:59:36'),('2021-03-08','SHIFT1','dfdsf',100.00,0.00,' ',NULL,NULL,0.00,'test','Admin','2021-08-04','18:26:51'),('2021-03-08','SHIFT1','hjkh',5000.00,0.00,' ',NULL,NULL,0.00,'Test','Admin','2021-08-04','18:26:51'),('2021-04-01','SHIFT1','dfdsf',200.00,100.00,'CARD',NULL,NULL,0.00,'Test','Admin','2021-08-04','19:46:50'),('2021-04-02','SHIFT1','dfdsf',100.00,0.00,' ',NULL,NULL,2000.00,'','Admin','2021-08-04','19:47:41'),('2021-04-02','SHIFT1','hjkh',100.00,0.00,' ',NULL,NULL,5100.00,'','Admin','2021-08-04','19:47:41'),('2021-04-04','SHIFT1','dfdsf',100.00,0.00,' ',NULL,NULL,0.00,'','Admin','2021-08-04','19:54:35'),('2021-07-31','SHIFT1','dfdsf',50000.00,5000.00,'CARD',100000.00,30000.00,NULL,'','Admin','2021-08-10','13:55:38'),('2021-07-31','SHIFT1','Test',100000.00,0.00,' ',150000.00,25000.00,NULL,'','Admin','2021-08-10','13:55:38'),('2021-08-06','SHIFT1','dfdsf',5000.00,0.00,' ',2000.00,1000.00,115000.00,'Test','Admin','2021-09-20','12:32:03'),('2021-08-06','SHIFT1','Test',10000.00,5000.00,'CARD',2000.00,5000.00,225000.00,'est1','Admin','2021-09-20','12:32:03'),('2021-08-07','SHIFT1','hjkh',1000.00,2000.00,'CARD',1000.00,0.00,NULL,'Test','Admin','2021-10-20','20:34:28'),('2021-08-07','SHIFT1','Test',2000.00,0.00,' ',0.00,3000.00,0.00,'Test1','Admin','2021-10-20','20:34:28'),('2021-08-07','SHIFT2','dfdsf',2000.00,0.00,' ',0.00,2000.00,0.00,'','Admin','2021-10-20','21:01:24'),('2021-08-07','SHIFT2','Test',10000.00,2000.00,'CARD',2000.00,5000.00,0.00,'','Admin','2021-10-20','21:01:24');
/*!40000 ALTER TABLE `owner_credit` ENABLE KEYS */;
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

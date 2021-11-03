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
-- Table structure for table `empwages`
--

DROP TABLE IF EXISTS `empwages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empwages` (
  `txndate` date NOT NULL,
  `labour_shift` varchar(25) NOT NULL,
  `name` varchar(45) NOT NULL,
  `attendance` varchar(3) DEFAULT NULL,
  `crdamount` float(21,2) DEFAULT '0.00',
  `dbamount` float(21,2) DEFAULT '0.00',
  `ch_crdamount` float(21,2) DEFAULT '0.00',
  `ch_dbamount` float(21,2) DEFAULT '0.00',
  `pastdue` float(21,2) DEFAULT '0.00' COMMENT 'If Negative, then Owner owns the amount & he need to provide to Employee',
  `act_salary` float(21,2) DEFAULT '0.00',
  `changedby` varchar(25) DEFAULT NULL,
  `changeddate` date DEFAULT NULL,
  `changedtime` time DEFAULT NULL,
  PRIMARY KEY (`txndate`,`labour_shift`,`name`),
  KEY `empname_idx` (`name`),
  CONSTRAINT `empname` FOREIGN KEY (`name`) REFERENCES `employee` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empwages`
--

LOCK TABLES `empwages` WRITE;
/*!40000 ALTER TABLE `empwages` DISABLE KEYS */;
INSERT INTO `empwages` VALUES ('2021-02-20','Shift1','dfs','F',100.00,150.00,0.00,0.00,100.00,1000.00,'teat',NULL,NULL),('2021-02-20','Shift1','dsdf','F',500.00,20.00,0.00,0.00,NULL,100.00,'test',NULL,NULL),('2021-02-21','Shift1','dfs','F',200.00,10.00,0.00,0.00,NULL,100.00,'test',NULL,NULL),('2021-02-21','Shift1','dsdf','F',200.00,500.00,0.00,0.00,NULL,500.00,'test',NULL,NULL),('2021-03-03','SHIFT1','dfs','S',0.00,0.00,0.00,0.00,0.00,354.84,'Admin','2021-08-04','20:46:31'),('2021-03-05','SHIFT1','dfs','S',100.00,0.00,0.00,0.00,240.00,354.84,'Admin','2021-07-28','16:30:18'),('2021-03-05','SHIFT1','hjjhjh','D',200.00,50.00,0.00,0.00,0.00,352029.41,'Admin','2021-07-28','16:30:18'),('2021-07-31','SHIFT1','454654','S',0.00,0.00,0.00,0.00,NULL,1356.65,'Admin','2021-08-10','13:57:56'),('2021-07-31','SHIFT1','dfs','S',1000.00,0.00,0.00,0.00,NULL,354.84,'Admin','2021-08-10','13:57:56'),('2021-07-31','SHIFT1','dsdf','S',5000.00,0.00,0.00,0.00,NULL,322.58,'Admin','2021-08-10','13:57:56'),('2021-08-01','SHIFT1','454654','S',1000.00,500.00,0.00,0.00,0.00,1356.65,'Admin','2021-08-20','22:31:15'),('2021-08-01','SHIFT1','dfs','S',0.00,0.00,0.00,0.00,0.00,354.84,'Admin','2021-08-20','22:31:15'),('2021-08-06','SHIFT1','454654','S',0.00,0.00,0.00,0.00,0.00,1356.65,'Admin','2021-08-10','13:59:00'),('2021-08-06','SHIFT1','dfs','D',0.00,0.00,0.00,0.00,1000.00,709.68,'Admin','2021-08-10','13:59:00'),('2021-08-06','SHIFT1','dsdf','S',0.00,0.00,0.00,0.00,5000.00,322.58,'Admin','2021-08-10','13:59:00'),('2021-08-06','SHIFT2','45456','S',1000.00,200.00,0.00,0.00,NULL,322.58,'Admin','2021-08-20','22:00:40'),('2021-08-06','SHIFT2','dfs','S',100.00,100.00,0.00,0.00,1000.00,354.84,'Admin','2021-08-20','22:00:40'),('2021-08-06','SHIFT2','dfsdfs_1','S',200.00,100.00,0.00,0.00,NULL,419.35,'Admin','2021-08-20','22:00:40'),('2021-08-07','SHIFT1','45456','S',2000.00,0.00,0.00,2000.00,0.00,322.58,'Admin','2021-10-20','21:05:40'),('2021-08-07','SHIFT1','dfs','S',0.00,0.00,0.00,0.00,0.00,354.84,'Admin','2021-10-20','21:05:40'),('2021-08-07','SHIFT1','dsdf','S',0.00,0.00,2000.00,0.00,0.00,322.58,'Admin','2021-10-20','21:05:40');
/*!40000 ALTER TABLE `empwages` ENABLE KEYS */;
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

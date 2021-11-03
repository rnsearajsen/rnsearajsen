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
-- Table structure for table `cust_credit`
--

DROP TABLE IF EXISTS `cust_credit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cust_credit` (
  `txndate` date NOT NULL,
  `labour_shift` varchar(25) NOT NULL,
  `name` varchar(45) NOT NULL,
  `crdamount` float(21,2) DEFAULT '0.00',
  `dbamount` float(21,2) DEFAULT '0.00',
  `mop` varchar(25) DEFAULT NULL,
  `ch_crdamount` float(21,2) DEFAULT '0.00',
  `ch_dbamount` float(21,2) DEFAULT '0.00',
  `pastdue` float(21,2) DEFAULT '0.00',
  `comments` varchar(150) DEFAULT NULL,
  `changedby` varchar(20) DEFAULT NULL,
  `changeddate` date DEFAULT NULL,
  `changedtime` time DEFAULT NULL,
  PRIMARY KEY (`txndate`,`labour_shift`,`name`),
  KEY `name_idx` (`name`),
  CONSTRAINT `name` FOREIGN KEY (`name`) REFERENCES `customer` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cust_credit`
--

LOCK TABLES `cust_credit` WRITE;
/*!40000 ALTER TABLE `cust_credit` DISABLE KEYS */;
INSERT INTO `cust_credit` VALUES ('2021-01-01','shift1','sadas',1500.00,0.00,'cash',0.00,0.00,NULL,'test','admin','2021-01-01','03:03:03'),('2021-01-01','shift1','werw',2500.00,300.00,'cash',0.00,0.00,NULL,'test','admin','2021-01-01','03:03:03'),('2021-01-02','shift1','sadas',1500.00,600.00,'cash',0.00,0.00,NULL,'test','admin','2021-01-01','03:03:03'),('2021-01-02','shift1','werw',0.00,600.00,'cash',0.00,0.00,NULL,'test','admin','2021-01-01','03:03:03'),('2021-02-01','SHIFT1','fdg',256.00,100.00,'CARD',0.00,0.00,555000.00,'Testsss','Admin','2021-06-24','21:53:57'),('2021-02-01','SHIFT1','fdg1',100.00,0.00,' ',0.00,0.00,1000.00,'','Admin','2021-06-24','21:53:57'),('2021-02-01','SHIFT1','sadas',100.00,200.00,'CARD',0.00,0.00,NULL,'Test','Admin','2021-06-24','21:53:57'),('2021-02-01','SHIFT1','sadas1',500.00,100.00,'CARD',0.00,0.00,2000.00,'Testq2','Admin','2021-06-24','21:53:57'),('2021-02-15','SHIFT1','werw',100.00,12.00,'CARD',0.00,0.00,NULL,'test','Admin','2021-06-24','17:12:54'),('2021-02-19','SHIFT1','fdg',1000.00,0.00,' ',0.00,0.00,NULL,'','Admin','2021-06-23','22:52:29'),('2021-02-20','SHIFT1','fdg',1000.00,0.00,' ',0.00,0.00,NULL,'','Admin','2021-06-23','22:57:19'),('2021-02-20','SHIFT1','werw1',1000.00,0.00,' ',0.00,0.00,NULL,'','Admin','2021-06-23','23:07:49'),('2021-02-21','SHIFT1','fdg',500.00,0.00,' ',0.00,0.00,NULL,'test','Admin','2021-06-24','21:44:56'),('2021-02-21','SHIFT1','fdg1',130.00,130.00,'CARD',0.00,0.00,NULL,'test','Admin','2021-06-24','21:45:00'),('2021-02-21','SHIFT1','sadas',456.00,20.00,'ONLINE',0.00,0.00,NULL,'','Admin','2021-06-24','21:45:02'),('2021-02-21','SHIFT1','sadasd',200.00,0.00,' ',0.00,0.00,NULL,'','Admin','2021-06-24','21:45:00'),('2021-02-21','SHIFT1','Tst',200.00,200.00,'ONLINE',0.00,0.00,500.00,'','Admin','2021-06-24','21:45:00'),('2021-02-21','SHIFT1','werw',400.00,300.00,'CARD',0.00,0.00,NULL,'Test','Admin','2021-06-24','21:45:00'),('2021-02-21','SHIFT1','werw1',200.00,0.00,' ',0.00,0.00,NULL,'Test','Admin','2021-06-24','21:45:00'),('2021-02-22','SHIFT1','werw1',1000.00,100.00,'CARD',0.00,0.00,NULL,'','Admin','2021-06-24','16:51:58'),('2021-02-23','SHIFT1','Tst',100.00,0.00,' ',0.00,0.00,NULL,'','Admin','2021-06-24','17:31:21'),('2021-02-23','SHIFT1','werw',0.00,1000.00,'CARD',0.00,0.00,NULL,'','Admin','2021-06-24','17:31:21'),('2021-02-28','SHIFT1','fdg',250.00,50.00,'CARD',0.00,0.00,NULL,'','Admin','2021-06-24','21:47:32'),('2021-02-28','SHIFT1','sadas',563.00,0.00,' ',0.00,0.00,NULL,'et','Admin','2021-06-24','21:47:32'),('2021-02-28','SHIFT1','sadas1',566.00,203.00,'ONLINE',0.00,0.00,NULL,'klj','Admin','2021-06-24','21:47:32'),('2021-03-01','SHIFT1','fdg',250.00,106.00,'CARD',0.00,0.00,NULL,'','Admin','2021-06-24','22:03:55'),('2021-03-01','SHIFT1','werw1',400.00,0.00,' ',0.00,0.00,NULL,'Test','Admin','2021-06-24','22:03:55'),('2021-03-05','SHIFT1','fdg',500.00,200.00,'ONLINE',0.00,0.00,0.00,'Test','Admin','2021-07-30','20:08:10'),('2021-03-05','SHIFT1','fdg1',500.00,100.00,'CARD',0.00,0.00,1100.00,'Test','Admin','2021-07-30','20:08:10'),('2021-03-05','SHIFT1','sadas',500.00,0.00,' ',0.00,0.00,NULL,'Test22','Admin','2021-07-30','20:08:10'),('2021-03-05','SHIFT1','sadas1',100.00,10.00,'CARD',0.00,0.00,2763.00,'Test_Pdue','Admin','2021-07-30','20:08:10'),('2021-03-06','SHIFT1','Tst',100.00,0.00,' ',0.00,0.00,600.00,'','Admin','2021-07-30','20:12:50'),('2021-04-01','SHIFT1','fdg',500.00,100.00,'CARD',0.00,0.00,0.00,'','Admin','2021-08-04','19:58:12'),('2021-04-02','SHIFT1','fdg',100.00,0.00,' ',0.00,0.00,444.00,'test','Admin','2021-08-04','19:56:30'),('2021-07-31','SHIFT1','fdg',500000.00,100000.00,'CARD',100000.00,50000.00,NULL,'','Admin','2021-08-06','19:19:07'),('2021-07-31','SHIFT1','sadas',100000.00,0.00,' ',200000.00,100000.00,NULL,'','Admin','2021-08-06','19:19:07'),('2021-08-06','SHIFT1','fdg',10000.00,0.00,' ',5000.00,0.00,450000.00,'Test','Admin','2021-09-20','12:16:59'),('2021-08-06','SHIFT1','sadas',10000.00,0.00,' ',0.00,5000.00,200000.00,'Test1','Admin','2021-09-20','12:16:59'),('2021-08-06','SHIFT2','fdg1',10000.00,2000.00,'CARD',10000.00,0.00,NULL,'','Admin','2021-09-20','12:19:56'),('2021-08-06','SHIFT2','sadas',5000.00,1000.00,'ONLINE',0.00,5000.00,0.00,'','Admin','2021-09-20','12:19:56'),('2021-08-06','SHIFT2','Tst',1000.00,200.00,'CARD',5000.00,0.00,NULL,'Test','Admin','2021-09-20','12:19:56'),('2021-08-07','SHIFT1','fdg',1000.00,0.00,' ',0.00,0.00,0.00,'','Admin','2021-10-20','20:30:22'),('2021-08-07','SHIFT1','sadas',0.00,1000.00,'CARD',0.00,5000.00,0.00,'','Admin','2021-10-20','20:30:22'),('2021-08-07','SHIFT1','werw',5000.00,0.00,' ',5000.00,0.00,NULL,'','Admin','2021-10-20','20:30:22'),('2021-08-07','SHIFT2','fdg1',2000.00,1000.00,'CARD',1000.00,2000.00,0.00,'','Admin','2021-10-20','20:32:38'),('2021-08-07','SHIFT2','sadas',2000.00,0.00,' ',0.00,4000.00,0.00,'','Admin','2021-10-20','20:32:38');
/*!40000 ALTER TABLE `cust_credit` ENABLE KEYS */;
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

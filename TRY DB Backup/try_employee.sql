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
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `group_name` varchar(25) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `subgroup_name` varchar(25) DEFAULT NULL,
  `salary` decimal(10,2) DEFAULT '0.00',
  `address` varchar(200) DEFAULT NULL,
  `contact1` varchar(15) DEFAULT NULL,
  `contact2` varchar(15) DEFAULT NULL,
  `idtype1` varchar(25) DEFAULT NULL,
  `idnum1` varchar(30) DEFAULT NULL,
  `comments` varchar(200) DEFAULT NULL,
  `joindate` date DEFAULT NULL,
  `lastdate` date DEFAULT NULL,
  `changedby` varchar(20) DEFAULT NULL,
  `changeddate` date DEFAULT NULL,
  `changedtime` time DEFAULT NULL,
  PRIMARY KEY (`employee_id`,`name`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'dfs','Employee','Female','Manager',11000.02,'Koduvai','4654654','','PAN Card','4654_2','sadasd ','2020-05-06',NULL,'Admin','2020-05-13','18:04:52'),(3,'dsdf','Employee','Male','Oil keeper',10000.00,'42342','342342','','PAN Card','asdad','   ','2020-05-06',NULL,'Admin','2020-05-06','17:15:21'),(4,'454654','Employee','Male','Cashier',42056.00,'kbkbkb','454654','45465','Aadhaar Card','54654','4564 -1','2020-05-05',NULL,'Admin','2020-05-13','17:07:34'),(6,'dfsdfs','Employee','Male','Cashier',10000.00,'565656','56565','','Aadhaar Card','dfsdfs',' ','2020-05-28',NULL,'Admin','2020-05-13','17:11:39'),(9,'dfsdfs_1','Employee','Male','Cashier',13000.00,'565656','56565','','Aadhaar Card','dfsdfs',' ','2020-05-28',NULL,'Admin','2020-05-13','17:11:56'),(11,'hjjhjh','Employee','Male','Cashier',9000.00,'jhhhjhj','23434','','Aadhaar Card','454564',' ','2020-05-05',NULL,'Admin','2020-05-13','17:47:16'),(12,'45456','Employee','Male','Cashier',10000.00,'454654_1','545645','','Aadhaar Card','45645','454  ','2020-05-05',NULL,'Admin','2020-05-13','18:03:55'),(14,'45456_1','Employee','Male','Cashier',12000.00,'454654','545645','','Aadhaar Card','45645','','2020-05-05',NULL,'Admin','2020-05-13','18:03:23');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
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

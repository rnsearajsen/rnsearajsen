-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: try
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Dumping data for table `expense`
--

LOCK TABLES `expense` WRITE;
/*!40000 ALTER TABLE `expense` DISABLE KEYS */;
INSERT INTO `expense` VALUES ('2021-02-16','SHIFT1','Others',23.00,0.00,'dsd','Admin','2021-02-17','16:57:01'),('2021-02-16','SHIFT1','Phone Bill',26.00,0.00,'32','Admin','2021-02-17','16:57:03'),('2021-02-17','SHIFT1','EB Bill',2342342400.00,0.00,'edfsdf','Admin','2021-06-24','15:23:05'),('2021-02-17','SHIFT1','Others',3232.00,0.00,'eds','Admin','2021-06-24','15:23:06'),('2021-02-17','SHIFT1','Phone Bill',24.00,0.00,'erwer','Admin','2021-06-24','15:23:11'),('2021-02-18','SHIFT2','EB Bill',232.00,0.00,'323','Admin','2021-02-24','10:02:55'),('2021-02-20','SHIFT1','Others',90.00,0.00,'Test','Admin','2021-06-24','16:01:12'),('2021-02-27','SHIFT1','Phone Bill',232.00,0.00,'dfs','Admin','2021-06-24','21:50:57'),('2021-02-28','SHIFT1','Others',250.00,0.00,'Test','Admin','2021-06-24','21:49:21'),('2021-03-02','SHIFT1','Others',250.00,0.00,'322','Admin','2021-06-24','22:06:02'),('2021-03-03','SHIFT1','Others',235.00,0.00,'rers','Admin','2021-06-24','22:08:47'),('2021-03-04','SHIFT1','EB Bill',456.00,0.00,'hghjki','Admin','2021-06-24','22:12:22'),('2021-03-04','SHIFT1','Others',456.00,0.00,'hgjg','Admin','2021-06-24','22:12:22'),('2021-03-04','SHIFT1','Phone Bill',78.00,0.00,'hjj','Admin','2021-06-24','22:12:22'),('2021-08-06','SHIFT1','EB Bill',500.00,0.00,'Test','Admin','2021-08-20','12:40:26'),('2021-08-06','SHIFT1','Others',500.00,1000.00,'Electrical Service','Admin','2021-08-20','12:40:26'),('2021-08-06','SHIFT1','Phone Bill',100.00,0.00,'Test','Admin','2021-08-20','12:40:26'),('2021-08-06','SHIFT2','Others',500.00,0.00,'Test','Admin','2021-08-18','15:47:00'),('2021-08-06','SHIFT2','Phone Bill',1000.00,0.00,'test','Admin','2021-08-18','15:47:00'),('2021-08-07','SHIFT1','EB Bill',0.00,1000.00,'Test','Admin','2021-10-20','20:25:37'),('2021-08-07','SHIFT1','Others',1000.00,0.00,'Generator Exp','Admin','2021-10-20','20:25:37'),('2021-08-07','SHIFT2','EB Bill',1000.00,500.00,'Test1','Admin','2021-10-20','20:27:45'),('2021-08-07','SHIFT2','Phone Bill',1000.00,0.00,'Test','Admin','2021-10-20','20:27:45'),('2021-11-06','SHIFT1','PLUMBER WORKS',0.00,65.00,'PIPE IN OFFICE CASH','Admin','2021-11-07','13:20:07'),('2021-11-07','SHIFT1','FLOWERS',30.00,0.00,'flowers - 30','Sivaraj','2021-11-08','11:30:07'),('2021-11-07','SHIFT1','Generator_HSD_Oil',3680.00,0.00,'generator diesel 40','Sivaraj','2021-11-08','11:30:07'),('2021-11-07','SHIFT1','Oil Coupon',220.00,0.00,'oil coupon -220','Sivaraj','2021-11-08','11:30:07'),('2021-11-08','SHIFT1','FLOWERS',30.00,0.00,'flower - 30','Admin','2021-11-09','11:01:23'),('2021-11-08','SHIFT1','Generator_HSD_Oil',0.00,5260.00,'generator oil service - 5063','Admin','2021-11-09','11:01:23'),('2021-11-08','SHIFT1','Lorry Mamul',230.00,0.00,'lorry mamul-230','Admin','2021-11-09','11:01:23'),('2021-11-08','SHIFT1','Oil Coupon',140.00,0.00,'oil coupon','Admin','2021-11-09','11:01:23'),('2021-11-09','SHIFT1','Auditor Fees',0.00,22917.00,'LFR & TDS FILLING - 22,917','Sivaraj','2021-11-10','09:44:30'),('2021-11-09','SHIFT1','EB Maintenance',0.00,9200.00,'changover - 9,200','Sivaraj','2021-11-10','09:44:30'),('2021-11-09','SHIFT1','Oil Coupon',140.00,0.00,'oil coupon - 140','Sivaraj','2021-11-10','09:44:30'),('2021-11-09','SHIFT1','PLUMBER WORKS',2100.00,0.00,'mesan - 2000 tea - 100','Sivaraj','2021-11-10','09:44:30');
/*!40000 ALTER TABLE `expense` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-10 18:13:55

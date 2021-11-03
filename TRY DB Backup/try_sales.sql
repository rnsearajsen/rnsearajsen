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
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales` (
  `product` varchar(25) NOT NULL,
  `salesdate` date NOT NULL,
  `labour_shift` varchar(25) NOT NULL,
  `sales_priceunit` float(21,2) NOT NULL,
  `purchase_priceunit` float(21,2) NOT NULL,
  `purchasedate` date DEFAULT NULL,
  `subgroup_name` varchar(25) DEFAULT NULL,
  `sales_qty` float(15,2) DEFAULT '0.00',
  `testqty` float(15,2) DEFAULT '0.00',
  `total_sales_price` float(15,2) DEFAULT '0.00',
  `uom` varchar(15) DEFAULT NULL,
  `taxpercent_total` float(21,2) DEFAULT '0.00',
  `taxamt_total` float(21,2) DEFAULT '0.00',
  `comments` varchar(150) DEFAULT NULL,
  `changedby` varchar(20) DEFAULT NULL,
  `changeddate` date DEFAULT NULL,
  `changedtime` time DEFAULT NULL,
  PRIMARY KEY (`product`,`salesdate`,`labour_shift`,`sales_priceunit`,`purchase_priceunit`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` VALUES ('20ml packet oil','2020-06-01','shift1',10.00,8.00,'2020-05-25','PACKET_OIL',100.00,0.00,1000.00,'packet',18.00,180.00,'test','admin','2020-06-03','05:06:08'),('20ml packet oil','2020-06-07','SHIFT1',7.00,14.00,'2020-05-06','PACKET_OIL',1.00,0.00,7.00,'Packet',0.00,0.00,NULL,'Admin','2021-01-18','21:56:02'),('20ml packet oil','2020-06-08','SHIFT1',7.00,14.00,'2020-05-06','PACKET_OIL',2.00,0.00,14.00,'Packet',0.00,0.00,NULL,'Admin','2021-01-19','12:16:45'),('20ml packet oil','2020-06-09','SHIFT1',7.00,14.00,'2020-05-06','PACKET_OIL',1.00,0.00,7.00,'Packet',12.00,0.84,NULL,'Admin','2021-01-19','13:54:40'),('20ml packet oil','2020-06-10','SHIFT1',7.00,14.00,'2020-05-06','PACKET_OIL',1.00,0.00,7.00,'Packet',12.00,0.84,NULL,'Admin','2021-01-20','08:43:23'),('20ml packet oil','2020-06-11','SHIFT1',7.00,14.00,'2020-05-06','PACKET_OIL',1.00,0.00,7.00,'Packet',12.00,0.84,NULL,'Admin','2021-01-20','09:02:41'),('20ml packet oil','2020-06-12','SHIFT1',7.00,14.00,'2020-05-06','PACKET_OIL',1.00,0.00,7.00,'Packet',12.00,0.84,NULL,'Admin','2021-01-20','09:08:46'),('20ml packet oil','2020-06-13','SHIFT1',7.00,14.00,'2020-05-06','PACKET_OIL',1.00,0.00,7.00,'Packet',12.00,0.84,NULL,'Admin','2021-01-20','09:14:38'),('20ml packet oil','2020-06-14','SHIFT1',7.00,14.00,'2020-05-06','PACKET_OIL',1.00,0.00,7.00,'Packet',12.00,0.84,NULL,'Admin','2021-01-20','16:01:18'),('20ml packet oil','2020-06-15','SHIFT1',7.00,14.00,'2020-05-06','PACKET_OIL',1.00,0.00,7.00,'Packet',12.00,0.84,NULL,'Admin','2021-01-20','16:09:21'),('20ml packet oil','2020-06-16','SHIFT1',7.00,14.00,'2020-05-06','PACKET_OIL',2.00,0.00,14.00,'Packet',12.00,1.68,NULL,'Admin','2021-01-20','16:40:22'),('20ml packet oil','2021-08-06','SHIFT1',10.00,14.00,'2020-05-06','PACKET_OIL',100.00,0.00,1000.00,'Packet',10.00,100.00,NULL,'Admin','2021-08-06','16:18:16'),('20ml packet oil','2021-08-06','SHIFT2',10.00,14.00,'2020-05-06','PACKET_OIL',10.00,0.00,100.00,'Packet',10.00,10.00,NULL,'Admin','2021-08-06','16:18:59'),('20ml packet oil','2021-08-07','SHIFT1',10.00,14.00,'2020-05-06','PACKET_OIL',46.00,0.00,460.00,'Packet',10.00,46.00,NULL,'Admin','2021-10-20','19:56:16'),('20ml packet oil','2021-08-07','SHIFT2',10.00,14.00,'2020-05-06','PACKET_OIL',25.00,0.00,250.00,'Packet',10.00,25.00,NULL,'Admin','2021-10-20','19:57:11'),('40ml packet oil','2020-06-01','shift1',15.00,13.00,'2020-05-25','PACKET_OIL',100.00,0.00,1500.00,'packet',18.00,180.00,'test','admin','2020-06-01','05:06:08'),('40ml packet oil','2020-06-07','SHIFT1',12.00,10.00,'2020-05-05','PACKET_OIL',1.00,0.00,12.00,'Packet',0.00,0.00,NULL,'Admin','2021-01-18','21:56:02'),('40ml packet oil','2020-06-08','SHIFT1',12.00,10.00,'2020-05-05','PACKET_OIL',2.00,0.00,24.00,'Packet',0.00,0.00,NULL,'Admin','2021-01-19','12:16:45'),('40ml packet oil','2020-06-09','SHIFT1',12.00,10.00,'2020-05-05','PACKET_OIL',1.00,0.00,12.00,'Packet',18.00,2.16,NULL,'Admin','2021-01-19','13:54:42'),('40ml packet oil','2020-06-10','SHIFT1',12.00,10.00,'2020-05-05','PACKET_OIL',1.00,0.00,12.00,'Packet',18.00,2.16,NULL,'Admin','2021-01-20','08:43:23'),('40ml packet oil','2020-06-11','SHIFT1',12.00,10.00,'2020-05-05','PACKET_OIL',1.00,0.00,12.00,'Packet',18.00,2.16,NULL,'Admin','2021-01-20','09:02:41'),('40ml packet oil','2020-06-12','SHIFT1',12.00,10.00,'2020-05-05','PACKET_OIL',1.00,0.00,12.00,'Packet',18.00,2.16,NULL,'Admin','2021-01-20','09:08:46'),('40ml packet oil','2020-06-13','SHIFT1',12.00,10.00,'2020-05-05','PACKET_OIL',1.00,0.00,12.00,'Packet',18.00,2.16,NULL,'Admin','2021-01-20','09:14:38'),('40ml packet oil','2020-06-14','SHIFT1',12.00,10.00,'2020-05-05','PACKET_OIL',1.00,0.00,12.00,'Packet',18.00,2.16,NULL,'Admin','2021-01-20','16:01:18'),('40ml packet oil','2020-06-15','SHIFT1',12.00,10.00,'2020-05-05','PACKET_OIL',1.00,0.00,12.00,'Packet',18.00,2.16,NULL,'Admin','2021-01-20','16:09:21'),('40ml packet oil','2020-06-16','SHIFT1',12.00,10.00,'2020-05-05','PACKET_OIL',1.00,0.00,12.00,'Packet',18.00,2.16,NULL,'Admin','2021-01-20','16:40:22'),('40ml packet oil','2021-08-06','SHIFT1',15.00,10.00,'2020-05-05','PACKET_OIL',50.00,0.00,750.00,'Packet',10.00,75.00,NULL,'Admin','2021-08-06','16:18:16'),('40ml packet oil','2021-08-06','SHIFT2',15.00,10.00,'2020-05-05','PACKET_OIL',5.00,0.00,75.00,'Packet',10.00,7.50,NULL,'Admin','2021-08-06','16:18:59'),('40ml packet oil','2021-08-07','SHIFT1',15.00,10.00,'2020-05-05','PACKET_OIL',32.00,0.00,480.00,'Packet',10.00,48.00,NULL,'Admin','2021-10-20','19:56:16'),('40ml packet oil','2021-08-07','SHIFT2',15.00,10.00,'2020-05-05','PACKET_OIL',20.00,0.00,300.00,'Packet',10.00,30.00,NULL,'Admin','2021-10-20','19:57:11'),('60ml packet oil','2020-06-02','shift1',15.00,13.00,'2020-05-25','PACKET_OIL',1000.00,0.00,15000.00,'packet',19.00,1900.00,'test','admin','2020-06-03','05:06:08'),('60ml packet oil','2020-06-07','SHIFT1',20.00,0.00,NULL,'PACKET_OIL',NULL,0.00,NULL,NULL,0.00,NULL,NULL,'Admin','2021-01-18','21:56:02'),('60ml packet oil','2020-06-08','SHIFT1',20.00,0.00,NULL,'PACKET_OIL',NULL,0.00,NULL,NULL,0.00,NULL,NULL,'Admin','2021-01-19','12:16:45'),('60ml packet oil','2020-06-09','SHIFT1',20.00,15.00,'2021-01-19','PACKET_OIL',1.00,0.00,20.00,'Packet',18.00,3.60,NULL,'Admin','2021-01-19','13:54:43'),('60ml packet oil','2020-06-15','SHIFT1',20.00,0.00,NULL,'PACKET_OIL',NULL,0.00,NULL,NULL,18.00,NULL,NULL,'Admin','2021-01-20','16:09:21'),('60ml packet oil','2020-06-16','SHIFT1',20.00,0.00,NULL,'PACKET_OIL',NULL,0.00,NULL,NULL,18.00,NULL,NULL,'Admin','2021-01-20','16:40:22'),('60ml packet oil','2021-08-06','SHIFT1',20.00,18.00,'2021-08-06','PACKET_OIL',100.00,0.00,2000.00,'Packet',10.00,200.00,NULL,'Admin','2021-08-06','16:18:16'),('60ml packet oil','2021-08-06','SHIFT2',20.00,18.00,'2021-08-06','PACKET_OIL',10.00,0.00,200.00,'Packet',10.00,20.00,NULL,'Admin','2021-08-06','16:18:59'),('60ml packet oil','2021-08-07','SHIFT1',20.00,18.00,'2021-08-06','PACKET_OIL',10.00,0.00,200.00,'Packet',10.00,20.00,NULL,'Admin','2021-10-20','19:56:16'),('60ml packet oil','2021-08-07','SHIFT2',20.00,18.00,'2021-08-06','PACKET_OIL',10.00,0.00,200.00,'Packet',10.00,20.00,NULL,'Admin','2021-10-20','19:57:11'),('800 NXT','2020-06-17','SHIFT1',200.00,150.00,'2021-01-22','Engine_oil',2.00,0.00,400.00,'Can',18.00,72.00,NULL,'Admin','2021-01-27','20:20:08'),('800 NXT','2020-06-19','SHIFT1',200.00,150.00,'2021-01-22','Engine_oil',2.00,0.00,400.00,'Can',18.00,72.00,NULL,'Admin','2021-02-17','16:37:25'),('800 NXT','2021-08-06','SHIFT1',350.00,150.00,'2021-01-22','Engine_oil',10.00,0.00,3500.00,'Can',10.00,350.00,NULL,'Admin','2021-08-06','16:17:23'),('800 NXT','2021-08-07','SHIFT1',350.00,150.00,'2021-01-22','Engine_oil',10.00,0.00,3500.00,'Can',10.00,350.00,NULL,'Admin','2021-10-20','19:54:51'),('900 HERO','2020-06-17','SHIFT1',150.00,120.00,'2021-01-22','Engine_oil',2.00,0.00,300.00,'Can',18.00,54.00,NULL,'Admin','2021-01-27','20:20:08'),('900 HERO','2020-06-18','SHIFT1',150.00,120.00,'2021-01-22','Engine_oil',1.00,0.00,150.00,'Can',18.00,27.00,NULL,'Admin','2021-01-27','21:18:44'),('900 HERO','2021-08-06','SHIFT1',650.00,600.00,'2021-08-06','Engine_oil',5.00,0.00,3250.00,'Can',10.00,325.00,NULL,'Admin','2021-08-06','16:17:23'),('900 HERO','2021-08-06','SHIFT2',650.00,600.00,'2021-08-06','Engine_oil',1.00,0.00,650.00,'Can',10.00,65.00,NULL,'Admin','2021-08-06','16:20:00'),('900 HERO','2021-08-07','SHIFT2',650.00,600.00,'2021-08-06','Engine_oil',5.00,0.00,3250.00,'Can',10.00,325.00,NULL,'Admin','2021-10-20','19:55:36'),('900 STALLION','2020-06-17','SHIFT1',200.00,150.00,'2021-01-22','Engine_oil',1.00,0.00,200.00,'Can',18.00,36.00,NULL,'Admin','2021-01-27','20:20:08'),('900 STALLION','2020-06-18','SHIFT1',200.00,150.00,'2021-01-22','Engine_oil',2.00,0.00,400.00,'Can',18.00,72.00,NULL,'Admin','2021-01-27','21:18:44'),('900 STALLION','2021-08-07','SHIFT1',550.00,150.00,'2021-01-22','Engine_oil',10.00,0.00,5500.00,'Can',10.00,550.00,NULL,'Admin','2021-10-20','19:54:51'),('900 STALLION','2021-08-07','SHIFT2',550.00,150.00,'2021-01-22','Engine_oil',5.00,0.00,2750.00,'Can',10.00,275.00,NULL,'Admin','2021-10-20','19:55:36'),('900NXT','2020-06-17','SHIFT1',150.00,100.00,'2020-05-23','Engine_oil',1.00,0.00,150.00,'Can',18.00,27.00,NULL,'Admin','2021-01-27','20:20:08'),('900NXT','2020-06-18','SHIFT1',150.00,100.00,'2020-05-23','Engine_oil',2.00,0.00,300.00,'Can',18.00,54.00,NULL,'Admin','2021-01-27','21:18:44'),('900NXT','2020-06-19','SHIFT1',150.00,100.00,'2020-05-23','Engine_oil',2.00,0.00,300.00,'Can',18.00,54.00,NULL,'Admin','2021-02-17','16:37:25'),('BRAKE OIL','2020-06-17','SHIFT1',25.00,20.00,'2021-01-22','Engine_oil',2.00,0.00,50.00,'Can',18.00,9.00,NULL,'Admin','2021-01-27','20:20:08'),('BRAKE OIL','2020-06-18','SHIFT1',25.00,20.00,'2021-01-22','Engine_oil',1.00,0.00,25.00,'Can',18.00,4.50,NULL,'Admin','2021-01-27','21:18:44'),('BRAKE OIL','2021-08-06','SHIFT2',500.00,20.00,'2021-01-22','Engine_oil',5.00,0.00,2500.00,'Can',10.00,250.00,NULL,'Admin','2021-08-06','16:20:00'),('Diesel(HSD)','2020-06-05','SHIFT1',60.00,10.00,'2020-05-12','fuel',300.00,0.00,18000.00,'Litres',0.00,0.00,'','Admin','2020-06-11','20:27:44'),('Diesel(HSD)','2021-08-06','SHIFT1',90.00,10.00,'2020-06-01','fuel',200.00,0.00,18000.00,'Litres',0.00,0.00,'','Admin','2021-08-06','15:10:50'),('Diesel(HSD)','2021-08-06','SHIFT1',90.00,70.00,'2020-06-01','fuel',1800.00,0.00,162000.00,'Litres',0.00,0.00,'','Admin','2021-08-06','15:10:50'),('Diesel(HSD)','2021-08-06','SHIFT2',90.00,70.00,'2020-06-01','fuel',200.00,0.00,18000.00,'Litres',0.00,0.00,'','Admin','2021-08-06','16:20:52'),('Diesel(HSD)','2021-08-07','SHIFT1',99.50,70.00,'2021-08-06','fuel',110.00,50.00,10945.00,'Litres',0.00,0.00,'','Admin','2021-10-20','19:20:46'),('Diesel(HSD)','2021-08-07','SHIFT1',99.50,97.35,'2021-08-06','fuel',640.00,50.00,63680.00,'Litres',0.00,0.00,'','Admin','2021-10-20','19:20:46'),('Diesel(HSD)','2021-08-07','SHIFT1',100.00,97.35,'2021-08-06','fuel',250.00,50.00,25000.00,'Litres',0.00,0.00,'','Admin','2021-10-20','19:21:20'),('Diesel(HSD)','2021-08-07','SHIFT2',99.50,97.35,'2021-08-06','fuel',540.00,60.00,53730.00,'Litres',0.00,0.00,'','Admin','2021-10-20','19:22:51'),('MULTI LOOSE','2020-06-17','SHIFT1',20.00,15.00,'2021-01-22','Engine_oil',2.00,0.00,40.00,'Can',18.00,7.20,NULL,'Admin','2021-01-27','20:20:08'),('MULTI LOOSE','2020-06-18','SHIFT1',20.00,15.00,'2021-01-22','Engine_oil',2.00,0.00,40.00,'Can',18.00,7.20,NULL,'Admin','2021-01-27','21:18:44'),('Petrol(MS)','2020-05-27','SHIFT1',76.00,72.00,'2020-06-01','fuel',300.00,0.00,22800.00,'Litres',0.00,0.00,'','Admin','2020-06-27','16:31:58'),('Petrol(MS)','2020-06-04','SHIFT1',72.00,70.00,'2020-06-01','fuel',500.00,0.00,36000.00,'Litres',18.00,6480.00,'test','admin','2020-06-04','05:05:01'),('Petrol(MS)','2020-06-04','SHIFT1',73.00,70.00,'2020-06-01','fuel',8000.00,0.00,584000.00,'Litres',18.00,105120.00,'test','admin','2020-06-04','05:05:01'),('Petrol(MS)','2020-06-05','SHIFT1',70.00,72.00,'2020-06-01','fuel',300.00,0.00,21000.00,'Litres',0.00,0.00,'','Admin','2020-06-11','10:59:33'),('Petrol(MS)','2020-06-06','SHIFT1',70.00,72.00,'2020-06-01','fuel',500.00,0.00,35000.00,'Litres',0.00,0.00,'','Admin','2020-06-27','16:22:10'),('Petrol(MS)','2021-08-06','SHIFT1',100.00,72.00,'2020-06-01','fuel',2000.00,0.00,200000.00,'Litres',0.00,0.00,'','Admin','2021-08-06','15:09:45'),('Petrol(MS)','2021-08-06','SHIFT2',100.00,72.00,'2020-06-01','fuel',200.00,0.00,20000.00,'Litres',0.00,0.00,'','Admin','2021-08-06','16:21:51'),('Petrol(MS)','2021-08-07','SHIFT1',103.50,72.00,'2020-06-01','fuel',530.00,70.00,54855.00,'Litres',0.00,0.00,'','Admin','2021-10-20','19:24:10'),('Petrol(MS)','2021-08-07','SHIFT1',104.00,72.00,'2020-06-01','fuel',280.00,20.00,29120.00,'Litres',0.00,0.00,'','Admin','2021-10-20','19:24:37'),('Petrol(MS)','2021-08-07','SHIFT2',103.50,72.00,'2020-06-01','fuel',135.00,15.00,13972.50,'Litres',0.00,0.00,'','Admin','2021-10-20','19:25:25'),('test','2020-06-03','shift1',72.00,70.00,NULL,'fuel',500.00,0.00,3600.00,'litres',18.00,600.00,'test','admin','2020-06-03','05:06:08'),('test','2020-06-03','shift1',73.00,80.00,NULL,'fuel',1000.00,0.00,3600.00,'litres',19.00,900.00,'test','admin','2020-06-03','05:06:08');
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;
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
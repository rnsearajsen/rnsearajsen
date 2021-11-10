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
-- Dumping data for table `sub_group`
--

LOCK TABLES `sub_group` WRITE;
/*!40000 ALTER TABLE `sub_group` DISABLE KEYS */;
INSERT INTO `sub_group` VALUES (1,'CARD','TXN_TYPE','																																			Card Txn2	'),(2,'Engine_oil','PRODUCT','																												test  4'),(3,'ONLINE','TXN_TYPE','							Online: (E.g)Paytm					  '),(4,'PACKET_OIL','PRODUCT','							Packet Oil 2'),(5,'FUEL','PRODUCT','Petrol/Diesel'),(6,'PARTNER','OWNER','Partner details						 '),(7,'Aadhaar Card','IDCARDTYPE','																					Aadhaar Card1				    '),(8,'PAN Card','IDCARDTYPE','PAN Card					 '),(9,'Driving License','IDCARDTYPE','Driving License				 '),(10,'Credit','CUSTOMER','Credit Customer							 '),(11,'Non-Credit','CUSTOMER','Non-Credit Customer							 '),(12,'One-Time Credit','CUSTOMER','One Time Credit Customer							 '),(14,'Cashier','EMPLOYEE','Cashier					 '),(17,'FUEL_SUP','VENDOR','Petrol(MS)/Diesel(HSD)'),(18,'OIL','VENDOR','Oil'),(19,'Male','Gender','Male							 '),(20,'Female','Gender','Female							 '),(21,'Manager','EMPLOYEE','							 '),(22,'Pump Holder','EMPLOYEE','							 '),(23,'Oil keeper','EMPLOYEE','Supervising Oil Shop				 '),(24,'Air Keeper','EMPLOYEE','			 '),(25,'Tanker Driver','EMPLOYEE','			 '),(26,'Tanker Cleaner','EMPLOYEE','			 '),(27,'Litres','UOM','Measure in Litres				 '),(28,'Packet','UOM','Measure in Packet size				 '),(29,'Can','UOM','Measure in Can			 '),(30,'Saving(SB)','BANK ACCT TYPE','Saving Acct							 '),(31,'Current(CB)','BANK ACCT TYPE','Current Acct							 '),(34,'SHIFT1','Labour_Shift','8AM to 8PM							 '),(39,'NOZZLE_P1','NOZZLE_PETROL','							Petrol Nozzle							  '),(40,'NOZZLE_P2','NOZZLE_PETROL','							Petrol Nozzle							  '),(41,'NOZZLE_P3','NOZZLE_PETROL','							Petrol Nozzle							  '),(43,'NOZZLE_D1','NOZZLE_DIESEL','Diesel Nozzle							 '),(44,'NOZZLE_D2','NOZZLE_DIESEL','Diesel Nozzle							 '),(45,'NOZZLE_D3','NOZZLE_DIESEL','Diesel Nozzle							 '),(46,'Others','EXPENSE','Other Expenses				 '),(47,'Phone Bill','EXPENSE','Phone Bill'),(48,'EB Bill','EXPENSE','EB Bill			 '),(49,'Bill_MS/HSD','Bill_Product','Fuel (MS/HSD)					  '),(51,'Bill_Oil','Bill_Product','Fuel (MS/HSD)					 '),(52,'Cash','collection_type','Cash Collection							 '),(53,'Fino','collection_type','Fino Collection							 '),(54,'PAYTM','collection_type','							PAYTM Collection							  '),(55,'PHONEPE','collection_type','							PHONEPE					  '),(56,'Sweeper','EMPLOYEE',NULL),(57,'Bottle','UOM','Measures in Litres'),(58,'KG','UOM','Kilogram'),(59,'NOZZLE_P4','NOZZLE_PETROL','							 '),(60,'NOZZLE_P5','NOZZLE_PETROL','							 '),(61,'NOZZLE_P6','NOZZLE_PETROL','							 '),(62,'NOZZLE_D4','NOZZLE_DIESEL','							 '),(63,'PLUMBER WORKS','EXPENSE','							 '),(64,'FLOWERS','EXPENSE','							 '),(65,'PETRO CARD','collection_type','	PETRO CARD						 '),(66,'HDFC','collection_type','	HDFC						 '),(67,'AGS','collection_type','	AGS						 '),(69,'CASH_1','TXN_TYPE','	CASH						 '),(70,'Generator_HSD_Oil','EXPENSE','Diesel Fill / Oil Maintenance							 '),(71,'Bike Petrol/Maintenance','EXPENSE','Bike Petrol & Maintenance							 '),(72,'Lorry Mamul','EXPENSE','Lorry Mamul'),(73,'Garbage Wages','EXPENSE','Garbage Collection Wages'),(74,'EB Maintenance','EXPENSE','EB Maintenance							 '),(75,'Oil Coupon','EXPENSE','Oil Coupon						 '),(76,'Officer Visit Expense','EXPENSE','Officer Visit Expense						 '),(77,'Stamping Visit Expense','EXPENSE','Stamping Visit Expense						 '),(78,'Auditor Fees','EXPENSE','Auditor Fees'),(79,'RD Deposit','EXPENSE','RD Deposit');
/*!40000 ALTER TABLE `sub_group` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-10 18:13:58

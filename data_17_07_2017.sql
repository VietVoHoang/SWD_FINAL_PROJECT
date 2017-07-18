-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: waterincdb
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bonuscoefficient`
--

DROP TABLE IF EXISTS `bonuscoefficient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bonuscoefficient` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `coefficientByDay` int(11) NOT NULL,
  `coefficientByHour` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bonuscoefficient`
--

LOCK TABLES `bonuscoefficient` WRITE;
/*!40000 ALTER TABLE `bonuscoefficient` DISABLE KEYS */;
INSERT INTO `bonuscoefficient` VALUES (2,2,2);
/*!40000 ALTER TABLE `bonuscoefficient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employees` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `dayOff` int(11) NOT NULL,
  `baseSalary` double NOT NULL,
  `titleName` varchar(45) NOT NULL,
  `bonusDay` int(11) NOT NULL,
  `hourOff` int(11) NOT NULL,
  `bonusHour` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (4,'Võ Thạch Nguyên',1,0,5000000,'Giám Đốc',4,1,24),(5,'Võ Hoàng Việt',1,5,3500000,'Tài Xế',2,5,10),(6,'Phan Hồng Đức',1,4,3500000,'Nhân Viên Quản Lí',2,4,9),(7,'Đặng Kim Anh',1,2,3500000,'Nhân Viên',3,8,8),(8,'Nguyễn Văn Tài',1,2,3500000,'Nhân Viên',2,5,12),(9,'Ưng Hoàng Phúc',1,1,3500000,'Nhân Viên',3,4,12),(10,'Văn Khắc Việt',1,2,4000000,'Nhân Viên Quản Lí',5,3,4);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderitems`
--

DROP TABLE IF EXISTS `orderitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderitems` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itemQuantity` int(11) NOT NULL,
  `Product_id` int(11) NOT NULL,
  `Order_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_OrderItem_Product_idx` (`Product_id`),
  KEY `fk_OrderItem_Order1_idx` (`Order_id`),
  CONSTRAINT `fk_OrderItem_Order1` FOREIGN KEY (`Order_id`) REFERENCES `orders` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrderItem_Product` FOREIGN KEY (`Product_id`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitems`
--

LOCK TABLES `orderitems` WRITE;
/*!40000 ALTER TABLE `orderitems` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderitems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderCreateDate` datetime NOT NULL,
  `orderTotal` double NOT NULL,
  `orderStatus` int(11) NOT NULL,
  `customerName` varchar(45) NOT NULL COMMENT '	',
  `customerPhone` varchar(11) NOT NULL,
  `customerAddress` varchar(100) NOT NULL,
  `Employee_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Order_Employee1_idx` (`Employee_id`),
  CONSTRAINT `fk_Order_Employee1` FOREIGN KEY (`Employee_id`) REFERENCES `employees` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productName` varchar(45) NOT NULL,
  `productQuantity` int(11) NOT NULL,
  `productPrice` double NOT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (3,'Chai NAOMI 330Ml',500,1500,1),(4,'Chai NAOMI 500Ml',500,2000,1),(5,'Chai NAOMI 1.5L',500,2500,1),(6,'Lóc NAOMI 330Ml',500,17000,1),(7,'Lóc NAOMI 500Ml',500,30000,1),(8,'Lóc NAOMI 1.5L',500,24000,1),(9,'Bình NAOMI 7.5L',500,7000,1),(10,'Bình NAOMI 20L',500,12000,1);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salary`
--

DROP TABLE IF EXISTS `salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salary` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '	',
  `salaryByDay` double NOT NULL,
  `salaryByHour` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salary`
--

LOCK TABLES `salary` WRITE;
/*!40000 ALTER TABLE `salary` DISABLE KEYS */;
INSERT INTO `salary` VALUES (2,150000,10000);
/*!40000 ALTER TABLE `salary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `enable` int(11) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `Employee_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `fk_User_Employee1_idx` (`Employee_id`),
  CONSTRAINT `fk_User_Employee1` FOREIGN KEY (`Employee_id`) REFERENCES `employees` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (7,'nguyenvt','nguyenvt',1,'ROLE_ADMIN',4),(8,'ducph','ducph',1,'ROLE_USER',6),(9,'vietvh','vietvh',1,'ROLE_USER',5);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-17 18:15:31

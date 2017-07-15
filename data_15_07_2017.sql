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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bonuscoefficient`
--

LOCK TABLES `bonuscoefficient` WRITE;
/*!40000 ALTER TABLE `bonuscoefficient` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` (`id`, `name`, `status`, `dayOff`, `baseSalary`, `titleName`, `bonusDay`, `hourOff`, `bonusHour`) VALUES (1,'viet',1,0,10000000,'Manager',0,0,0),(2,'nguyen',1,0,1000000,'Driver',5,0,10);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitems`
--

LOCK TABLES `orderitems` WRITE;
/*!40000 ALTER TABLE `orderitems` DISABLE KEYS */;
INSERT INTO `orderitems` (`id`, `itemQuantity`, `Product_id`, `Order_id`) VALUES (1,10,1,3),(2,10,1,10),(3,10,2,10);
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`id`, `orderCreateDate`, `orderTotal`, `orderStatus`, `customerName`, `customerPhone`, `customerAddress`, `Employee_id`) VALUES (3,'2017-07-12 00:00:00',100000,2,'abcd','123456789','jashdgjash',1),(4,'2017-07-12 00:00:00',100000,2,'sdfds','987654321','sdfdsf',1),(5,'2017-07-12 00:00:00',100000,1,'sdfdgfhgfh','564791345','dsfsdfsdgf',1),(6,'2017-07-13 00:00:00',1000000,1,'aksjhdjaks','123456879','khjdgfksdjhfk',1),(7,'2017-07-14 00:00:00',1231546,0,'skjdfhdsk','123456456','dskjfhsdkj',1),(8,'2017-07-14 18:48:05',1000000,0,'abcd','123456789','abcd',1),(9,'2017-07-14 18:48:05',1000000,0,'abcd','123456789','abcd',NULL),(10,'2017-07-14 19:48:05',1000000,1,'abcd','123456789','abcd',1),(11,'2017-07-14 19:48:05',50000,2,'Nguyen','01233461809','123 ABCD',1),(12,'2017-07-14 09:30:47',500000,2,'viet','123456789','123 abcd',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` (`id`, `productName`, `productQuantity`, `productPrice`, `status`) VALUES (1,'a',1,12,1),(2,'b',2,2,1);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salary`
--

LOCK TABLES `salary` WRITE;
/*!40000 ALTER TABLE `salary` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `username`, `password`, `enable`, `role`, `Employee_id`) VALUES (4,'viet','123456',1,'ROLE_ADMIN',1),(5,'nguyen','123456',1,'ROLE_USER',2);
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

-- Dump completed on 2017-07-15 22:23:32

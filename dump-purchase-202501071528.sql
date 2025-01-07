-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: purchase
-- ------------------------------------------------------
-- Server version	9.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase` (
  `id` varchar(16) NOT NULL,
  `buyDate` date NOT NULL,
  `price` float NOT NULL,
  `quantity` int NOT NULL,
  `state` enum('pending','cancelled','delivered','in_delivery','confirmed') NOT NULL,
  `idProduct` varchar(36) NOT NULL,
  `product` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES ('0ee4800f-e69f-4','2024-05-29',92.6,17,'pending','77cf4a22-2de7-49d5-9f70-a539994ead33','Product_975'),('1947c848-8122-4','2024-02-17',427.25,41,'confirmed','8a3ffa99-f53a-4778-91e2-feedc1b40416','Product_446'),('1fd50eb8-b8c4-4','2024-10-14',78.21,30,'delivered','b147be12-c903-45f5-be5f-2406dfa670b6','Product_914'),('23838f15-1676-4e','2024-06-21',436.51,37,'in_delivery','dfae690f-1e50-4763-9225-a2634454b216','Product_181'),('38bf3233-9028-4','2024-02-20',433.44,4,'cancelled','0b188c3e-c9d2-464b-9efa-c00c59073faf','Product_154'),('3ab47ad6-044d-4','2024-10-26',57.46,14,'pending','53f83c9c-40df-4f71-97a4-563204a2ff01','Product_861'),('650599a6-8b5e-4','2024-01-19',32.68,48,'in_delivery','69d47814-9742-4bae-b3da-8515b4d2e5f4','Product_577'),('7a538497-81df-42','2024-06-02',147.78,11,'confirmed','58d6cd08-c674-45c3-a3b0-1f96398f43fa','Product_147'),('bd7a9f8c-9a4c-4','2024-06-11',246.57,30,'pending','36f88fb6-275e-45eb-a1b8-ef0f4adcf7c6','Product_934'),('ea2ad64f-8f2e-4','2024-06-20',365.07,48,'confirmed','abb64834-2cfd-4080-9b45-c22b8adb6ab8','Product_866');
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'purchase'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-07 15:28:49

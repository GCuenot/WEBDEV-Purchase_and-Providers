-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: provider
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
-- Table structure for table `provider`
--

DROP TABLE IF EXISTS `provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provider` (
  `id` varchar(16) NOT NULL,
  `name` varchar(255) NOT NULL,
  `service` varchar(255) NOT NULL,
  `siren` varchar(9) NOT NULL,
  `status` varchar(50) NOT NULL,
  `id_contact` varchar(16) NOT NULL,
  `registration_date` date NOT NULL,
  `region` varchar(100) NOT NULL,
  `legal_informations` text NOT NULL,
  `category` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provider`
--

LOCK TABLES `provider` WRITE;
/*!40000 ALTER TABLE `provider` DISABLE KEYS */;
INSERT INTO `provider` VALUES ('1a7e9b4f3c2d5c8e','Gonzalez','embrace bleeding-edge mindshare','469212479','Inactive','343','2024-06-25','Bretagne','L\'assurance d\'avancer de mani�re efficace','Retail'),('2d4f9a8e7c1b3d6f','Roux','grow web-enabled mindshare','524027091','Active','111','2024-03-03','Bretagne','L\'avantage d\'�voluer sans soucis','Healthcare'),('3e5d9c7a4f1b2c6d','Dufour','deploy seamless users','665149097','Suspended','220','2023-04-05','Occitanie','La libert� d\'avancer plus rapidement','IT Services'),('4a5c9b8d3e2f1d4c','Grenier','cultivate end-to-end infrastructures','984179959','Inactive','129','2015-04-24','Occitanie','Le confort d\'�voluer plus rapidement','Agriculture'),('6d8c7e1b2f9a3d4f','Schneider SARL','benchmark turn-key infrastructures','522132365','Suspended','405','2019-07-12','Nouvelle-Aquitaine','Le pouvoir de rouler avant-tout','IT Services'),('7c1d5f3b4e2a9d8f','Duhamel','innovate front-end web services','779346520','Active','79','2018-06-05','Bourgogne-Franche-Comt�','Le droit de rouler autrement','Education'),('7d3f5e9b4c1d3a8f','Olivier Normand et Fils','reinvent holistic technologies','896059353','Pending','7','2017-01-22','Grand Est','L\'avantage d\'atteindre vos buts de mani�re efficace','Healthcare'),('9b4c3d5e6f7a8c1d','Lebon','strategize vertical e-business','981208650','Active','460','2024-07-05','Normandie','L\'assurance de changer � la pointe','IT Services'),('b2f4e7d3c1a8b9c5','Guilbert','matrix granular applications','476309348','Pending','322','2024-06-01','Bourgogne-Franche-Comt�','Le plaisir de changer sans soucis','Education'),('f91a3e4b2d7f9c7b','Courtois et Fils','scale revolutionary infrastructures','309218916','Suspended','337','2017-10-04','Bretagne','Le plaisir d\'�voluer de mani�re s�re','Construction');
/*!40000 ALTER TABLE `provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'provider'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-07 15:10:39

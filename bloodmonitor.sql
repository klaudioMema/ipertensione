-- MySQL dump 10.13  Distrib 8.0.35, for Linux (x86_64)
--
-- Host: localhost    Database: bloodmonitor
-- ------------------------------------------------------
-- Server version	8.0.35-0ubuntu0.23.10.1

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
-- Table structure for table `agentesanitario`
--

DROP TABLE IF EXISTS `agentesanitario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agentesanitario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` text,
  `cognome` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agentesanitario`
--

LOCK TABLES `agentesanitario` WRITE;
/*!40000 ALTER TABLE `agentesanitario` DISABLE KEYS */;
INSERT INTO `agentesanitario` VALUES (2,'giorgio','giorgio@gmail.com','giorgio','di'),(3,'a','a','a','a');
/*!40000 ALTER TABLE `agentesanitario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bloodpressure`
--

DROP TABLE IF EXISTS `bloodpressure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bloodpressure` (
  `user_id` int DEFAULT NULL,
  `sbp` int DEFAULT NULL,
  `dbp` int DEFAULT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bloodpressure`
--

LOCK TABLES `bloodpressure` WRITE;
/*!40000 ALTER TABLE `bloodpressure` DISABLE KEYS */;
INSERT INTO `bloodpressure` VALUES (1,100,100,'2023-07-10'),(2,100,90,'2023-07-10'),(32,100,100,'2023-07-13'),(32,90,90,'2023-07-12'),(32,90,100,'2023-07-16');
/*!40000 ALTER TABLE `bloodpressure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medics`
--

DROP TABLE IF EXISTS `medics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medics` (
  `doctor_id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) DEFAULT NULL,
  `cognome` varchar(50) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` text,
  PRIMARY KEY (`doctor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medics`
--

LOCK TABLES `medics` WRITE;
/*!40000 ALTER TABLE `medics` DISABLE KEYS */;
INSERT INTO `medics` VALUES (10,'Jon','Non','Jo@gmail.com','jo'),(11,'antonio','rossi','antoniorossi@gmail.com','a'),(12,'biagio','alfarano','biagio@gmail.com','biagio');
/*!40000 ALTER TABLE `medics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patients` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) DEFAULT NULL,
  `cognome` varchar(50) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` text,
  `codicef` varchar(16) DEFAULT NULL,
  `bday` date DEFAULT NULL,
  `doctor_id` int DEFAULT NULL,
  `fattoridirischio` text,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `patients_pk` (`codicef`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES (35,'luca','fron','luca@gmail.com','luca','luca','1999-09-13',2,'fumatore, drogato'),(36,'a','a','a','aaaaaaaa','a','2024-02-12',10,NULL),(37,'b','b','b','bbbbbbbb','b','2024-02-05',12,NULL),(38,'b','b','b','bbbbbbbb','bb','2024-02-05',11,NULL),(39,'asdf','asdf','asdf','asdfasdf','asdf','2024-01-29',12,NULL),(40,'qwer','qwerqwer','qwer','qwerqwer','qwer','2024-02-16',11,NULL),(41,'zxcv','zxcv','zxcv','zxcvzxcv','zxcv','2024-02-10',10,NULL),(42,'vbnm','vbmn','vbmn','vbmnvbmn','vbm','2024-03-08',10,NULL),(43,'uiop','uiop','uiop','uiopuiop','uiop','2024-02-17',10,NULL),(44,'hjkl','hjkl','hjkl','hjklhjkl','hjkl','2024-02-03',11,NULL),(45,'vczx','vcxz','vcxz','vcxzvcxz','vcxz','2024-02-15',10,NULL),(46,'fdsa','fdsa','fdsa','fdsafdsa','fdsa','2024-02-14',10,NULL);
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescriptions`
--

DROP TABLE IF EXISTS `prescriptions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prescriptions` (
  `user_id` int DEFAULT NULL,
  `medication` text,
  `indications` text,
  `days` int DEFAULT NULL,
  `fromDate` date DEFAULT NULL,
  `assumption` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescriptions`
--

LOCK TABLES `prescriptions` WRITE;
/*!40000 ALTER TABLE `prescriptions` DISABLE KEYS */;
INSERT INTO `prescriptions` VALUES (32,'asddas','321',2,'2023-07-15',NULL),(32,'bbbb','bsrbb',4,'2023-07-16',NULL);
/*!40000 ALTER TABLE `prescriptions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `takenmedication`
--

DROP TABLE IF EXISTS `takenmedication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `takenmedication` (
  `user_id` int DEFAULT NULL,
  `medication` varchar(255) DEFAULT NULL,
  `indications` text,
  `daythatwastaken` date DEFAULT NULL,
  `days` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `takenmedication`
--

LOCK TABLES `takenmedication` WRITE;
/*!40000 ALTER TABLE `takenmedication` DISABLE KEYS */;
INSERT INTO `takenmedication` VALUES (32,'bbbb','bsrbb','2023-07-16',4),(32,'asddas','321','2023-07-16',2);
/*!40000 ALTER TABLE `takenmedication` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-12 17:44:59
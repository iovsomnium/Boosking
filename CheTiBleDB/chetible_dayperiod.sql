-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: chetible
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `dayperiod`
--

DROP TABLE IF EXISTS `dayperiod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dayperiod` (
  `peCode` int(11) NOT NULL,
  `dayOfWeek` varchar(5) NOT NULL,
  `period` int(11) NOT NULL,
  PRIMARY KEY (`peCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dayperiod`
--

LOCK TABLES `dayperiod` WRITE;
/*!40000 ALTER TABLE `dayperiod` DISABLE KEYS */;
INSERT INTO `dayperiod` VALUES (1,'월요일',1),(2,'월요일',2),(3,'월요일',3),(4,'월요일',4),(5,'월요일',5),(6,'월요일',6),(7,'월요일',7),(8,'화요일',1),(9,'화요일',2),(10,'화요일',3),(11,'화요일',4),(12,'화요일',5),(13,'화요일',6),(14,'화요일',7),(15,'수요일',1),(16,'수요일',2),(17,'수요일',3),(18,'수요일',4),(19,'수요일',5),(20,'수요일',6),(21,'수요일',7),(22,'목요일',1),(23,'목요일',2),(24,'목요일',3),(25,'목요일',4),(26,'목요일',5),(27,'목요일',6),(28,'목요일',7),(29,'금요일',1),(30,'금요일',2),(31,'금요일',3),(32,'금요일',4),(33,'금요일',5),(34,'금요일',6),(35,'금요일',7);
/*!40000 ALTER TABLE `dayperiod` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-07 23:26:32

-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: escritorioadvocacia
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `processo`
--

DROP TABLE IF EXISTS `processo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `processo` (
  `idProcesso` int NOT NULL AUTO_INCREMENT,
  `nroProcesso` varchar(100) NOT NULL,
  `dataAbertura` date DEFAULT NULL,
  `dataConclusao` date DEFAULT NULL,
  `situacao` varchar(500) DEFAULT NULL,
  `idPesAutor` int DEFAULT NULL,
  `idPesReu` int DEFAULT NULL,
  `idVara` int DEFAULT NULL,
  PRIMARY KEY (`idProcesso`),
  KEY `idPes_idx` (`idPesAutor`),
  KEY `idVara_idx` (`idVara`),
  KEY `idPesReu_idx` (`idPesReu`),
  CONSTRAINT `idPesAutor` FOREIGN KEY (`idPesAutor`) REFERENCES `pessoa` (`idPes`),
  CONSTRAINT `idPesReu` FOREIGN KEY (`idPesReu`) REFERENCES `pessoa` (`idPes`),
  CONSTRAINT `idVara` FOREIGN KEY (`idVara`) REFERENCES `vara` (`idVara`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `processo`
--

LOCK TABLES `processo` WRITE;
/*!40000 ALTER TABLE `processo` DISABLE KEYS */;
INSERT INTO `processo` VALUES (1,'0001','2022-03-13','2024-04-14','PROTOCOLIZADO',2,4,1),(2,'0002','2022-03-13','2022-04-15','PROTOCOLIZADO',4,3,2),(3,'0003','2022-03-13',NULL,'PROTOCOLIZADO',4,4,1),(4,'0004','2022-03-13',NULL,'PROTOCOLIZADO',4,3,2);
/*!40000 ALTER TABLE `processo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-16 19:31:17

CREATE DATABASE  IF NOT EXISTS `sistema_reporte_incidentes` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sistema_reporte_incidentes`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: sistema_reporte_incidentes
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id_cliente` bigint NOT NULL AUTO_INCREMENT,
  `id_tipo_cliente` bigint NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `identificacion` varchar(100) NOT NULL,
  PRIMARY KEY (`id_cliente`),
  KEY `id_tipo_cliente_idx` (`id_tipo_cliente`),
  CONSTRAINT `tipo_de_cliente` FOREIGN KEY (`id_tipo_cliente`) REFERENCES `tipos_cliente` (`id_tipo_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,1,'Pepega Johnson','20-69420-4'),(3,2,'AMAZON','AMAZON INDUSTRIES SRL');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contactos`
--

DROP TABLE IF EXISTS `contactos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contactos` (
  `id_contacto` bigint NOT NULL AUTO_INCREMENT,
  `id_cliente` bigint DEFAULT NULL,
  `id_tecnico` bigint DEFAULT NULL,
  `id_tipo_contacto` bigint NOT NULL,
  `contacto` varchar(250) NOT NULL,
  PRIMARY KEY (`id_contacto`),
  KEY `tipo_de_contacto_idx` (`id_tipo_contacto`),
  CONSTRAINT `tipo_de_contacto` FOREIGN KEY (`id_tipo_contacto`) REFERENCES `tipos_contacto` (`id_tipo_contacto`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contactos`
--

LOCK TABLES `contactos` WRITE;
/*!40000 ALTER TABLE `contactos` DISABLE KEYS */;
INSERT INTO `contactos` VALUES (1,1,NULL,1,'pepega@gmail.com'),(2,3,NULL,1,'amazon@bigmoney.com'),(3,NULL,1,1,'buffken@SF6l.com'),(4,NULL,2,2,'3547-6942069');
/*!40000 ALTER TABLE `contactos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_problema`
--

DROP TABLE IF EXISTS `detalle_problema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_problema` (
  `id_incidente` bigint NOT NULL,
  `id_problema` bigint NOT NULL,
  PRIMARY KEY (`id_incidente`,`id_problema`),
  KEY `FK_problema_del_incidente_idx` (`id_problema`),
  CONSTRAINT `FK_incidente` FOREIGN KEY (`id_incidente`) REFERENCES `incidentes` (`id_incidente`),
  CONSTRAINT `FK_problema_del_incidente` FOREIGN KEY (`id_problema`) REFERENCES `problemas` (`id_problema`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_problema`
--

LOCK TABLES `detalle_problema` WRITE;
/*!40000 ALTER TABLE `detalle_problema` DISABLE KEYS */;
INSERT INTO `detalle_problema` VALUES (1,2),(15,2),(15,3);
/*!40000 ALTER TABLE `detalle_problema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especialidad_x_problema`
--

DROP TABLE IF EXISTS `especialidad_x_problema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `especialidad_x_problema` (
  `id_problema` bigint NOT NULL,
  `id_especialidad` bigint NOT NULL,
  PRIMARY KEY (`id_problema`,`id_especialidad`),
  KEY `FK_especialidad_idx` (`id_especialidad`),
  CONSTRAINT `FK_especialidad` FOREIGN KEY (`id_especialidad`) REFERENCES `especialidades` (`id_especialidad`),
  CONSTRAINT `FK_problema` FOREIGN KEY (`id_problema`) REFERENCES `problemas` (`id_problema`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especialidad_x_problema`
--

LOCK TABLES `especialidad_x_problema` WRITE;
/*!40000 ALTER TABLE `especialidad_x_problema` DISABLE KEYS */;
INSERT INTO `especialidad_x_problema` VALUES (3,2),(25,2),(2,3),(3,3),(24,3),(27,3);
/*!40000 ALTER TABLE `especialidad_x_problema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especialidades`
--

DROP TABLE IF EXISTS `especialidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `especialidades` (
  `id_especialidad` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_especialidad`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especialidades`
--

LOCK TABLES `especialidades` WRITE;
/*!40000 ALTER TABLE `especialidades` DISABLE KEYS */;
INSERT INTO `especialidades` VALUES (2,'COMPLEX PROBLEM SOLVER'),(3,'FULL STACK SOLVER');
/*!40000 ALTER TABLE `especialidades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incidentes`
--

DROP TABLE IF EXISTS `incidentes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `incidentes` (
  `id_incidente` bigint NOT NULL AUTO_INCREMENT,
  `id_cliente` bigint DEFAULT NULL,
  `id_servicio` bigint DEFAULT NULL,
  `id_tecnico` bigint DEFAULT NULL,
  `fecha_inicio` datetime DEFAULT NULL,
  `resuelto` tinyint(1) DEFAULT NULL,
  `fecha_resolucion` datetime DEFAULT NULL,
  `mensaje` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id_incidente`),
  KEY `FK_cliente_y_servicio_del_incidente_idx` (`id_cliente`,`id_servicio`),
  KEY `FK_tecnico_del_incidente_idx` (`id_tecnico`),
  CONSTRAINT `FK_cliente_y_servicio_del_incidente` FOREIGN KEY (`id_cliente`, `id_servicio`) REFERENCES `servicio_x_cliente` (`id_cliente`, `id_servicio`),
  CONSTRAINT `FK_tecnico_del_incidente` FOREIGN KEY (`id_tecnico`) REFERENCES `tecnicos` (`id_tecnico`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incidentes`
--

LOCK TABLES `incidentes` WRITE;
/*!40000 ALTER TABLE `incidentes` DISABLE KEYS */;
INSERT INTO `incidentes` VALUES (1,1,2,2,'2023-12-14 00:00:00',1,'2024-01-22 20:10:23','mensaje inyectado desde MySQL WORKBENC'),(15,1,2,2,'2024-01-22 19:12:06',1,'2024-02-07 19:47:17','\"mensaje generado desde endpoint en swaggeer\"');
/*!40000 ALTER TABLE `incidentes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `problemas`
--

DROP TABLE IF EXISTS `problemas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `problemas` (
  `id_problema` bigint NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `tiempo_maximo_resolucion` bigint DEFAULT NULL,
  `complejo` tinyint(1) DEFAULT NULL,
  `id_servicio` bigint DEFAULT NULL,
  PRIMARY KEY (`id_problema`),
  KEY `FK_servicio_idx` (`id_servicio`),
  CONSTRAINT `FK_servicio` FOREIGN KEY (`id_servicio`) REFERENCES `servicios` (`id_servicio`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `problemas`
--

LOCK TABLES `problemas` WRITE;
/*!40000 ALTER TABLE `problemas` DISABLE KEYS */;
INSERT INTO `problemas` VALUES (2,'BSOD','BLUE SCREEN OF DEATH',200,0,2),(3,'UNRESPONSIVE PERIPHERAL','PERIFERAL DEL COMPUTADOR NO RESPONDE',300,1,2),(24,'BSOD','BLUE SCREEN CRASH',45,0,3),(25,'SYSTEM SLOWDOWN','SISTEMA LENTO',600,1,3),(27,'UPDATE FAILURE','PROGRAMA NO ACTUALIZA EXITOSAMENTE',180,0,2);
/*!40000 ALTER TABLE `problemas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicio_x_cliente`
--

DROP TABLE IF EXISTS `servicio_x_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicio_x_cliente` (
  `id_cliente` bigint NOT NULL,
  `id_servicio` bigint NOT NULL,
  PRIMARY KEY (`id_cliente`,`id_servicio`),
  KEY `servicio_idx` (`id_servicio`),
  CONSTRAINT `cliente` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`),
  CONSTRAINT `servicio` FOREIGN KEY (`id_servicio`) REFERENCES `servicios` (`id_servicio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicio_x_cliente`
--

LOCK TABLES `servicio_x_cliente` WRITE;
/*!40000 ALTER TABLE `servicio_x_cliente` DISABLE KEYS */;
INSERT INTO `servicio_x_cliente` VALUES (1,2),(3,2),(3,3);
/*!40000 ALTER TABLE `servicio_x_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicios`
--

DROP TABLE IF EXISTS `servicios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicios` (
  `id_servicio` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id_servicio`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicios`
--

LOCK TABLES `servicios` WRITE;
/*!40000 ALTER TABLE `servicios` DISABLE KEYS */;
INSERT INTO `servicios` VALUES (3,'SISTEMA OPERATIVO LINUX'),(2,'SOPORTE OPERATIVO WINDOWS');
/*!40000 ALTER TABLE `servicios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tecnico_x_especialidad`
--

DROP TABLE IF EXISTS `tecnico_x_especialidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tecnico_x_especialidad` (
  `id_tecnico` bigint NOT NULL,
  `id_especialidad` bigint NOT NULL,
  PRIMARY KEY (`id_tecnico`,`id_especialidad`),
  KEY `FK_espcialidad_con_tecnico_idx` (`id_especialidad`),
  CONSTRAINT `FK_espcialidad_con_tecnico` FOREIGN KEY (`id_especialidad`) REFERENCES `especialidades` (`id_especialidad`),
  CONSTRAINT `FK_tecnico_con_especialidad` FOREIGN KEY (`id_tecnico`) REFERENCES `tecnicos` (`id_tecnico`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tecnico_x_especialidad`
--

LOCK TABLES `tecnico_x_especialidad` WRITE;
/*!40000 ALTER TABLE `tecnico_x_especialidad` DISABLE KEYS */;
INSERT INTO `tecnico_x_especialidad` VALUES (1,2),(2,2),(2,3);
/*!40000 ALTER TABLE `tecnico_x_especialidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tecnicos`
--

DROP TABLE IF EXISTS `tecnicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tecnicos` (
  `id_tecnico` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  PRIMARY KEY (`id_tecnico`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tecnicos`
--

LOCK TABLES `tecnicos` WRITE;
/*!40000 ALTER TABLE `tecnicos` DISABLE KEYS */;
INSERT INTO `tecnicos` VALUES (1,'KEN','MASTERS'),(2,'LUKE','SANDBLAST');
/*!40000 ALTER TABLE `tecnicos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tiempo_estimado_x_problema`
--

DROP TABLE IF EXISTS `tiempo_estimado_x_problema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tiempo_estimado_x_problema` (
  `id_estimacion` bigint NOT NULL AUTO_INCREMENT,
  `id_incidente` bigint NOT NULL,
  `id_problema` bigint NOT NULL,
  `tiempo_estimado_resolucion` bigint DEFAULT NULL,
  PRIMARY KEY (`id_estimacion`),
  KEY `problema_de_la_estimacion_idx` (`id_incidente`,`id_problema`),
  CONSTRAINT `problema_de_la_estimacion` FOREIGN KEY (`id_incidente`, `id_problema`) REFERENCES `detalle_problema` (`id_incidente`, `id_problema`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiempo_estimado_x_problema`
--

LOCK TABLES `tiempo_estimado_x_problema` WRITE;
/*!40000 ALTER TABLE `tiempo_estimado_x_problema` DISABLE KEYS */;
INSERT INTO `tiempo_estimado_x_problema` VALUES (1,1,2,50),(2,15,2,100),(3,15,2,150),(4,15,3,300),(5,15,3,500);
/*!40000 ALTER TABLE `tiempo_estimado_x_problema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos_cliente`
--

DROP TABLE IF EXISTS `tipos_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipos_cliente` (
  `id_tipo_cliente` bigint NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) NOT NULL,
  PRIMARY KEY (`id_tipo_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos_cliente`
--

LOCK TABLES `tipos_cliente` WRITE;
/*!40000 ALTER TABLE `tipos_cliente` DISABLE KEYS */;
INSERT INTO `tipos_cliente` VALUES (1,'PERSONA'),(2,'EMPRESA');
/*!40000 ALTER TABLE `tipos_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos_contacto`
--

DROP TABLE IF EXISTS `tipos_contacto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipos_contacto` (
  `id_tipo_contacto` bigint NOT NULL AUTO_INCREMENT,
  `tipo` varchar(100) NOT NULL,
  `regex` varchar(255) NOT NULL,
  `mensaje_error` varchar(255) NOT NULL,
  PRIMARY KEY (`id_tipo_contacto`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos_contacto`
--

LOCK TABLES `tipos_contacto` WRITE;
/*!40000 ALTER TABLE `tipos_contacto` DISABLE KEYS */;
INSERT INTO `tipos_contacto` VALUES (1,'EMAIL','^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,7}$','El EMAIL es inválido.'),(2,'TELEFONO','\\\\d{1,6}-\\\\d{1,10}','El TELEFONO es inválido.');
/*!40000 ALTER TABLE `tipos_contacto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-05 12:41:06

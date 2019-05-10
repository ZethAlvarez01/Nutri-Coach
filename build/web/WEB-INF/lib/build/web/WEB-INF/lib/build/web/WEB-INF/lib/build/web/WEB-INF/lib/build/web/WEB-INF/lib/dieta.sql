-- MySQL dump 10.13  Distrib 5.7.24, for Win64 (x86_64)
--
-- Host: localhost    Database: dieta
-- ------------------------------------------------------
-- Server version	5.7.24

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
-- Table structure for table `alimento`
--

DROP TABLE IF EXISTS `alimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alimento` (
  `id_alimento` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `desayuno` int(11) DEFAULT NULL,
  `col1` int(11) DEFAULT NULL,
  `col2` int(11) DEFAULT NULL,
  `comida` int(11) DEFAULT NULL,
  `cena` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_alimento`)
) ENGINE=MyISAM AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alimento`
--

LOCK TABLES `alimento` WRITE;
/*!40000 ALTER TABLE `alimento` DISABLE KEYS */;
INSERT INTO `alimento` VALUES (1,'Huevo',1,0,0,1,0),(2,'Sandwitch',1,0,0,1,1),(3,'Lechuga',0,1,1,1,0),(4,'Pan dulce',1,0,0,0,1),(5,'Jitomate',1,1,1,0,0),(6,'Cebolla',1,1,1,0,0),(7,'Jamon',1,0,0,0,1),(8,'Jamon de pavo',1,0,0,0,1),(9,'Chambarete en adobo',0,0,0,1,0),(10,'Zanahoria',1,1,1,0,0),(11,'Elote',1,1,1,0,0),(12,'Tortitas de pollo en tomate',0,0,0,1,0),(13,'Nopales',1,0,0,1,0),(14,'SOpa',0,0,0,1,0),(15,'Ensalada de verduras',0,1,1,1,0),(16,'Gelatina light',0,0,0,0,1),(17,'Yogurt natural',0,0,0,0,1),(18,'Papaya',1,1,1,0,1),(19,'Manzana',1,1,1,0,1),(20,'Uva',1,1,1,0,1),(21,'Tacos al pastor con piña',0,0,0,1,1),(22,'Tacos de suadero',0,0,0,1,1),(23,'Tacos campechanos',0,0,0,1,1),(24,'Jicama',0,1,1,0,0),(25,'Pepino',0,1,1,0,0),(26,'Te',0,0,0,1,0),(27,'Cafe',0,0,0,1,0),(28,'Salmon a la plancha',0,0,0,1,0),(29,'Pollo rostizado',0,0,0,1,0),(30,'Nopales a la mexicana',0,0,0,1,0),(31,'Longaniza en salsa',0,0,0,1,0),(32,'Carnitas con pico de gallo',0,0,0,1,0),(33,'Pechuda de pollo asada',0,0,0,1,0),(34,'Agua',1,1,1,1,1),(35,'Bistec de res',0,0,0,1,0),(36,'Espinazo de cerdo en morita',0,0,0,1,0),(37,'Tamalitos de frijol',0,0,0,1,0),(38,'Salmon empapelado',0,0,0,1,0),(39,'Atun light con pico de gallo',1,0,0,1,0),(40,'Tinga de pollo',NULL,NULL,NULL,NULL,NULL),(41,'Claras de huevo',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `alimento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semana`
--

DROP TABLE IF EXISTS `semana`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `semana` (
  `id_comida` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` int(11) DEFAULT NULL,
  `lunes` int(11) DEFAULT NULL,
  `martes` int(11) DEFAULT NULL,
  `miercoles` int(11) DEFAULT NULL,
  `jueves` int(11) DEFAULT NULL,
  `viernes` int(11) DEFAULT NULL,
  `sabado` int(11) DEFAULT NULL,
  `domingo` int(11) DEFAULT NULL,
  `indicaciones` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id_comida`),
  KEY `lunes` (`lunes`),
  KEY `martes` (`martes`),
  KEY `miercoles` (`miercoles`),
  KEY `jueves` (`jueves`),
  KEY `viernes` (`viernes`),
  KEY `sabado` (`sabado`),
  KEY `domingo` (`domingo`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semana`
--

LOCK TABLES `semana` WRITE;
/*!40000 ALTER TABLE `semana` DISABLE KEYS */;
INSERT INTO `semana` VALUES (1,0,2,2,2,2,2,2,2,'Beber 2 litos de agua durante el dia. Picar jicama y pepino entre comidas. AYUNO: Yakult con 1pz de manzana. VITAMINAS: Dolo neurobion forte 1 tableta con el desayuno'),(2,0,39,39,41,41,21,21,8,'Beber 2 litos de agua durante el dia. Picar jicama y pepino entre comidas. AYUNO: Yakult con 1pz de manzana. VITAMINAS: Dolo neurobion forte 1 tableta con el desayuno'),(3,0,2,2,2,2,2,2,2,'Beber 2 litos de agua durante el dia. Picar jicama y pepino entre comidas. AYUNO: Yakult con 1pz de manzana. VITAMINAS: Dolo neurobion forte 1 tableta con el desayuno'),(4,0,4,4,4,13,13,13,10,'Beber 2 litos de agua durante el dia. Picar jicama y pepino entre comidas. AYUNO: Yakult con 1pz de manzana. VITAMINAS: Dolo neurobion forte 1 tableta con el desayuno'),(5,1,26,26,26,26,26,26,26,''),(6,1,27,27,27,27,27,27,27,''),(7,3,24,24,24,24,24,24,24,''),(8,3,26,26,26,26,26,26,26,''),(9,1,24,24,24,24,24,24,24,''),(10,3,27,27,27,27,27,27,27,''),(11,2,9,9,12,12,28,29,29,''),(12,2,9,9,12,12,28,29,29,''),(13,2,35,40,12,9,33,31,32,''),(14,2,38,38,12,12,35,35,36,''),(15,2,39,39,32,21,22,23,21,''),(16,3,16,16,17,17,18,18,18,''),(17,3,17,17,16,16,16,19,20,''),(18,3,19,19,20,20,18,19,17,'');
/*!40000 ALTER TABLE `semana` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-15  1:24:10

-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: nutricoach
-- ------------------------------------------------------
-- Server version	5.7.15-log

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
-- Table structure for table `actividadp`
--

DROP TABLE IF EXISTS `actividadp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actividadp` (
  `id_actividad` int(11) NOT NULL AUTO_INCREMENT,
  `no_boleta` int(11) NOT NULL,
  `no_cedula` int(11) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `contenido` varchar(1000) DEFAULT NULL,
  `multimedia` longblob,
  PRIMARY KEY (`id_actividad`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividadp`
--

LOCK TABLES `actividadp` WRITE;
/*!40000 ALTER TABLE `actividadp` DISABLE KEYS */;
INSERT INTO `actividadp` VALUES (1,2014630304,234234,'2019-05-05 00:00:00','Una actividad',''),(2,2014630307,234234,'2019-05-05 00:00:00','Actividad para ti',''),(3,2014630304,234234,'2019-05-05 00:00:00','activate',''),(4,2014630304,234234,'2019-05-05 00:00:00','activate',''),(5,2014630304,234234,'2019-05-05 00:00:00','ACTIVATE MAS',''),(6,2014630304,234234,'2019-05-05 00:00:00','ACTIVATE MAS',''),(7,2014630304,234234,'2019-05-05 00:00:00','ACTIVATE MAS',''),(8,2014630304,234234,'2019-05-05 00:00:00','ACTIVATE MAS',''),(9,2014630304,234234,'2019-05-05 00:00:00','ACTIVATE MAS',''),(10,2014630304,234234,'2019-05-05 00:00:00','ACTIVATE MAS',''),(11,2014630304,234234,'2019-05-05 00:00:00','ACTIVATE MAS',''),(12,2014630304,234234,'2019-05-05 00:00:00','SAL A CORRER',''),(13,2014630304,234234,'2019-05-05 00:00:00','SAL A CORRER',''),(14,2014630304,234234,'2019-05-05 00:00:00','SAL A CORRER',''),(15,2014630304,234234,'2019-05-05 00:00:00','corre',''),(16,2014630304,234234,'2019-05-05 00:00:00','ponte a hacer ejercicio',''),(17,2014630304,234234,'2019-05-05 00:00:00','ponte a hacer ejercicio',''),(18,2014630304,234234,'2019-05-05 00:00:00','lagartijas',''),(19,2014630304,234234,'2019-05-05 00:00:00','lagartijas',''),(20,2014630304,234234,'2019-05-05 00:00:00','sentadillas',''),(21,2014630304,234234,'2019-05-05 00:00:00','sentadillas',''),(22,2014630304,234234,'2019-05-05 00:00:00','actividad asignada',''),(23,2014630304,234234,'2019-05-05 00:00:00','debes correr',''),(24,2014630304,234234,'2019-05-05 00:00:00','debes correr',''),(25,2014630304,234234,'2019-05-05 00:00:00','debes correr',''),(26,2014630304,234234,'2019-05-05 00:00:00','escribe un diario',''),(27,2014630304,234234,'2019-05-05 16:10:20','escribe un diario',''),(28,2014630304,234234,'2019-05-05 16:10:20','escribe un diario',''),(29,2014630304,234234,'2019-05-05 16:23:28','mas actividades como correr',''),(30,2014630307,234234,'2019-05-05 16:24:58','Hola\r\nnecesito que realices algunas actividades\r\nPuedes escribir un diario con todo tu día\r\ny mandarmelo para leerlo\r\npor favor',''),(31,2014630304,234234,'2019-05-05 16:34:41','Manda tu diario POR FAVOR',''),(32,2014630304,234234,'2019-05-05 16:34:41','Manda tu diario',''),(33,2014630304,234234,'2019-05-05 16:34:41','Manda tu diario',''),(34,2014630307,234234,'2019-05-05 16:47:00','manda tu avance del día por favor',''),(35,2014630304,234234,'2019-05-05 16:57:00','actividad 2',''),(36,2014630304,234234,'2019-05-05 17:06:40','descripcion de la actividad otra vez','');
/*!40000 ALTER TABLE `actividadp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrador` (
  `no_empleado` int(11) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `ap_uno` varchar(40) NOT NULL,
  `ap_dos` varchar(40) DEFAULT NULL,
  `cargo` varchar(45) DEFAULT NULL,
  `contraseña` varchar(20) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  PRIMARY KEY (`no_empleado`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
INSERT INTO `administrador` VALUES (2014310030,'Zeth','Alvarez','Hernandez','Administrador','12345','5547131440'),(11111111,'Juan','Martinez','Salinas','Administrador','Admin1234','55443322');
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cita`
--

DROP TABLE IF EXISTS `cita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cita` (
  `no_cita` int(11) NOT NULL AUTO_INCREMENT,
  `no_boleta` int(11) NOT NULL,
  `no_cedula` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `hora` varchar(10) NOT NULL,
  `estado` varchar(15) NOT NULL,
  PRIMARY KEY (`no_cita`),
  KEY `no_boleta` (`no_boleta`),
  KEY `no_cedula` (`no_cedula`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cita`
--

LOCK TABLES `cita` WRITE;
/*!40000 ALTER TABLE `cita` DISABLE KEYS */;
/*!40000 ALTER TABLE `cita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comentarios`
--

DROP TABLE IF EXISTS `comentarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comentarios` (
  `id_comnt` int(11) NOT NULL AUTO_INCREMENT,
  `id_entrada` int(11) NOT NULL,
  `id_usuario` varchar(20) DEFAULT NULL,
  `contenido` varchar(150) NOT NULL,
  `multimedia` longblob,
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`id_comnt`),
  KEY `id_entrada` (`id_entrada`)
) ENGINE=MyISAM AUTO_INCREMENT=81 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentarios`
--

LOCK TABLES `comentarios` WRITE;
/*!40000 ALTER TABLE `comentarios` DISABLE KEYS */;
INSERT INTO `comentarios` VALUES (14,21,'2014630304','Comentario4','',NULL),(2,34,'2014630304','un comentario en otra publicacion','',NULL),(13,21,'2014630304','Comentario3','',NULL),(12,21,'2014630304','Comentario2','',NULL),(11,21,'2014630304','Comentario1','',NULL),(10,1,'2014630304','Mi primer comentario','',NULL),(9,34,'2014630304','COMENTANDO OTRA VEZ','',NULL),(15,21,'2014630304','Comentario5','',NULL),(16,21,'2014630304','Comentario6','',NULL),(17,21,'2014630304','Comentario6','',NULL),(18,21,'2014630304','Comentario6','',NULL),(19,21,'2014630304','comentario 7','',NULL),(37,38,'2014630306','mas comentarios','',NULL),(33,38,'2014630306','AGREGANDO MAS COMENTARIOS+\r\n','',NULL),(56,1,'2014630306','hola','',NULL),(34,38,'2014630306','AGREGANDO MAS COMENTARIOS-\r\n','',NULL),(55,52,'2014630306','hola','',NULL),(57,38,'2014630304','comentando','','2019-05-03 17:24:46'),(58,52,'2014630304','comentando','','2019-05-03 17:31:06'),(59,55,'11111111','comentando','','2019-05-03 18:01:17'),(60,56,'5452453','comentario','','2019-05-03 18:02:09'),(61,57,'12344','nutriologo','','2019-05-03 18:04:48'),(77,59,'123456789','hola psicologo como esta','','2019-05-04 14:36:50'),(76,58,'11111111','admin','','2019-05-04 14:35:35'),(64,58,'11111111','ASD','','2019-05-04 00:39:27'),(66,57,'5452453','HOLA','','2019-05-04 00:42:56'),(67,57,'5452453','HOLA','','2019-05-04 00:42:56'),(68,59,'123456789','...','','2019-05-04 01:14:24'),(69,58,'11111111','hola','','2019-05-04 13:05:40'),(70,59,'123456789','comentario','','2019-05-04 13:07:04'),(71,59,'5452453','hola','','2019-05-04 13:09:09'),(72,59,'5452453','hola','','2019-05-04 13:09:09'),(73,59,'5452453','hola','','2019-05-04 13:09:50'),(74,60,'2014630304','coososo','','2019-05-04 14:29:45'),(75,61,'2014630304','comentando','','2019-05-04 14:32:36'),(78,61,'123456789','hi','','2019-05-04 14:37:28'),(79,56,'5452453','psicologo','','2019-05-04 14:38:01'),(80,58,'5452453','hola','','2019-05-04 14:38:26');
/*!40000 ALTER TABLE `comentarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diario`
--

DROP TABLE IF EXISTS `diario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diario` (
  `id_diario` int(11) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(11) DEFAULT NULL,
  `titulo` varchar(30) DEFAULT NULL,
  `fecha_ini` datetime DEFAULT NULL,
  PRIMARY KEY (`id_diario`),
  KEY `id_expediente` (`id_expediente`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diario`
--

LOCK TABLES `diario` WRITE;
/*!40000 ALTER TABLE `diario` DISABLE KEYS */;
INSERT INTO `diario` VALUES (3,1,'Diario de seguimiento','2019-05-07 19:55:50');
/*!40000 ALTER TABLE `diario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrada`
--

DROP TABLE IF EXISTS `entrada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entrada` (
  `id_entrada` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `titulo` varchar(20) NOT NULL,
  `contenido` varchar(25) NOT NULL,
  `multimedia` longblob,
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`id_entrada`),
  KEY `id_usuario` (`id_usuario`)
) ENGINE=MyISAM AUTO_INCREMENT=65 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrada`
--

LOCK TABLES `entrada` WRITE;
/*!40000 ALTER TABLE `entrada` DISABLE KEYS */;
INSERT INTO `entrada` VALUES (1,2014630304,'TITULO4','CONTENIDO3',NULL,NULL),(2,2014630304,'hola','HOLA SOY UNA PRUEBA',NULL,NULL),(3,2014630304,'hola','HOLA SOY UNA PRUEBA',NULL,NULL),(4,2014630304,'hola','SOY UNA PRUEBA',NULL,NULL),(5,2014630304,'hola','SOY UNA PRUEBA',NULL,NULL),(6,2014630304,'hola','SOY UNA PRUEBA',NULL,NULL),(7,2014630304,'Titulo','CONTENIDO',NULL,NULL),(8,2014630304,'PRUEBA EN VISTA2','PRUEBA DE CONTENIDO2 ',NULL,NULL),(9,2014630304,'PRUEBA TITULO','PRUEBA CONTENIDO','',NULL),(33,2014630306,'Basket','Malo en basket','',NULL),(32,2014630306,'Otro deporte','Otro deporte+','',NULL),(12,2014630304,'Titulo7','CONTENIDO7','',NULL),(13,2014630304,'NUEVO TITULO','NUEVO CONTENIDO','',NULL),(14,2014630304,'NUEVO TITULO','NUEVO CONTENIDO','',NULL),(15,2014630304,'NUEVO TITULO8','Nuevo contenido8','',NULL),(16,2014630304,'TITULO10','CONTENIDO10','',NULL),(17,2014630304,'Titulo11','CONTENIDO11','',NULL),(18,2014630304,'Titulo11','CONTENIDO11','',NULL),(19,2014630304,'12312312','asdasdsadasda','',NULL),(20,2014630304,'12312312','asdasdsadasda','',NULL),(21,2014630304,'prueba17','17','',NULL),(22,2014630304,'prueba','prueba ','',NULL),(23,2014630304,'23','23','',NULL),(24,2014630304,'23','23','',NULL),(25,2014630304,'25','25','',NULL),(26,2014630304,'26','26','',NULL),(27,2014630304,'27','27','',NULL),(28,2014630304,'27B','27B','',NULL),(34,2014630306,'Comida','Comida saludable','',NULL),(38,2014630304,'Nueva publicacion','Nueva publicacion','',NULL),(36,2014630304,'333','333','',NULL),(52,2014630306,'123456','123456','',NULL),(46,1312213,'Entrada Nutriologo','nutriologo','',NULL),(48,5452453,'PSICOLOGO','Psicologo','',NULL),(50,11111111,'ADMINSTRADOR','ADMINISTRADOR','',NULL),(53,2014630304,'titulo','contenido','','2019-05-03 17:43:43'),(54,2014630304,'titulo2','entrada2','','2019-05-03 17:54:17'),(55,11111111,'psico','psico','','2019-05-03 17:56:56'),(56,5452453,'tituloPsico','Psicologo','','2019-05-03 18:01:54'),(57,12344,'nutriologo2','nutriologo2','','2019-05-03 18:04:33'),(58,11111111,'ADMINISTRADORE','hola','','2019-05-04 00:39:19'),(59,123456789,'Entrada nutriólogo','contenido Nutriólogo','','2019-05-04 01:14:13'),(60,2014630304,'entrada2','contenido2','','2019-05-04 14:29:16'),(61,2014630304,'hola','hola','','2019-05-04 14:32:30'),(62,2014630304,'DIARIO','NO TENGO DIARIO','','2019-05-07 19:02:22'),(63,2014630304,'DIARIO','NO TENGO DIARIO','','2019-05-07 19:02:22'),(64,2014630304,'DIARIO','NO TENGO DIARIO','','2019-05-07 19:02:22');
/*!40000 ALTER TABLE `entrada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evolucion`
--

DROP TABLE IF EXISTS `evolucion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `evolucion` (
  `id_seg` int(11) NOT NULL AUTO_INCREMENT,
  `id_exp` int(11) NOT NULL,
  `result_ext` varchar(100) DEFAULT NULL,
  `diagnostico` varchar(200) DEFAULT NULL,
  `pronostico` varchar(200) DEFAULT NULL,
  `indicaciones` varchar(150) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `motivo_v` varchar(50) DEFAULT NULL,
  `t_consulta` varchar(30) DEFAULT NULL,
  `act_sex` tinyint(1) DEFAULT NULL,
  `edo_gestacion` varchar(30) DEFAULT NULL,
  `m_anticonceptivo` varchar(20) DEFAULT NULL,
  `terapia_rh` varchar(50) DEFAULT NULL,
  `dosis` varchar(20) DEFAULT NULL,
  `peso` float DEFAULT NULL,
  `talla` float DEFAULT NULL,
  `temperatura` float DEFAULT NULL,
  `tension_art` int(11) DEFAULT NULL,
  `frec_cardiaca` varchar(10) DEFAULT NULL,
  `frec_respiratoria` varchar(20) DEFAULT NULL,
  `cabeza` float DEFAULT NULL,
  `cuello` float DEFAULT NULL,
  `brazo` float DEFAULT NULL,
  `cadera` float DEFAULT NULL,
  `torax` float DEFAULT NULL,
  `antebrazo` float DEFAULT NULL,
  `abdomen` float DEFAULT NULL,
  `muslo` float DEFAULT NULL,
  `pierna` float DEFAULT NULL,
  `aspect_grles` varchar(100) DEFAULT NULL,
  `otras_medidas` varchar(150) DEFAULT NULL,
  `g_corp` varchar(20) DEFAULT NULL,
  `geb` varchar(30) DEFAULT NULL,
  `ge_t` varchar(30) DEFAULT NULL,
  `sist_oseo` varchar(100) DEFAULT NULL,
  `g_inf` varchar(20) DEFAULT NULL,
  `niv_liq` varchar(30) DEFAULT NULL,
  `musc_sup` varchar(40) DEFAULT NULL,
  `musc_inf` varchar(30) DEFAULT NULL,
  `mas_musc` varchar(40) DEFAULT NULL,
  `g_viceral` varchar(20) DEFAULT NULL,
  `grado` varchar(20) DEFAULT NULL,
  `rel_cc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_seg`),
  KEY `id_exp` (`id_exp`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evolucion`
--

LOCK TABLES `evolucion` WRITE;
/*!40000 ALTER TABLE `evolucion` DISABLE KEYS */;
/*!40000 ALTER TABLE `evolucion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exp_dieta`
--

DROP TABLE IF EXISTS `exp_dieta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exp_dieta` (
  `id_exp_dieta` int(11) NOT NULL AUTO_INCREMENT,
  `id_exp` int(11) NOT NULL,
  PRIMARY KEY (`id_exp_dieta`),
  KEY `id_exp` (`id_exp`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exp_dieta`
--

LOCK TABLES `exp_dieta` WRITE;
/*!40000 ALTER TABLE `exp_dieta` DISABLE KEYS */;
/*!40000 ALTER TABLE `exp_dieta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exp_dieta_menus`
--

DROP TABLE IF EXISTS `exp_dieta_menus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exp_dieta_menus` (
  `id_exp_diet` int(11) NOT NULL,
  `clave_menu` int(11) NOT NULL,
  KEY `id_exp_diet` (`id_exp_diet`),
  KEY `clave_menu` (`clave_menu`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exp_dieta_menus`
--

LOCK TABLES `exp_dieta_menus` WRITE;
/*!40000 ALTER TABLE `exp_dieta_menus` DISABLE KEYS */;
/*!40000 ALTER TABLE `exp_dieta_menus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expediente`
--

DROP TABLE IF EXISTS `expediente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expediente` (
  `id_expediente` int(11) NOT NULL AUTO_INCREMENT,
  `no_boleta` int(11) NOT NULL,
  `diagnostico` varchar(200) DEFAULT NULL,
  `pronostico` varchar(200) DEFAULT NULL,
  `fecha_ini` date DEFAULT NULL,
  `motivacional` tinyint(1) DEFAULT NULL,
  `preparacionA` tinyint(1) DEFAULT NULL,
  `beneficiosA` tinyint(1) DEFAULT NULL,
  `deportes` tinyint(1) DEFAULT NULL,
  `medicamentos` tinyint(1) DEFAULT NULL,
  `salud` tinyint(1) DEFAULT NULL,
  `antec_hf` varchar(200) DEFAULT NULL,
  `act_f` tinyint(1) DEFAULT NULL,
  `tipo_act` varchar(40) DEFAULT NULL,
  `frecuencia` int(11) DEFAULT NULL,
  `padecimiento` varchar(100) DEFAULT NULL,
  `tabaco` tinyint(1) DEFAULT NULL,
  `frec_tabaco` int(11) DEFAULT NULL,
  `alcohol` tinyint(1) DEFAULT NULL,
  `frec_alcohol` int(11) DEFAULT NULL,
  `tratamiento` varchar(200) DEFAULT NULL,
  `tiempo` varchar(50) DEFAULT NULL,
  `motivo` varchar(150) DEFAULT NULL,
  `hora` varchar(40) DEFAULT NULL,
  `alergias` varchar(200) DEFAULT NULL,
  `postre` varchar(150) DEFAULT NULL,
  `ansiedad` tinyint(1) DEFAULT NULL,
  `depresion` tinyint(1) DEFAULT NULL,
  `ira` tinyint(1) DEFAULT NULL,
  `estres` tinyint(1) DEFAULT NULL,
  `felicidad` tinyint(1) DEFAULT NULL,
  `dulce` tinyint(1) DEFAULT NULL,
  `amarga` tinyint(1) DEFAULT NULL,
  `salada` tinyint(1) DEFAULT NULL,
  `picante` tinyint(1) DEFAULT NULL,
  `acida` tinyint(1) DEFAULT NULL,
  `act_sex` tinyint(1) DEFAULT NULL,
  `edo_gestacion` varchar(30) DEFAULT NULL,
  `m_anticonceptivo` varchar(20) DEFAULT NULL,
  `terapia_rh` varchar(50) DEFAULT NULL,
  `dosis` varchar(20) DEFAULT NULL,
  `peso` float DEFAULT NULL,
  `talla` float DEFAULT NULL,
  `temperatura` float DEFAULT NULL,
  `tension_art` int(11) DEFAULT NULL,
  `frec_cardiaca` varchar(10) DEFAULT NULL,
  `frec_respiratoria` varchar(20) DEFAULT NULL,
  `cabeza` float DEFAULT NULL,
  `cuello` float DEFAULT NULL,
  `brazo` float DEFAULT NULL,
  `cadera` float DEFAULT NULL,
  `torax` float DEFAULT NULL,
  `antebrazo` float DEFAULT NULL,
  `abdomen` float DEFAULT NULL,
  `muslo` float DEFAULT NULL,
  `pierna` float DEFAULT NULL,
  `aspect_grles` varchar(100) DEFAULT NULL,
  `otras_medidas` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id_expediente`),
  KEY `no_boleta` (`no_boleta`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expediente`
--

LOCK TABLES `expediente` WRITE;
/*!40000 ALTER TABLE `expediente` DISABLE KEYS */;
INSERT INTO `expediente` VALUES (1,2014630304,'','','2019-05-05',0,0,0,0,0,0,'',0,'',0,'',0,0,0,0,'','','','','','',0,0,0,0,0,0,0,0,0,0,0,'','','','',0,0,0,0,'','',0,0,0,0,0,0,0,0,0,'',''),(2,2014630307,'','','2019-05-05',0,0,0,0,0,0,'',0,'',0,'',0,0,0,0,'','','','','','',0,0,0,0,0,0,0,0,0,0,0,'','','','',0,0,0,0,'','',0,0,0,0,0,0,0,0,0,'','');
/*!40000 ALTER TABLE `expediente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hojas`
--

DROP TABLE IF EXISTS `hojas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hojas` (
  `id_hojas` int(11) NOT NULL AUTO_INCREMENT,
  `id_diario` int(11) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `contenido` varchar(500) DEFAULT NULL,
  `sentimiento` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`id_hojas`),
  KEY `id_diario` (`id_diario`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hojas`
--

LOCK TABLES `hojas` WRITE;
/*!40000 ALTER TABLE `hojas` DISABLE KEYS */;
INSERT INTO `hojas` VALUES (1,3,'2019-05-07 20:44:47','Hoy fue un día muy cansado','Estresado'),(2,3,'2019-05-07 20:45:22','Ya se acerca la presentación','Ansioso'),(3,3,'2019-05-07 20:45:22','Ya se acerca la presentación','Ansioso'),(4,3,'2019-05-07 20:45:22','Ya se acerca la presentación','Ansioso'),(5,3,'2019-05-07 21:14:45','Estoy algo cansado','Estresado'),(6,3,'2019-05-07 21:19:40','ESTOY MUY CANSADO','Estresado'),(7,3,'2019-05-07 21:27:35','YA QUIERO TERMINAR EL PROYECTO','Ansioso'),(8,3,'2019-05-07 21:27:35','YA QUIERO TERMINAR EL PROYECTO','Ansioso'),(9,3,'2019-05-07 21:27:35','YA QUIERO TERMINAR EL PROYECTO','Ansioso'),(10,3,'2019-05-07 21:27:35','YA QUIERO TERMINAR EL PROYECTO','Ansioso'),(11,3,'2019-05-07 21:29:22','ya puedo escribir en mi diario','Feliz'),(12,3,'2019-05-07 21:30:39','Aun no puedo ver el historial','Triste'),(13,3,'2019-05-07 21:30:39','Aun no puedo ver el historial','Triste'),(14,3,'2019-05-07 15:28:42','NO QUEDA ','Enojado');
/*!40000 ALTER TABLE `hojas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horarios_atencion`
--

DROP TABLE IF EXISTS `horarios_atencion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `horarios_atencion` (
  `horario_k` int(11) NOT NULL AUTO_INCREMENT,
  `no_cedula` int(11) NOT NULL,
  `dia` varchar(15) NOT NULL,
  `hora_i` time NOT NULL,
  `hora_f` time NOT NULL,
  PRIMARY KEY (`horario_k`),
  KEY `no_cedula` (`no_cedula`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horarios_atencion`
--

LOCK TABLES `horarios_atencion` WRITE;
/*!40000 ALTER TABLE `horarios_atencion` DISABLE KEYS */;
/*!40000 ALTER TABLE `horarios_atencion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensaje`
--

DROP TABLE IF EXISTS `mensaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mensaje` (
  `id_mensaje` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario_emisor` int(11) NOT NULL,
  `id_usuario_receptor` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `contenido` varchar(200) NOT NULL,
  PRIMARY KEY (`id_mensaje`),
  KEY `id_usuario_emisor` (`id_usuario_emisor`),
  KEY `id_usuario_receptor` (`id_usuario_receptor`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensaje`
--

LOCK TABLES `mensaje` WRITE;
/*!40000 ALTER TABLE `mensaje` DISABLE KEYS */;
/*!40000 ALTER TABLE `mensaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menus`
--

DROP TABLE IF EXISTS `menus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menus` (
  `clave_menu` int(11) NOT NULL AUTO_INCREMENT,
  `flg_menu` varchar(10) NOT NULL,
  `autor` varchar(40) NOT NULL,
  PRIMARY KEY (`clave_menu`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menus`
--

LOCK TABLES `menus` WRITE;
/*!40000 ALTER TABLE `menus` DISABLE KEYS */;
/*!40000 ALTER TABLE `menus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nutriologo`
--

DROP TABLE IF EXISTS `nutriologo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nutriologo` (
  `no_cedula` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `ap_uno` varchar(20) NOT NULL,
  `ap_dos` varchar(20) DEFAULT NULL,
  `telefono` varchar(15) NOT NULL,
  `consultorio` varchar(100) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `no_empleado` int(11) DEFAULT NULL,
  `contraseña` varchar(20) NOT NULL,
  `institucion` varchar(50) NOT NULL,
  `estatus` int(1) DEFAULT NULL,
  `horaEntrada` time DEFAULT NULL,
  `horaSalida` time DEFAULT NULL,
  PRIMARY KEY (`no_cedula`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nutriologo`
--

LOCK TABLES `nutriologo` WRITE;
/*!40000 ALTER TABLE `nutriologo` DISABLE KEYS */;
INSERT INTO `nutriologo` VALUES (987654321,'Zeth','Alvarez','Hernandez','55112244','JUYRRLKASHDU','ejemplo@ejemplo.com',987654321,'Zeth1234','Escuela Superior de Cómputo (ESCOM)',1,NULL,NULL),(1234567,'Miguel','Aguirre','Hernandez','5566778899','Av. Juan de Dios Bátiz S/N, Nueva Industrial Vallejo, 07738 Ciudad de México, CDMX','ejemplo@ejemplo.com',765542,'Migue1234','Escuela Superior de Cómputo (ESCOM)',1,NULL,NULL),(1987654321,'Marcos Angel','Maya','Ramirez','56500835','oiioiooi','ejemplo@ejemplo.com',123456789,'Maya1234','Escuela Superior de Cómputo (ESCOM)',1,NULL,NULL),(3123124,'Juan Jesus ','Mondragón','Barrios','5532334345','Av. Juan de Dios Bátiz S/N, Nueva Industrial Vallejo, 07738 Ciudad de México, CDMX','ejemplo@ejemplo.com',12344,'jESUS1234','Escuela Superior de Cómputo (ESCOM)',1,'07:00:00','10:00:00');
/*!40000 ALTER TABLE `nutriologo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paciente`
--

DROP TABLE IF EXISTS `paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paciente` (
  `no_boleta` int(11) NOT NULL,
  `no_cedula` int(11) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `ap_uno` varchar(40) NOT NULL,
  `ap_dos` varchar(40) DEFAULT NULL,
  `edad` int(11) NOT NULL,
  `sexo` varchar(1) NOT NULL,
  `fecha_n` date NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `domicilio` varchar(100) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `contraseña` varchar(20) NOT NULL,
  `estatus` int(11) DEFAULT NULL,
  `no_cedulap` int(11) DEFAULT NULL,
  PRIMARY KEY (`no_boleta`),
  KEY `no_cedula` (`no_cedula`),
  KEY `no_cedulap` (`no_cedulap`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paciente`
--

LOCK TABLES `paciente` WRITE;
/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
INSERT INTO `paciente` VALUES (2014630304,1987654321,'Alexis Israel','Rodrigues',' Almázan',23,'H','1995-03-22','5532397936','iiuuytrykiu','ejemplo@ejemplo.com','Alex1234',1,234234),(2014630305,1312213,'Juan Jesus','Mondragón','Barrios',23,'H','1995-05-20','55566565','Domicilio','ejemplo@ejemplo.com','JJesus1234',1,NULL),(2014630306,1987654321,'Leonardo Miguel','Aguirre','Hernández',24,'H','1995-01-04','5532334345','Un barrio bien bravo','ejemplo@ejemplo.com','Migue1234',1,NULL),(2014630307,0,'José Eduardo','Quintero','López',24,'H','1995-04-24','5532334345','Nueva rosita','ejemplo@ejemplo.com','Pepe1234',1,234234);
/*!40000 ALTER TABLE `paciente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `psicologo`
--

DROP TABLE IF EXISTS `psicologo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `psicologo` (
  `no_cedula` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `ap_uno` varchar(20) NOT NULL,
  `ap_dos` varchar(20) DEFAULT NULL,
  `telefono` varchar(15) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `no_empleado` int(11) NOT NULL,
  `contraseña` varchar(20) NOT NULL,
  `estatus` int(11) DEFAULT NULL,
  `consultorio` varchar(100) DEFAULT NULL,
  `institucion` varchar(50) DEFAULT NULL,
  `horaEntrada` time DEFAULT NULL,
  `horaSalida` time DEFAULT NULL,
  PRIMARY KEY (`no_cedula`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `psicologo`
--

LOCK TABLES `psicologo` WRITE;
/*!40000 ALTER TABLE `psicologo` DISABLE KEYS */;
INSERT INTO `psicologo` VALUES (234234,'Pedro','Perez','Perez','55555555','ejemplo@ejemplo.com',5452453,'Pedro1234',1,NULL,NULL,NULL,NULL),(99999999,'Leonardo Miguel','Aguirre','Hernández','5566778899','ejemplo@ejemplo.com',88888888,'Leonardo1234',1,'Av. Juan de Dios Bátiz S/N, Nueva Industrial Vallejo, 07738 Ciudad de México, CDMX','Escuela Superior de Cómputo (ESCOM)','07:00:00','17:00:00');
/*!40000 ALTER TABLE `psicologo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `psicologo_paciente`
--

DROP TABLE IF EXISTS `psicologo_paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `psicologo_paciente` (
  `no_cedula` int(11) NOT NULL,
  `no_boleta` int(11) NOT NULL,
  KEY `no_cedula` (`no_cedula`),
  KEY `no_boleta` (`no_boleta`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `psicologo_paciente`
--

LOCK TABLES `psicologo_paciente` WRITE;
/*!40000 ALTER TABLE `psicologo_paciente` DISABLE KEYS */;
/*!40000 ALTER TABLE `psicologo_paciente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recomendaciones`
--

DROP TABLE IF EXISTS `recomendaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recomendaciones` (
  `id_recom` int(11) NOT NULL AUTO_INCREMENT,
  `id_autor` int(11) NOT NULL,
  `tipo` varchar(20) NOT NULL,
  `contenido` varchar(150) NOT NULL,
  `multimedia` longblob,
  PRIMARY KEY (`id_recom`),
  KEY `id_autor` (`id_autor`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recomendaciones`
--

LOCK TABLES `recomendaciones` WRITE;
/*!40000 ALTER TABLE `recomendaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `recomendaciones` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-08 23:18:13

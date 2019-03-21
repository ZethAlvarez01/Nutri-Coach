-- MySQL dump 10.13  Distrib 5.7.24, for Win64 (x86_64)
--
-- Host: localhost    Database: nutricoach
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
  `contraseña` varchar(20) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`no_empleado`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
INSERT INTO `administrador` VALUES (2014310030,'Zeth','Alvarez','Hernandez','Administrador',NULL,NULL),(11111111,'Juan','Martinez','Salinas','Administrador',NULL,NULL);
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
  `titulo` varchar(20) NOT NULL,
  `contenido` varchar(150) NOT NULL,
  `multimedia` longblob,
  PRIMARY KEY (`id_comnt`),
  KEY `id_entrada` (`id_entrada`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentarios`
--

LOCK TABLES `comentarios` WRITE;
/*!40000 ALTER TABLE `comentarios` DISABLE KEYS */;
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
  `id_expediente` varchar(40) NOT NULL,
  `titulo` varchar(30) DEFAULT NULL,
  `fecha_ini` date DEFAULT NULL,
  PRIMARY KEY (`id_diario`),
  KEY `id_expediente` (`id_expediente`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diario`
--

LOCK TABLES `diario` WRITE;
/*!40000 ALTER TABLE `diario` DISABLE KEYS */;
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
  PRIMARY KEY (`id_entrada`),
  KEY `id_usuario` (`id_usuario`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrada`
--

LOCK TABLES `entrada` WRITE;
/*!40000 ALTER TABLE `entrada` DISABLE KEYS */;
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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expediente`
--

LOCK TABLES `expediente` WRITE;
/*!40000 ALTER TABLE `expediente` DISABLE KEYS */;
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
  `fecha` date DEFAULT NULL,
  `contenido` varchar(500) DEFAULT NULL,
  `multimedia` longblob,
  PRIMARY KEY (`id_hojas`),
  KEY `id_diario` (`id_diario`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hojas`
--

LOCK TABLES `hojas` WRITE;
/*!40000 ALTER TABLE `hojas` DISABLE KEYS */;
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
  `contraseña` varchar(20) DEFAULT NULL,
  `institucion` varchar(50) DEFAULT NULL,
  `estatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`no_cedula`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nutriologo`
--

LOCK TABLES `nutriologo` WRITE;
/*!40000 ALTER TABLE `nutriologo` DISABLE KEYS */;
INSERT INTO `nutriologo` VALUES (123123,'Juan','Mart?nez','Salinas','56500835','jjjjjjjjjj','ejemplo@ejemplo.com',1312213,NULL,NULL,NULL),(123456789,'Miguel','Aguirre','Hernandez','5533442211','OOIUUYYYUI','ejemplo@ejemplo.com',123456789,NULL,NULL,NULL),(987654321,'Zeth','Alvarez','Hernandez','55112244','JUYRRLKASHDU','ejemplo@ejemplo.com',987654321,NULL,NULL,NULL);
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
  `contraseña` varchar(20) DEFAULT NULL,
  `estatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`no_boleta`),
  KEY `no_cedula` (`no_cedula`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paciente`
--

LOCK TABLES `paciente` WRITE;
/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
INSERT INTO `paciente` VALUES (2014630304,0,'Alexis Israel','Rodrigues',' Alm?zan',23,'H','1995-03-22','5532397936','iiuuytrykiu','ejemplo@ejemplo.com',NULL,NULL),(2014310030,0,'Zeth','Alvarez','Hernandez',24,'H','1995-03-18','5547131440','Trompillo #23 col. 20 de Noviembre Del. Venustiano Carranza Del. Venistiano Carranza','zetokaiba2000@hotmail.com','Excaliburpipper2',1);
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
  `contraseña` varchar(20) DEFAULT NULL,
  `estatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`no_cedula`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `psicologo`
--

LOCK TABLES `psicologo` WRITE;
/*!40000 ALTER TABLE `psicologo` DISABLE KEYS */;
INSERT INTO `psicologo` VALUES (234234,'Pedro','Perez','Perez','55555555','ejemplo@ejemplo.com',5452453,NULL,NULL);
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

-- Dump completed on 2019-03-20 20:35:58

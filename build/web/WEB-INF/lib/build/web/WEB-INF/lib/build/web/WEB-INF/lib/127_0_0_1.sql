-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 23-11-2018 a las 15:18:25
-- Versión del servidor: 5.7.23
-- Versión de PHP: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `nutricoach`
--
CREATE DATABASE IF NOT EXISTS `nutricoach` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `nutricoach`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador`
--

DROP TABLE IF EXISTS `administrador`;
CREATE TABLE IF NOT EXISTS `administrador` (
  `no_empleado` int(11) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `ap_uno` varchar(40) NOT NULL,
  `ap_dos` varchar(40) DEFAULT NULL,
  `cargo` varchar(45) DEFAULT NULL,
  `contraseña` varchar(20) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  PRIMARY KEY (`no_empleado`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `administrador`
--

INSERT INTO `administrador` (`no_empleado`, `nombre`, `ap_uno`, `ap_dos`, `cargo`, `contraseña`, `telefono`) VALUES
(2014310030, 'Zeth', 'Alvarez', 'Hernandez', 'Administrador', '12345', '5547131440');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cita`
--

DROP TABLE IF EXISTS `cita`;
CREATE TABLE IF NOT EXISTS `cita` (
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentarios`
--

DROP TABLE IF EXISTS `comentarios`;
CREATE TABLE IF NOT EXISTS `comentarios` (
  `id_comnt` int(11) NOT NULL AUTO_INCREMENT,
  `id_entrada` int(11) NOT NULL,
  `titulo` varchar(20) NOT NULL,
  `contenido` varchar(150) NOT NULL,
  `multimedia` longblob,
  PRIMARY KEY (`id_comnt`),
  KEY `id_entrada` (`id_entrada`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `diario`
--

DROP TABLE IF EXISTS `diario`;
CREATE TABLE IF NOT EXISTS `diario` (
  `id_diario` int(11) NOT NULL AUTO_INCREMENT,
  `id_expediente` varchar(40) NOT NULL,
  `titulo` varchar(30) DEFAULT NULL,
  `fecha_ini` date DEFAULT NULL,
  PRIMARY KEY (`id_diario`),
  KEY `id_expediente` (`id_expediente`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entrada`
--

DROP TABLE IF EXISTS `entrada`;
CREATE TABLE IF NOT EXISTS `entrada` (
  `id_entrada` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `titulo` varchar(20) NOT NULL,
  `contenido` varchar(25) NOT NULL,
  `multimedia` longblob,
  PRIMARY KEY (`id_entrada`),
  KEY `id_usuario` (`id_usuario`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `evolucion`
--

DROP TABLE IF EXISTS `evolucion`;
CREATE TABLE IF NOT EXISTS `evolucion` (
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `expediente`
--

DROP TABLE IF EXISTS `expediente`;
CREATE TABLE IF NOT EXISTS `expediente` (
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
  `altura` int(11) NOT NULL,
  `suplementos` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_expediente`),
  KEY `no_boleta` (`no_boleta`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `expediente`
--

INSERT INTO `expediente` (`id_expediente`, `no_boleta`, `diagnostico`, `pronostico`, `fecha_ini`, `motivacional`, `preparacionA`, `beneficiosA`, `deportes`, `medicamentos`, `salud`, `antec_hf`, `act_f`, `tipo_act`, `frecuencia`, `padecimiento`, `tabaco`, `frec_tabaco`, `alcohol`, `frec_alcohol`, `tratamiento`, `tiempo`, `motivo`, `hora`, `alergias`, `postre`, `ansiedad`, `depresion`, `ira`, `estres`, `felicidad`, `dulce`, `amarga`, `salada`, `picante`, `acida`, `act_sex`, `edo_gestacion`, `m_anticonceptivo`, `terapia_rh`, `dosis`, `peso`, `talla`, `temperatura`, `tension_art`, `frec_cardiaca`, `frec_respiratoria`, `cabeza`, `cuello`, `brazo`, `cadera`, `torax`, `antebrazo`, `abdomen`, `muslo`, `pierna`, `aspect_grles`, `otras_medidas`, `altura`, `suplementos`) VALUES
(3, 2014310031, 'Diabetes cronica y estado emocional delicado', 'Se va a curar', '2018-11-21', 1, 1, 0, 1, 0, 0, 'Sangre de demonio', 1, 'Pelear', 3, 'Me duele todo', 0, 3, 1, 5, 'Elizabeth', 'De por vida', 'Reencarnacion', 'diario', 'ninguna', 'Siempre', 1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, NULL, NULL, NULL, NULL, 85, 36, 25, 12, '12', '12', 16.5, 42.1, 80, 70, 30, 56, 20, 54, 10, 'Ninguna', 'Examen medico', 170, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `exp_dieta`
--

DROP TABLE IF EXISTS `exp_dieta`;
CREATE TABLE IF NOT EXISTS `exp_dieta` (
  `id_exp_dieta` int(11) NOT NULL AUTO_INCREMENT,
  `id_exp` int(11) NOT NULL,
  PRIMARY KEY (`id_exp_dieta`),
  KEY `id_exp` (`id_exp`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `exp_dieta_menus`
--

DROP TABLE IF EXISTS `exp_dieta_menus`;
CREATE TABLE IF NOT EXISTS `exp_dieta_menus` (
  `id_exp_diet` int(11) NOT NULL,
  `clave_menu` int(11) NOT NULL,
  KEY `id_exp_diet` (`id_exp_diet`),
  KEY `clave_menu` (`clave_menu`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hojas`
--

DROP TABLE IF EXISTS `hojas`;
CREATE TABLE IF NOT EXISTS `hojas` (
  `id_hojas` int(11) NOT NULL AUTO_INCREMENT,
  `id_diario` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `contenido` varchar(500) DEFAULT NULL,
  `multimedia` longblob,
  PRIMARY KEY (`id_hojas`),
  KEY `id_diario` (`id_diario`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horarios_atencion`
--

DROP TABLE IF EXISTS `horarios_atencion`;
CREATE TABLE IF NOT EXISTS `horarios_atencion` (
  `horario_k` int(11) NOT NULL AUTO_INCREMENT,
  `no_cedula` int(11) NOT NULL,
  `dia` varchar(15) NOT NULL,
  `hora_i` time NOT NULL,
  `hora_f` time NOT NULL,
  PRIMARY KEY (`horario_k`),
  KEY `no_cedula` (`no_cedula`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensaje`
--

DROP TABLE IF EXISTS `mensaje`;
CREATE TABLE IF NOT EXISTS `mensaje` (
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `menus`
--

DROP TABLE IF EXISTS `menus`;
CREATE TABLE IF NOT EXISTS `menus` (
  `clave_menu` int(11) NOT NULL AUTO_INCREMENT,
  `flg_menu` varchar(10) NOT NULL,
  `autor` varchar(40) NOT NULL,
  PRIMARY KEY (`clave_menu`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nutriologo`
--

DROP TABLE IF EXISTS `nutriologo`;
CREATE TABLE IF NOT EXISTS `nutriologo` (
  `no_cedula` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `ap_uno` varchar(20) NOT NULL,
  `ap_dos` varchar(20) DEFAULT NULL,
  `telefono` varchar(15) NOT NULL,
  `consultorio` varchar(100) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `contraseña` varchar(20) NOT NULL,
  `institucion` varchar(50) NOT NULL,
  `no_empleado` int(11) NOT NULL,
  PRIMARY KEY (`no_cedula`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `nutriologo`
--

INSERT INTO `nutriologo` (`no_cedula`, `nombre`, `ap_uno`, `ap_dos`, `telefono`, `consultorio`, `correo`, `contraseña`, `institucion`, `no_empleado`) VALUES
(123456789, 'Juan', 'Martinez', 'Salinas', '5547131440', 'Trompillo #23 col. 20 de Noviembre C.P 15300 Del. Venustiano Carranza', 'ejemplo@ejemplo.com.mx', '12345678', 'Escuela superior de computo', 2014310032);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paciente`
--

DROP TABLE IF EXISTS `paciente`;
CREATE TABLE IF NOT EXISTS `paciente` (
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
  PRIMARY KEY (`no_boleta`),
  KEY `no_cedula` (`no_cedula`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `paciente`
--

INSERT INTO `paciente` (`no_boleta`, `no_cedula`, `nombre`, `ap_uno`, `ap_dos`, `edad`, `sexo`, `fecha_n`, `telefono`, `domicilio`, `correo`, `contraseña`) VALUES
(2014310031, 0, 'Zeth', 'Alvarez', 'Hernandez', 23, 'H', '1995-03-18', '5547131440', 'Trompillo #23 col. 20 de Noviembre C.P 15300 Del. Venustiano Carranza', 'ejemplo@ejemplo.com.mx', '12345678'),
(2014310034, 0, 'Pipper', 'Violin', '', 21, 'M', '1996-12-20', '5547131440', 'Mi kokoro', 'ejemplo@ejemplo.com.mx', '12345678');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `psicologo`
--

DROP TABLE IF EXISTS `psicologo`;
CREATE TABLE IF NOT EXISTS `psicologo` (
  `no_cedula` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `ap_uno` varchar(20) NOT NULL,
  `ap_dos` varchar(20) DEFAULT NULL,
  `telefono` varchar(15) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `no_empleado` int(11) NOT NULL,
  `contraseña` varchar(20) NOT NULL,
  PRIMARY KEY (`no_cedula`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `psicologo`
--

INSERT INTO `psicologo` (`no_cedula`, `nombre`, `ap_uno`, `ap_dos`, `telefono`, `correo`, `no_empleado`, `contraseña`) VALUES
(987654321, 'Leonardo Miguel', 'Aguirre', 'Hernandez', '55723225235', 'ejemplo@ejemplo.com.mx', 2014310033, '12345678');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `psicologo_paciente`
--

DROP TABLE IF EXISTS `psicologo_paciente`;
CREATE TABLE IF NOT EXISTS `psicologo_paciente` (
  `no_cedula` int(11) NOT NULL,
  `no_boleta` int(11) NOT NULL,
  KEY `no_cedula` (`no_cedula`),
  KEY `no_boleta` (`no_boleta`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recomendaciones`
--

DROP TABLE IF EXISTS `recomendaciones`;
CREATE TABLE IF NOT EXISTS `recomendaciones` (
  `id_recom` int(11) NOT NULL AUTO_INCREMENT,
  `id_autor` int(11) NOT NULL,
  `tipo` varchar(20) NOT NULL,
  `contenido` varchar(150) NOT NULL,
  `multimedia` longblob,
  PRIMARY KEY (`id_recom`),
  KEY `id_autor` (`id_autor`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

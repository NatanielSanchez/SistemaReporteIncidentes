CREATE TABLE IF NOT EXISTS `tipos_cliente` (
  `id_tipo_cliente` bigint NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) NOT NULL,
  PRIMARY KEY (`id_tipo_cliente`)
);

CREATE TABLE IF NOT EXISTS `tipos_notificacion` (
  `id_tipo_notificacion` bigint NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) NOT NULL,
  PRIMARY KEY (`id_tipo_notificacion`)
);

CREATE TABLE IF NOT EXISTS `clientes` (
  `id_cliente` bigint NOT NULL AUTO_INCREMENT,
  `id_tipo_cliente` bigint NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `identificacion` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE KEY `identificacion_UNIQUE` (`identificacion`),
  KEY `id_tipo_cliente_idx` (`id_tipo_cliente`),
  CONSTRAINT `tipo_de_cliente` FOREIGN KEY (`id_tipo_cliente`) REFERENCES `tipos_cliente` (`id_tipo_cliente`)
);

CREATE TABLE IF NOT EXISTS `servicios` (
  `id_servicio` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id_servicio`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
);

CREATE TABLE IF NOT EXISTS `problemas` (
  `id_problema` bigint NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `tiempo_maximo_resolucion` bigint DEFAULT NULL,
  `complejo` tinyint(1) DEFAULT NULL,
  `id_servicio` bigint DEFAULT NULL,
  PRIMARY KEY (`id_problema`),
  KEY `FK_servicio_idx` (`id_servicio`),
  CONSTRAINT `FK_servicio` FOREIGN KEY (`id_servicio`) REFERENCES `servicios` (`id_servicio`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS `servicio_x_cliente` (
  `id_cliente` bigint NOT NULL,
  `id_servicio` bigint NOT NULL,
  PRIMARY KEY (`id_cliente`,`id_servicio`),
  KEY `servicio_idx` (`id_servicio`),
  CONSTRAINT `cliente` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`),
  CONSTRAINT `servicio` FOREIGN KEY (`id_servicio`) REFERENCES `servicios` (`id_servicio`)
);

CREATE TABLE IF NOT EXISTS `tecnicos` (
  `id_tecnico` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `id_tipo_notificacion` bigint NOT NULL,
  `contacto` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_tecnico`),
  KEY `FK_tipo_de_notificacion_idx` (`id_tipo_notificacion`),
  CONSTRAINT `FK_tipo_de_notificacion` FOREIGN KEY (`id_tipo_notificacion`) REFERENCES `tipos_notificacion` (`id_tipo_notificacion`)
);

CREATE TABLE IF NOT EXISTS `especialidades` (
  `id_especialidad` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_especialidad`)
);

CREATE TABLE IF NOT EXISTS `tecnico_x_especialidad` (
  `id_tecnico` bigint NOT NULL,
  `id_especialidad` bigint NOT NULL,
  PRIMARY KEY (`id_tecnico`,`id_especialidad`),
  KEY `FK_espcialidad_con_tecnico_idx` (`id_especialidad`),
  CONSTRAINT `FK_espcialidad_con_tecnico` FOREIGN KEY (`id_especialidad`) REFERENCES `especialidades` (`id_especialidad`),
  CONSTRAINT `FK_tecnico_con_especialidad` FOREIGN KEY (`id_tecnico`) REFERENCES `tecnicos` (`id_tecnico`)
);

CREATE TABLE IF NOT EXISTS `especialidad_x_problema` (
  `id_problema` bigint NOT NULL,
  `id_especialidad` bigint NOT NULL,
  PRIMARY KEY (`id_problema`,`id_especialidad`),
  KEY `FK_especialidad_idx` (`id_especialidad`),
  CONSTRAINT `FK_especialidad` FOREIGN KEY (`id_especialidad`) REFERENCES `especialidades` (`id_especialidad`),
  CONSTRAINT `FK_problema` FOREIGN KEY (`id_problema`) REFERENCES `problemas` (`id_problema`)
);

CREATE TABLE IF NOT EXISTS `incidentes` (
  `id_incidente` bigint NOT NULL AUTO_INCREMENT,
  `id_cliente` bigint DEFAULT NULL,
  `id_servicio` bigint DEFAULT NULL,
  `id_tecnico` bigint DEFAULT NULL,
  `fecha_inicio` datetime DEFAULT NULL,
  `resuelto` tinyint(1) DEFAULT NULL,
  `fecha_resolucion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_incidente`),
  KEY `FK_cliente_y_servicio_del_incidente_idx` (`id_cliente`,`id_servicio`),
  KEY `FK_tecnico_del_incidente_idx` (`id_tecnico`),
  CONSTRAINT `FK_cliente_y_servicio_del_incidente` FOREIGN KEY (`id_cliente`, `id_servicio`) REFERENCES `servicio_x_cliente` (`id_cliente`, `id_servicio`),
  CONSTRAINT `FK_tecnico_del_incidente` FOREIGN KEY (`id_tecnico`) REFERENCES `tecnicos` (`id_tecnico`)
);

CREATE TABLE IF NOT EXISTS `detalle_problema` (
  `id_incidente` bigint NOT NULL,
  `id_problema` bigint NOT NULL,
  PRIMARY KEY (`id_incidente`,`id_problema`),
  KEY `FK_problema_del_incidente_idx` (`id_problema`),
  CONSTRAINT `FK_incidente` FOREIGN KEY (`id_incidente`) REFERENCES `incidentes` (`id_incidente`),
  CONSTRAINT `FK_problema_del_incidente` FOREIGN KEY (`id_problema`) REFERENCES `problemas` (`id_problema`)
);

CREATE TABLE IF NOT EXISTS `tiempo_estimado_x_problema` (
  `id_estimacion` bigint NOT NULL AUTO_INCREMENT,
  `id_incidente` bigint DEFAULT NULL,
  `id_problema` bigint DEFAULT NULL,
  `tiempo_estimado_resolucion` bigint DEFAULT NULL,
  PRIMARY KEY (`id_estimacion`),
  KEY `FK_problema_de_incidente_idx` (`id_incidente`,`id_problema`),
  CONSTRAINT `FK_problema_de_incidente` FOREIGN KEY (`id_incidente`, `id_problema`) REFERENCES `detalle_problema` (`id_incidente`, `id_problema`)
);



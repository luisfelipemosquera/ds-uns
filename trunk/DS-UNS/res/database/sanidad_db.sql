SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `sanidad-db` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `sanidad-db` ;

-- -----------------------------------------------------
-- Table `sanidad-db`.`Documento_Resultado`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sanidad-db`.`Documento_Resultado` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `URL` VARCHAR(256) NOT NULL ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sanidad-db`.`Tipo_Consulta`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sanidad-db`.`Tipo_Consulta` (
  `Nombre` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`Nombre`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sanidad-db`.`Doc_Tipo`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sanidad-db`.`Doc_Tipo` (
  `Tipo` VARCHAR(20) NOT NULL ,
  PRIMARY KEY (`Tipo`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sanidad-db`.`UNS_Relacion`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sanidad-db`.`UNS_Relacion` (
  `Relacion` VARCHAR(20) NOT NULL ,
  PRIMARY KEY (`Relacion`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sanidad-db`.`Persona`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sanidad-db`.`Persona` (
  `Doc_Tipo` VARCHAR(20) NOT NULL ,
  `Doc_Numero` INT NOT NULL ,
  `Apellido` VARCHAR(45) NULL ,
  `Nombre` VARCHAR(45) NULL ,
  `UNS_Relacion_Relacion` VARCHAR(20) NOT NULL ,
  PRIMARY KEY (`Doc_Tipo`, `Doc_Numero`) ,
  INDEX `fk_Persona_Doc_Tipo` (`Doc_Tipo` ASC) ,
  INDEX `fk_Persona_UNS_Relacion1` (`UNS_Relacion_Relacion` ASC) ,
  CONSTRAINT `fk_Persona_Doc_Tipo`
    FOREIGN KEY (`Doc_Tipo` )
    REFERENCES `sanidad-db`.`Doc_Tipo` (`Tipo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Persona_UNS_Relacion1`
    FOREIGN KEY (`UNS_Relacion_Relacion` )
    REFERENCES `sanidad-db`.`UNS_Relacion` (`Relacion` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sanidad-db`.`Paciente`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sanidad-db`.`Paciente` (
  `Doc_Tipo` VARCHAR(20) NOT NULL ,
  `Doc_Numero` INT NOT NULL ,
  PRIMARY KEY (`Doc_Tipo`, `Doc_Numero`) ,
  CONSTRAINT `fk_Paciente_Persona1`
    FOREIGN KEY (`Doc_Tipo` , `Doc_Numero` )
    REFERENCES `sanidad-db`.`Persona` (`Doc_Tipo` , `Doc_Numero` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sanidad-db`.`Consulta`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sanidad-db`.`Consulta` (
  `ID` INT NOT NULL ,
  `Tipo_Consulta_Nombre` VARCHAR(45) NOT NULL ,
  `Paciente_Doc_Tipo` VARCHAR(20) NOT NULL ,
  `Paciente_Doc_Numero` INT NOT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `fk_Consulta_Tipo_Consulta1` (`Tipo_Consulta_Nombre` ASC) ,
  INDEX `fk_Consulta_Paciente1` (`Paciente_Doc_Tipo` ASC, `Paciente_Doc_Numero` ASC) ,
  CONSTRAINT `fk_Consulta_Tipo_Consulta1`
    FOREIGN KEY (`Tipo_Consulta_Nombre` )
    REFERENCES `sanidad-db`.`Tipo_Consulta` (`Nombre` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Consulta_Paciente1`
    FOREIGN KEY (`Paciente_Doc_Tipo` , `Paciente_Doc_Numero` )
    REFERENCES `sanidad-db`.`Paciente` (`Doc_Tipo` , `Doc_Numero` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sanidad-db`.`Historia_Clinica`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sanidad-db`.`Historia_Clinica` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `Paciente_Doc_Tipo` VARCHAR(20) NOT NULL ,
  `Paciente_Doc_Numero` INT NOT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `fk_Historia_Clinica_Paciente1` (`Paciente_Doc_Tipo` ASC, `Paciente_Doc_Numero` ASC) ,
  CONSTRAINT `fk_Historia_Clinica_Paciente1`
    FOREIGN KEY (`Paciente_Doc_Tipo` , `Paciente_Doc_Numero` )
    REFERENCES `sanidad-db`.`Paciente` (`Doc_Tipo` , `Doc_Numero` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sanidad-db`.`Resultado`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sanidad-db`.`Resultado` (
  `FechaConsulta` DATE NOT NULL ,
  `Consulta_ID` INT NOT NULL ,
  `Historia_Clinica_ID` INT NOT NULL ,
  `Notas` TEXT NULL ,
  `Documento_Resultado_ID` INT NOT NULL ,
  PRIMARY KEY (`FechaConsulta`, `Consulta_ID`, `Historia_Clinica_ID`) ,
  INDEX `fk_Resultado_Documento_Resultado1` (`Documento_Resultado_ID` ASC) ,
  INDEX `fk_Resultado_Consulta1` (`Consulta_ID` ASC) ,
  INDEX `fk_Resultado_Historia_Clinica1` (`Historia_Clinica_ID` ASC) ,
  CONSTRAINT `fk_Resultado_Documento_Resultado1`
    FOREIGN KEY (`Documento_Resultado_ID` )
    REFERENCES `sanidad-db`.`Documento_Resultado` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Resultado_Consulta1`
    FOREIGN KEY (`Consulta_ID` )
    REFERENCES `sanidad-db`.`Consulta` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Resultado_Historia_Clinica1`
    FOREIGN KEY (`Historia_Clinica_ID` )
    REFERENCES `sanidad-db`.`Historia_Clinica` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sanidad-db`.`Medico`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sanidad-db`.`Medico` (
  `Doc_Tipo` VARCHAR(20) NOT NULL ,
  `Doc_Numero` INT NOT NULL ,
  PRIMARY KEY (`Doc_Tipo`, `Doc_Numero`) ,
  INDEX `fk_Medico_Persona1` (`Doc_Tipo` ASC, `Doc_Numero` ASC) ,
  CONSTRAINT `fk_Medico_Persona1`
    FOREIGN KEY (`Doc_Tipo` , `Doc_Numero` )
    REFERENCES `sanidad-db`.`Persona` (`Doc_Tipo` , `Doc_Numero` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sanidad-db`.`InterConsulta`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sanidad-db`.`InterConsulta` (
  `Consulta_ID` INT NOT NULL ,
  `Medico_Doc_Tipo` VARCHAR(20) NOT NULL ,
  `Medico_Doc_Numero` INT NOT NULL ,
  PRIMARY KEY (`Consulta_ID`) ,
  INDEX `fk_InterConsulta_Medico1` (`Medico_Doc_Tipo` ASC, `Medico_Doc_Numero` ASC) ,
  CONSTRAINT `fk_InterConsulta_Consulta1`
    FOREIGN KEY (`Consulta_ID` )
    REFERENCES `sanidad-db`.`Consulta` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_InterConsulta_Medico1`
    FOREIGN KEY (`Medico_Doc_Tipo` , `Medico_Doc_Numero` )
    REFERENCES `sanidad-db`.`Medico` (`Doc_Tipo` , `Doc_Numero` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sanidad-db`.`Especialidad`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sanidad-db`.`Especialidad` (
  `Nombre` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`Nombre`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sanidad-db`.`Especializa`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sanidad-db`.`Especializa` (
  `Medico_Doc_Tipo` VARCHAR(20) NOT NULL ,
  `Medico_Doc_Numero` INT NOT NULL ,
  `Especialidad_Nombre` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`Medico_Doc_Tipo`, `Medico_Doc_Numero`, `Especialidad_Nombre`) ,
  INDEX `fk_Medico_has_Especialidad_Especialidad1` (`Especialidad_Nombre` ASC) ,
  INDEX `fk_Medico_has_Especialidad_Medico1` (`Medico_Doc_Tipo` ASC, `Medico_Doc_Numero` ASC) ,
  CONSTRAINT `fk_Medico_has_Especialidad_Medico1`
    FOREIGN KEY (`Medico_Doc_Tipo` , `Medico_Doc_Numero` )
    REFERENCES `sanidad-db`.`Medico` (`Doc_Tipo` , `Doc_Numero` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Medico_has_Especialidad_Especialidad1`
    FOREIGN KEY (`Especialidad_Nombre` )
    REFERENCES `sanidad-db`.`Especialidad` (`Nombre` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sanidad-db`.`Turno`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sanidad-db`.`Turno` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `FechaHoraInicio` DATETIME NOT NULL ,
  `FechaHoraFin` DATETIME NOT NULL ,
  `Medico_Doc_Tipo` VARCHAR(20) NOT NULL ,
  `Medico_Doc_Numero` INT NOT NULL ,
  `Especialidad_Nombre` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `fk_Turno_Especializa1` (`Medico_Doc_Tipo` ASC, `Medico_Doc_Numero` ASC, `Especialidad_Nombre` ASC) ,
  CONSTRAINT `fk_Turno_Especializa1`
    FOREIGN KEY (`Medico_Doc_Tipo` , `Medico_Doc_Numero` , `Especialidad_Nombre` )
    REFERENCES `sanidad-db`.`Especializa` (`Medico_Doc_Tipo` , `Medico_Doc_Numero` , `Especialidad_Nombre` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sanidad-db`.`IntraConsulta`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sanidad-db`.`IntraConsulta` (
  `Consulta_ID` INT NOT NULL ,
  `Turno_ID` INT NOT NULL ,
  PRIMARY KEY (`Consulta_ID`) ,
  INDEX `fk_IntraConsulta_Turno1` (`Turno_ID` ASC) ,
  CONSTRAINT `fk_IntraConsulta_Consulta1`
    FOREIGN KEY (`Consulta_ID` )
    REFERENCES `sanidad-db`.`Consulta` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_IntraConsulta_Turno1`
    FOREIGN KEY (`Turno_ID` )
    REFERENCES `sanidad-db`.`Turno` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sanidad-db`.`Realiza`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sanidad-db`.`Realiza` (
  `Especialidad_Nombre` VARCHAR(45) NOT NULL ,
  `Tipo_Consulta_Nombre` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`Especialidad_Nombre`, `Tipo_Consulta_Nombre`) ,
  INDEX `fk_Realizar_Tipo_Consulta1` (`Tipo_Consulta_Nombre` ASC) ,
  CONSTRAINT `fk_Realizar_Especialidad1`
    FOREIGN KEY (`Especialidad_Nombre` )
    REFERENCES `sanidad-db`.`Especialidad` (`Nombre` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Realizar_Tipo_Consulta1`
    FOREIGN KEY (`Tipo_Consulta_Nombre` )
    REFERENCES `sanidad-db`.`Tipo_Consulta` (`Nombre` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `sanidad-db`.`Tipo_Consulta`
-- -----------------------------------------------------
START TRANSACTION;
USE `sanidad-db`;
INSERT INTO `sanidad-db`.`Tipo_Consulta` (`Nombre`) VALUES ('Clínica_Médica');
INSERT INTO `sanidad-db`.`Tipo_Consulta` (`Nombre`) VALUES ('Traumatología');
INSERT INTO `sanidad-db`.`Tipo_Consulta` (`Nombre`) VALUES ('Clínica_Quirúrgica');
INSERT INTO `sanidad-db`.`Tipo_Consulta` (`Nombre`) VALUES ('Psiquiatría');
INSERT INTO `sanidad-db`.`Tipo_Consulta` (`Nombre`) VALUES ('Ginecología');
INSERT INTO `sanidad-db`.`Tipo_Consulta` (`Nombre`) VALUES ('Odontología');
INSERT INTO `sanidad-db`.`Tipo_Consulta` (`Nombre`) VALUES ('Enfermería');

COMMIT;

-- -----------------------------------------------------
-- Data for table `sanidad-db`.`Doc_Tipo`
-- -----------------------------------------------------
START TRANSACTION;
USE `sanidad-db`;
INSERT INTO `sanidad-db`.`Doc_Tipo` (`Tipo`) VALUES ('DNI');
INSERT INTO `sanidad-db`.`Doc_Tipo` (`Tipo`) VALUES ('LE');
INSERT INTO `sanidad-db`.`Doc_Tipo` (`Tipo`) VALUES ('LC');

COMMIT;

-- -----------------------------------------------------
-- Data for table `sanidad-db`.`UNS_Relacion`
-- -----------------------------------------------------
START TRANSACTION;
USE `sanidad-db`;
INSERT INTO `sanidad-db`.`UNS_Relacion` (`Relacion`) VALUES ('Alumno');
INSERT INTO `sanidad-db`.`UNS_Relacion` (`Relacion`) VALUES ('Empleado');
INSERT INTO `sanidad-db`.`UNS_Relacion` (`Relacion`) VALUES ('Ninguna');

COMMIT;

-- -----------------------------------------------------
-- Data for table `sanidad-db`.`Persona`
-- -----------------------------------------------------
START TRANSACTION;
USE `sanidad-db`;
INSERT INTO `sanidad-db`.`Persona` (`Doc_Tipo`, `Doc_Numero`, `Apellido`, `Nombre`, `UNS_Relacion_Relacion`) VALUES ('DNI', 31298030, 'Gonzalez', 'Juan Domingo', 'Alumno');
INSERT INTO `sanidad-db`.`Persona` (`Doc_Tipo`, `Doc_Numero`, `Apellido`, `Nombre`, `UNS_Relacion_Relacion`) VALUES ('DNI', 5432145, 'Castro', 'Julieta', 'Empleado');
INSERT INTO `sanidad-db`.`Persona` (`Doc_Tipo`, `Doc_Numero`, `Apellido`, `Nombre`, `UNS_Relacion_Relacion`) VALUES ('DNI', 6879534, 'Ponce', 'Carlos', 'Empleado');
INSERT INTO `sanidad-db`.`Persona` (`Doc_Tipo`, `Doc_Numero`, `Apellido`, `Nombre`, `UNS_Relacion_Relacion`) VALUES ('DNI', 29878422, 'Macaroni', 'Cintia', 'Alumno');
INSERT INTO `sanidad-db`.`Persona` (`Doc_Tipo`, `Doc_Numero`, `Apellido`, `Nombre`, `UNS_Relacion_Relacion`) VALUES ('DNI', 30212145, 'Berardo', 'Javier P.', 'Alumno');
INSERT INTO `sanidad-db`.`Persona` (`Doc_Tipo`, `Doc_Numero`, `Apellido`, `Nombre`, `UNS_Relacion_Relacion`) VALUES ('DNI', 33548778, 'Palmieri', 'Jorgelina A.', 'Alumno');
INSERT INTO `sanidad-db`.`Persona` (`Doc_Tipo`, `Doc_Numero`, `Apellido`, `Nombre`, `UNS_Relacion_Relacion`) VALUES ('DNI', 31878452, 'Rodriguez', 'Blanca', 'Alumno');
INSERT INTO `sanidad-db`.`Persona` (`Doc_Tipo`, `Doc_Numero`, `Apellido`, `Nombre`, `UNS_Relacion_Relacion`) VALUES ('DNI', 20981234, 'Kappes', 'Antonio O.', 'Empleado');
INSERT INTO `sanidad-db`.`Persona` (`Doc_Tipo`, `Doc_Numero`, `Apellido`, `Nombre`, `UNS_Relacion_Relacion`) VALUES ('DNI', 22587497, 'Salvatore', 'Marianela', 'Empleado');
INSERT INTO `sanidad-db`.`Persona` (`Doc_Tipo`, `Doc_Numero`, `Apellido`, `Nombre`, `UNS_Relacion_Relacion`) VALUES ('DNI', 27544876, 'Basualdo', 'Daniel', 'Empleado');

COMMIT;

-- -----------------------------------------------------
-- Data for table `sanidad-db`.`Paciente`
-- -----------------------------------------------------
START TRANSACTION;
USE `sanidad-db`;
INSERT INTO `sanidad-db`.`Paciente` (`Doc_Tipo`, `Doc_Numero`) VALUES ('DNI', 29878422);
INSERT INTO `sanidad-db`.`Paciente` (`Doc_Tipo`, `Doc_Numero`) VALUES ('DNI', 30212145);
INSERT INTO `sanidad-db`.`Paciente` (`Doc_Tipo`, `Doc_Numero`) VALUES ('DNI', 33548778);
INSERT INTO `sanidad-db`.`Paciente` (`Doc_Tipo`, `Doc_Numero`) VALUES ('DNI', 31878452);
INSERT INTO `sanidad-db`.`Paciente` (`Doc_Tipo`, `Doc_Numero`) VALUES ('DNI', 22587497);
INSERT INTO `sanidad-db`.`Paciente` (`Doc_Tipo`, `Doc_Numero`) VALUES ('DNI', 27544876);

COMMIT;

-- -----------------------------------------------------
-- Data for table `sanidad-db`.`Medico`
-- -----------------------------------------------------
START TRANSACTION;
USE `sanidad-db`;
INSERT INTO `sanidad-db`.`Medico` (`Doc_Tipo`, `Doc_Numero`) VALUES ('DNI', 6879534);
INSERT INTO `sanidad-db`.`Medico` (`Doc_Tipo`, `Doc_Numero`) VALUES ('DNI', 5432145);
INSERT INTO `sanidad-db`.`Medico` (`Doc_Tipo`, `Doc_Numero`) VALUES ('DNI', 20981234);

COMMIT;

-- -----------------------------------------------------
-- Data for table `sanidad-db`.`Especialidad`
-- -----------------------------------------------------
START TRANSACTION;
USE `sanidad-db`;
INSERT INTO `sanidad-db`.`Especialidad` (`Nombre`) VALUES ('Clínica');
INSERT INTO `sanidad-db`.`Especialidad` (`Nombre`) VALUES ('Traumatología');
INSERT INTO `sanidad-db`.`Especialidad` (`Nombre`) VALUES ('Cirugía_General');
INSERT INTO `sanidad-db`.`Especialidad` (`Nombre`) VALUES ('Psiquiatría');
INSERT INTO `sanidad-db`.`Especialidad` (`Nombre`) VALUES ('Ginecología');
INSERT INTO `sanidad-db`.`Especialidad` (`Nombre`) VALUES ('Odontología');
INSERT INTO `sanidad-db`.`Especialidad` (`Nombre`) VALUES ('Enfermería');
INSERT INTO `sanidad-db`.`Especialidad` (`Nombre`) VALUES ('Cardiología');

COMMIT;

-- -----------------------------------------------------
-- Data for table `sanidad-db`.`Especializa`
-- -----------------------------------------------------
START TRANSACTION;
USE `sanidad-db`;
INSERT INTO `sanidad-db`.`Especializa` (`Medico_Doc_Tipo`, `Medico_Doc_Numero`, `Especialidad_Nombre`) VALUES ('DNI', 6879534, 'Clínica');
INSERT INTO `sanidad-db`.`Especializa` (`Medico_Doc_Tipo`, `Medico_Doc_Numero`, `Especialidad_Nombre`) VALUES ('DNI', 6879534, 'Traumatología');
INSERT INTO `sanidad-db`.`Especializa` (`Medico_Doc_Tipo`, `Medico_Doc_Numero`, `Especialidad_Nombre`) VALUES ('DNI', 6879534, 'Cirugía_General');
INSERT INTO `sanidad-db`.`Especializa` (`Medico_Doc_Tipo`, `Medico_Doc_Numero`, `Especialidad_Nombre`) VALUES ('DNI', 5432145, 'Psiquiatría');
INSERT INTO `sanidad-db`.`Especializa` (`Medico_Doc_Tipo`, `Medico_Doc_Numero`, `Especialidad_Nombre`) VALUES ('DNI', 20981234, 'Ginecología');
INSERT INTO `sanidad-db`.`Especializa` (`Medico_Doc_Tipo`, `Medico_Doc_Numero`, `Especialidad_Nombre`) VALUES ('DNI', 20981234, 'Odontología');
INSERT INTO `sanidad-db`.`Especializa` (`Medico_Doc_Tipo`, `Medico_Doc_Numero`, `Especialidad_Nombre`) VALUES ('DNI', 20981234, 'Enfermería');
INSERT INTO `sanidad-db`.`Especializa` (`Medico_Doc_Tipo`, `Medico_Doc_Numero`, `Especialidad_Nombre`) VALUES ('DNI', 5432145, 'Enfermería');

COMMIT;

-- -----------------------------------------------------
-- Data for table `sanidad-db`.`Realiza`
-- -----------------------------------------------------
START TRANSACTION;
USE `sanidad-db`;
INSERT INTO `sanidad-db`.`Realiza` (`Especialidad_Nombre`, `Tipo_Consulta_Nombre`) VALUES ('Clínica', 'Clínica_Médica');
INSERT INTO `sanidad-db`.`Realiza` (`Especialidad_Nombre`, `Tipo_Consulta_Nombre`) VALUES ('Traumatología', 'Traumatología');
INSERT INTO `sanidad-db`.`Realiza` (`Especialidad_Nombre`, `Tipo_Consulta_Nombre`) VALUES ('Cirugía_General', 'Clínica_Quirúrgica');
INSERT INTO `sanidad-db`.`Realiza` (`Especialidad_Nombre`, `Tipo_Consulta_Nombre`) VALUES ('Psiquiatría', 'Psiquiatría');
INSERT INTO `sanidad-db`.`Realiza` (`Especialidad_Nombre`, `Tipo_Consulta_Nombre`) VALUES ('Ginecología', 'Ginecología');
INSERT INTO `sanidad-db`.`Realiza` (`Especialidad_Nombre`, `Tipo_Consulta_Nombre`) VALUES ('Odontología', 'Odontología');
INSERT INTO `sanidad-db`.`Realiza` (`Especialidad_Nombre`, `Tipo_Consulta_Nombre`) VALUES ('Enfermería', 'Enfermería');

COMMIT;

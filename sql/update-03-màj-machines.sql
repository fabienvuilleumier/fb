-- MySQL Workbench Synchronization
-- Generated: 2015-06-04 15:46
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: gaetan

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

ALTER TABLE `fablab`.`t_machine` 
ADD COLUMN `code` VARCHAR(5) NOT NULL AFTER `machine_id`,
ADD COLUMN `acquisition_date` DATE NOT NULL AFTER `code`,
ADD COLUMN `machine_status_id` INT(11) NOT NULL AFTER `machine_type_id`,
ADD COLUMN `machine_state_id` INT(11) NOT NULL AFTER `machine_status_id`,
ADD UNIQUE INDEX `mach_code_UNIQUE` (`code` ASC),
ADD INDEX `fk_t_machine_t_machine_status1_idx` (`machine_status_id` ASC),
ADD INDEX `fk_t_machine_t_machine_state1_idx` (`machine_state_id` ASC);

CREATE TABLE IF NOT EXISTS `fablab`.`t_machine_status` (
  `machine_status_id` INT(11) NOT NULL AUTO_INCREMENT,
  `label` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`machine_status_id`),
  UNIQUE INDEX `label_UNIQUE` (`label` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `fablab`.`t_machine_state` (
  `machine_state_id` INT(11) NOT NULL AUTO_INCREMENT,
  `label` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`machine_state_id`),
  UNIQUE INDEX `label_UNIQUE` (`label` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

ALTER TABLE `fablab`.`t_machine` 
ADD CONSTRAINT `fk_t_machine_t_machine_status1`
  FOREIGN KEY (`machine_status_id`)
  REFERENCES `fablab`.`t_machine_status` (`machine_status_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_t_machine_t_machine_state1`
  FOREIGN KEY (`machine_state_id`)
  REFERENCES `fablab`.`t_machine_state` (`machine_state_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

  
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

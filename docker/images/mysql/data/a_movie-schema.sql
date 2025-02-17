-- MySQL Script generated by MySQL Workbench
-- mer 30 oct 2019 11:35:28 CET
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema movie_history
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema movie_history
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `movie_history` DEFAULT CHARACTER SET utf8 ;
USE `movie_history` ;

-- -----------------------------------------------------
-- Table `movie_history`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movie_history`.`User` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `Lastname` VARCHAR(45) NOT NULL,
  `Firstname` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie_history`.`Movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movie_history`.`Movie` (
  `idMovie` INT NOT NULL AUTO_INCREMENT,
  `Title` VARCHAR(200) NOT NULL,
  `Year` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idMovie`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie_history`.`User_has_seen_Movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movie_history`.`User_has_seen_Movie` (
  `User_idUser` INT NOT NULL,
  `Movie_idMovie` INT NOT NULL,
  PRIMARY KEY (`User_idUser`, `Movie_idMovie`),
  INDEX `fk_User_has_Movie_Movie1_idx` (`Movie_idMovie` ASC),
  INDEX `fk_User_has_Movie_User_idx` (`User_idUser` ASC),
  CONSTRAINT `fk_User_has_Movie_User`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `movie_history`.`User` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Movie_Movie1`
    FOREIGN KEY (`Movie_idMovie`)
    REFERENCES `movie_history`.`Movie` (`idMovie`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

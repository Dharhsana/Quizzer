-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema quizApp_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema quizApp_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `quizApp_db` DEFAULT CHARACTER SET utf8 ;
USE `quizApp_db` ;

-- -----------------------------------------------------
-- Table `quizApp_db`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quizApp_db`.`user` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `lastlogging` DATETIME(6) NULL,
  `otp` VARCHAR(45) NULL,
  `email` VARCHAR(255) NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quizApp_db`.`classroom`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quizApp_db`.`classroom` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `created_date` DATETIME(6) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quizApp_db`.`quiz`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quizApp_db`.`quiz` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `time` TIME NULL,
  `description` VARCHAR(45) NULL,
  `number_of_questions` INT NULL,
  `classroom_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_quiz_classroom_idx` (`classroom_id` ASC) VISIBLE,
  CONSTRAINT `fk_quiz_classroom`
    FOREIGN KEY (`classroom_id`)
    REFERENCES `quizApp_db`.`classroom` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quizApp_db`.`tutor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quizApp_db`.`tutor` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `profile_pic` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_tutor_user1`
    FOREIGN KEY (`id`)
    REFERENCES `quizApp_db`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quizApp_db`.`answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quizApp_db`.`answer` (
  `id` INT NOT NULL,
  `label` VARCHAR(45) NULL,
  `text` VARCHAR(255) NULL,
  `question_id` INT NULL,
  `tutor_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_answer_belongs_to_question_idx` (`question_id` ASC) VISIBLE,
  INDEX `fk_answer_tutor1_idx` (`tutor_id` ASC) VISIBLE,
  CONSTRAINT `fk_answer_belongs_to_question`
    FOREIGN KEY (`question_id`)
    REFERENCES `quizApp_db`.`question` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_answer_tutor1`
    FOREIGN KEY (`tutor_id`)
    REFERENCES `quizApp_db`.`tutor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quizApp_db`.`question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quizApp_db`.`question` (
  `id` INT NOT NULL,
  `text` VARCHAR(255) NULL,
  `weight` INT NULL,
  `answer_correct` INT NOT NULL,
  `tutor_id` INT NOT NULL,
  PRIMARY KEY (`id`, `tutor_id`),
  INDEX `fk_correct_answer_idx` (`answer_correct` ASC) VISIBLE,
  INDEX `fk_tutor_created_idx` (`tutor_id` ASC) VISIBLE,
  CONSTRAINT `fk_correct_answer`
    FOREIGN KEY (`answer_correct`)
    REFERENCES `quizApp_db`.`answer` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_tutor_created`
    FOREIGN KEY (`tutor_id`)
    REFERENCES `quizApp_db`.`tutor` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quizApp_db`.`user_support`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quizApp_db`.`user_support` (
  `id` INT NOT NULL,
  `subject` VARCHAR(255) NULL,
  `content` VARCHAR(255) NULL,
  `date` DATETIME(6) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quizApp_db`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quizApp_db`.`student` (
  `id` INT NOT NULL,
  `profile_pic` VARCHAR(255) NULL,
  `name` VARCHAR(45) NOT NULL,
  INDEX `fk_user_student_idx` (`id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_user_student`
    FOREIGN KEY (`id`)
    REFERENCES `quizApp_db`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quizApp_db`.`classroom_has_tutor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quizApp_db`.`classroom_has_tutor` (
  `classroom_id` INT NOT NULL,
  `tutor_id` INT NOT NULL,
  PRIMARY KEY (`classroom_id`, `tutor_id`),
  INDEX `fk_classroom_has_tutor_tutor1_idx` (`tutor_id` ASC) VISIBLE,
  INDEX `fk_classroom_has_tutor_classroom1_idx` (`classroom_id` ASC) VISIBLE,
  CONSTRAINT `fk_classroom_has_tutor_classroom1`
    FOREIGN KEY (`classroom_id`)
    REFERENCES `quizApp_db`.`classroom` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_classroom_has_tutor_tutor1`
    FOREIGN KEY (`tutor_id`)
    REFERENCES `quizApp_db`.`tutor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quizApp_db`.`student_has_classroom`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quizApp_db`.`student_has_classroom` (
  `student_id` INT NOT NULL,
  `classroom_id` INT NOT NULL,
  `joined_date` DATETIME(6) NULL,
  PRIMARY KEY (`student_id`, `classroom_id`),
  INDEX `fk_student_has_classroom_classroom1_idx` (`classroom_id` ASC) VISIBLE,
  CONSTRAINT `fk_student_has_classroom_student1`
    FOREIGN KEY (`student_id`)
    REFERENCES `quizApp_db`.`student` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_student_has_classroom_classroom1`
    FOREIGN KEY (`classroom_id`)
    REFERENCES `quizApp_db`.`classroom` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quizApp_db`.`quiz_has_question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quizApp_db`.`quiz_has_question` (
  `quiz_id` INT NOT NULL,
  `question_id` INT NOT NULL,
  `question_label` VARCHAR(45) NULL,
  `question_display_order` VARCHAR(45) NULL,
  PRIMARY KEY (`quiz_id`, `question_id`),
  INDEX `fk_quiz_has_question_question1_idx` (`question_id` ASC) VISIBLE,
  INDEX `fk_quiz_has_question_quiz1_idx` (`quiz_id` ASC) VISIBLE,
  CONSTRAINT `fk_quiz_has_question_quiz1`
    FOREIGN KEY (`quiz_id`)
    REFERENCES `quizApp_db`.`quiz` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_quiz_has_question_question1`
    FOREIGN KEY (`question_id`)
    REFERENCES `quizApp_db`.`question` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quizApp_db`.`student_has_quiz`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quizApp_db`.`student_has_quiz` (
  `quiz_id` INT NOT NULL,
  `attempt` INT NULL,
  `student_id` INT NOT NULL,
  `result` VARCHAR(45) NULL,
  `grade` VARCHAR(45) NULL,
  PRIMARY KEY (`quiz_id`, `student_id`),
  INDEX `fk_student_has_quiz_quiz2_idx` (`student_id` ASC) INVISIBLE,
  INDEX `fk_student_has_quiz_quiz1_idx` (`quiz_id` ASC) VISIBLE,
  CONSTRAINT `fk_student_has_quiz_quiz1`
    FOREIGN KEY (`quiz_id`)
    REFERENCES `quizApp_db`.`quiz` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_student_has_quiz_quiz2`
    FOREIGN KEY (`student_id`)
    REFERENCES `quizApp_db`.`student` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quizApp_db`.`system_config`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quizApp_db`.`system_config` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `true` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

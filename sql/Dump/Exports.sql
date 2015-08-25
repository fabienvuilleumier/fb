-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: fablab
-- ------------------------------------------------------
-- Server version	5.6.24-log

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
-- Dumping data for table `r_group_role`
--

LOCK TABLES `r_group_role` WRITE;
/*!40000 ALTER TABLE `r_group_role` DISABLE KEYS */;
INSERT INTO `r_group_role` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(4,2),(9,2),(10,2),(11,2),(12,2),(9,3),(10,3),(11,3),(12,3);
/*!40000 ALTER TABLE `r_group_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `r_group_user`
--

LOCK TABLES `r_group_user` WRITE;
/*!40000 ALTER TABLE `r_group_user` DISABLE KEYS */;
INSERT INTO `r_group_user` VALUES (1,1),(1,2),(2,4),(3,3);
/*!40000 ALTER TABLE `r_group_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `r_price_machine`
--

LOCK TABLES `r_price_machine` WRITE;
/*!40000 ALTER TABLE `r_price_machine` DISABLE KEYS */;
INSERT INTO `r_price_machine` VALUES (1,1,1,10),(1,1,2,7),(1,1,3,5),(1,1,4,5),(1,2,1,40),(1,2,2,20),(1,2,3,10),(1,2,4,10),(1,3,1,40),(1,3,2,20),(1,3,3,10),(1,3,4,10),(1,4,1,0),(1,4,2,0),(1,4,3,0),(1,4,4,0);
/*!40000 ALTER TABLE `r_price_machine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `r_user_authorized_machine_type`
--

LOCK TABLES `r_user_authorized_machine_type` WRITE;
/*!40000 ALTER TABLE `r_user_authorized_machine_type` DISABLE KEYS */;
INSERT INTO `r_user_authorized_machine_type` VALUES (1,1,'2014-01-01');
/*!40000 ALTER TABLE `r_user_authorized_machine_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `t_audit`
--

LOCK TABLES `t_audit` WRITE;
/*!40000 ALTER TABLE `t_audit` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_audit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `t_configuration`
--

LOCK TABLES `t_configuration` WRITE;
/*!40000 ALTER TABLE `t_configuration` DISABLE KEYS */;
INSERT INTO `t_configuration` VALUES (1,'FABLAB_NAME','Fablab-Fribourg'),(2,'FABLAB_URL','http://fablab-fribourg.ch'),(3,'ACCOUNTING_EDIT_HISTORY_LIMIT','7'),(4,'GOOGLE_CALENDAR_API_KEY',NULL),(5,'RECAPTCHA_SITE_KEY',NULL),(6,'CURRENCY','CHF'),(7,'RECAPTCHA_SECRET',NULL);
/*!40000 ALTER TABLE `t_configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `t_group`
--

LOCK TABLES `t_group` WRITE;
/*!40000 ALTER TABLE `t_group` DISABLE KEYS */;
INSERT INTO `t_group` VALUES (1,'comite','Comité'),(2,'animator','Animateur'),(3,'member','Membre');
/*!40000 ALTER TABLE `t_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `t_machine`
--

LOCK TABLES `t_machine` WRITE;
/*!40000 ALTER TABLE `t_machine` DISABLE KEYS */;
INSERT INTO `t_machine` VALUES (1,'ULTI1','0000-00-00 00:00:00','Ultimaker V1',1,0,0),(2,'ULTI2','0000-00-00 00:00:00','Ultimkaer V2',1,0,0),(3,'MARKE','0000-00-00 00:00:00','MakerBot Replicatior 2',1,0,0),(4,'DECOU','0000-00-00 00:00:00','Decoupeuse laser',2,0,0),(5,'CNCC1','0000-00-00 00:00:00','CNC chinoise 1',3,0,0),(6,'CNCC2','0000-00-00 00:00:00','CNC chinoise 2',3,0,0);
/*!40000 ALTER TABLE `t_machine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `t_machine_type`
--

LOCK TABLES `t_machine_type` WRITE;
/*!40000 ALTER TABLE `t_machine_type` DISABLE KEYS */;
INSERT INTO `t_machine_type` VALUES (1,'3dprinter','Imprimante 3D',1),(2,'lasercutter','Decoupeuse Laser',1),(3,'cnc','CNC',1),(4,'other','Other',0),(5,'Test2','Test2',0);
/*!40000 ALTER TABLE `t_machine_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `t_membership_type`
--

LOCK TABLES `t_membership_type` WRITE;
/*!40000 ALTER TABLE `t_membership_type` DISABLE KEYS */;
INSERT INTO `t_membership_type` VALUES (1,'extern',1,10),(2,'normal',1,8),(3,'student',1,5),(4,'angel',1,15);
/*!40000 ALTER TABLE `t_membership_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `t_payment`
--

LOCK TABLES `t_payment` WRITE;
/*!40000 ALTER TABLE `t_payment` DISABLE KEYS */;
INSERT INTO `t_payment` VALUES (1,0.05,'2015-05-28 09:19:36',3,2,NULL);
/*!40000 ALTER TABLE `t_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `t_price_revision`
--

LOCK TABLES `t_price_revision` WRITE;
/*!40000 ALTER TABLE `t_price_revision` DISABLE KEYS */;
INSERT INTO `t_price_revision` VALUES (1,'2001-01-20 14:00:00',365);
/*!40000 ALTER TABLE `t_price_revision` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `t_reservation`
--

LOCK TABLES `t_reservation` WRITE;
/*!40000 ALTER TABLE `t_reservation` DISABLE KEYS */;
INSERT INTO `t_reservation` VALUES (1,'2014-06-23 18:00:00','2014-06-23 19:00:00',1,1),(2,'2014-06-23 18:00:00','2014-06-23 19:00:00',1,4),(3,'2014-06-23 18:00:00','2014-06-23 20:00:00',1,2),(4,'2015-05-27 18:00:00','2015-05-27 19:00:00',3,1),(5,'2015-05-27 18:00:00','2015-05-27 19:00:00',3,2),(6,'2015-05-27 18:00:00','2015-05-27 19:00:00',3,3),(7,'2015-05-27 18:00:00','2015-05-27 19:00:00',3,4),(8,'2015-05-27 18:00:00','2015-05-27 19:00:00',3,5),(9,'2015-05-27 18:00:00','2015-05-27 19:00:00',3,6);
/*!40000 ALTER TABLE `t_reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` VALUES (1,'Administration','ROLE_ADMIN'),(2,'View user','ROLE_USER_VIEW'),(3,'Manage user','ROLE_USER_MANAGE'),(4,'View machine','ROLE_MACHINE_VIEW'),(5,'Manage machine','ROLE_MACHINE_MANAGE'),(6,'View payment','ROLE_PAYMENT_VIEW'),(7,'Manage payment','ROLE_PAYMENT_MANAGE'),(8,'View accounting','ROLE_ACCOUNTING_VIEW'),(9,'Manage accounting','ROLE_ACCOUNTING_MANAGE'),(10,'View audit','ROLE_AUDIT_VIEW'),(11,'View reservation','ROLE_RESERVATION_VIEW'),(12,'Use reservation','ROLE_RESERVATION_USE'),(13,'Manage reservation','ROLE_RESERVATION_MANAGE'),(14,'Manage Mailing list','ROLE_MAILINGLIST_MANAGE');
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `t_subscription`
--

LOCK TABLES `t_subscription` WRITE;
/*!40000 ALTER TABLE `t_subscription` DISABLE KEYS */;
INSERT INTO `t_subscription` VALUES (1,1,3,'2015-05-26 14:50:59',5,1,NULL),(2,2,2,'2015-05-26 15:01:50',8,1,NULL),(3,3,1,'2015-05-27 13:34:27',10,1,NULL),(4,4,4,'2015-05-27 13:40:34',15,1,NULL);
/*!40000 ALTER TABLE `t_subscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `t_usage`
--

LOCK TABLES `t_usage` WRITE;
/*!40000 ALTER TABLE `t_usage` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_usage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,3,1,'fabien','e3bc7aa498d8d58544be42e4e3473c90e96cf3e796d3f99c0b82b73f26f590c6','e3bc7aa498d8d58544be42e4e3473c90e96cf3e796d3f99c0b82b73f26f590c6','2qoPnWBNOCITp6WfvVaWGmS4C3iWORdtWzyk13Bx','Fabien','Vuilleumier','fabien.vuilleumier@gmail.com','2015-05-26 13:05:17',0,'120050C9ED66',1,NULL,NULL,NULL,NULL,NULL),(2,2,1,'admin','7c0deadab3077e14480bc4cfc80714b7cd8f12bd4c8968f69e4a0053c38867f1','7c0deadab3077e14480bc4cfc80714b7cd8f12bd4c8968f69e4a0053c38867f1','tW7RjN0OvD3ltNFb27fi4nZ39qIhLM1x99ls1qFZ','Admin','Admin','1@1.com','2015-05-26 14:57:12',0,'120050C9ED65',1,NULL,NULL,NULL,NULL,NULL),(3,1,1,'member',NULL,'e3bc7aa498d8d58544be42e4e3473c90e96cf3e796d3f99c0b82b73f26f590c6','2qoPnWBNOCITp6WfvVaWGmS4C3iWORdtWzyk13Bx','Membre','Membre','1@2.com','2015-05-27 13:30:24',0,'120050C9ED64',1,NULL,NULL,NULL,NULL,NULL),(4,4,1,'confirme',NULL,'e3bc7aa498d8d58544be42e4e3473c90e96cf3e796d3f99c0b82b73f26f590c6','2qoPnWBNOCITp6WfvVaWGmS4C3iWORdtWzyk13Bx','Confirme','Confirme','1@3.com','2015-05-27 13:30:25',0,'120050C9ED63',1,NULL,NULL,NULL,NULL,NULL),(5,1,1,NULL,NULL,'e36eccead6e7d5d68d9e41f1bcf3dd1fa3b91b68baba9af6c8c413d730a3ef67','3rv3EcMDQHbBgLB7VmLS8p5K3uqrnotdNBv6HHPZ','Test','Test','test@1.com','2015-06-03 13:21:58',0,NULL,0,NULL,NULL,NULL,1,NULL);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-05 14:52:53

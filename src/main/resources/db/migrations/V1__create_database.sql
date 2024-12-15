-- MySQL dump 10.13  Distrib 8.0.40, for Linux (x86_64)
--
-- Host: localhost    Database: quickmart
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
                          `client_id` varchar(255) NOT NULL,
                          `created_at` datetime(6) DEFAULT NULL,
                          `email` varchar(255) NOT NULL,
                          `full_name` varchar(255) DEFAULT NULL,
                          `nickname` varchar(255) NOT NULL,
                          `password` varchar(255) DEFAULT NULL,
                          `role` enum('ADMIN','CLIENT','SELLER') DEFAULT NULL,
                          `updated_at` datetime(6) DEFAULT NULL,
                          `username` varchar(255) NOT NULL,
                          PRIMARY KEY (`client_id`),
                          UNIQUE KEY `UKbfgjs3fem0hmjhvih80158x29` (`email`),
                          UNIQUE KEY `UK3avnxjein999stjw9ia6bgq9o` (`nickname`),
                          UNIQUE KEY `UKah5c1ribskm746956okm9283n` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `flyway_schema_history`
--

DROP TABLE IF EXISTS `flyway_schema_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flyway_schema_history` (
                                         `installed_rank` int NOT NULL,
                                         `version` varchar(50) DEFAULT NULL,
                                         `description` varchar(200) NOT NULL,
                                         `type` varchar(20) NOT NULL,
                                         `script` varchar(1000) NOT NULL,
                                         `checksum` int DEFAULT NULL,
                                         `installed_by` varchar(100) NOT NULL,
                                         `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                         `execution_time` int NOT NULL,
                                         `success` tinyint(1) NOT NULL,
                                         PRIMARY KEY (`installed_rank`),
                                         KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `imagedb`
--

DROP TABLE IF EXISTS `imagedb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `imagedb` (
                           `image_id` varchar(255) NOT NULL,
                           `created_at` datetime(6) DEFAULT NULL,
                           `data` longblob,
                           `file_type` varchar(255) DEFAULT NULL,
                           `filename` varchar(255) DEFAULT NULL,
                           `product_id` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`image_id`),
                           KEY `FKch4orsd8yngws6fbaa2ixuglm` (`product_id`),
                           CONSTRAINT `FKch4orsd8yngws6fbaa2ixuglm` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
                           `product_id` varchar(255) NOT NULL,
                           `category` varchar(255) DEFAULT NULL,
                           `description` varchar(255) DEFAULT NULL,
                           `name` varchar(255) DEFAULT NULL,
                           `price` double DEFAULT NULL,
                           `rating` double DEFAULT NULL,
                           `stock` double DEFAULT NULL,
                           `client_id` varchar(255) DEFAULT NULL,
                           `seller_id` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`product_id`),
                           UNIQUE KEY `UKjmivyxk9rmgysrmsqw15lqr5b` (`name`),
                           KEY `FK3g8nmhhbt7mwbf9r0g5qon8m0` (`client_id`),
                           KEY `FKesd6fy52tk7esoo2gcls4lfe3` (`seller_id`),
                           CONSTRAINT `FK3g8nmhhbt7mwbf9r0g5qon8m0` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`),
                           CONSTRAINT `FKesd6fy52tk7esoo2gcls4lfe3` FOREIGN KEY (`seller_id`) REFERENCES `seller` (`seller_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `seller`
--

DROP TABLE IF EXISTS `seller`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seller` (
                          `seller_id` varchar(255) NOT NULL,
                          `created_at` datetime(6) DEFAULT NULL,
                          `email` varchar(255) NOT NULL,
                          `full_name` varchar(255) DEFAULT NULL,
                          `nickname` varchar(255) NOT NULL,
                          `password` varchar(255) DEFAULT NULL,
                          `role` enum('ADMIN','CLIENT','SELLER') DEFAULT NULL,
                          `updated_at` datetime(6) DEFAULT NULL,
                          `username` varchar(255) NOT NULL,
                          `rating` double DEFAULT NULL,
                          `sales_quantity` int DEFAULT NULL,
                          PRIMARY KEY (`seller_id`),
                          UNIQUE KEY `UKcrgbovyy4gvgsum2yyb3fbfn7` (`email`),
                          UNIQUE KEY `UK6bmdx3eykxvwnlmj32loi8hgp` (`nickname`),
                          UNIQUE KEY `UK3gnjncn8l4no25wl7pyjqrx3p` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-15 13:04:33

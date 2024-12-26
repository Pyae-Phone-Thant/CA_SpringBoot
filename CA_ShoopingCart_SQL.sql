create database ca_shoopingcart;

-- table for product
create table Product
(
product_id int not null auto_increment,
product_name varchar(25) not null,
product_description  varchar(50),
purchase_price double(18,2) not null,
sale_price double(18,2) not null,
product_brand varchar(15) not null,
quantity int not null,
image_url varchar(50),
primary key(product_id)
);

alter table product
add column product_category varchar(15);

alter table product 
modify image_url varchar(400);

alter table product 
add column product_feature varchar(400);

truncate table product;

insert into product 
(image_url,product_brand,product_name,product_description,purchase_price,quantity,sale_price,product_category,product_feature) values
('ASUS VivoBook_1.jpg,ASUS VivoBook_2.jpg,ASUS VivoBook_3.jpg,ASUS VivoBook_4.jpg,ASUS VivoBook_5.jpg,ASUS VivoBook_6.jpg,ASUS VivoBook_7.jpg','ASUS','ASUS VivoBook','ASUS VivoBook Description',1099.99,50,1199.99,'Laptop','Screen Size: 14" (1920 X 1200) Touch;Weight: 1.50kg;Main Memory: 16gb Ddr4;Warranty: 2 Years International;Operating System: Windows 11 Home (64bit);Graphics Processor: Amd Grap;Processor Model: Amd R5 7430u (up To 4.3ghz)'),
('ASUS ZenBook_1.jpg,ASUS ZenBook_2.jpg,ASUS ZenBook_3.jpg,ASUS ZenBook_4.jpg,ASUS ZenBook_5.jpg,ASUS ZenBook_6.jpg,ASUS ZenBook_7.jpg','ASUS','ASUS ZenBook','ASUS ZenBook Description',1499.99,50,1599.99,'Laptop','Screen Size: 14" (1920 X 1200) Touch;Weight: 1.50kg;Main Memory: 16gb Ddr4;Warranty: 2 Years International;Operating System: Windows 11 Home (64bit);Graphics Processor: Amd Grap;Processor Model: Amd R5 7430u (up To 4.3ghz)'),
('Lenovo Yoga_1.jpg,Lenovo Yoga_2.jpg,Lenovo Yoga_3.jpg,Lenovo Yoga_4.jpg,Lenovo Yoga_5.jpg,Lenovo Yoga_6.jpg,Lenovo Yoga_7.jpg','LENOVO','Lenovo Yoga','Lenovo Yoga Desciprion',1499.99,50,1599.99,'Laptop','Screen Size: 14" (1920 X 1200) Touch;Weight: 1.50kg;Main Memory: 16gb Ddr4;Warranty: 2 Years International;Operating System: Windows 11 Home (64bit);Graphics Processor: Amd Grap;Processor Model: Amd R5 7430u (up To 4.3ghz)'),
('Lenovo Legion_1.jpg,Lenovo Legion_2.jpg,Lenovo Legion_3.jpg,Lenovo Legion_4.jpg,Lenovo Legion_5.jpg,Lenovo Legion_6.jpg,Lenovo Legion_7.jpg','LENOVO','Lenovo Legion','Lenovo Legion Description',1499.99,50,1599.99,'Laptop','Screen Size: 14" (1920 X 1200) Touch;Weight: 1.50kg;Main Memory: 16gb Ddr4;Warranty: 2 Years International;Operating System: Windows 11 Home (64bit);Graphics Processor: Amd Grap;Processor Model: Amd R5 7430u (up To 4.3ghz)'),
('MSI Cyborg_1.jpg,MSI Cyborg_2.jpg,MSI Cyborg_3.jpg,MSI Cyborg_4.jpg,MSI Cyborg_5.jpg,MSI Cyborg_6.jpg,MSI Cyborg_7.jpg','MSI','MSI Cyborg','MSI Cyborg Description',1299.99,50,1399.99,'Laptop','Screen Size: 14" (1920 X 1200) Touch;Weight: 1.50kg;Main Memory: 16gb Ddr4;Warranty: 2 Years International;Operating System: Windows 11 Home (64bit);Graphics Processor: Amd Grap;Processor Model: Amd R5 7430u (up To 4.3ghz)'),
('ACER Swift_1.jpg,ACER Swift_2.jpg,ACER Swift_3.jpg,ACER Swift_4.jpg,ACER Swift_5.jpg,ACER Swift_6.jpg,ACER Swift_7.jpg','ACER','ACER Swift','ACER Swift',1299.99,50,1399.99,'Laptop','Screen Size: 14" (1920 X 1200) Touch;Weight: 1.50kg;Main Memory: 16gb Ddr4;Warranty: 2 Years International;Operating System: Windows 11 Home (64bit);Graphics Processor: Amd Grap;Processor Model: Amd R5 7430u (up To 4.3ghz)'),
('ASUS VivoBook_1.jpg,ASUS VivoBook_2.jpg,ASUS VivoBook_3.jpg,ASUS VivoBook_4.jpg,ASUS VivoBook_5.jpg,ASUS VivoBook_6.jpg,ASUS VivoBook_7.jpg','ASUS','ASUS VivoBook','ASUS VivoBook Description',1099.99,50,1199.99,'Laptop','Screen Size: 14" (1920 X 1200) Touch;Weight: 1.50kg;Main Memory: 16gb Ddr4;Warranty: 2 Years International;Operating System: Windows 11 Home (64bit);Graphics Processor: Amd Grap;Processor Model: Amd R5 7430u (up To 4.3ghz)'),
('ASUS ZenBook_1.jpg,ASUS ZenBook_2.jpg,ASUS ZenBook_3.jpg,ASUS ZenBook_4.jpg,ASUS ZenBook_5.jpg,ASUS ZenBook_6.jpg,ASUS ZenBook_7.jpg','ASUS','ASUS ZenBook','ASUS ZenBook Description',1499.99,50,1599.99,'Laptop','Screen Size: 14" (1920 X 1200) Touch;Weight: 1.50kg;Main Memory: 16gb Ddr4;Warranty: 2 Years International;Operating System: Windows 11 Home (64bit);Graphics Processor: Amd Grap;Processor Model: Amd R5 7430u (up To 4.3ghz)'),
('Lenovo Yoga_1.jpg,Lenovo Yoga_2.jpg,Lenovo Yoga_3.jpg,Lenovo Yoga_4.jpg,Lenovo Yoga_5.jpg,Lenovo Yoga_6.jpg,Lenovo Yoga_7.jpg','LENOVO','Lenovo Yoga','Lenovo Yoga Desciprion',1499.99,50,1599.99,'Laptop','Screen Size: 14" (1920 X 1200) Touch;Weight: 1.50kg;Main Memory: 16gb Ddr4;Warranty: 2 Years International;Operating System: Windows 11 Home (64bit);Graphics Processor: Amd Grap;Processor Model: Amd R5 7430u (up To 4.3ghz)'),
('Lenovo Legion_1.jpg,Lenovo Legion_2.jpg,Lenovo Legion_3.jpg,Lenovo Legion_4.jpg,Lenovo Legion_5.jpg,Lenovo Legion_6.jpg,Lenovo Legion_7.jpg','LENOVO','Lenovo Legion','Lenovo Legion Description',1499.99,50,1599.99,'Laptop','Screen Size: 14" (1920 X 1200) Touch;Weight: 1.50kg;Main Memory: 16gb Ddr4;Warranty: 2 Years International;Operating System: Windows 11 Home (64bit);Graphics Processor: Amd Grap;Processor Model: Amd R5 7430u (up To 4.3ghz)'),
('MSI Cyborg_1.jpg,MSI Cyborg_2.jpg,MSI Cyborg_3.jpg,MSI Cyborg_4.jpg,MSI Cyborg_5.jpg,MSI Cyborg_6.jpg,MSI Cyborg_7.jpg','MSI','MSI Cyborg','MSI Cyborg Description',1299.99,50,1399.99,'Laptop','Screen Size: 14" (1920 X 1200) Touch;Weight: 1.50kg;Main Memory: 16gb Ddr4;Warranty: 2 Years International;Operating System: Windows 11 Home (64bit);Graphics Processor: Amd Grap;Processor Model: Amd R5 7430u (up To 4.3ghz)'),
('ACER Swift_1.jpg,ACER Swift_2.jpg,ACER Swift_3.jpg,ACER Swift_4.jpg,ACER Swift_5.jpg,ACER Swift_6.jpg,ACER Swift_7.jpg','ACER','ACER Swift','ACER Swift',1299.99,50,1399.99,'Laptop','Screen Size: 14" (1920 X 1200) Touch;Weight: 1.50kg;Main Memory: 16gb Ddr4;Warranty: 2 Years International;Operating System: Windows 11 Home (64bit);Graphics Processor: Amd Grap;Processor Model: Amd R5 7430u (up To 4.3ghz)');
-- end of product part
-- ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- table for customer
create table customer
(
customer_id int not null auto_increment,
password varchar(25) not null,
name varchar(25) not null,
email varchar(25) not null,
phone_number varchar(25),
address varchar(255) not null,
primary key(customer_id),
constraint cus_idx unique (email)
);
-- end of customer part
-- ------------------------------------------------------------------------------------------------------
-- table for product review
create table productreview
(
id int not null auto_increment,
product_id int not null,
customer_id bigint not null,
review_title varchar(25),
review_text varchar(255),
rating int not null,
created_date datetime,
primary key(id),
foreign key (product_id) references product(product_id),
foreign key (customer_id) references customer(customer_id)
);

truncate table productreview;

-- end of product review part
-- -------------------------------------------------------------------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
--
-- Table structure for table `product_seq`
--

DROP TABLE IF EXISTS `product_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_seq`
--

LOCK TABLES `product_seq` WRITE;
/*!40000 ALTER TABLE `product_seq` DISABLE KEYS */;
INSERT INTO `product_seq` VALUES (1);
/*!40000 ALTER TABLE `product_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spring_session`
--

DROP TABLE IF EXISTS `spring_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spring_session` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint NOT NULL,
  `LAST_ACCESS_TIME` bigint NOT NULL,
  `MAX_INACTIVE_INTERVAL` int NOT NULL,
  `EXPIRY_TIME` bigint NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`),
  UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spring_session`
--

LOCK TABLES `spring_session` WRITE;
/*!40000 ALTER TABLE `spring_session` DISABLE KEYS */;
INSERT INTO `spring_session` VALUES ('edd08a7c-75e2-465a-8098-f6a32af2204c','c3c2cb01-ac5d-4249-9876-d3664cca3183',1728109791417,1728112135357,1800,1728113935357,NULL);
/*!40000 ALTER TABLE `spring_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spring_session_attributes`
--

DROP TABLE IF EXISTS `spring_session_attributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spring_session_attributes` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spring_session_attributes`
--

LOCK TABLES `spring_session_attributes` WRITE;
/*!40000 ALTER TABLE `spring_session_attributes` DISABLE KEYS */;
INSERT INTO `spring_session_attributes` VALUES ('edd08a7c-75e2-465a-8098-f6a32af2204c','userId',_binary '¬\í\0sr\0java.lang.Long;\äÌ#\ß\0J\0valuexr\0java.lang.Number¬\à\0\0xp\0\0\0\0\0\0\0');
/*!40000 ALTER TABLE `spring_session_attributes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-05 15:19:28

DROP TABLE IF EXISTS `carts`;
CREATE TABLE `carts` (
  `cart_id` int NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME(6) NOT NULL,
  `status` varchar(255) NOT NULL,
  `updated_at` DATETIME(6) NOT NULL,
  `customer_customer_id` BIGINT DEFAULT NULL,
  PRIMARY KEY (`cart_id`),
  UNIQUE KEY `unique_customer` (`customer_customer_id`), -- Unique constraint
  FOREIGN KEY (`customer_customer_id`) REFERENCES `customer` (`customer_id`) -- Foreign key constraint
);


DROP TABLE IF EXISTS `cart_items`;
CREATE TABLE `cart_items` (
  `item_id` int NOT NULL,
  `added_at` DATETIME(6) NOT NULL,
  `quantity` int NOT NULL,
  `cart_id` int NOT NULL,
  `product_id` int NOT NULL,
  PRIMARY KEY (`item_id`),
  FOREIGN KEY (`cart_id`) REFERENCES `carts` (`cart_id`) ON DELETE CASCADE,  -- Foreign key referencing carts table
  FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE  -- Foreign key referencing products table
);
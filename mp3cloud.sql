/*
SQLyog Enterprise - MySQL GUI v7.02 
MySQL - 5.0.67-community-nt : Database - mp3cloud
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`mp3cloud` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `mp3cloud`;

/*Table structure for table `rating` */

DROP TABLE IF EXISTS `rating`;

CREATE TABLE `rating` (
  `r_id` int(11) NOT NULL auto_increment,
  `u_id` varchar(10) default NULL,
  `t_id` varchar(10) default NULL,
  `rating` double(11,0) default NULL,
  UNIQUE KEY `r_id` (`r_id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=latin1;

/*Table structure for table `registration` */

DROP TABLE IF EXISTS `registration`;

CREATE TABLE `registration` (
  `uid` varchar(10) NOT NULL,
  `email` varchar(50) default NULL,
  `username` varchar(50) default NULL,
  `password` varchar(50) default NULL,
  `usertype` varchar(50) default NULL,
  PRIMARY KEY  (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `tracks` */

DROP TABLE IF EXISTS `tracks`;

CREATE TABLE `tracks` (
  `Sno` int(10) NOT NULL auto_increment,
  `track_id` varchar(10) NOT NULL,
  `track_name` varchar(100) default NULL,
  `track_artist` varchar(100) default NULL,
  `date` date default NULL,
  `uid` varchar(10) default NULL,
  `genre` varchar(100) default NULL,
  `rate_5` int(10) default '0',
  `rate_4` int(10) default '0',
  `rate_3` int(10) default '0',
  `rate_2` int(10) default '0',
  `rate_1` int(10) default '0',
  `track_dp` varchar(1000) default NULL,
  `track_file` varchar(1000) default NULL,
  PRIMARY KEY  (`Sno`,`track_id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=latin1;

/*Table structure for table `user_followers` */

DROP TABLE IF EXISTS `user_followers`;

CREATE TABLE `user_followers` (
  `uf_id` int(10) NOT NULL auto_increment,
  `user_id` varchar(10) default NULL,
  `follower_id` varchar(10) default NULL,
  PRIMARY KEY  (`uf_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `uid` varchar(10) NOT NULL,
  `name` varchar(50) default NULL,
  `username` varchar(50) default NULL,
  `followers` int(100) default NULL,
  `uploads` int(100) default NULL,
  `user_dp` varchar(500) default NULL,
  PRIMARY KEY  (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;

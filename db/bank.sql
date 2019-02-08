create database Bank;
use Bank;

create table if not exists `bankAccount`(
  `userId` bigint auto_increment primary key,
  `account` varchar(20)  unique,
  `password` varchar(20),
  `balance` double(16,2)
)ENGINE = InnoDB DEFAULT CHARSET =utf8;
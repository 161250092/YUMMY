drop database Yummy;
create database Yummy;
use  Yummy;


create table if not exists `merchantInfo`(
`idCode` varchar(7) primary key,
`bankAccount` varchar(20),
`restaurantName` varchar(20),
`restaurantType` varchar(20),
`phone` varchar(20),
`minDeliveryCost` double(16,2),
`deliveryCost` double(16,2)

)ENGINE = InnoDB DEFAULT CHARSET =utf8;

-- 商家账号
create table if not exists `merchant`(
`merchantId` bigint auto_increment primary key,
`idCode`  varchar(7),
`password` varchar(20)
) ENGINE = InnoDB DEFAULT CHARSET =utf8;
--
-- 地址
create table if not exists `location`(
`locationId` bigint auto_increment primary key,
`account` varchar(20),
`lat` double,
`lng` double,
`address` varchar(100)
)ENGINE = InnoDB DEFAULT CHARSET =utf8;



-- 菜品表
create table if not exists `dish`(
`dishId` bigint auto_increment primary key,
`idCode` varchar(7),
`startTime` date,
`endTime` date,
`dishType` varchar(30),
`dishName` varchar(50),
`price` double(16,2),
`quantity` int,
`description` varchar(100)
)ENGINE = InnoDB DEFAULT CHARSET =utf8;


-- discount
create table if not exists `discount`(
`discountId` bigint auto_increment primary key,
`idCode` varchar(7),
`totalPrice` double,
`reducePrice` double
)ENGINE = InnoDB DEFAULT CHARSET =utf8;


 -- member
create table if not exists `member`(
`userId` bigint auto_increment primary key,
`account` varchar(20),
`password` varchar(20),
`nickName` varchar(20),
`phone`  varchar(20),
`mail` varchar(50),
`memberLevel` int
)ENGINE = InnoDB DEFAULT CHARSET =utf8;


-- manager
create table if not exists `manager`(
`managerId` bigint auto_increment primary key,
`account`  varchar(7),
`password` varchar(20)
)ENGINE = InnoDB DEFAULT CHARSET =utf8;


-- application
create table if not exists `application`(
`applicationId` bigint auto_increment primary key,
`isRead` boolean,
`isApproved` boolean,


`idCode` varchar(7) ,
`bankAccount` varchar(20),
`restaurantName` varchar(20),
`restaurantType` varchar(20),
`phone` varchar(20),

`address`  varchar(100),
`lat`   double,
`lng`  double,

`minDeliveryCost` double(16,2),
`deliveryCost` double(16,2)
)ENGINE = InnoDB DEFAULT CHARSET =utf8;


-- Order
create table if not exists `order_tb`(
`orderId` bigint auto_increment primary key,
`account` varchar(20),
`idCode`  varchar(7),
`userLocation` bigint,
`orderAcceptedTime`  datetime,
`expectedArriveTime`  datetime,
`totalPrice` double,
`isPayed`  boolean,
`isReceived`  boolean,
`isAbolished` boolean
)ENGINE = InnoDB DEFAULT CHARSET =utf8;


-- dish in order
create table if not exists `dishInOrder`(
`orderId` bigint,
`dishId` bigint,
`selectQuantity` int
)ENGINE = InnoDB DEFAULT CHARSET =utf8;


-- 注销的账号
create table if not exists `deletedMember`(
`userId` bigint primary key,
`account` varchar(20),
`password` varchar(20),
`nickName` varchar(20),
`phone`  varchar(20),
`mail` varchar(50),
`memberLevel` int
)ENGINE = InnoDB DEFAULT CHARSET =utf8;

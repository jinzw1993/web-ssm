DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `telephone` varchar(15) NOT NULL,
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '状态(0: 正常, 1: 黑名单, 2: 删除)',
  `password` varchar(256) NOT NULL COMMENT '不得存储明文密码, 应加盐并hash后存储',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `customer` VALUES ('1', '赵大宝', '18182428888', '0', '123');

DROP TABLE IF EXISTS `owner`;
CREATE TABLE `owner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `password` varchar(256) NOT NULL COMMENT '不得存储明文密码, 应加盐并hash后存储',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '状态(0: 正常, 1: 黑名单, 2: 删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into  owner VALUES ('1','owner1','123','0');

DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `contact` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telephone` varchar(15) NOT NULL,
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '状态(0: 正常, 1: 黑名单, 2: 删除)',
  PRIMARY KEY (`id`),
  KEY `idx_owner_id` (`owner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO shop VALUES ('1','1','shop1','contact','111122121@qq.com','18182421111','0');

DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO brand VALUES ('1','apple');

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO category VALUES ('1','phone');

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) NOT NULL,
  `shop_id` int(11) NOT NULL,
  `brand_id` int(11) NOT NULL,
  `product_photo_id` int(11) NOT NULL COMMENT '列表页展示的商品图片 id',
  `name` varchar(50) NOT NULL,
  `price` int(11) NOT NULL COMMENT '单位: 分',
  `category_id` int(11) NOT NULL,
  `detail` varchar(10000) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_owner_id` (`owner_id`),
  KEY `idx_shop_id` (`shop_id`),
  KEY `idx_brand_id` (`brand_id`),
  KEY `idx_product_photo_id` (`product_photo_id`),
  KEY `idx_category_id` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT into product VALUES ('1','1','1','1','1','iphone','123456','1','detail','2016-07-15 23:38:56','2016-07-15 23:38:56');

DROP TABLE IF EXISTS `product_photo`;
CREATE TABLE `product_photo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `path` varchar(256) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT into product_photo VALUES ('1','1','http://ohcabv7e3.bkt.clouddn.com/iPhone.png');
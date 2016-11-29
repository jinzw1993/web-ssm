DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `telephone` varchar(15) NOT NULL,
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '状态(0: 正常, 1: 黑名单, 2: 删除)',
  `password` varchar(256) NOT NULL COMMENT '不得存储明文密码, 应加盐并hash后存储',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `customer_address`;
CREATE TABLE `customer_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `telephone` varchar(15) NOT NULL,
  `address` varchar(100) NOT NULL,
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '状态(0: 正常, 1: 黑名单, 2: 删除)',
  PRIMARY KEY (`id`),
  KEY `idx_customer_id` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `owner`;
CREATE TABLE `owner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `password` varchar(256) NOT NULL COMMENT '不得存储明文密码, 应加盐并hash后存储',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '状态(0: 正常, 1: 黑名单, 2: 删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


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


DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `password` varchar(256) NOT NULL COMMENT '不得存储明文密码, 应加盐并hash后存储',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `express`;
CREATE TABLE `express` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `price` int(11) NOT NULL COMMENT '单位: 分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


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


DROP TABLE IF EXISTS `product_comment`;
CREATE TABLE `product_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `comment` varchar(1000) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_customer_id` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `product_photo`;
CREATE TABLE `product_photo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `path` varchar(256) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  `shop_id` int(11) NOT NULL,
  `owner_id` int(11) NOT NULL,
  `express_id` int(11) NOT NULL,
  `price` int(11) NOT NULL COMMENT '单位: 分',
  `amount` int(11) NOT NULL,
  `commission` int(11) NOT NULL COMMENT '佣金提成',
  `commission_rate` int(11) NOT NULL COMMENT '佣金比例(%)',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '状态(0: 未付款, 1: 已付款, 2: 取消, 3: 删除)',
  `process_status` int(11) NOT NULL COMMENT '处理状态(1: 处理订单, 2: 准备运输, 3: 运输中, 4: 完成)',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_customer_id` (`customer_id`),
  KEY `idx_shop_id` (`shop_id`),
  KEY `idx_owner_id` (`owner_id`),
  KEY `idx_express_id` (`express_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `product_in_order`;
CREATE TABLE `product_in_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `price` int(11) NOT NULL COMMENT '单位: 分',
  `amount` int(11) NOT NULL,
  `shop_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_shop_id` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_customer_id` (`customer_id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `product_in_cart`;
CREATE TABLE `product_in_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `cart_id` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_cart_id` (`cart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `favourite_product`;
CREATE TABLE `favourite_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_customer_id` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `favourite_shop`;
CREATE TABLE `favourite_shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_shop_id` (`shop_id`),
  KEY `idx_customer_id` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `product_advertisement`;
CREATE TABLE `product_advertisement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '状态(0: 正常, 1: 黑名单, 2: 删除)',
  `rank` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `shop_advertisement`;
CREATE TABLE `shop_advertisement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '状态(0: 正常, 1: 黑名单, 2: 删除)',
  `rank` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_shop_id` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `mall_income`;
CREATE TABLE `mall_income` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `income` int(11) NOT NULL COMMENT '单位: 分',
  `year` int(11) NOT NULL COMMENT 'YYYY-00-00 表示 YYYY 年总报表',
  `month` int(11) NOT NULL COMMENT 'YYYY-MM-00 表示 YYYY 年 MM 月总报表',
  `day` int(11) NOT NULL,
  `week` int(11) NOT NULL COMMENT 'YYYY-WW 表示 YYYY 年第 WW 周 总报表',
  PRIMARY KEY (`id`),
  KEY `idx_year_month_day` (`year`, `month`, `day`) COMMENT '复合索引',
  KEY `idx_year_week` (`year`, `week`) COMMENT '复合索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `shop_income`;
CREATE TABLE `shop_income` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) NOT NULL,
  `income` int(11) NOT NULL COMMENT '单位: 分',
  `year` int(11) NOT NULL COMMENT 'YYYY-00-00 表示 YYYY 年总报表',
  `month` int(11) NOT NULL COMMENT 'YYYY-MM-00 表示 YYYY 年 MM 月总报表',
  `day` int(11) NOT NULL,
  `week` int(11) NOT NULL COMMENT 'YYYY-WW 表示 YYYY 年第 WW 周 总报表',
  PRIMARY KEY (`id`),
  KEY `idx_shop_id` (`shop_id`),
  KEY `idx_year_month_day` (`year`, `month`, `day`) COMMENT '复合索引',
  KEY `idx_year_week` (`year`, `week`) COMMENT '复合索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `mall_config`;
CREATE TABLE `mall_config` (
  `key` varchar(256) NOT NULL COMMENT '参数与对应的值(如 commission_rate=2; advertisement_rate_daily=500)',
  `value` varchar(1024) NOT NULL,
  PRIMARY KEY (`key`(25))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
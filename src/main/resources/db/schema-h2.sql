DROP TABLE IF EXISTS `stock_number`;
CREATE TABLE `stock_number`
(
    id VARCHAR(100) NOT NULL ,
    model VARCHAR(100),
    sku_number VARCHAR(100),
    color VARCHAR(500),
    capacity VARCHAR(500),
    description VARCHAR(500),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS `item_detail`;
CREATE TABLE `item_detail`
(
    id VARCHAR(100) NOT NULL ,
    batch_no VARCHAR(100),
    product_no VARCHAR(100),
    imei VARCHAR(100),
    sn VARCHAR(100),
    order_no VARCHAR(100),
    cartoon_no VARCHAR(100),
    box_total VARCHAR(100),
    model VARCHAR(100),
    color VARCHAR(100),
    capacity VARCHAR(100),
    create_date date,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS `conf`;
CREATE TABLE `conf`
(
    id VARCHAR(100) NOT NULL ,
    item VARCHAR(100),
    value VARCHAR(100),
    PRIMARY KEY (id)
);
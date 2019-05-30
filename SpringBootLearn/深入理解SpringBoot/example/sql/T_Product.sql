/***产品信息表***/
create table T_Product(
	id						int(12)					not null auto_increment comment '编号',
	product_name	varchar(60) 		not null comment '产品名称',
	stock					decimal(16, 2)	not null comment '库存',
	price					decimal(16, 2) 	not null comment '单价',
	version				int(10)					not null default 0 comment '版本号',
	note 					varchar(256) 		comment	'备注',
	primary	key(id)
) engine=InnoDB charset=utf-8;
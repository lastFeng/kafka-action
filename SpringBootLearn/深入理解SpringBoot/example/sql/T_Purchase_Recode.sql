/***购买信息表***/
create table T_Purchase_Recode(
	id							int(12) 				not null auto_increment comment '编号',
	user_id					int(12)					not null comment '用户编号',
	product_id			int(12)					not null comment '产品编号',
	price						decimal(16, 2)	not null comment '价格',
	quantity				int(12)					not null comment '数量',
	sum							decimal(16, 2)  not null comment '总价',
	purchase_date		timestamp				not null default now() comment '购买日期',
	note						varchar(256)		comment '备注',
	primary key(id)
)engine=InnDB charset=utf-8;
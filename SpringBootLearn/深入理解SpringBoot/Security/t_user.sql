/***用户表***/
create table t_user(
	id				int(12) not null auto_increment,
	user_name	varchar(60) not null,
	pwd				varchar(100) not null,
	/**是否可用，1表示可用，0表示不可用**/
	available	INT(1) default 1 check(available in(0,1)),
	note 			varchar(256),
	primary key(id),
	unique(user_name)
);
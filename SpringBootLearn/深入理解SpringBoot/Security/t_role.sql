/***½ÇÉ«±í***/
create table t_role(
	id				int(12) not null auto_increment,
	role_name	varchar(60) not null,
	note 			varchar(256),
	primary key(id)
);
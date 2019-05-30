create table t_user(
	id int(12) auto_increment,
	user_name varchar(60) not null,
	note varchar(512),
	primary key(id)
)engine=InnDB;
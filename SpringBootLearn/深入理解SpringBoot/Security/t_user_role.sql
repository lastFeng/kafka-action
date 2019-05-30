/**用户角色表**/
create table t_user_role(
	id			int(12) not null auto_increment,
	role_id	int(12) not null,
	user_id	int(12) not null,
	primary key(id),
	unique(role_id, user_id)
);
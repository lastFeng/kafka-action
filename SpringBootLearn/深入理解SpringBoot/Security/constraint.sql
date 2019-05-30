/**Íâ¼üÔ¼Êø**/
alter table t_user_role add constraint FK_Reference_1 foreign key(role_id) references t_role (id) on delete restrict on update restrict;
alter table t_user_role add constraint FK_Reference_2 foreign key(user_id) references t_user (id) on delete restrict on update restrict;
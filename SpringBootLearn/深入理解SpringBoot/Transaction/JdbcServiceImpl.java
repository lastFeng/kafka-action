package com.springboot.chapter6.service.impl;

@Service
public class JdbcServiceImpl implements JdbcService{
		@Autowired
		private DataSource dataSource = null;
		
		@Override
		public int inserUser(String userName, String note){
			Connection conn = null;
			int result = 0;
			
			try{
					// 获取连接
					conn = dataSource.getConnection();
					// 开启事务
					conn.setAutoCommit(false);
					// 设置隔离
					conn.setTransactionIsolation(TransactionIsolationLevel.READ_COMMITTED.getLevel());
					// 执行SQL
					PreparedStatement ps = conn.preparedStatement("insert into t_user(user_name, note) values (?, ?)");
					
					// 设置SQL参数, 下标从1开始计算
					ps.setString(1, userName);
					ps.setString(2, note);
					result = ps.executeUpdate();
					
					// 提交事务
					conn.commit();
				} catch(Exception e){
						// 回滚事务
						if(conn != null){
							try{
								conn.rollback();
							} catch(SQLException el){
									el.printStackTrace();
								}
						}
						e.printStackTrace();
					} finally{
						try{
							if(conn != null && !conn.isClosed()){
								conn.close();
							}
						} catch(SQLException e){
							e.printStackTrace();
						}
					}
				return result;
		}		
}
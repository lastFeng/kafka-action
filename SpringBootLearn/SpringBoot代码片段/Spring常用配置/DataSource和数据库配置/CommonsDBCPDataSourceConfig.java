// Commons DBCP≈‰÷√DataSource ˝æ›‘¥

public class CommonsDBCPDataSourceConfig{
	@Bean(destroyMethod="close")
	public DataSource dataSource(){
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:h2:tcp://dbserver/~/test");
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUserName("sa");
		dataSource.setPassword("password");
		dataSource.setInitialSize(20);
		dataSource.setMaxActive(30);
		
		return dataSource;
	}
}
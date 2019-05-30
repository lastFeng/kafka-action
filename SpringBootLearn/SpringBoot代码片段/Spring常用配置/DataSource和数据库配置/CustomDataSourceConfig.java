public CustomDataSourceConfig{
	@Bean 
	public JdbcTemplate jdbcTemplate(DataSource dataSource){
			return new JdbcTemplate(dataSource);
	}
	
	@Bean 
	public DataSource dataSource(){
		return new EmbeddedDatabaseBuilder()
						// 嵌入式H2数据库，作为数据源
						.setType(EmbeddedDatabaseType.H2)
						.addScripts('schema.sql', 'data.sql')
						.build();
	}
}
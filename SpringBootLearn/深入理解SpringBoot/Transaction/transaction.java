package org.springframework.transaction.annotation;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Ingerited
@Documentd
public @interface Transactional{
	// 通过bean name指定事务管理器
	@AliasFor("transcationManager")
	String value() default "";
	
	// 同value属性
	@AliasFor("value")
	String transactionManager() default "";
	
	// 指定传播行为
	Propagation propagation() default Propagation.REQUIRED;
	
	// 指定隔离级别
	Isolation isolation() default Isolation.DEFAULT;
	
	// 指定超时时间（单位秒）
	int timeout() default TransactionDefinition.TIMEOUT_DEFAULT;
	
	// 是否只读事务
	boolean readOnly() default fasle;
	
	// 方法在发生指定异常名称时回滚，默认是所有异常都回滚
	String[] rollbackForClassName() default {};
	
	// 方法在发生指定异常类时不回滚，默认是所有异常都回滚
	Class<? extends Throwable>[] noRollbackFor() default {};
	
	// 方法在发生指定异常名称时不回滚，默认是所有异常都回滚
	String[] noRollbackForClassName() default {};
}
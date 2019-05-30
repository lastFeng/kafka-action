// Spring的事件需要遵循的流程为：
/**
*	1. 自定义事件，继承ApplicationEvent
* 2. 定义事件监听器，实现ApplicationListener
* 3. 使用容器发布事件
*/

// 1. 自定义事件
class DemoEvent extends ApplicationEvent{
	private static final long serialVersionUID = 1L;
	private String msg;
	
	public DemoEvent(Object source, String msg){
		super(source);
		this.msg = msg;
	}
	
	public String getMsg(){
		return this.msg;
	}
	
	public void setMsg(String msg){
		this.msg = msg;
	}
}

// 2. 自定义事件监听器
@Component
class DemoListener implements ApplicationListener<DemoEvent>{
	@Override
	public void onApplicationEvent(DemoEvent event){
		String msg = event.getMsg();
		System.out.println("收到消息：" + msg);
	}
}

// 3. 发布事件

@Component
public class DemoPublisher{
	@Autowired
	private ApplicationContext applicatinoContext;
	
	public void public(String msg){
		applicationContext.publishEvent(new DemoEvent(this, msg));
	}
}
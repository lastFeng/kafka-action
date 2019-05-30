// 计划任务可以和异步任务进行结合

// 1. 利用@EnableScheduling开启定时任务
// 2. 通过@Scheduled声明方法的定时任务，可以使用（cron、fixDelay、fixRate等来定义具体定时）

// 开启定时任务配置
@Configuration
@EnableScheduling
public class TaskSchedulingConfig{

}

// 2. 在方法中使用@Scheduled来声明定时任务
@Service
class ScheduledTaskService{
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime(){
		System.out.println("每隔五秒执行一次 " + dateFormat.format(new Date()));
	}
	
	@Scheduled(cron="0 28 11 ? * *")
	public void fixTimeExecution(){
		System.out.println("在指定时间 " + dateForm.format(new Date()) + "执行");
	}
}
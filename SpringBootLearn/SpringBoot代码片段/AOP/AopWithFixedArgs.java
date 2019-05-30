// // 处理通知中的参数！！！

@Aspect
public class AopWithFixedArgs{
	private Map<Integer, Integer> traceCounts = new HashMap<>();
	
	@Pointcut("execution(* xxx.xxx.xxx.xxx(int)) && args(trackNumber)")
	public void trackPlayed(int trackNumber){}
	
	@Before("trackPlayed(trackNumber)")
	public void countTrack(int trackNumber){
		int currentCount = getPlayCount(trackNumber);
		trackCounts.put(trackNumber, trackNumber+1);
	}
	
	public int getPlayConut(int trackNumber){
		return trackCounts.containsKey(trackNumber) ? trackCounts.get(trackNumber) : 0;
	}
}
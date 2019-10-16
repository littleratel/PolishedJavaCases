package kit3rd.quartz;

import org.quartz.SchedulerException;

public class Test {

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws SchedulerException {
		MyScheduler scheduler = new MyScheduler();
		scheduler.schedulerJob();
	}
}

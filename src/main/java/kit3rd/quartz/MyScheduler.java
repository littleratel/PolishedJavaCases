package kit3rd.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class MyScheduler {

	// 创建调度器
	public static Scheduler getScheduler() throws SchedulerException {
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		return schedulerFactory.getScheduler();
	}

	public static void schedulerJob() throws SchedulerException {
		// 创建任务
		JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1").build();
		// 创建触发器 每3秒钟执行一次
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever()).build();
		Scheduler scheduler = getScheduler();
		// 将任务及其触发器放入调度器
		scheduler.scheduleJob(jobDetail, trigger);
		// 调度器开始调度任务
		scheduler.start();

	}
}

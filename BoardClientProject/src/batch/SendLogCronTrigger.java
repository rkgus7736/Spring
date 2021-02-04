package batch;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class SendLogCronTrigger {
	private String timer; //크론식
	private Class<?extends Job> job; //job을 상속받는 class만 데려오겠다
	private Scheduler scheduler;
	
	public SendLogCronTrigger(String timer, Class<? extends Job> job) {
		super();
		this.timer = timer;
		this.job = job;
	}
	
	// 스케쥴러에 등록
	public void triggerJob() {
		SchedulerFactory factory;
		//실제로 일 할 Job 생성
		JobDetail jobDetail = JobBuilder.newJob(job).withIdentity(job.getName(), "group").build();
		//CronTrigger 생성
		CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger", "group").
				withSchedule(CronScheduleBuilder.cronSchedule(timer)).build();
		//scheduler가 일을 시키는 애
		factory = new StdSchedulerFactory();
		try {
			scheduler = factory.getScheduler();
			scheduler.start();
			//JobDetail, CronTrigger를 스케쥴러에 등록 (일을 할 두가지를 지정해주면 됨)
			scheduler.scheduleJob(jobDetail, cronTrigger);
			
			
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
}

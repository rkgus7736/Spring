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

public class CronTriggerCreate {
	private String timer;
	private Class<? extends Job> job;
	Scheduler scheduler;
	public CronTriggerCreate(String timer, Class<? extends Job> job) {
		this.timer = timer;
		this.job = job;
	}

	public void triggerJob() {
		SchedulerFactory factory;
		
		try {
			//Job 생성
			JobDetail job1 = JobBuilder.newJob(job).withIdentity(job.getName(),"group1").build();
			
			//크론을 이용하여 트리거 생성
			CronTrigger cronTrigger = TriggerBuilder.newTrigger().
					withIdentity("tigger", "group1").
					withSchedule(CronScheduleBuilder.cronSchedule(timer)).build();
			
			factory = new StdSchedulerFactory();
			scheduler = factory.getScheduler();
			scheduler.start();			
			
			scheduler.scheduleJob(job1, cronTrigger);
			
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	

}

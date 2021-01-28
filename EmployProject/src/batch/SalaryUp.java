package batch;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.SchedulerException;

/**
 * Application Lifecycle Listener implementation class SalaryUp
 *
 */
@WebListener
public class SalaryUp implements ServletContextListener {
	CronTriggerCreate trigger;
    /**
     * Default constructor. 
     */
    public SalaryUp() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	try {
			trigger.scheduler.clear();
			trigger.scheduler.shutdown();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
//	    CronTriggerCreate trigger = new CronTriggerCreate("0 0 0 1 1 ? *", SalaryQuartz.class);
    	System.out.println("Web Application Start");
	    trigger = new CronTriggerCreate("5 * * * * ?", SalaryQuartz.class);
	    trigger.triggerJob();
    }
	
}

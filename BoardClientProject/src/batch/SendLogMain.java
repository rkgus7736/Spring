package batch;

public class SendLogMain {

	public static void main(String[] args) {
		SendLogCronTrigger trigger = new SendLogCronTrigger("5 * * * * ?", SendLogJob.class);
		trigger.triggerJob();
		
		
	}

}

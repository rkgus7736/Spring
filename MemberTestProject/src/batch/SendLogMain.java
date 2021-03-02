package batch;

public class SendLogMain {

	public static void main(String[] args) {
		SendLogCronTrigger trigger = new SendLogCronTrigger("0 0 13 0/3 * ?", SendLogJob.class);
		trigger.triggerJob();
		
		
	}

}

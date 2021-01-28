package batch;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import dao.EmployeeDAO;

public class SalaryQuartz  implements Job{
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		int count = EmployeeDAO.getInstance().updateSalaryBottom5();
		System.out.println("사원 연봉 증가 완료 "+count + "건");
	}

}

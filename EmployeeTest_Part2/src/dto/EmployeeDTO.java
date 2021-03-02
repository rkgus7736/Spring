package dto;

public class EmployeeDTO {
	private String sabun;
	private String name;
	private String department;
	private int position;
	private int salary;
	public EmployeeDTO(String sabun, String name, String department, int position, int salary) {
		super();
		this.sabun = sabun;
		this.name = name;
		this.department = department;
		this.position = position;
		this.salary = salary;
	}
	public String getSabun() {
		return sabun;
	}
	public void setSabun(String sabun) {
		this.sabun = sabun;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "EmployeeDTO [sabun=" + sabun + ", name=" + name + ", department=" + department + ", position="
				+ position + ", salary=" + salary + "]";
	}
	
}

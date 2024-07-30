package EmpMngt;

public class Employee 
{
	private String empId,name,position;
	private double salary;
	
	public Employee(String empId,String name,String position,double salary)
	{
		this.empId=empId;
		this.name=name;
		this.position=position;
		this.salary=salary;
	}
	public String getEmployeeId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }
    
    @Override
    public String toString() {
        return String.format("%-15s %-20s %-15s %-10.2f", empId, name, position, salary);
    }
}

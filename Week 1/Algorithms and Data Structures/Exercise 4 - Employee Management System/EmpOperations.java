package EmpMngt;

public class EmpOperations {
    private Employee[] employees;
    private int idx;

    public EmpOperations() {
        employees = new Employee[10];
        idx = 0;
    }

    public boolean addEmployee(Employee employee) {
        if (idx >= employees.length) {
            System.out.println("Array is full. Cannot add more employees.");
            return false;
        }
        employees[idx++] = employee;
        return true;
    }

    public Employee searchEmployee(String employeeId) {
        for (int i = 0; i < idx; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                return employees[i];
            }
        }
        return null;
    }

    public void traverseEmployees() {
    	if (idx == 0) {
            System.out.println("No employees to display.");
            return;
        }
    	
    	System.out.printf("%-15s %-20s %-15s %-10s%n", "EmployeeID", "Name", "Position", "Salary");
        System.out.println("-------------------------------------------------------------");
        for (int i = 0; i < idx; i++) {
            System.out.println(employees[i]);
        }
    }

    public boolean deleteEmployee(String employeeId) {
        for (int i = 0; i < idx; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                employees[i] = employees[idx - 1];
                employees[idx - 1] = null;
                idx--;
                return true;
            }
        }
        return false;
    }
}

package EmpMngt;

import java.util.Scanner;

public class EmpMain {

	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		EmpOperations emp=new EmpOperations();
		int ch;
		do 
		{
			System.out.println("Employee:\n 1.Add\n 2.Delete\n 3.Display\n 4.Search\n 5.Exit");
			System.out.print("Enter action: ");
			ch=sc.nextInt();
			sc.nextLine();
			
            switch (ch) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    String id = sc.nextLine();

                    System.out.print("Enter Employee Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Employee Position: ");
                    String position = sc.nextLine();

                    System.out.print("Enter Employee Salary: ");
                    double salary = sc.nextDouble();
                    sc.nextLine(); 

                    Employee employee = new Employee(id, name, position, salary);
                    if (emp.addEmployee(employee)) {
                        System.out.println("Employee added successfully.");
                    } else {
                        System.out.println("Failed to add employee. Array might be full.");
                    }
                    break;

                case 2:
                    System.out.print("Enter Employee ID to search: ");
                    String search = sc.nextLine();
                    sc.nextLine(); 
                    Employee res = emp.searchEmployee(search);
                    if (res != null) {
                        System.out.println("Employee found: " + res);
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;

                case 3:
                    System.out.println("Displaying all employees:");
                    emp.traverseEmployees();
                    break;

                case 4:
                    System.out.print("Enter Employee ID to delete: ");
                    String del = sc.nextLine();
                    sc.nextLine();  

                    if (emp.deleteEmployee(del)) {
                        System.out.println("Employee deleted successfully.");
                    } else {
                        System.out.println("Employee not found. Deletion failed.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting");
                    break;

                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } while (ch != 5);
	sc.close();
	}
}

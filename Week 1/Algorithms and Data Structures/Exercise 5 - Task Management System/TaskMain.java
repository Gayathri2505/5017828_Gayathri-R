package tasks;

import java.util.LinkedList;
import java.util.Scanner;

public class TaskMain 
{	
	public static void main(String[] args) {
        LinkedList<Task> taskList = new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        int ch;

        do {
        	System.out.println("Operations:\n 1.Add\n 2.Search\n 3.Traversal\n 4.Delete\n 5.Exit");
            System.out.print("Operation to perform: ");
            ch = sc.nextInt();
            sc.nextLine(); 

            switch (ch) {
                case 1:
                    System.out.print("Enter Task ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Task Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Task Status: ");
                    String status = sc.nextLine();
                    taskList.add(new Task(id, name, status));
                    System.out.println("Task added successfully.");
                    break;

                case 2:
                    System.out.print("Enter Task ID to search: ");
                    id = sc.nextLine();
                    Task res = taskList.stream().filter(task -> task.getTaskId().equals(id))
                                             .findFirst() .orElse(null);
                    if (res != null) {
                        System.out.println("Task found: " + res);
                    } else {
                        System.out.println("Task with ID " + id + " not found.");
                    }
                    break;

                case 3:
                    System.out.printf("%-10s %-30s %-15s%n", "Task ID", "Task Name", "Status");
                    System.out.println("-----------------------------------------------------------");
                    for (Task task : taskList) {
                        System.out.printf("%-10s %-30s %-15s%n", task.getTaskId(), task.getTaskName(), task.getStatus());
                    }
                    break;

                case 4:
                    System.out.print("Enter Task ID to delete: ");
                    id = sc.nextLine();
                    boolean removed = taskList.removeIf(task -> task.getTaskId().equals(id));
                    if (removed) {
                        System.out.println("Task removed successfully.");
                    } else {
                        System.out.println("Task with ID " + id + " not found.");
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

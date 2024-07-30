package stud;

import java.util.Scanner;

public class MVC_Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Student Grade: ");
        String grade = sc.nextLine();

        Student student = new Student(id, name, grade);

        StudentView view = new StudentView();
        StudentController controller = new StudentController(student, view);

        System.out.println("\nValues:");
        controller.updateView();

        System.out.println("\nUpdating Student Details...");
        
        System.out.print("Enter new Grade to update: ");
        String new_grade = sc.nextLine();
        controller.setStudentGrade(new_grade);

        System.out.println("\nValues after update:");
        controller.updateView();

        sc.close();
    }
}

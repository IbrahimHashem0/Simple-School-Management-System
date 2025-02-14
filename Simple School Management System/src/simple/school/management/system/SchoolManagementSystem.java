package simple.school.management.system;

import java.util.Scanner;

class SchoolManagementSystem 
{
    private static Student[] students = new Student[100];
    private static int studentCount = 0;
    private static String[] courses = {"OOP", "DB", "Network", "Web development", "Montag"};
    private static Scanner scanner = new Scanner(System.in);

    public static void run() 
    {
        while (true) 
        {
            System.out.println("\n1- Add Student\n2- Enroll Course\n3- Remove Course\n4- Show Student Info\n5- Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    enrollInCourse();
                    break;
                case 3:
                    removeCourse();
                    break;
                case 4:
                    showStudentInfo();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    // Add a new student with input validation
    private static void addStudent() 
    {
        System.out.print("Enter student ID (numbers only): ");
        String id = scanner.nextLine();
        if (!id.matches("\\d+")) {
            System.out.println("Invalid ID. Must contain only numbers.");
            return;
        }
        if (findStudentById(id) != null) 
        {
            System.out.println("ID already exists.");
            return;
        }

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        if (!name.matches("[a-zA-Z ]+")) 
        {
            System.out.println("Invalid name. Must contain only letters.");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (password.trim().isEmpty()) 
        {
            System.out.println("Password cannot be empty.");
            return;
        }

        students[studentCount] = new Student(id, name, password);
        studentCount++;
        System.out.println("Student added successfully.");
    }

    // Enroll a student in a course with course verification
    private static void enrollInCourse() 
    {
        Student student = authenticateStudent();
        if (student == null) return;

        System.out.println("Available courses:");
        for (int i = 0; i < courses.length; i++) 
            System.out.println((i + 1) + ". " + courses[i]);
        
        System.out.print("Enter course number to enroll: ");
        int courseNumber = scanner.nextInt();
        scanner.nextLine();

        if (courseNumber >= 1 && courseNumber <= courses.length) 
            student.enrollCourse(courses[courseNumber - 1]);
        else
            System.out.println("Invalid course number.");
    }

    // Remove a course from a student with course verification
    private static void removeCourse() 
    {
        Student student = authenticateStudent();
        if (student == null) return;

        System.out.println("Enrolled courses:");
        String[] enrolledCourses = student.getEnrolledCourses();
        for (int i = 0; i < 5 && enrolledCourses[i] != null; i++) 
            System.out.println((i + 1) + ". " + enrolledCourses[i]);

        System.out.print("Enter course number to remove: ");
        int courseNumber = scanner.nextInt();
        scanner.nextLine();
        if (courseNumber >= 1 && courseNumber <= 5 && enrolledCourses[courseNumber - 1] != null) 
            student.removeCourse(enrolledCourses[courseNumber - 1]);
        else
            System.out.println("Invalid course number or course not found.");
        
    }

    // Show student info
    private static void showStudentInfo() 
    {
        Student student = authenticateStudent();
        if (student != null)
            student.showStudentInfo();
    }

    // Authenticate student by ID and password
    private static Student authenticateStudent() 
    {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Student student = findStudentById(id);
        if (student == null || !student.getPassword().equals(password)) 
        {
            System.out.println("Invalid ID or password.");
            return null;
        }
        return student;
    }

    // Find a student by ID
    private static Student findStudentById(String id) 
    {
        for (int i = 0; i < studentCount; i++) 
        {
            if (students[i].getId().equals(id)) 
            {
                return students[i];
            }
        }
        return null;
    }
}
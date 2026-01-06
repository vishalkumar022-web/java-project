package student;

import java.util.*;
import storage.StudentFileImpl;

public class Student {

    // ---------- DATA MEMBERS ----------
    private static int counter = 1000;

    private int studentId;
    private String name;
    private int age;
    private String course;
    private double cgpa;
    private String contact;
    private String enrollmentDate;

    // ---------- CONSTRUCTOR ----------
    public Student(String name, int age, String course,
                   double cgpa, String contact, String enrollmentDate) {

        this.studentId = counter++;
        this.name = name;
        this.age = age;
        this.course = course;
        this.cgpa = cgpa;
        this.contact = contact;
        this.enrollmentDate = enrollmentDate;
    }

    // ---------- GETTERS ----------
    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCourse() {
        return course;
    }

    public double getCgpa() {
        return cgpa;
    }

    public String getContact() {
        return contact;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    // ---------- SETTERS ----------
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    // ---------- toString ----------
    public String toString() {
        return getStudentId() + "," + getName() + "," + getAge() + "," +
                getCourse() + "," + getCgpa() + "," + getContact() + "," + getEnrollmentDate();
    }

    // ---------- MAIN ----------
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentFileImpl file = new StudentFileImpl();

        while (true) {

            System.out.println("\n===== STUDENT MANAGEMENT =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Update Student");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Age (18-25): ");
                    int age = sc.nextInt();
                    sc.nextLine();

                    if (age < 18 || age > 25) {
                        System.out.println("Invalid Age! Please input correct age between given range ");
                        break;
                    }

                    System.out.print("Course: ");
                    String course = sc.nextLine();

                    System.out.print("CGPA (0-10): ");
                    double cgpa = sc.nextDouble();
                    sc.nextLine();

                    if (cgpa < 0 || cgpa > 10) {
                        System.out.println("Invalid CGPA! Please input correct age between given range ");
                        break;
                    }

                    System.out.print("Contact: ");
                    String contact = sc.nextLine();

                    System.out.print("Enrollment Date: ");
                    String date = sc.nextLine();

                    Student s = new Student(name, age, course, cgpa, contact, date);
                    file.addStudent(s);
                    break;

                case 2:
                    file.viewStudents();
                    break;

                case 3:
                    System.out.print("Enter Student ID: ");
                    file.searchStudent(sc.nextInt());
                    break;

                case 4:
                    System.out.print("Enter Student ID: ");
                    file.deleteStudent(sc.nextInt());
                    break;

                case 5:
                    System.out.print("Enter Student ID: ");
                    file.UpdateStudent(sc.nextInt());
                    break;

                case 6:
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}

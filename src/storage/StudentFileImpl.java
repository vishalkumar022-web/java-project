package storage;
import java.io.*;
import java.util.*;
import student.Student;

interface StudentFileRules {

    void addStudent(Student s);
    void viewStudents();
    void searchStudent(int id);
    void deleteStudent(int id);
    void UpdateStudent(int id);
}


public class StudentFileImpl implements StudentFileRules {

    File file;
    ArrayList<Student> list = new ArrayList<>();   // ✅ ArrayList

    public StudentFileImpl() {

        file = new File("data.txt");

        try {
            file.createNewFile(); // ye file ko create krne ka kaam karta hai .. agr ye file exist na kre to
            loadFromFile();   // ** ye Private loadFromFile() ka call hai :--> file → ArrayList Jab program start ho , aur pehle se file me data pada ho, toh wo data ArrayList me aana chahiye.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------- LOAD ----------
    private void loadFromFile() { // file ke data ko read krke yaha pr ham arraylist me store krte hai

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] d = line.split(",");

                Student s = new Student( // d[0] isliye nahi hai kyuki id constructor me paas  nahi kiya gya hai to automatic assign hoga
                        d[1],
                        Integer.parseInt(d[2]),
                        d[3],
                        Double.parseDouble(d[4]),
                        d[5],
                        d[6]
                );

                list.add(s);
            }

        } catch (Exception e) {
        }
    }

    // ---------- SAVE ----------
    private void saveToFile() {  // ye arraylist ke data ko file me store krne ke liye hota hai..

        try (FileWriter fw = new FileWriter(file)) {

            for (Student s : list) {
                fw.write(s.toString() + "\n");
            }

        } catch (Exception e) {
        }
    }

    // ---------- ADD ----------
    @Override
    public void addStudent(Student s) {

        list.add(s);     // ✅ ArrayList me add
        saveToFile();    // ✅ File me save

        System.out.println("Student Added Successfully!");
    }

    // ---------- VIEW ----------
    @Override
    public void viewStudents() {

        System.out.println("\nID,Name,Age,Course,CGPA,Contact,Date");

        for (Student s : list) {
            System.out.println(s);
        }
    }

    // ---------- SEARCH ----------
    @Override
    public void searchStudent(int id) {

        boolean found = false;

        for (Student s : list) {

            if (s.getStudentId() == id) {
                System.out.println("Student Found:");
                System.out.println(s);
                found = true;
                break;
            }
        }

        if (!found)
            System.out.println("Student Not Found");
    }

    // ---------- DELETE ----------
    @Override
    public void deleteStudent(int id) {

        boolean found = false;

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getStudentId() == id) {
                list.remove(i);
                found = true;
                break;
            }
        }

        saveToFile();

        if (found)
            System.out.println("Student Deleted Successfully");
        else
            System.out.println("Student Not Found");
    }

    // ---------- UPDATE ----------
    @Override
    public void UpdateStudent(int id) {

        boolean found = false;
        Scanner sc = new Scanner(System.in);

        for (Student s : list) {

            if (s.getStudentId() == id) {

                System.out.print("New Name: ");
                s.setName(sc.nextLine());

                System.out.print("New Age: ");
                s.setAge(sc.nextInt());
                sc.nextLine();

                System.out.print("New Course: ");
                s.setCourse(sc.nextLine());

                System.out.print("New CGPA: ");
                s.setCgpa(sc.nextDouble());
                sc.nextLine();

                System.out.print("New Contact: ");
                s.setContact(sc.nextLine());

                System.out.print("New Date: ");
                s.setEnrollmentDate(sc.nextLine());

                found = true;
                break;
            }
        }

        if (found) {
            saveToFile();
            System.out.println("Student Updated Successfully");
        } else {
            System.out.println("Student Not Found");
        }
    }

}

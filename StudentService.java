import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class StudentService
{
    static final String FILE_NAME = "students.txt";
    static Comparator<Student> sortbyid = (i,j) -> i.getId()>j.getId()?1: -1;
            // {
            //     if(i.getId() > j.getId())
            //         return 1;
            //     else
            //         return -1;
            // };
    static Comparator<Student> sortbymarks = (i,j) -> i.getMarks()<j.getMarks()?1: -1;

    static List<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // Register a new student and save it automatically.
    static void RegisterNewStudent()
    {
        try
        {
        System.out.println("Enter Student ID :");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter Student Name :");
        String name;
        while(true) {
            name = sc.nextLine();
            if(name.matches("^[a-zA-Z ]+$"))
                break;
            else
                System.out.println("Invalid Name! Please Enter Letters Only.");
            }

        System.out.println("Enter Student Marks :");
        int marks = sc.nextInt();

        students.add(new Student(id,name,marks));
        SaveStudentsInFile();
        System.out.println("Student Added Successfully.");
        }
        catch(InputMismatchException rns)
        {
            System.out.println("Invalid input! please enter numbers only.");
            sc.nextLine(); //clear wrong input
        }
    }

    // View of all students
    static void ViewAllStudent(){
        if(students.isEmpty())
        {
            System.out.println("No Student Available.");
            return;
        }
        for(Student s : students)
        {
            System.out.println(s);
        }
    }

    // Search certain student
    static void SearchStudent(){
        try {
            System.out.println("Enter ID to search Student : ");
            int id = sc.nextInt();

            students.stream()
                    .filter(s -> s.getId() == id)
                    .findFirst()
                    .ifPresentOrElse(s -> System.out.println(s),
                        () -> System.out.println("Student Not Found")
                        );
        }
        catch(InputMismatchException ss) {
            System.out.println("Invalid input! please enter numbers only.");
            sc.nextLine();
        }

    }

    // Sort students by their ID in ascending order
    static void SortByStudentId() {
        if(students.isEmpty()){
            System.out.println("No Student available");
            return;
        }
        students.stream();
        Collections.sort(students,sortbyid);
        //Main.ViewAllStudents();
        students.forEach(s ->System.out.println(s));
        
    }

    // Sort students by their Marks in descending order
    static void SortByStudentWithHighestMarks() {
        if(students.isEmpty()){
            System.out.println("No Student available");
            return;
        }
        //students.stream();
        List<Student> temp = new ArrayList<>(students);
        Collections.sort(temp,sortbymarks);
        temp.forEach(s ->System.out.println(s));
    }

    // Update student marks and name using the given ID.
    static void UpdateStudentMarks() {
        try {
            System.out.println("Enter student ID to update marks and name:");
            int id = sc.nextInt();
            sc.nextLine();

            boolean found = false;
            for( Student s : students) {

                if(s.getId() == id) {

                    System.out.println("Enter new name:");
                    String newName;
                    while(true) {
                        newName = sc.nextLine();
                        if(newName.matches("^[a-zA-Z ]+$"))
                            break;
                        else
                            System.out.println("Invalid Name! Please Enter Letters Only.");
                    }

                    System.out.println("Enter new marks:");
                    int newMarks = sc.nextInt();

                    s.setName(newName);
                    s.setMarks(newMarks);

                    found = true;
                    break;
                }
            }
            if(found) {
                SaveStudentsInFile();
                System.out.println("Student updated successfully");
            }
            else {
                System.out.println("Student not found");
            }
        }
        catch (InputMismatchException up) {
            System.out.println("Invalid input! please enter numbers only.");
            sc.nextLine();
        }
    }

    // Delete one student at a time using the given ID.
    static void DeleteStudent() {
        try {
            System.out.println("Enter student ID to delete:");
            int id = sc.nextInt();

            boolean removed = students.removeIf(s -> s.getId() == id);

            if(removed) {
                SaveStudentsInFile();
                System.out.println("Student deleted successfully.");
            }
            else
                System.out.println("Student not found.");
        }
        catch (InputMismatchException ds) {
            System.out.println("Invalid input! please enter numbers only.");
            sc.nextLine();
        }
    }
    

    /* Student data will save automatically. When we enter
        student datails to register,updated and delete. */
    static void SaveStudentsInFile() {
        try {
            FileWriter writer = new FileWriter(FILE_NAME);

            for(Student s : students) {
                writer.write(s.getId()+ "\t" + s.getName()+ "\t"+ s.getMarks()+"\n");
            }
            writer.close();
        }
                    // or
        // try(FileWriter writer = new FileWriter(FILE_NAME)) {
        //     for(Student s : students) {
        //         writer.write(s.getId()+ "\t" + s.getName()+ "\t"+ s.getMarks()+"\n");
        //     }
        // }
        
        catch(IOException ssif) {
            System.out.println("Failed to save the file. Please try again.");
        }
    }

    // Loads previous students data automatically from the file when the program starts.
    static void LoadStudentsDataFromFile() {

        students.clear();
        try {
            
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            while((line = reader.readLine()) != null) {
                String[] data = line.split("\t");
                
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                int marks = Integer.parseInt(data[2]);

                students.add(new Student(id,name,marks));
            }
            reader.close();
        }

        catch(IOException l) {
            System.out.println("No previous student records found.");
        }
    }
}
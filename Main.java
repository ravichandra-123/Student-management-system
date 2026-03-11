// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.Comparator;
// import java.util.InputMismatchException;
// import java.util.List;
import java.util.Scanner;

public class Main
{
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        StudentService.LoadStudentsDataFromFile();
        int choice;

        do{
            System.out.println("\n ------Student management------");
            System.out.println("1) Register New Student");
            System.out.println("2) View All Students");
            System.out.println("3) Search Student");
            System.out.println("4) Sort students by their ID in ascending order");
            System.out.println("5) Sort students by their marks in descending order");
            System.out.println("6) Update Student marks and name");
            System.out.println("7) Delete Student");
            System.out.println("8) Exit");

            System.out.print("Enter Your Choice: ");
            choice = sc.nextInt();

            switch(choice)
            {
                case 1:
                StudentService.RegisterNewStudent();
                break;

                case 2:
                StudentService.ViewAllStudent();
                break;

                case 3:
                StudentService.SearchStudent();
                break;

                case 4:
                StudentService.SortByStudentId();
                break;

                case 5:
                StudentService.SortByStudentWithHighestMarks();
                break;

                case 6:
                StudentService.UpdateStudentMarks();
                break;

                case 7:
                StudentService.DeleteStudent();
                break;

                case 8:
                System.out.println("Exiting....");
                break;

                default:
                System.out.println("Invalid Option");
            }
        }while (choice !=8);
    }
}

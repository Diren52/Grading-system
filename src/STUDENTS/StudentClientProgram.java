package STUDENTS;

/*
Title: MR. 
Author: Joseph Sigar
Date: 28/10/2016
File Name: Assignment 2
Package: STUDENT
Unit: ICT 167
StudentID: 32492428 

Purpose: Design a class/es that inputs a Student details including their Assignment mark,
exam mark and weekly tutorial mark, and outputs his/her
respective GRADE which would be calculated from the Overall Mark.
The Grade would have to be in String format where HD represents an overall mark of 80 and above
D for an overall mark of 70 to 79 inclusive, C for a mark of 60 to 69, P for a mark of
50 to 59 and N for anything below 50.

Assumption:
1. The program requires the use of inheritance to simplify the program and testing procedure. Namely the person class, for individuals, Student class for Students and the client class.
2. If one of the class has passed its testing, doesnt require to be tested again when called/inhereted with another class.
3. The program requires the use of exceptions to help in validation rather than writing validation codes.
4. The program requires an addidtional class/es for validation, namely user defined exception classes.
5. The PersonClass would only be concerned with the fullname/first/last name and date of birth of the individual.
6. The client/User enters a four digit number with regards to the year of birth eg. 2016.
7. The age of the student allowed to the program ranges from 5 to 100. 
8. The program requires a method to throw exceptions when validating month to day. For example 31/2/2016 would throw an invalid comment since february cant have 31 days.
9. Similarly the person class can distinguish between leap year or not.
10. The User requires the month and day input to be validated eg. 31/04/2016 would be invalid input.
11. The program does not require a method to valid individual/Student name.
12. The User/Client has impeccable precision is entering student/individual name.
13. Person don't have other priop titles like Dr, Hon, Emp. etc.
14. Person Titles only consist of Mr, Miss, Ms, Mrs.
15. A student could be assigned any student number between 0 and (2^63) - 1
16. Offers the client two chances to input data. otherwise skips that students details if entered wrong.
17. Assume client Knows that an Integer is required for the number of students.
18. Assume that The number of students entered in the beginning of the program is the final number of students the clients requires.
19. If an Error is spotted the user is required to re-enter the whole student details again.
20. Requires the Use of the math library to help in the calculation of the Square root.
21. Users requires the output to be in table format.


 */
import DatingApplication.UnknownOpException;
import java.util.*;
import java.math.*;

public class StudentClientProgram {

    static Scanner kb = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        System.out.println("To Start the program. Enter the number of students: ");
        int studentcount = kb.nextInt();
        Student[] s = new Student[studentcount];
        Student s1 = new Student();
        System.out.println();
        s1.studentInfo();
        do {
            displayMenu();            
            System.out.println("\nEnter choice here:");
            choice = kb.nextInt();
            doChoice(choice, s);
            System.out.println();
        } while (choice != 1);
    }
    
    // Displays to the User the Available choices.
    public static void displayMenu() {
        System.out.println("Enter the corresponding number to perform an Action. E.g 1 to quit the program.");
        System.out.println("1. Quits the program.");
        System.out.println("2. To enter student details and get grade.");
        System.out.println("3. To Display Student information, assessment details, overall mark and grade.");
        System.out.println("4. To get the Average of all students.");
        System.out.println("5. To Display how many student got above or below the Average mark.");
        System.out.println("6. To Display the distribution of grades.");
        System.out.println("7. To Display student information when Student Number is given.");
        System.out.println("8. To Display student information when Student Names is given (both).");
        System.out.println("9. To Display Student Names and Overall mark of the two highest overall mark scored.");
        System.out.println("10. To sort Students according to their student number in Ascending order.");
        System.out.println("11. To sort students According to their last name in Ascending order.");
        System.out.println("12. A work in progress to display the standard deviation.");
    }
    
    // Implements the user's choice.
    public static void doChoice(int choice, Student[] s) {
        switch (choice) {
            case 1:
                System.out.println("You have Choosen to Quit. Good Bye");
                break;
            case 2:
                addStudent(s);
                break;
            case 3:
                displayOutput(s);
                studentEquality(s);
                break;
            case 4:
                displayAverage(s);
                break;
            case 5:
                studentDistribution(s);
                break;
            case 6:
                studentDistribution(s, "Grade Distribution for the student is as Follows: ");
                break;
            case 7:
                System.out.println("\"To Search for a particular Student. Enter Student Numbe. ");
                long snumber = kb.nextLong();
                searchStudent(s, snumber);
                break;
            case 8:
                System.out.println("To Search for a particular Student. Enter Student Firstname and Surname.");
                String fname = kb.next();
                String lname = kb.next();
                searchStudent(s, fname, lname);
                break;
            case 9:
                findHighestOverallMark(s);
                break;
            case 10:
                sortStudentByInsertion(s);
                break;
            case 11:
                sortStudentBySelection(s);
                break;
            case 12:
                calculateStandardDeviation(s);
                break;
            default:
                System.out.println("You have entered an Invalid option.");
                break;
        }
    }

    // Adds new student to the temporary storage
    private static void addStudent(Student[] s) {
        for (int i = 0; i < s.length; i++) {
            s[i] = new Student();
            System.out.println("For Student " + (i + 1));
            try {
                s[i].enterStudentDetails();
                s[i].calculateStudentMark();
            } catch (UnknownOpException e1) {
                System.out.println();
                s[i].handleUnknownOpException(e1);
            } catch (StringIndexOutOfBoundsException e2) {
                s[i].handleStringIndexOutOfBoundsException(e2);
            } catch (NumberFormatException e3) {
                s[i].handleNumberFormatException(e3);
            }catch (InputMismatchException e4){
                s[i].handleInputMisMatchException(e4);
            }
        }
    }

    // Displays the Output of the students that are help in the temporary storage
    private static void displayOutput(Student[] s) {
        Student s1 = new Student();
        s1.getOutputInformation();
        System.out.println();
        System.out.println();
        for (int index = 0; index < s.length; index++) {
            s[index].writeOutput();
            System.out.println();
        }
        System.out.println();
    }
    
    // Calculates and returns the Average of the Students.
    private static double calculateAverage(Student[] s) {
        double sum = 0;
        int count = 0;
        for (int i = 0; i < s.length; i++) {
            sum = sum + s[i].getOverallMark();
            count++;
        }
        return sum / count;
    }
  
    // Displays the Average of the Students
    private static void displayAverage(Student[] s) {
        System.out.println("The Average of the Overall Mark is: " + calculateAverage(s));
    }

    // Displays Distribution of students above or below the Average mark.
    private static void studentDistribution(Student[] s) {
        int count1 = 0;
        int count2 = 0;
        double avg = calculateAverage(s);
        for (int i = 0; i < s.length; i++) {
            if (s[i].getOverallMark() >= avg) {
                count1++;
            } else {
                count2++;
            }
        }
        System.out.println("There are " + count1 + " Students who achieved an overall mark above or equals to the Average " + avg);
        System.out.println("There are " + count2 + " Students who achieved an overall mark below the Average " + avg);
    }

    // Displays the Grade Distribution of the students.
    private static void studentDistribution(Student[] s, String s1) {
        int hd = 0;
        int d = 0;
        int c = 0;
        int p = 0;
        int n = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i].getGrade().equalsIgnoreCase("hd")) {
                hd++;
            } else {
                if (s[i].getGrade().equalsIgnoreCase("d")) {
                    d++;
                } else {
                    if (s[i].getGrade().equalsIgnoreCase("c")) {
                        c++;
                    } else {
                        if (s[i].getGrade().equalsIgnoreCase("p")) {
                            p++;
                        } else {
                            n++;
                        }
                    }
                }
            }
        }
        System.out.println(s1);
        System.out.println("High Dinstinction: " + hd + "\nDDinstinction: " + d + "\nCredit: " + c + "\nPass: " + p + "\nFail: " + n);
    }

    // Searches the Storage for students with student number snumber.  And Displays the student details
    private static void searchStudent(Student[] s, long snumber) {
        int found = -1;
        for (int i = 0; i < s.length; i++) {
            if (s[i].getStudentNumber() == snumber) {
                found = i;
            }
        }
        if (found != -1) {
            s[found].writeOutput();
        } else {
            System.out.println("The Student Number " + snumber + " is either invalid or has not yet been used.");
        }
    }

    // Searchs the storage for students with student name fname lname. And Displays the student details
    private static void searchStudent(Student[] s, String fname, String lname) {
        int found = -1;
        for (int i = 0; i < s.length; i++) {
            if (s[i].getFirstName().equalsIgnoreCase(fname) && s[i].getLastName().equalsIgnoreCase(lname)) {
                found = i;
            }
        }
        if (found != -1) {
            s[found].writeOutput();
        } else {
            System.out.println("The Student: " + fname + " " + lname + " cannot be found in our Database.");
        }

    }

    // Searches the storage for students with the highest and second highest overall marks.
    private static void findHighestOverallMark(Student[] s) {
        int highindex = 0;
        int secondhighindex = 0;

        for (int i = 0; i < s.length; i++) {
            if (s[i].getOverallMark() > s[highindex].getOverallMark()) {
                secondhighindex = highindex;
                highindex = i;
            }
        }
        System.out.println("Students with the Highest and Second Highest Overall MArk are: ");
        System.out.println("Highest: " + s[highindex].getFirstName() + " " + s[highindex].getLastName() + " Overall mark " + s[highindex].getOverallMark());
        System.out.println("Second Highest: " + s[secondhighindex].getFirstName() + " " + s[secondhighindex].getLastName() + " Overall mark " + s[secondhighindex].getOverallMark());
    }

    // Sorts the Storage using the Insertion method. In ascending order of the student number
    private static void sortStudentByInsertion(Student[] s) {
        long temp, tmep1, temp2;
        int j;
        for (int i = 0; i < s.length; i++) {
            temp = s[i].getStudentNumber();
            j = 0;
            while (temp > s[j].getStudentNumber()) {
                j++;
            }
            for (int k = i; k > j; k--) {
                tmep1 = s[k].getStudentNumber();
                temp2 = s[k - 1].getStudentNumber();
                tmep1 = temp2;
            }
            try {
                s[j].setStudentNumber(temp);
            } catch (UnknownOpException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Sorted Database by Student Number in Ascending order.");
        displayOutput(s);
    }

    // sorts the storage using the selection algorithm. in ascending order of the student last name
    private static void sortStudentBySelection(Student[] s) {
        for (int i = 0; i < s.length - 1; i++) {
            int larIndex = i;
            for (int j = i + 1; j < s.length; j++) {
                if (s[j].getLastName().compareToIgnoreCase(s[larIndex].getLastName()) < 0) {
                    larIndex = j;
                }
            }
            String temp = s[i].getLastName();
            String temp1 = s[i].getFirstName();
            s[i].setName(s[larIndex].getFirstName(), s[larIndex].getLastName());
            s[larIndex].setName(temp1, temp);
        }
        System.out.println("Sorted Database by Student Number in Ascending order.");
        displayOutput(s);
    }

    // Calculates and displays the Standard Deviation of the students overall mark.
    private static void calculateStandardDeviation(Student[] s) {
        double sum = 0;
        double sumsquared = 0;
        double sd;
        int count = 0;
        for (int i = 0; i < s.length; i++) {
            sum = sum + s[i].getOverallMark();
            sumsquared = sumsquared + (sum * sum);
            count++;
        }
        double avg = calculateAverage(s);
        sd = Math.sqrt((sumsquared / s.length) - (avg * avg));
        System.out.println("The Standard Deviation of th Overall marks is " + sd);
    }
    
    // Checks whether there are students that have the same names, date of birth and student numbers
    private static void studentEquality(Student[] s){
        int found = -1;
        for(int i = 0; i < s.length; i++){
            for(int j = i; j < i+1; j++){
                if(s[j].isEquals(s[i]))
                    found = 1;
            }
        }
        if(found == 1){
            System.out.println("There are Two or more students with the same names, date of birth and student number");
        }
    }
}

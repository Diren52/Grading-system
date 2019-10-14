package STUDENTS;

import DatingApplication.UnknownOpException;
import java.util.InputMismatchException;

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
4. The program requires an additional class/es for validation, namely user defined exception classes.
5. The PersonClass would only be concerned with the fullname/first/last name and date of birth of the individual.
6. The client/User enters a four digit number with regards to the year of birth eg. 2016.
7. The age of the student allowed to the program ranges from 0 to 116. 
8. The program requires a method to throw exceptions when validating month to day. For example 31/2/2016 would throw an invalid comment since february cant have 31 days.
9. Similarly the person class can distinguish between leap year or not.
10. The User requires the month and day input to be validated eg. 31/04/2016 would be invalid input.
11. The program does not require a method to validate individual/Student name.
12. The User/Client has impeccable precision is entering student/individual name.
13. Person don't have other prior titles like Dr, Hon, Emp. etc.
14. Person Titles only consist of Mr, Miss, Ms, Mrs.
15. A student could be assigned any student number between 0 and (2^63) - 1 inclusive
16. Offers the client two chances to input data. otherwise skips that students details if entered wrong.
17. Assume client Knows that an Integer is required for the number of students.
18. Assume that The number of students entered in the beginning of the program is the final number of students the clients requires.
19. If an Error is spotted the user is required to re-enter the whole student details again.
20. Requires the Use of the math library to help in the calculation of the Square root.
21. Users requires the output to be in table format.

Conditions of Input:
PersonClass:
    INPUT                   EXPECTED OUTPUT
    title = String              title = Mr
    firstName  =  String        Name = Joseph 
    lastName  = String          lastname = Sigar
    dob   = dd/mm/yyyy          dob = 10/3/1995
Student Class
    INPUT                   EXPECTED OUTPUT
    studentNumber = long    StudentNumber = 123456789
    assign1 = double        assign1 = 99.0
    assign2 = double        assign2 = 01.0
    tutmark = double        tutmark = 5.0
    exammark = double       exammark = 56.6
 */

// Extends the PersonClass to Create a Student Class
public class Student extends PersonClass {

    private String grade;
    private long studentnumber;
    private double assign1, assign2, tutmark, exammark, overallmark;
    
    // Deafualt Constructor
    public Student() {
        super();
        studentnumber = 0;
        assign1 = 0;
        assign2 = 0;
        tutmark = 0;
    }

    // Student Constructor Used to initialize variables
    public Student(String initialtitle, String initialfirstname, String initiallastname, String initialdob, long initialstudentnumber, double initialassign_1, double initialassign_2, double initialtut_mark, double initialexammark) {
        // Call to Super class, PersonClass 
        super(initialtitle, initialfirstname, initiallastname, initialdob);
        studentnumber = initialstudentnumber;
        assign1 = initialassign_1;
        assign2 = initialassign_2;
        tutmark = initialtut_mark;
        exammark = initialexammark;
    }
    
    // Getter method for the Student Number
    public long getStudentNumber() {
        return studentnumber;
    }
    
    // Sets the StudentNumber when given a new Student number. Throws exceptions for Validating.
    public void setStudentNumber(long newstudentnumber) throws UnknownOpException, InputMismatchException {
        studentnumber = newstudentnumber;
        // Only allows the Student Number to be 1 or more.
        if (studentnumber < 1) {
            throw new UnknownOpException("Invalid Student Number Entered.");
        }
    }

    // Getter method for the Assignment 1
    public double getAssignment1() {
        return assign1;
    }
    
    // Getter Method for the Second Assignment
    public double getAssignment2() {
        return assign2;
    }

    // Getter method for the Tutorial marks.
    public double getTutorialmark() {
        return tutmark;
    }

    // Set the first Assignment to a value given by the parameter.
    public void setAssignment1(double newassign1) throws UnknownOpException {
        assign1 = newassign1;

        if (assign1 < 0 || assign1 > 100.00) {
            throw new UnknownOpException("Invalid Assignment1 Mark out of 100.");
        }
    }

    // Set the second Assignment to a value given by the parameter.
    public void setAssignment2(double newassign2) throws UnknownOpException {
        assign2 = newassign2;

        if (assign2 < 0 || assign2 > 100) {
            throw new UnknownOpException("Invalid Assignment2 Mark out of 100.");
        }
    }

    // Set the tutorial mark to a value given by the parameter.
    public void setTutorialMark(double newtutorialmark) throws UnknownOpException {
        tutmark = newtutorialmark;

        if (tutmark < 0 || tutmark > 10) {
            throw new UnknownOpException("Invalid Tutorial mark Out of 10");
        }
    }

    // Gets the Exams mark when called upon by the User.
    public double getExamMark() {
        return exammark;
    }

    // Sets the Exam Mark to a specified value given by the parameter.
    public void setExamMark(double newexammark) throws UnknownOpException {
        exammark = newexammark;
        if (exammark < 0 || exammark > 100) {
            throw new UnknownOpException("Invalid Exam Mark out of 100.");
        }
    }
    
    // Allows Multiple details to be entered by the user at once. Allowing easier coding in the CLient side of the program
    public void enterStudentDetails() throws UnknownOpException, NumberFormatException, StringIndexOutOfBoundsException, InputMismatchException{
        System.out.println("Enter Student title.");
        String title = kb.next();
        setTitle(title);
        System.out.println("Enter Student Firstname and Lastname: e.g. John Doe");
        String name1 = kb.next();
        String name2 = kb.next();
        setName(name1, name2);
        System.out.println("Enter date of birth of student. dd/mm/yyyy");
        String dob = kb.next();
        setDob(dob);
        System.out.println("Enter Student Number: ");
        long stnmbr = kb.nextLong();
        setStudentNumber(stnmbr);
        System.out.println("Enter Student Assignment 1 score. out of 100.");
        double assin1 = kb.nextDouble();
        setAssignment1(assin1);
        System.out.println("Enter Student Assignment 2 score. out of 100.");
        double assin2 = kb.nextDouble();
        setAssignment2(assin2);
        System.out.println("Enter Tutorial mark out of 10.");
        double tutmak = kb.nextDouble();
        setTutorialMark(tutmak);
        System.out.println("Enter Exam mark out of 100.");
        double examark = kb.nextDouble();
        setExamMark(examark);
    }

    // Calculates the Overall Mark and Grade of the student.
    public void calculateStudentMark() throws UnknownOpException {
        setOverallMark(assign1, assign2, tutmark, exammark);
        setGrade();
    }

    // Returns the grade, when called upon by the user
    public String getGrade() {
        return grade;
    }
    
    // Returns the overall mark, when called upon by the user.
    public double getOverallMark() {
        return overallmark;
    }

    // Sets and Calculates the Overall mark when given new parameters.
    public void setOverallMark(double newassign1, double newassign2, double newtutorialmark, double newexammark) throws UnknownOpException {
        double assign1perc = calculateWeightPerc(newassign1, 100, 20);
        double assign2perc = calculateWeightPerc(newassign2, 100, 20);
        double tutmarkperc = calculateWeightPerc(newtutorialmark, 10, 10);
        double exammarkperc = calculateWeightPerc(newexammark, 100, 50);
        overallmark = assign1perc + assign2perc + tutmarkperc + exammarkperc;
        if (overallmark < 0 || overallmark > 100) {
            throw new UnknownOpException("Invalid Overall mark. One of the Assessed Components is invalid.");
        }
    }

    // Set the grades upon a successfull calculation of the overall mark.
    public void setGrade() {
        double totalmark = getOverallMark();
        if (totalmark >= 80.0) {
            grade = "HD";
        } else if (totalmark >= 70) {
            grade = "D";
        } else if (totalmark >= 60) {
            grade = "C";
        } else if (totalmark >= 50) {
            grade = "P";
        } else {
            grade = "N";
        }
    }

    // Allows the calculation of the weight that helps in the calculation of the overall mark.
    private double calculateWeightPerc(double mark, int totalmark, int componentweight) {
        return (mark / (double) totalmark) * (double) componentweight;
    }

    // Is used when indicating whether two students have the same student number, same both names and same date of birth. Overrides method from PersonClass
    public boolean isEquals(Student oo) {
        return (super.isEquals(oo) == true && this.studentnumber == oo.studentnumber);
    }

    // Prints the output details. Is to be used in conjunction with the writeOutput() method.
    public void getOutputInformation() {
        System.out.println("Student/s Information: ");
        System.out.format("%-10s%15s%19s%19s%19s%19s%19s%19s%19s%19s%19s", "Student Number", "Title", "Firstname", "Lastname", "Date Of Birth", "Assignment 1", "Assignment 2", "Tutorials", "Exam mark", "Overall Mark", "Grade");
    }

    public void writeOutput() {
        System.out.format("%-10s%15s%19s%19s%19s%19s%19s%19s%19s%19s%19s", studentnumber, getTitle(), getFirstName(), getLastName(), getDob(), assign1, assign2, tutmark, exammark, overallmark, grade);
    }

    // Handling of any User Defined exceptions.
    public void handleUnknownOpException(UnknownOpException e) {
        try {
            System.out.println(e.getMessage());
            System.out.println("Try Again and adhere to the above Error Message.");
            enterStudentDetails();
            calculateStudentMark();
        } catch (UnknownOpException e2) {
            System.out.println(e2.getMessage());
            System.out.println("There was a data entry error at the above mentioned area.");
            System.out.println("Program will now skip this student");            
        }catch(StringIndexOutOfBoundsException e3){
            handleStringIndexOutOfBoundsException(e3);
        }catch(NumberFormatException e4){
            handleNumberFormatException(e4);
        }catch(InputMismatchException e5){
            handleInputMisMatchException(e5);
        }
    }
    
    // Handling of Exception thrown when an index is out of bounds.
    public void handleStringIndexOutOfBoundsException(StringIndexOutOfBoundsException e){
        try{
            System.out.println("The Date entered includes characters that are unrecognizable.");
            System.out.println("Try Again.");
            enterStudentDetails();
            calculateStudentMark();
        }catch(StringIndexOutOfBoundsException e2){
            System.out.println("There was a date entry error for the second time.");
            System.out.println("Program will now skip this student");
        }catch(UnknownOpException e3){
            handleUnknownOpException(e3);
        }catch(NumberFormatException e4){
            handleNumberFormatException(e4);
        }catch(InputMismatchException e5){
            handleInputMisMatchException(e5);
        }
    }
    
    // Handling of exceptions thrown when the date inputs triggers a Number format exception
    public void handleNumberFormatException(NumberFormatException e){
        try{
            System.out.println("The Date entered includes characters that are unrecognizable.");
            System.out.println("Try Again.");
            enterStudentDetails();
            calculateStudentMark();
        }catch(NumberFormatException e2){
            System.out.println("There was a date entry error for the second time.");
            System.out.println("Program will now skip this student");
        }catch(StringIndexOutOfBoundsException e3){
            handleStringIndexOutOfBoundsException(e3);
        }catch(UnknownOpException e4){
            handleUnknownOpException(e4);
        }catch(InputMismatchException e5){
            handleInputMisMatchException(e5);
        }
    }
    
    // Handling of the exceptions thrown when the student number is entered incorrectly.
    public void handleInputMisMatchException(InputMismatchException e){
        try{
            System.out.println("The Student Number entered includes characters that are unrecognizable.");
            System.out.println("Try Again.");
            enterStudentDetails();
            calculateStudentMark();
        }catch(InputMismatchException e2){
            System.out.println("There was a date entry error for the second time.");
            System.out.println("Program will now skip this student");
        }catch(StringIndexOutOfBoundsException e3){
            handleStringIndexOutOfBoundsException(e3);
        }catch(UnknownOpException e4){
            handleUnknownOpException(e4);
        }catch(NumberFormatException e5){
            handleNumberFormatException(e5);
        }
    }
    
    // Displays programmers/Student/Author information.
    @Override
    public void studentInfo() {
        System.out.println("Name: Joseph Sigar.\nCourse: ICT167.\nStudent Number:32492428.\nTutor: Beena Kishore.");
        System.out.println("Tutorial Time: 13:30 to 15:30.\nTutorial day: Thursday.\nMode Of Enrolment: Internal.");
    }
}

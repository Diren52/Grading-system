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
 */
import DatingApplication.UnknownOpException;
import java.util.*;

// Used to input data about an individual, mainly their firstName and date of birth.
public class PersonClass {

    private String firstName, lastName, dob, title;
    private int day, month, year;

    Scanner kb = new Scanner(System.in);

    // Default PersonClass Constructor.
    public PersonClass() {
        title = "No title yet";
        firstName = "No name yet";
        lastName = "No name yet";
        dob = "No date of birth yet";
        day = 0;
        month = 0;
        year = 0;
    }

    // PersonClass Constructor used to initialize the instance variables.
    public PersonClass(String initialtitle, String initialfirstname, String initiallastname, String initialdob) {
        title = initialtitle;
        firstName = initialfirstname;
        lastName = initiallastname;
        dob = initialdob;
    }

    // Methods to set Name of the individual from the client/user.
    public void setName(String newfirstname, String newlastname) {
        firstName = newfirstname;
        lastName = newlastname;
    }

    // Method to set the title of the individual. Also throws exceptions.
    public void setTitle(String newtitle) throws UnknownOpException {
        title = newtitle.trim();
        // Error checking the input using exceptions. limits the input to the specified names.
        if (!newtitle.equalsIgnoreCase("mr") && !newtitle.equalsIgnoreCase("ms") && !newtitle.equalsIgnoreCase("miss") && !newtitle.equalsIgnoreCase("mrs")) {
            throw new UnknownOpException("Invalid input on the title of the person");
        }
    }

    // Set Method used to divide the date format into day, month and year. Throws exception to allow client to recoginze input errors
    public void setDob(String initialdob) throws StringIndexOutOfBoundsException, UnknownOpException, NumberFormatException {
        dob = initialdob.replaceAll("\\s", "");
        int indexoffslash1 = searchString(dob, "/", 0);
        int indexoffslash2 = searchString(dob, "/", indexoffslash1 + 1);
        day = convertString(dob, 0, indexoffslash1);
        month = convertString(dob, indexoffslash1 + 1, indexoffslash2);
        year = convertString(dob, indexoffslash2 + 1, dob.length());
        // The calculation of leap year is also included to help validated against the month of febraury during the leap years.
        if ((year >= 1900 && year <= 2016) && isLeapYear(year) == true && (month >= 1 && month <= 12) && (day >= 1 && day <= 31)) {
            if (month == 2 && (day < 1 || day > 29)) {
                throw new UnknownOpException("Invalid Date for the month February. ");
            } else {
                if ((month == 4 || month == 6 || month == 9 || month == 11) && (day < 1 || day > 30)) {
                    throw new UnknownOpException("Invalid date for this particular month.");
                }
            }
        } else {
            if ((year >= 1900 && year <= 2016) && isLeapYear(year) == false && (month >= 1 && month <= 12) && (day >= 1 && day <= 31)) {
                if (month == 2 && (day < 1 || day > 28)) {
                    throw new UnknownOpException("Invalid Date for the month February. ");
                } else {
                    if ((month == 4 || month == 6 || month == 9 || month == 11) && (day < 1 || day > 30)) {
                        throw new UnknownOpException("Invalid date for this particular month.");
                    }
                }
            } else {
                throw new UnknownOpException("Invalid Date Input. Please Enter DD/MM/YYYY");
            }
        }
    }
    
    // Converts the input into Integers. Note Requires that the integer to be inbetween index1 and index 2.
    private int convertString(String itemtobeconverted, int index1, int index2) {
        Integer dte = new Integer(itemtobeconverted.substring(index1, index2));
        int date = dte;
        return date;
    }

    // Checks whether the input, initial year is a leap year not. Only used incase of validation for the month february.
    private boolean isLeapYear(int initialyear) {
        boolean flag = false;
        // Reqiures the initialyear to be divisible by 4 with no remainder.
        if (initialyear % 4 == 0) {
            // Secondly if year is divisible by 100, then further checking is required. 
            if (initialyear % 100 == 0) {
                // finally if initialyear is divisble by 400 then definetly a leap year.
                if (initialyear % 400 == 0) {
                    flag = true;
                } else {
                    flag = false;
                }
            } else {
                flag = true;
            }
        } else {
            flag = false;
        }
        return flag;
    }

    // Searchs the item to be searched, and returns the index of the first occurence of search item, also includes the starting position incase of there being multiple search items of the same type. 
    private int searchString(String itemtobesearched, String searchitem, int priorSIindex) {
        return itemtobesearched.indexOf(searchitem, priorSIindex);
    }

    // Checks whether two individuals have the same firstName, lastName and Date of birth otherwise returns false if not equal.
    public boolean isEquals(PersonClass oo) {
        return (this.firstName.equalsIgnoreCase(oo.firstName) && this.lastName.equalsIgnoreCase(oo.lastName) && this.dob.equalsIgnoreCase(oo.dob));
    }

    // Getter method for firstName.
    public String getFirstName() {
        return firstName;
    }

    // Getter method for lastName.
    public String getLastName() {
        return lastName;
    }

    // Get method for title of person.
    public String getTitle() {
        return title;
    }

    // Getter method for Date Of Birth
    public String getDob() {
        return dob;
    }

    // Getter method for Date of birth. if client specifies whether they want the day, month or year.
    public int getDob(String date) {
        int dte = 0;
        if (date.equalsIgnoreCase("day")) {
            dte = day;
        } else {
            if (date.equalsIgnoreCase("month")) {
                dte = month;
            } else {
                if (date.equalsIgnoreCase("year")) {
                    dte = year;
                } else {
                    System.out.println("Error: Invalid Input for date.");
                }
            }
        }
        return dte;
    }

    // Displays the result to the user.
    public void writeOutput(String title) {
        System.out.println("Title: " + title);
        System.out.println("Firstname/Given name: " + firstName);
        System.out.println("Lastname/Surname/Family name: " + lastName);
        System.out.println("Date of Birth: " + dob);
    }
    
    // Display's Joseph's Information usually in the beginning of a program.
    public void studentInfo() {
        System.out.println("Name: Joseph Sigar.\nCourse: ICT167.\nStudent Number:32492428.\nTutor: Beena Kishore.");
        System.out.println("Tutorial Time: 13:30 to 15:30.\nTutorial day: Thursday.\nMode Of Enrolment: Internal.");
    }
}

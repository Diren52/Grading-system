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
respective GRADE.
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


Conditions of Input:
PersonClass:
    INPUT                   EXPECTED OUTPUT
    name  =  String             Name = Joseph Sigar
    dob   = dd/mm/yyyy          dob = 10/3/1995

For external Documents
Limitations:
1. Program is unable to valid Student name. eg. an input of rtr90 would be regarded as a student despite the peculiar name.
2. Limits the title from Person class to only Mr, Miss, Ms, Mrs.
*/

public class UnknownOpException1 extends Exception {
    
    public UnknownOpException1(){
        super("UnknownOpException");
    }
    public UnknownOpException1(char op){
        super(op + " is an unknown operator.");
    }
    public UnknownOpException1(String message){
        super(message);
    }
    
}

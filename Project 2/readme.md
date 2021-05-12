# CS 4348 Project 2

## Overview
This project was created in Java to help us get familiar with using Semaphores and coordinating threads.

## Instructions
Complete commmand line used to compile the program:
cd src
javac *.java

Complete command line used to run the program:
java Project2

## Additional details
The program should prompt you to enter the number of doctors and patients

Sample Output showing this (with 3 doctors and 30 patients):

Patient 28 enters waiting room, waits for receptionist  
Patient 10 enters waiting room, waits for receptionist  
Patient 21 enters waiting room, waits for receptionist  
Patient 26 enters waiting room, waits for receptionist  
Patient 19 enters waiting room, waits for receptionist  
Patient 3 enters waiting room, waits for receptionist  
Patient 23 enters waiting room, waits for receptionist  
Patient 20 enters waiting room, waits for receptionist  
Patient 11 enters waiting room, waits for receptionist  
Patient 24 enters waiting room, waits for receptionist  
Patient 12 enters waiting room, waits for receptionist  
Patient 13 enters waiting room, waits for receptionist  
Patient 7 enters waiting room, waits for receptionist  
Receptionist registers patient 28  
Patient 17 enters waiting room, waits for receptionist  
Patient 29 enters waiting room, waits for receptionist  
Patient 18 enters waiting room, waits for receptionist  
Patient 0 enters waiting room, waits for receptionist  
Patient 28 leaves receptionist and sits in waiting room  
Patient 1 enters waiting room, waits for receptionist  
Patient 15 enters waiting room, waits for receptionist  
Patient 8 enters waiting room, waits for receptionist  
Receptionist registers patient 10  
Patient 9 enters waiting room, waits for receptionist  
Patient 10 leaves receptionist and sits in waiting room  
Patient 5 enters waiting room, waits for receptionist  
Patient 27 enters waiting room, waits for receptionist  
Receptionist registers patient 21  
Patient 22 enters waiting room, waits for receptionist  
Patient 6 enters waiting room, waits for receptionist  
Patient 2 enters waiting room, waits for receptionist  
Patient 16 enters waiting room, waits for receptionist  
Patient 14 enters waiting room, waits for receptionist  
Patient 4 enters waiting room, waits for receptionist  
Patient 25 enters waiting room, waits for receptionist  
Patient 21 leaves receptionist and sits in waiting room  
Receptionist registers patient 26  
Patient 26 leaves receptionist and sits in waiting room  
Receptionist registers patient 19  
Patient 19 leaves receptionist and sits in waiting room  
Receptionist registers patient 3  
Patient 3 leaves receptionist and sits in waiting room  
Receptionist registers patient 23  
Patient 23 leaves receptionist and sits in waiting room    
Receptionist registers patient 20  
Patient 20 leaves receptionist and sits in waiting room  
Receptionist registers patient 11  
Patient 11 leaves receptionist and sits in waiting room  
Receptionist registers patient 24  
Patient 24 leaves receptionist and sits in waiting room  
Receptionist registers patient 12  
Patient 12 leaves receptionist and sits in waiting room  
Receptionist registers patient 13  
Patient 13 leaves receptionist and sits in waiting room   
Receptionist registers patient 7  
Patient 7 leaves receptionist and sits in waiting room  
Receptionist registers patient 17  
Patient 17 leaves receptionist and sits in waiting room  
Receptionist registers patient 29  
Patient 29 leaves receptionist and sits in waiting room  
Receptionist registers patient 18  
Patient 18 leaves receptionist and sits in waiting room  
Receptionist registers patient 0  
Patient 0 leaves receptionist and sits in waiting room  
Receptionist registers patient 1  
Patient 1 leaves receptionist and sits in waiting room  
Receptionist registers patient 15  
Patient 15 leaves receptionist and sits in waiting room  
Receptionist registers patient 8  
Patient 8 leaves receptionist and sits in waiting room  
Receptionist registers patient 9  
Patient 9 leaves receptionist and sits in waiting room  
Receptionist registers patient 5  
Patient 5 leaves receptionist and sits in waiting room  
Receptionist registers patient 27  
Patient 27 leaves receptionist and sits in waiting room  
Nurse 2 takes patient 28 to doctor's office  
Receptionist registers patient 22  
Nurse 1 takes patient 10 to doctor's office  
Nurse 0 takes patient 21 to doctor's office  
Patient 22 leaves receptionist and sits in waiting room  
Receptionist registers patient 6  
Patient 10 enters doctor 1's office  
Patient 28 enters doctor 2's office  
Doctor 2 listens to symptoms from patient 28  
Patient 6 leaves receptionist and sits in waiting room  
Doctor 1 listens to symptoms from patient 10  
Doctor 0 listens to symptoms from patient 21  
Patient 21 enters doctor 0's office  
Receptionist registers patient 2  
Patient 28 receives advice from doctor 2  
Patient 10 receives advice from doctor 1  
Patient 21 receives advice from doctor 0  
Patient 2 leaves receptionist and sits in waiting room  
Patient 28 leaves  
Receptionist registers patient 16  
Nurse 2 takes patient 26 to doctor's office  
Patient 10 leaves  
Patient 21 leaves  
Nurse 1 takes patient 19 to doctor's office  
Patient 26 enters doctor 2's office  
Doctor 2 listens to symptoms from patient 26  
Patient 16 leaves receptionist and sits in waiting room  
Patient 26 receives advice from doctor 2  
Patient 19 enters doctor 1's office  
Nurse 0 takes patient 3 to doctor's office  
Doctor 1 listens to symptoms from patient 19  
Receptionist registers patient 14  
Patient 26 leaves  
Patient 14 leaves receptionist and sits in waiting room  
Patient 3 enters doctor 0's office  
Doctor 0 listens to symptoms from patient 3  
Receptionist registers patient 4  
Nurse 2 takes patient 23 to doctor's office  
Patient 19 receives advice from doctor 1  
Doctor 2 listens to symptoms from patient 23  
Patient 4 leaves receptionist and sits in waiting room  
Patient 3 receives advice from doctor 0  
Patient 19 leaves  
Patient 23 enters doctor 2's office  
Patient 3 leaves  
Receptionist registers patient 25  
Nurse 0 takes patient 20 to doctor's office  
Nurse 1 takes patient 12 to doctor's office  
Patient 23 receives advice from doctor 2  
Patient 12 enters doctor 1's office  
Patient 23 leaves  
Doctor 1 listens to symptoms from patient 12  
Nurse 2 takes patient 13 to doctor's office  
Patient 12 receives advice from doctor 1  
Patient 13 enters doctor 2's office  
Patient 20 enters doctor 0's office  
Doctor 0 listens to symptoms from patient 20  
Patient 25 leaves receptionist and sits in waiting room  
Patient 20 receives advice from doctor 0  
Patient 12 leaves  
Doctor 2 listens to symptoms from patient 13  
Nurse 1 takes patient 7 to doctor's office  
Patient 13 receives advice from doctor 2  
Patient 20 leaves  
Patient 7 enters doctor 1's office  
Patient 13 leaves  
Doctor 1 listens to symptoms from patient 7  
Nurse 2 takes patient 0 to doctor's office  
Patient 7 receives advice from doctor 1  
Doctor 2 listens to symptoms from patient 0  
Nurse 0 takes patient 11 to doctor's office  
Patient 7 leaves  
Patient 11 enters doctor 0's office  
Patient 0 enters doctor 2's office  
Nurse 1 takes patient 29 to doctor's office  
Doctor 0 listens to symptoms from patient 11  
Doctor 1 listens to symptoms from patient 29  
Patient 0 receives advice from doctor 2  
Patient 11 receives advice from doctor 0  
Patient 29 enters doctor 1's office  
Patient 11 leaves  
Patient 0 leaves  
Patient 29 receives advice from doctor 1  
Patient 29 leaves  
Nurse 0 takes patient 24 to doctor's office  
Nurse 2 takes patient 27 to doctor's office  
Doctor 0 listens to symptoms from patient 24  
Patient 27 enters doctor 2's office  
Patient 24 enters doctor 0's office  
Nurse 1 takes patient 15 to doctor's office  
Patient 24 receives advice from doctor 0  
Patient 24 leaves  
Doctor 2 listens to symptoms from patient 27  
Patient 15 enters doctor 1's office  
Doctor 1 listens to symptoms from patient 15  
Patient 27 receives advice from doctor 2  
Nurse 0 takes patient 17 to doctor's office  
Patient 27 leaves   
Patient 15 receives advice from doctor 1  
Patient 17 enters doctor 0's office  
Doctor 0 listens to symptoms from patient 17  
Nurse 2 takes patient 22 to doctor's office  
Patient 17 receives advice from doctor 0  
Patient 15 leaves  
Patient 22 enters doctor 2's office  
Doctor 2 listens to symptoms from patient 22  
Nurse 1 takes patient 5 to doctor's office  
Patient 22 receives advice from doctor 2  
Patient 17 leaves  
Doctor 1 listens to symptoms from patient 5  
Patient 5 enters doctor 1's office  
Nurse 0 takes patient 18 to doctor's office  
Patient 22 leaves  
Doctor 0 listens to symptoms from patient 18  
Patient 18 enters doctor 0's office  
Patient 5 receives advice from doctor 1   
Patient 18 receives advice from doctor 0  
Nurse 2 takes patient 14 to doctor's office  
Patient 18 leaves  
Patient 5 leaves  
Doctor 2 listens to symptoms from patient 14  
Nurse 0 takes patient 1 to doctor's office  
Patient 14 enters doctor 2's office  
Doctor 0 listens to symptoms from patient 1  
Nurse 1 takes patient 6 to doctor's office  
Patient 1 enters doctor 0's office  
Patient 14 receives advice from doctor 2  
Patient 14 leaves  
Patient 6 enters doctor 1's office  
Doctor 1 listens to symptoms from patient 6  
Patient 1 receives advice from doctor 0  
Patient 6 receives advice from doctor 1  
Patient 6 leaves  
Patient 1 leaves  
Nurse 1 takes patient 16 to doctor's office  
Nurse 0 takes patient 8 to doctor's office  
Patient 16 enters doctor 1's office  
Patient 8 enters doctor 0's office  
Doctor 1 listens to symptoms from patient 16  
Doctor 0 listens to symptoms from patient 8  
Patient 16 receives advice from doctor 1  
Patient 16 leaves  
Patient 8 receives advice from doctor 0  
Patient 8 leaves  
Nurse 1 takes patient 4 to doctor's office  
Nurse 0 takes patient 9 to doctor's office  
Patient 4 enters doctor 1's office  
Doctor 0 listens to symptoms from patient 9  
Patient 9 enters doctor 0's office  
Patient 9 receives advice from doctor 0  
Patient 9 leaves  
Doctor 1 listens to symptoms from patient 4  
Nurse 0 takes patient 2 to doctor's office  
Patient 4 receives advice from doctor 1  
Patient 2 enters doctor 0's office  
Doctor 0 listens to symptoms from patient 2  
Patient 4 leaves  
Patient 2 receives advice from doctor 0  
Nurse 1 takes patient 25 to doctor's office  
Patient 2 leaves  
Doctor 1 listens to symptoms from patient 25  
Patient 25 enters doctor 1's office  
Patient 25 receives advice from doctor 1  
Patient 25 leaves  

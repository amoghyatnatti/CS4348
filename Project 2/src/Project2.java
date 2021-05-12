/*
    Amogh Yatnatti
    ARY180001
    CS 4348.001
    Project 2
 */
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.Scanner;

public class Project2 {
    // Receptionist related semaphores
    public static Semaphore receptionist = new Semaphore(0, true);
    public static Semaphore mutexReception = new Semaphore(1, true);
    public static Semaphore register = new Semaphore(0, true);
    public static Semaphore patientRdy = new Semaphore(0, true);

    // Semaphores relating to doctors and nurses
    // Array index represents corresponding doctor/nurse
    public static Semaphore nurse[];
    public static Semaphore mutex[];
    public static Semaphore enterOff[];
    public static Semaphore doctor[];
    public static Semaphore advice[];
    public static Semaphore adviceRec[];

    // Stores number of doctor/nurse threads
    public static int docThreads;

    // Stores assigned doctors for every patient
    public static int assignedDoc[];

    // Buffers for nurse/doctor and receptionist
    public static HashMap<Integer, LinkedList<Integer>> docBuffer = new HashMap<Integer, LinkedList<Integer>>();
    public static Queue<Integer> receptionBuffer = new LinkedList<Integer>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Gets the number of doctors from user
        System.out.println("Enter the number of Doctors (Max of 3): ");
        docThreads = in.nextInt();
        while (docThreads > 3 || docThreads <= 0) {
            System.out.println("Invalid number! Enter a valid number: ");
            docThreads = in.nextInt();
        }

        // Gets the number of patients from user
        System.out.println("Enter the number of Patients (Max of 30): ");
        int patThreads = in.nextInt();
        while (patThreads > 30 || patThreads <= 0) {
            System.out.println("Invalid number! Enter a valid number: ");
            patThreads = in.nextInt();
        }

        // Initializes semaphore arrays with number of doctors
        nurse = new Semaphore[docThreads];
        enterOff = new Semaphore[docThreads];
        doctor = new Semaphore[docThreads];
        advice = new Semaphore[docThreads];
        adviceRec = new Semaphore[docThreads];
        mutex = new Semaphore[docThreads];

        // Initializes semaphore values for all arrays
        for (int i = 0; i < docThreads; i++) {
            nurse[i] = new Semaphore(0, true);
            enterOff[i] = new Semaphore(0, true);
            doctor[i] = new Semaphore(0, true);
            advice[i] = new Semaphore(0, true);
            adviceRec[i] = new Semaphore(0, true);
            mutex[i] = new Semaphore(1, true);
            docBuffer.put(i, new LinkedList<Integer>());
        }

        // Records assigned doctor values for all patients
        assignedDoc = new int[patThreads];

        // Initializes and starts receptionist thread
        Thread reception = new Thread(new Receptionist());
        reception.start();

        // Initializes and starts all doctor threads
        Thread doctor_threads[] = new Thread[docThreads];
        Doctor doctors[] = new Doctor[docThreads];
        for (int i = 0; i < docThreads; i++) {
            doctors[i] = new Doctor(i);
            doctor_threads[i] = new Thread(doctors[i]);
            doctor_threads[i].start();

        }

        // Initializes and starts all nurse threads
        Thread nurse_threads[] = new Thread[docThreads];
        Nurse nurses[] = new Nurse[docThreads];
        for (int i = 0; i < docThreads; i++) {
            nurses[i] = new Nurse(i);
            nurse_threads[i] = new Thread(nurses[i]);
            nurse_threads[i].start();

        }

        // Initializes and starts all patient threads
        Thread patient_threads[] = new Thread[patThreads];
        Patient patients[] = new Patient[patThreads];
        for (int i = 0; i < patThreads; i++) {
            patients[i] = new Patient(i);
            patient_threads[i] = new Thread(patients[i]);
            patient_threads[i].start();

        }

        // Joins all patient threads back to main after completion
        for (int i = 0; i < patThreads; i++) {
            try {
                patient_threads[i].join();
            } catch (InterruptedException e) {
                System.out.println("Error");
            }
        }

        // Exits and terminates all threads
        System.exit(0);
    }
}

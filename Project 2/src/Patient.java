/*
    Amogh Yatnatti
    ARY180001
    CS 4348.001
    Patient
 */

public class Patient implements Runnable {
    private int threadID;

    public int getThreadID() {
        return threadID;
    }

    public Patient(int threadID) {
        this.threadID = threadID;
    }

    public void run() {
        // Patient enters waiting room
        System.out.println("Patient " + threadID + " enters waiting room, waits for receptionist");

        // Receptionist registers 1 at a time
        try {
            Project2.mutexReception.acquire();
        } catch (InterruptedException e) {}

        // Adds patient to the receptionist buffer
        Project2.receptionBuffer.add(threadID);

        // Signals a patient in the receptionist room
        Project2.receptionist.release();

        // Waits for receptionist to register patient
        try {
            Project2.register.acquire();
        } catch (InterruptedException e) {}

        // Gets the assigned nurse and doctor ID
        int docID = Project2.assignedDoc[threadID];

        // Leaves and signals receptionist that they have left
        System.out.println("Patient " + threadID + " leaves receptionist and sits in waiting room");
        Project2.patientRdy.release();

        // Signals another patient to visit receptionist now
        Project2.mutexReception.release();

        // Waits for the nurse to guide patient into the office
        try {
            Project2.enterOff[docID].acquire();
        } catch (InterruptedException e) {}
        System.out.println("Patient " + threadID + " enters doctor " + docID + "'s office");

        // Waits for the doctor to give advice
        try {
            Project2.advice[docID].acquire();
        } catch (InterruptedException e) {}
        System.out.println("Patient " + threadID + " receives advice from doctor " + docID);

        // Patient leaves
        System.out.println("Patient " + threadID + " leaves");

        // Patient signals that he is done
        Project2.adviceRec[docID].release();
    }
}

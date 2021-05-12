/*
    Amogh Yatnatti
    ARY180001
    CS 4348.001
    Receptionist
 */
import java.util.Random;

public class Receptionist implements Runnable {
    private Random rand = new Random();

    public void run() {
        while (true) {
            // Waits for patient to enter the waiting room
            try {
                Project2.receptionist.acquire();
            } catch (InterruptedException e) {}

            // Gets patient information from the buffer
            int copy = Project2.receptionBuffer.poll();

            // Assigns patient to a random doctor
            int docID = rand.nextInt(Project2.docThreads);

            // Adds patient to the assigned doctor's buffer
            Project2.docBuffer.get(docID).add(copy);

            // Gives assigned doctor information
            Project2.assignedDoc[copy] = docID;

            // Registers patient
            System.out.println("Receptionist registers patient " + copy);

            // Signals that patient is registered
            Project2.register.release();

            // Waits for patient to leave to the waiting room
            try {
                Project2.patientRdy.acquire();
            } catch (InterruptedException e) {}

            // Signals nurse that patient is ready
            Project2.nurse[docID].release();
        }
    }
}

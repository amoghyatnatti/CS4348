/*
    Amogh Yatnatti
    ARY180001
    CS 4348.001
    Doctor
 */

public class Doctor implements Runnable {
    int threadID;

    public Doctor(int threadID) {
        this.threadID = threadID;
    }

    public void run() {
        while (true) {
            // Waits for nurse to call them
            try {
                Project2.doctor[threadID].acquire();
            } catch (InterruptedException e) {}

            // Looks at patient's information and removes it from buffer
            int copy = Project2.docBuffer.get(threadID).poll();

            // Listens to symptoms
            System.out.println("Doctor " + threadID + " listens to symptoms from patient " + copy);

            // Doctor gives advice to the patient
            Project2.advice[threadID].release();

            // Waits for the patient to receive the advice and leave
            try {
                Project2.adviceRec[threadID].acquire();
            } catch (InterruptedException e) {}

            // Doctor and Nurse are done so another person can continue
            Project2.mutex[threadID].release();
        }
    }
}

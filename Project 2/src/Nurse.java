/*
    Amogh Yatnatti
    ARY180001
    CS 4348.001
    Nurse
 */

public class Nurse implements Runnable {
    int threadID;

    public Nurse(int threadID) {
        this.threadID = threadID;
    }

    public void run() {
        while (true) {
            // Waits for receptionist to call this nurse
            try {
                Project2.nurse[threadID].acquire();
            } catch (InterruptedException e) {}

            // Nurse and corresponding Doctor can only handle one person at a time
            try {
                Project2.mutex[threadID].acquire();
            } catch (InterruptedException e) {}

            // Looks at patient info
            int copy = Project2.docBuffer.get(threadID).peekFirst();

            // Takes patient to the office
            System.out.println("Nurse " + threadID + " takes patient " + copy + " to doctor's office");

            // Signals patient that they have entered the office
            Project2.enterOff[threadID].release();

            // Signals doctor to come
            Project2.doctor[threadID].release();
        }
    }
}
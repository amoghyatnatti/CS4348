/*
 * Amogh Yatnatti
 * ARY180001
 * CS4348.001
 * Program Description: This program is designed to mimic 3 schedulers: FCFS, SPN, HRRN (In that order).
 * Input is provided via "jobs.txt" file
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Project3 {

    static ArrayList<Job> lst;      // Stores list of all jobs
    static ArrayList<Job> waiting;  // Stores jobs that are waiting
    static int time;                // Keeps track of time for each method

    // Stores the characteristics of every job we read in
    static class Job {

        String name;        // Stores name of job
        int arrTime;        // Stores arrival time of job
        int servTime;       // Stores service time of job
        int space;          // Temp variable used to store the spaces in each graph
        boolean completed;  // Temp variable that stores if job is completed or not
        int index;          // Stores index of the job

        // Initializes values
        public Job(String name, int arrTime, int servTime, int index) {
            this.name = name;
            this.arrTime = arrTime;
            this.servTime = servTime;
            this.index = index;
            space = 0;
            completed = false;
        }
    }


    public static void main(String args[]) {

        try {
            Scanner in = new Scanner(new File("jobs.txt"));

            lst = new ArrayList<Job>();
            waiting = new ArrayList<Job>();

            // Traverses file and records each job's characteristics
            int index = 0;
            while (in.hasNextLine()) {
                String name = in.next();
                int arrTime = in.nextInt();
                int servTime = in.nextInt();
                Job temp = new Job(name, arrTime, servTime, index);
                // Adds each job to the list
                lst.add(temp);
                index++;
            }

            // Move time to the first job's arrival
            time = lst.get(0).arrTime;

            // FCFS Section
            // Prints the formatting/chart
            System.out.println("FCFS\n");
            for (int i = 0; i < lst.size(); i++) {

                // Gets job characteristics
                Job job = lst.get(i);

                // Jumps time to the next job's arrival if there is a gap in time
                if (time < job.arrTime) {
                    time = job.arrTime;
                }

                // Prints corresponding job name
                System.out.print(job.name + " ");

                // Adds space for every unit of time
                for (int space = 0; space < time; space++) {
                    System.out.print(" ");
                }
                // Adds job's service time to overall time
                time += job.servTime;

                // Prints an X for every unit of service time
                for (int x = 0; x < job.servTime; x++) {
                    System.out.print("X");
                }
                System.out.println();
            }

            // SPN Section
            // Reset time to the first arrival of job
            time = lst.get(0).arrTime;

            getWaitingJobs();

            while (!waiting.isEmpty()) {
                // Stores shortest service time
                int minServ = Integer.MAX_VALUE;
                // Stores corresponding index
                int minIndex = 0;

                // Traverses all waiting jobs
                for (int i = 0; i < waiting.size(); i++) {
                    Job job = waiting.get(i);

                    // Stores index of shortest job service time
                    if (job.servTime < minServ) {
                        minServ = job.servTime;
                        minIndex = job.index;
                    }
                }
                // Retrieves the shortest job's characteristics
                Job shortJob = lst.get(minIndex);
                runJob(shortJob);
                getWaitingJobs();
            }

            // Prints out the formatting/chart
            System.out.println("\nSPN\n");
            printChart();

            // HRRN
            // Reset time to the first arrival of job
            time = lst.get(0).arrTime;

            // Adds all the jobs currently waiting
            getWaitingJobs();

            while (!waiting.isEmpty()) {
                // Stores highest response ratio and that job's index
                double maxRRatio = -1;
                int maxIndex = 0;
                // Traverses waiting jobs
                for (int i = 0; i < waiting.size(); i++) {
                    Job job = waiting.get(i);
                    // Calculates response ratio for each job
                    double rRatio = (time - job.arrTime + job.servTime)/ (double) job.servTime;
                    // Stores index of highest response ratio
                    if (rRatio > maxRRatio) {
                        maxRRatio = rRatio;
                        maxIndex = job.index;
                    }
                }

                // Gets the highest response ratio job
                Job highJob = lst.get(maxIndex);
                runJob(highJob);
                getWaitingJobs();
            }
            // Prints out the chart/formatting for HRRN
            System.out.println("\nHRRN\n");
            printChart();

        } catch (FileNotFoundException e) {
            System.out.println("File not Found!");
        }
    }

    // Formats and prints the chart
    public static void printChart() {
        for (int i = 0; i < lst.size(); i++) {
            Job job = lst.get(i);

            System.out.print(job.name + " ");

            for (int space = 0; space < job.space; space++) {
                System.out.print(" ");
            }
            // Sets job spaces and completed variables false for HRRN section
            job.space = 0;
            job.completed = false;
            for (int x = 0; x < job.servTime; x++) {
                System.out.print("X");
            }
            System.out.println();
        }
    }

    // Runs job and updates the corresponding variables
    public static void runJob(Job job) {
        // Stores the number of spaces for the output
        job.space = time;

        // Adds its service time to overall time
        time+=job.servTime;

        // Marks the job as completed
        job.completed = true;

        // Clears the waiting list for next set of jobs
        waiting.clear();
    }

    // Adds jobs that have arrived to the waiting list
    public static void getWaitingJobs() {

        for (int i = 0; i < lst.size(); i++) {
            Job job = lst.get(i);

            // Stores jobs that are not completed
            if (!job.completed) {

                // If the job has arrived then we add to waiting
                if (job.arrTime <= time) {
                    waiting.add(job);
                }

                // If no jobs have arrived
                if (waiting.isEmpty() && job.arrTime > time) {

                    // Need to move time to the next job's arrival time
                    time = job.arrTime;
                    // Add job to waiting
                    waiting.add(job);
                }
            }
        }
    }
}

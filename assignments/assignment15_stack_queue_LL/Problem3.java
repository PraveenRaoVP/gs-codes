package assignments.assignment15_stack_queue_LL;

import java.util.Comparator;
import java.util.PriorityQueue;

// Simulate a CPU job scheduling system where arriving jobs (objects with processing time) are added to a queue. The CPU dequeues and processes jobs one at a time, tracking waiting and turnaround times. You can introduce priorities for fairer scheduling, if you want more challenge.

class PriorityComparator implements Comparator<Job> {
    @Override
    public int compare(Job j1, Job j2) {
        return j2.priority - j1.priority;
    }
}

class Job {
    String name;
    int processingTime;
    int priority;

    public Job(String name, int processingTime, int priority) {
        this.name = name;
        this.processingTime = processingTime;
        this.priority = priority;
    }
}

class CPU {
    PriorityQueue<Job> jobs;
    int waitingTime;
    int turnaroundTime;

    public CPU() {
        jobs = new PriorityQueue<>(new PriorityComparator());
        this.waitingTime = 0;
        this.turnaroundTime = 0;
    }

    public void addJob(Job job) {
        jobs.add(job);
    }

    public void processJobs() {
        while (!jobs.isEmpty()) {
            Job job = jobs.poll();
            waitingTime += job.processingTime;
            turnaroundTime += waitingTime;
            System.out.println("Processing Job: " + job.name + " with processing time: " + job.processingTime);
        }
    }

    public void printTimes() {
        System.out.println("Waiting Time: " + waitingTime);
        System.out.println("Turnaround Time: " + turnaroundTime);
    }
}


public class Problem3 {

    public static void main(String[] args) {
        CPU cpu = new CPU();

        cpu.addJob(new Job("Job1", 5, 10));
        cpu.addJob(new Job("Job2", 3, 5));
        cpu.addJob(new Job("Job3", 8, 2 ));
        cpu.addJob(new Job("Job4", 2, 10));
        cpu.addJob(new Job("Job5", 6, 7));

        cpu.processJobs();
        cpu.printTimes();
    }
}

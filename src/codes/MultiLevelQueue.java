package codes;
import java.util.*;

public class MultiLevelQueue {

    public static List<String> executeMLQAlgorithm(Queue<Process> queue) {
        List<String> executionSteps = new ArrayList<>();
        PriorityQueue<Process> priorityQueue = new PriorityQueue<>();
        while(!queue.isEmpty()) {
//        	while()
        }

        return executionSteps;
    }


    // Method to execute processes in FCFS order
    private static void executeRR(Queue<Process> queue, List<String> executionSteps) {
        RoundRobin scheduler = new RoundRobin(queue.size());
        List<Process> processes = new ArrayList<>(queue);
        int[] arrivalTimes = new int[processes.size()];
        int[] burstTimes = new int[processes.size()];
        String[] processName = new String[processes.size()];
        for (int i = 0; i < processes.size(); i++) {
            arrivalTimes[i] = processes.get(i).arrivalTime;
            burstTimes[i] = processes.get(i).burstTime;
            processName[i] = processes.get(i).name;
        }
        scheduler.executeRRAlgorithm(processes.size(),processName, arrivalTimes, burstTimes, 2);

    }

    private static void executeSJF(Queue<Process> queue, List<String> executionSteps) {
        SJFScheduler scheduler = new SJFScheduler(queue.size());
        List<Process> processes = new ArrayList<>(queue);
        int[] arrivalTimes = new int[processes.size()];
        int[] burstTimes = new int[processes.size()];
        String[] processName = new String[processes.size()];
        for (int i = 0; i < processes.size(); i++) {
            arrivalTimes[i] = processes.get(i).arrivalTime;
            burstTimes[i] = processes.get(i).burstTime;
            processName[i] = processes.get(i).name;
        }
        scheduler.executeSJFAlgorithm(processes.size(),processName, arrivalTimes, burstTimes);

    }

    private static void executeFCFS(Queue<Process> queue, List<String> executionSteps) {
        FCFS scheduler = new FCFS(queue.size());
        List<Process> processes = new ArrayList<>(queue);
        int[] arrivalTimes = new int[processes.size()];
        int[] burstTimes = new int[processes.size()];
        String[] processName = new String[processes.size()];
        for(int i = 0; i < processes.size(); i++) {
            arrivalTimes[i] = processes.get(i).arrivalTime;
            burstTimes[i] = processes.get(i).burstTime;
            processName[i] = processes.get(i).name;
        }
        scheduler.executeFCFSAlgorithm(processes.size(),processName, arrivalTimes, burstTimes);
    }
}
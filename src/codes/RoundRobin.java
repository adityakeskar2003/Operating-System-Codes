package codes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RoundRobin {
    int quantum;
    int totalProcesses;
    Process[] processes;

    public RoundRobin(int totalProcesses) {
        this.totalProcesses = totalProcesses;
        processes = new Process[totalProcesses];
    }

    // Method to set the processes externally
    public void setProcesses(Process[] processes) {
        this.processes = processes;
        this.totalProcesses = processes.length;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public List<String> executeRRAlgorithm(int numProcesses,String[] processName,int[] arrivalTimes, int[] burstTimes, int quantum) {
        List<String> executionSteps = new ArrayList<>();
        int time = 0;
        int[] remainingTime = Arrays.copyOf(burstTimes, burstTimes.length);
//        executionSteps.add("\nExecution steps of RR:");

        while (true) {
            boolean done = true;
            for (int i = 0; i < numProcesses; i++) {
                if (remainingTime[i] > 0) {
                    done = false;
                    if (remainingTime[i] > quantum) {
                        executionSteps.add("Executing process " + processName[i] + " at time " + time);
                        time += quantum;
                        remainingTime[i] -= quantum;
                    } else {
                        executionSteps.add("Executing process " + processName[i] + " at time " + time);
                        time += remainingTime[i];
                        remainingTime[i] = 0;
                        executionSteps.add("Process " + processName[i] + " is completed at time " + time);
                    }
                }
            }
            if (done)
                break;
        }
//        Global_Variable.time = time;

        // Return the total time taken for execution
        return executionSteps;
    }

}
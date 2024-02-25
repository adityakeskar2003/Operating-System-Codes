package codes;
import java.util.*;

import codes.Process;


public class FCFS {
    int totalProcesses;
    Process[] processes;
    public FCFS(int totalProcesses) {
        this.totalProcesses = totalProcesses;
        processes = new Process[totalProcesses];
    }

    public List<String> executeFCFSAlgorithm(int numProcesses,String[] processName, int[] arrivalTimes, int[] burstTimes) {
        List<String> executionSteps = new ArrayList<>();

        int time = 0;
//        executionSteps.add("\nExecution steps of FCFS:");

        for (int i = 0; i < numProcesses; i++) {
            Process currentProcess = new Process(processName[i], arrivalTimes[i], burstTimes[i]);
            if (time < currentProcess.arrivalTime) {
                time = currentProcess.arrivalTime;
            }
            executionSteps.add("Executing process " + currentProcess.name + " at time " + (time));
            time += currentProcess.burstTime;
            currentProcess.completionTime = time;
            currentProcess.turnaroundTime = currentProcess.completionTime - currentProcess.arrivalTime;
            currentProcess.waitingTime = currentProcess.turnaroundTime - currentProcess.burstTime;
            executionSteps.add("Process " + currentProcess.name + " is completed at time " + (time));
        }
//        Global_Variable.time = time;

        return executionSteps;
    }
}

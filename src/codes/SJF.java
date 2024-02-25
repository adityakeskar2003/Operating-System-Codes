package codes;
import java.util.*;

class SJFScheduler {
    Process[] processes;
    int totalProcesses;
    ArrayList<Process> executedProcesses;

    SJFScheduler(int totalProcesses) {
        this.totalProcesses = totalProcesses;
        this.processes = new Process[totalProcesses];
        for (int i = 0; i < totalProcesses; i++) {
            this.processes[i] = new Process("", 0, 0); // Initialize with default values
        }
    }

    void sortProcessesByArrivalTime() {
        for (int i = 0; i < totalProcesses - 1; i++) {
            for (int j = 0; j < totalProcesses - i - 1; j++) {
                if (processes[j].arrivalTime > processes[j + 1].arrivalTime) {
                    Process temp = processes[j];
                    processes[j] = processes[j + 1];
                    processes[j + 1] = temp;
                }
            }
        }
    }
    void addProcess(Process process) {
        for (int i = 0; i < totalProcesses; i++) {
            if (processes[i] == null) {
                processes[i] = process;
                break;
            }
        }
    }
    
    
    
    public List<String> executeSJFAlgorithm(int numProcesses,String[] processName, int[] arrivalTimes, int[] burstTimes) {
        List<String> executionSteps = new ArrayList<>();
        int totalProcesses = numProcesses;
//        executionSteps.add("\nExecution steps of SJF:");
        // Assuming Process class is already defined
        System.out.println();
        Process[] processes = new Process[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
            processes[i] = new Process(processName[i], arrivalTimes[i], burstTimes[i]);
        }

        int time = 0;
        int totalCompleted = 0;

        while (totalCompleted < totalProcesses) {
            int min = Integer.MAX_VALUE;
            int index = -1;
            System.out.println();
            for (int i = 0; i < totalProcesses; i++) {
                if (processes[i].arrivalTime <= time && processes[i].remainingTime < min && !processes[i].done) {
                    min = processes[i].remainingTime;
                    index = i;
                }
            }


            if (index != -1) {
                executionSteps.add("Executing process " + processes[index].name + " at time " + (time));
                processes[index].remainingTime--;

                if (processes[index].remainingTime == 0) {
                    executionSteps.add("Process " + processes[index].name + " is completed at time " + (time));
//                    processes[index].completionTime = counter;
                    processes[index].turnaroundTime = processes[index].completionTime - processes[index].arrivalTime;
                    processes[index].waitingTime = processes[index].turnaroundTime - processes[index].burstTime;
                    processes[index].done = true;
                    totalCompleted++;
                }
            } else {
                executionSteps.add("No process is ready at time " + (time));
            }
            time++;
        }
//        Global_Variable.time = time;
        

        return executionSteps;
    }


//    void calculateAverages() {
//        float totalWaitingTime = 0;
//        float totalTurnaroundTime = 0;
//        for (Process process : processes) {
//            totalWaitingTime += process.waitingTime;
//            totalTurnaroundTime += process.turnaroundTime;
//        }
//        avgWaitingTime = totalWaitingTime / totalProcesses;
//        avgTurnaroundTime = totalTurnaroundTime / totalProcesses;
//    }

//    void displayResults() {
//        System.out.println("\nName\tArrival\tBurst\tCompletion\tTurnaround\tWaiting");
//        for (Process process : processes) {
//            System.out.println(process.name + "\t" + process.arrivalTime + "\t" + process.burstTime + "\t" +
//                    process.completionTime + "\t\t" + process.turnaroundTime + "\t\t" + process.waitingTime);
//        }
//        System.out.println("\nAverage turnaround time: " + avgTurnaroundTime);
//        System.out.println("Average waiting time: " + avgWaitingTime);
//    }
}

public class SJF {
    public static void main(String[] args) {
    }
}

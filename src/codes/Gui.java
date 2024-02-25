package codes;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class Gui extends JFrame {
	private JComboBox<String> algorithmComboBox;
    private JTextField numProcessesField;
    private JTextArea resultTextArea;
    private JPanel ganttPanel;

    public Gui() {
    	
        setTitle("CPU Scheduling Algorithm");
        setLayout(null); // Set layout to null for absolute positioning

        JLabel headingLabel = new JLabel("CPU Scheduling Algorithm");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 40));
        headingLabel.setBounds(235, 10, 600, 60); // Set bounds for heading label

        JLabel numProcessesLabel2 = new JLabel("Select the Algorithm:");
        numProcessesLabel2.setFont(new Font("Arial", Font.BOLD, 15));
        numProcessesLabel2.setBounds(520, 90, 150, 30); // Set bounds for label

        String[] algorithmOptions = {"FCFS", "SJF", "RR"};
        algorithmComboBox = new JComboBox<>(algorithmOptions);
        algorithmComboBox.setSelectedIndex(0);
        algorithmComboBox.setBounds(700, 90, 150, 30); // Set bounds for combo box
        
        JLabel numProcessesLabel = new JLabel("Number of Processes:");
        numProcessesLabel.setFont(new Font("Arial", Font.BOLD, 15));
        numProcessesLabel.setBounds(235, 90, 200, 30); // Set bounds for label

        numProcessesField = new JTextField(5);
        numProcessesField.setBounds(455, 90, 150, 30); // Set bounds for text field

        JButton goButton = new JButton("Go");
        goButton.setBounds(345, 140, 100, 30); // Set bounds for button
        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeSelectedAlgorithm();
            }
        });

        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        resultTextArea.setFont(new Font("Arial", Font.PLAIN, 12));
        resultTextArea.setLineWrap(true);
        resultTextArea.setWrapStyleWord(true);
        resultTextArea.setBounds(150, 200, 500, 500); // Set bounds for text area

        ganttPanel = new JPanel();
        ganttPanel.setBounds(150, 200, 500, 100); // Adjust bounds as needed
        ganttPanel.setLayout(new BorderLayout());

        add(headingLabel);
        add(algorithmComboBox);
        add(numProcessesLabel);
        add(numProcessesField);
        add(goButton);
        add(resultTextArea);
        add(ganttPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700); // Set initial size of JFrame
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void executeSelectedAlgorithm() {
        try {
            String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();
            if (selectedAlgorithm != null && !selectedAlgorithm.isEmpty()) {
                switch (selectedAlgorithm) {
                    case "FCFS":
                        executeFCFS();
                        break;
                    case "SJF":
                        executeSJF();
                        break;
                    case "RR":
                        executeRR();
                        break;
//                    case "MLQ":
//                        executeMLQ();
//                        break;
                    default:
                        System.out.println("Invalid algorithm selected");
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for the number of processes.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void executeRR() {

    	try {
            int numProcesses = Integer.parseInt(numProcessesField.getText());
            StringBuilder result = new StringBuilder();
            result.append("RR Results:\n");
            
            String[] processName = new String[numProcesses];
            int[] arrivalTimes = new int[numProcesses];
            int[] burstTimes = new int[numProcesses];

            for (int i = 0; i < numProcesses; i++) {
//                processName[i] = String.parseStr
            	processName[i] = JOptionPane.showInputDialog("Enter name for process " + (i + 1));
                arrivalTimes[i] = Integer.parseInt(JOptionPane.showInputDialog("Enter arrival time for process " + processName));
                burstTimes[i] = Integer.parseInt(JOptionPane.showInputDialog("Enter burst time for process " + processName));
                result.append("Process ").append(processName).append(": Arrival Time = ").append(arrivalTimes[i]).append(", Burst Time = ").append(burstTimes[i]).append("\n");
            }
            int timeQuantum = Integer.parseInt(JOptionPane.showInputDialog("Enter the time quantum:"));
            RoundRobin a = new RoundRobin(numProcesses); 

            // Call RR algorithm with the obtained parameters
            System.out.println();
            List<String> executionSteps = a.executeRRAlgorithm(numProcesses,processName, arrivalTimes, burstTimes,timeQuantum);

            // Append execution steps to the result text area
            for (String step : executionSteps) {
                result.append(step).append("\n");
            }

            resultTextArea.setText(result.toString());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for the number of processes.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void executeSJF() {
    	try {
            int numProcesses = Integer.parseInt(numProcessesField.getText());
            StringBuilder result = new StringBuilder();
            result.append("SJF Results:\n");
            
            String[] processName = new String[numProcesses];

            int[] arrivalTimes = new int[numProcesses];
            int[] burstTimes = new int[numProcesses];

            for (int i = 0; i < numProcesses; i++) {
            	processName[i] = JOptionPane.showInputDialog("Enter name for process " + (i + 1));
                arrivalTimes[i] = Integer.parseInt(JOptionPane.showInputDialog("Enter arrival time for process " + (i + 1)));
                burstTimes[i] = Integer.parseInt(JOptionPane.showInputDialog("Enter burst time for process " + (i + 1)));
            }
            for (int i = 0; i < numProcesses; i++) {
            result.append("Process ").append(processName[i]).append(": Arrival Time = ").append(arrivalTimes[i]).append(", Burst Time = ").append(burstTimes[i]).append("\n");
            }
            SJFScheduler a = new SJFScheduler(numProcesses); 

            // Call SJF algorithm with the obtained parameters
            System.out.println();
            List<String> executionSteps = a.executeSJFAlgorithm(numProcesses,processName, arrivalTimes, burstTimes);

            // Append execution steps to the result text area
            for (String step : executionSteps) {
                result.append(step).append("\n");
            }

            resultTextArea.setText(result.toString());
         // Auto-size resultTextArea
            resultTextArea.setRows(Math.max(5, resultTextArea.getLineCount()));
            resultTextArea.setColumns(Math.max(30, resultTextArea.getPreferredSize().width / resultTextArea.getFontMetrics(resultTextArea.getFont()).charWidth('W')));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for the number of processes.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void executeMLQ() {
        Queue<Process> queue = new LinkedList<>();
        int numProcesses = 0;
        try {
            numProcesses = Integer.parseInt(numProcessesField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number of processes. Please enter a valid integer.");
            return;
        }

        StringBuilder result = new StringBuilder();
        result.append("MLQ Results:\n");

        int[] arrivalTimes = new int[numProcesses];
        int[] burstTimes = new int[numProcesses];
        String[] processNames = new String[numProcesses];
        int[] priorities  = new int[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
//            String name = JOptionPane.showInputDialog("Enter name for Process " + (i + 1));
        	String processName;
            int arrivalTime = 0;
            int burstTime = 0;
            int priority = 0;
            try {
            	processName = JOptionPane.showInputDialog("Enter Name for Process " + (i + 1));
            	processNames[i] = processName;
                arrivalTime = Integer.parseInt(JOptionPane.showInputDialog("Enter arrival time for Process " + (i + 1)));
                arrivalTimes[i] = arrivalTime;
                burstTime = Integer.parseInt(JOptionPane.showInputDialog("Enter burst time for Process " + (i + 1)));
                burstTimes[i] = burstTime;
                priority = Integer.parseInt(JOptionPane.showInputDialog("Enter priority for Process " + (i + 1) +
                        " (1: RoundRobin, 2: SJF, 3: FCFS)"));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid integers.");
                return;
            }
            

            Process process = new Process(processName, arrivalTime, burstTime, priority);
            queue.add(process);
            
        }
        for (int i = 0; i < numProcesses; i++) {
            result.append("Process ").append(processNames[i]).append(": Arrival Time = ").append(arrivalTimes[i]).append(", Burst Time = ").append(burstTimes[i]).append(",Priority=").append(priorities[i]).append("\n");
            
            }

        // Call the MultiLevelQueue class to execute the algorithm
        List<String> executionSteps = MultiLevelQueue.executeMLQAlgorithm(queue);

        for (String step : executionSteps) {
            result.append(step).append("\n");
        }

        // Debug statement to check the content of the result before setting it to the text area
        System.out.println("MLQ Result:\n" + result.toString());

        // Set the text in the text area
        resultTextArea.setText(result.toString());

        // Auto-size resultTextArea
        resultTextArea.setRows(Math.max(5, resultTextArea.getLineCount()));
        resultTextArea.setColumns(Math.max(30, resultTextArea.getPreferredSize().width / resultTextArea.getFontMetrics(resultTextArea.getFont()).charWidth('W')));

        // Trigger repaint or 
        resultTextArea.repaint(); // or resultTextArea.revalidate();
    }



    private void executeFCFS() {
        try {
            int numProcesses = Integer.parseInt(numProcessesField.getText());
            StringBuilder result = new StringBuilder();
            result.append("FCFS Results:\n");

            int[] arrivalTimes = new int[numProcesses];
            int[] burstTimes = new int[numProcesses];
            String[] processName = new String[numProcesses];

            for (int i = 0; i < numProcesses; i++) {
            	processName[i] = JOptionPane.showInputDialog("Enter Name for Process " + (i + 1));
                arrivalTimes[i] = Integer.parseInt(JOptionPane.showInputDialog("Enter arrival time for process " + processName));
                burstTimes[i] = Integer.parseInt(JOptionPane.showInputDialog("Enter burst time for process " + processName));
                result.append("Process ").append(processName).append(": Arrival Time = ").append(arrivalTimes[i]).append(", Burst Time = ").append(burstTimes[i]).append("\n");
            }
            
            
            FCFS a = new FCFS(numProcesses); 

            System.out.println();
            // Call FCFS algorithm with the obtained parameters
            List<String> executionSteps = a.executeFCFSAlgorithm(numProcesses,processName,arrivalTimes, burstTimes);

            // Append execution steps to the result text area
            for (String step : executionSteps) {
                result.append(step).append("\n");
            }

            resultTextArea.setText(result.toString());
            resultTextArea.setFont(resultTextArea.getFont().deriveFont(16f));
            
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for the number of processes.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(Gui::new);
    }
}

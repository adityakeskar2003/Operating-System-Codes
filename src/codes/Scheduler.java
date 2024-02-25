package codes;
import java.util.*;

import codes.Process;

public interface Scheduler {
    void setProcesses(List<Process> processesList);
    void executeProcesses();
}
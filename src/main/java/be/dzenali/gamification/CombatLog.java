package be.dzenali.gamification;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CombatLog {
    private List<String> logs;
    private DateTimeFormatter formatter;

    public CombatLog() {
        this.logs = new ArrayList<>();
        this.formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    }

    public void log(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        String logEntry = "[" + timestamp + "] " + message;
        logs.add(logEntry);
    }

    public void printLog() {
        System.out.println("\n=== Combat Log ===");
        for (String entry : logs) {
            System.out.println(entry);
        }
        System.out.println("==================\n");
    }

    public List<String> getLogs() {
        return new ArrayList<>(logs);
    }

    public void clear() {
        logs.clear();
    }
}

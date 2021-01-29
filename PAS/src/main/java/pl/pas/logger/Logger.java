package pl.pas.logger;

import lombok.Data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Named
@ApplicationScoped
public class Logger {
    private List<String> logs;

    public Logger() {
        logs = new ArrayList<>();
    }

    public void addLog(String user, String message, Priority priority) {
        logs.add(priority.name() + " : " + (new Date()).toString() + " : " + user + " : " + message + "\n");
    }
}

package ru.mail.crashcourse;

import com.google.inject.Inject;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements LoggerType {

    @Getter
    @NotNull
    private final LineCounter lineCounter;

    @NotNull
    private File logFile;

    @NotNull
    private final String tag;

    @Inject
    public FileLogger(@NotNull LineCounter lineCounter,
                      @NotNull String tag) {
        this.lineCounter = lineCounter;
        this.tag = tag;
        logFile = new File("log.txt");
    }

    @Override
    public void log(@NotNull String message) {
        try {
            boolean fileCreatedFlag = logFile.createNewFile();
            FileWriter fileWriter = new FileWriter(logFile, true);
            fileWriter.write(createLogMessage(message));
            fileWriter.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        lineCounter.increase();
    }

    @NotNull
    private String createLogMessage(@NotNull String str) {
        return lineCounter.getNum() + ": <" + tag + ">"
                + str + "</" + tag + ">" + "\n";
    }

    @Override
    public void clearOutput() {
        try {
            FileWriter fileWriter = new FileWriter(logFile);
            BufferedWriter out = new BufferedWriter(fileWriter);
            out.write("");
            out.close();
        } catch (Exception e) {
            System.err.println("Error in file cleaning: " + e.getMessage());
        }
    }

}

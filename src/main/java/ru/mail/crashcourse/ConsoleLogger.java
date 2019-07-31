package ru.mail.crashcourse;

import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;

public class ConsoleLogger implements LoggerType {

    @NotNull
    private LineCounter lineCounter;

    @Inject
    public ConsoleLogger(@NotNull LineCounter lineCounter) {
        this.lineCounter = lineCounter;
    }

    @Override
    @CheckLine
    public void log(@NotNull String message) {
        System.out.println(lineCounter.getNum() + ": " + message);
        lineCounter.increase();
    }

    @Override
    public void clearOutput() {
    }
}

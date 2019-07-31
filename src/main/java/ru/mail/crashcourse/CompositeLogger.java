package ru.mail.crashcourse;

import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;

public class CompositeLogger implements LoggerType {
    @NotNull
    private ConsoleLogger consoleLogger;
    @NotNull
    private FileLogger fileLogger;

    @Inject
    public CompositeLogger(@NotNull ConsoleLogger consoleLogger,
                           @NotNull FileLogger fileLogger) {
        this.consoleLogger = consoleLogger;
        this.fileLogger = fileLogger;
    }

    @Override
    public void log(@NotNull String message) {
        consoleLogger.log(message);
        fileLogger.log(message);
    }

    @Override
    public void clearOutput() {
        fileLogger.clearOutput();
    }
}

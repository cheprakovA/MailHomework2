package ru.mail.crashcourse;

import org.jetbrains.annotations.NotNull;

public interface LoggerType {
    void log (@NotNull String message);
    void clearOutput();
}

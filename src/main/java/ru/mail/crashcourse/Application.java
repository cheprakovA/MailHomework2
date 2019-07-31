package ru.mail.crashcourse;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;
import java.util.Scanner;

public final class Application {

    @NotNull
    private final LoggerType logger;

    @Inject
    public Application(@NotNull LoggerType loggerType) {
        this.logger = loggerType;
    }

    public static void main(@NotNull String[] args) {
        final Injector injector = Guice.createInjector(new LoggerModule(args));
        injector.getInstance(Application.class).waitForInput();

    }

    private void waitForInput() {
        logger.clearOutput();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Waiting for new lines. Key in Ctrl+D to exit.");
            while (true) {
                logger.log(scanner.nextLine());
            }
        } catch (IllegalStateException | NoSuchElementException ignored) {
        }
    }

}

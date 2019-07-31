package ru.mail.crashcourse;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.matcher.Matchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class LoggerModule extends AbstractModule {

    @Nullable
    private static String tag;
    @NotNull
    private String type;
    @NotNull
    private static LineCounter lineCounter;

    @Inject
    public LoggerModule(@NotNull String[] args) {
        try {
            this.type = args[0];
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.err.println("Wrong 1st command line argument input");
        }
        try {
            tag = args[1];
        } catch (ArrayIndexOutOfBoundsException ex) {
            tag = "";
        }
        lineCounter = new LineCounter();
    }

    @Override
    protected void configure() {

        switch (type) {
            case "console":
                bind(LoggerType.class).to(ConsoleLogger.class);
                break;
            case "file":
                bind(LoggerType.class).toProvider(FileLogging.class);
                break;
            case "composite":
                bind(LoggerType.class).toProvider(CompositeLogging.class);
                break;
            default:
                System.err.println("Wrong 1st command line argument input");
        }
        bind(LineCounter.class)
                .toInstance(lineCounter);
        bindInterceptor(
                Matchers.any(),
                Matchers.annotatedWith(CheckLine.class),
                new ThirdLineChecker(lineCounter));
    }

    public static final class FileLogging implements Provider<LoggerType> {
        @NotNull
        @Override
        public LoggerType get() {
            return new FileLogger(lineCounter, tag);
        }
    }

    public static final class CompositeLogging implements Provider<LoggerType> {
        @NotNull
        @Override
        public LoggerType get() {
            return new CompositeLogger(new ConsoleLogger(lineCounter), new FileLogger(lineCounter, tag));
        }
    }

}

package ru.mail.crashcourse;

import com.google.inject.Inject;
import lombok.Getter;

public final class LineCounter {

    @Getter
    private int num;

    @Inject
    public LineCounter() {
        num = 1;
    }

    void increase() {
        num += 1;
    }
}

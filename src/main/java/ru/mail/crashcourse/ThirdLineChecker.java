package ru.mail.crashcourse;

import com.google.inject.Inject;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.jetbrains.annotations.NotNull;

public final class ThirdLineChecker implements MethodInterceptor {

    @NotNull
    private LineCounter lineCounter;

    @Inject
    public ThirdLineChecker(@NotNull LineCounter lineCounter) {
        this.lineCounter = lineCounter;
    }

    @NotNull
    @Override
    public Object invoke(@NotNull MethodInvocation methodInvocation) throws Throwable{
        if (lineCounter.getNum() % 3 == 0) {
            System.out.println("3-fold input: ");
        }
        return methodInvocation.proceed();
    }
}

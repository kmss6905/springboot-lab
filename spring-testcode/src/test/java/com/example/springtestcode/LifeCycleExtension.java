package com.example.springtestcode;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class LifeCycleExtension implements InvocationInterceptor {
    public static final Logger log = LoggerFactory.getLogger(LifeCycleExtension.class);

    @Override
    public  <T> T interceptTestClassConstructor(
            Invocation<T> invocation,
            ReflectiveInvocationContext<Constructor<T>> invocationContext, ExtensionContext extensionContext
    )throws Throwable {
        log.info("constructed, {}", invocation.getClass());
        return InvocationInterceptor.super.interceptTestClassConstructor(invocation, invocationContext, extensionContext);
    }

    @Override
    public void interceptTestMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext,
                                     ExtensionContext extensionContext) throws Throwable {
        String methodName = invocationContext.getExecutable().getName();
        long beforeTime = System.currentTimeMillis();
        InvocationInterceptor.super.interceptTestMethod(invocation, invocationContext, extensionContext);
        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime);
        log.debug("테스트명 = {}, 실행시간 = {}(ms)", methodName, secDiffTime);
    }

}

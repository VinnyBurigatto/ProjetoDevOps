package com.projetodevops.infrastructure.tracing;

public class CorrelationIdContext {

    private static final ThreadLocal<String> correlationIdHolder = new ThreadLocal<>();

    private CorrelationIdContext() {

    }

    public static void setCorrelationId(String correlationId) {
        correlationIdHolder.set(correlationId);
    }

    public static String getCorrelationId() {
        return correlationIdHolder.get();
    }

    public static void clear() {
        correlationIdHolder.remove();
    }

}

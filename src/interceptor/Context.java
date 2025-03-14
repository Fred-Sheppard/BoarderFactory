package interceptor;

import java.time.LocalDateTime;

/**
 * Context class that holds information about the intercepted method call.
 * This includes method name, parameters, return value, and timing information.
 */
public class Context {
    private final String methodName;
    private final Object[] parameters;
    private Object returnValue;
    private final LocalDateTime startTime;
    private LocalDateTime endTime;
    private Exception exception;

    public Context(String methodName, Object... parameters) {
        this.methodName = methodName;
        this.parameters = parameters;
        this.startTime = LocalDateTime.now();
    }

    public String getMethodName() {
        return methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public Object getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
        this.endTime = LocalDateTime.now();
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
        this.endTime = LocalDateTime.now();
    }

    public long getDuration() {
        if (endTime == null) {
            return 0;
        }
        return java.time.Duration.between(startTime, endTime).toMillis();
    }
} 
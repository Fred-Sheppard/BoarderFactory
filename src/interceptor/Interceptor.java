package interceptor;

/**
 * Interface defining the contract for all interceptors in the system.
 * Interceptors can be used to add cross-cutting concerns like logging.
 */
public interface Interceptor {
    /**
     * Called before the intercepted method is executed.
     * @param context The context containing information about the intercepted call
     */
    void before(Context context);

    /**
     * Called after the intercepted method is executed.
     * @param context The context containing information about the intercepted call
     */
    void after(Context context);
} 
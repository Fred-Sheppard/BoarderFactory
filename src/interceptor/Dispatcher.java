package interceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Dispatcher class that manages interceptors and handles method interception.
 * This class is responsible for creating contexts and notifying interceptors
 * of method entry and exit.
 */
public class Dispatcher {
    private static final List<Interceptor> interceptors = new ArrayList<>();

    /**
     * Adds an interceptor to the dispatcher.
     * @param interceptor The interceptor to add
     */
    public static void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }

    /**
     * Removes an interceptor from the dispatcher.
     * @param interceptor The interceptor to remove
     */
    public static void removeInterceptor(Interceptor interceptor) {
        interceptors.remove(interceptor);
    }

    /**
     * Creates a new context and notifies all interceptors of method entry.
     * @param methodName The name of the method being intercepted
     * @param parameters The parameters of the method
     * @return The created context
     */
    public static Context before(String methodName, Object... parameters) {
        Context context = new Context(methodName, parameters);
        for (Interceptor interceptor : interceptors) {
            interceptor.before(context);
        }
        return context;
    }

    /**
     * Notifies all interceptors of method exit and sets the return value.
     * @param context The context created in the before method
     * @param returnValue The return value of the method
     */
    public static void after(Context context, Object returnValue) {
        context.setReturnValue(returnValue);
        for (Interceptor interceptor : interceptors) {
            interceptor.after(context);
        }
    }
} 
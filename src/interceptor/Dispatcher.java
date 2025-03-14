package interceptor;

import java.util.ArrayList;
import java.util.List;


public class Dispatcher {
    private static final List<Interceptor> interceptors = new ArrayList<>();

    public static void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }

    public static void removeInterceptor(Interceptor interceptor) {
        interceptors.remove(interceptor);
    }

    public static Context before(String methodName, Object... parameters) {
        Context context = new Context(methodName, parameters);
        for (Interceptor interceptor : interceptors) {
            interceptor.before(context);
        }
        return context;
    }

    public static void after(Context context, Object returnValue) {
        context.setReturnValue(returnValue);
        for (Interceptor interceptor : interceptors) {
            interceptor.after(context);
        }
    }
} 
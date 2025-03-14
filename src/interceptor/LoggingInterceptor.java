package interceptor;

import java.util.logging.Logger;
import java.util.logging.Level;

public class LoggingInterceptor implements Interceptor {
    private static final Logger logger = Logger.getLogger(LoggingInterceptor.class.getName());

    @Override
    public void before(Context context) {
        logger.log(Level.INFO, "Entering method: {0}", context.getMethodName());
        if (context.getParameters() != null && context.getParameters().length > 0) {
            logger.log(Level.INFO, "Parameters: {0}", formatParameters(context.getParameters()));
        }
    }

    @Override
    public void after(Context context) {
        logger.log(Level.INFO, "Exiting method: {0}", context.getMethodName());
        logger.log(Level.INFO, "Duration: {0}ms", context.getDuration());
        if (context.getReturnValue() != null) {
            logger.log(Level.INFO, "Return value: {0}", context.getReturnValue());
        }
    }

    private String formatParameters(Object[] parameters) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parameters.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(parameters[i]);
        }
        return sb.toString();
    }
} 
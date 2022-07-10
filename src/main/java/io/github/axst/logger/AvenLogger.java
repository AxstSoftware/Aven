package io.github.axst.logger;

import io.github.axst.logger.type.AvenLoggerType;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The <code>AvenLogger</code> class, contains 2 methods for log your messages
 * @see AvenLoggerType AvenLoggerType for more logs type
 * @author sdxqw
 * @since 0.1
 */
public class AvenLogger {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("HH:mm:ss");
    private static final Date DATE = new Date();

    /**
     * Logger Message method
     * @param message String message
     * @param type AvenLoggerType logger type
     */
    public static void message(String message, @NotNull AvenLoggerType type) {
        switch (type) {
            case INFO: System.out.format("[" + FORMAT.format(DATE) + "] " +"[AvenLogger] [Main/INFO]: " +  message + "\n");
            break;
            case ERROR: System.out.format("[" + FORMAT.format(DATE) + "] " +"[AvenLogger] [Main/ERROR]: " +  message + "\n");
            break;
            case WARN: System.out.format("[" + FORMAT.format(DATE) + "] " +"[AvenLogger] [Main/WARN]: " +  message + "\n");
            break;
            case FATAL:
                System.out.format("[" + FORMAT.format(DATE) + "] " +"[AvenLogger] [Main/FATAL]: " +  message + "\n");
                System.exit(-1);
            break;
        }
    }

    /**
     * Logger Message method, display custom name
     * @param clientName String your client name
     * @param message String message
     * @param type AvenLoggerType logger type
     */
    public static void message(String clientName, String message, @NotNull AvenLoggerType type) {
        switch (type) {
            case INFO: System.out.format("[" + FORMAT.format(DATE) + "] [" + clientName + "] [Main/INFO]: " +  message + "\n");
                break;
            case ERROR: System.out.format("[" + FORMAT.format(DATE) + "] [" + clientName + "] [Main/ERROR]: " +  message + "\n");
                break;
            case WARN: System.out.format("[" + FORMAT.format(DATE) + "] [" + clientName + "] [Main/WARN]: " +  message + "\n");
                break;
            case FATAL:
                System.out.format("[" + FORMAT.format(DATE) + "] [" + clientName + "] [Main/FATAL]: " +  message + "\n");
                System.exit(-1);
                break;
        }
    }
}

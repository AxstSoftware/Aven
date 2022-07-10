package io.github.axst.dev;

import io.github.axst.client.IAven;
import io.github.axst.logger.AvenLogger;
import io.github.axst.logger.type.AvenLoggerType;

public class ClientCore implements IAven {
    @Override
    public void startClient() {
        System.out.println("TEST");
        AvenLogger.message("test", AvenLoggerType.ERROR);
    }

    @Override
    public void stopClient() {
        System.out.println("TEST2");
    }
}

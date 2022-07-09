package io.github.axst;

import io.github.axst.client.IAven;

public class ClientCore implements IAven {
    @Override
    public void startClient() {
        System.out.println("TEST");
    }

    @Override
    public void stopClient() {
        System.out.println("TEST2");
    }
}

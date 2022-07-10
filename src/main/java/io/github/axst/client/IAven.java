package io.github.axst.client;

/**
 * Aven interface, implements this class and use these methods
 * for initialize or stop your client.
 * @author sdxqw
 * @since 0.1
 */
public interface IAven {

    /**
     * This void method need to start your code.
     */
    default void startClient() {
        System.out.println("Starting Aven");
    }

    /**
     * This void method need to stop your code.
     */
    default void stopClient() {
        System.out.println("Stopping Aven");
    }
}

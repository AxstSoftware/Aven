package io.github.axst.client;

/**
 * The <code>IAven</code> class, it's used to initialize or stop your client code
 * @author sdxqw
 * @since 0.1
 */
public interface IAven {

    /**
     * Method used for start your client code
     */
    default void startClient() {
        System.out.println("Starting Aven");
    }

    /**
     * Method used for stop your client code
     * @see org.spongepowered.asm.mixin.injection.At At for more infos.
     */
    default void stopClient() {
        System.out.println("Stopping Aven");
    }
}

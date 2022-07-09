package io.github.axst.mixins;

import io.github.axst.client.IAven;
import io.github.classgraph.*;
import lombok.SneakyThrows;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Minecraft.class)
class MinecraftMixins {
    @SneakyThrows
    @Inject(method = "startGame", at = @At(value = "HEAD"))
    void injectStartGame(CallbackInfo ci) {
        try (ScanResult scanResult = new ClassGraph().enableAllInfo().scan()) {
            ClassInfoList iAvenClasses = scanResult.getClassesImplementing("io.github.axst.client.IAven");
            List<Class<?>> iAvenClassRefs = iAvenClasses.loadClasses();
            for (Class<?> iAven : iAvenClassRefs) {
                ((IAven) iAven.newInstance()).startClient();
            }
        }
    }

    @SneakyThrows
    @Inject(method = "shutdownMinecraftApplet", at = @At(value = "HEAD"))
    void injectStopGame(CallbackInfo ci) {
        try (ScanResult scanResult = new ClassGraph().enableAllInfo().scan()) {
            ClassInfoList iAvenClasses = scanResult.getClassesImplementing("io.github.axst.client.IAven");
            List<Class<?>> iAvenClassRefs = iAvenClasses.loadClasses();
            for (Class<?> iAven : iAvenClassRefs) {
                ((IAven) iAven.newInstance()).stopClient();
            }
        }
    }
}

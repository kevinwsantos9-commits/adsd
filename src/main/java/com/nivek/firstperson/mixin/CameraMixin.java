package com.nivek.firstperson.mixin;

import com.nivek.firstperson.NivekFirstPersonClient;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * O mundo só desenha o jogador local quando a câmera "é" de terceira pessoa.
 * Aqui dizemos que sim para o jogador local, mesmo em primeira pessoa;
 * a posição da câmera continua sendo a dos olhos.
 */
@Mixin(Camera.class)
public abstract class CameraMixin {
    @Shadow
    public abstract Entity getFocusedEntity();

    @Inject(method = "isThirdPerson", at = @At("HEAD"), cancellable = true)
    private void nivek$drawBodyInFirstPerson(CallbackInfoReturnable<Boolean> cir) {
        if (NivekFirstPersonClient.isLocalPlayerInFirstPerson(getFocusedEntity())) {
            cir.setReturnValue(true);
        }
    }
}

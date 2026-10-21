package com.nivek.firstperson.mixin;

import com.nivek.firstperson.NivekFirstPersonClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Esconde só a cabeça (e a camada do chapéu) para a câmera não ficar dentro dela. */
@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin {
    @Inject(method = "setModelPose", at = @At("RETURN"))
    private void nivek$hideHead(AbstractClientPlayerEntity player, CallbackInfo ci) {
        if (NivekFirstPersonClient.isLocalPlayerInFirstPerson(player)) {
            PlayerEntityModel<AbstractClientPlayerEntity> model =
                    ((PlayerEntityRenderer) (Object) this).getModel();
            model.head.visible = false;
            model.hat.visible = false;
        }
    }
}

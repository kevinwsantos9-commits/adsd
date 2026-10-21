package com.nivek.firstperson.mixin;

import com.nivek.firstperson.NivekFirstPersonClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Não desenha itens na cabeça (abóbora, caveiras) do jogador local em primeira pessoa. */
@Mixin(HeadFeatureRenderer.class)
public abstract class HeadFeatureRendererMixin {
    @Inject(
            method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;FFFFFF)V",
            at = @At("HEAD"),
            cancellable = true,
            require = 0
    )
    private void nivek$skipHeadItem(MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                                    int light, LivingEntity entity,
                                    float limbAngle, float limbDistance, float tickDelta,
                                    float animationProgress, float headYaw, float headPitch,
                                    CallbackInfo ci) {
        if (NivekFirstPersonClient.isLocalPlayerInFirstPerson(entity)) {
            ci.cancel();
        }
    }
}

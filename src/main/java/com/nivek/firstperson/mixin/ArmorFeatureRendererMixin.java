package com.nivek.firstperson.mixin;

import com.nivek.firstperson.NivekFirstPersonClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Não desenha o capacete do jogador local em primeira pessoa (ele cobriria a tela). */
@Mixin(ArmorFeatureRenderer.class)
public abstract class ArmorFeatureRendererMixin {
    @Inject(method = "renderArmor", at = @At("HEAD"), cancellable = true, require = 0)
    private void nivek$skipHelmet(MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                                  LivingEntity entity, EquipmentSlot slot, int light,
                                  BipedEntityModel<?> model, CallbackInfo ci) {
        if (slot == EquipmentSlot.HEAD && NivekFirstPersonClient.isLocalPlayerInFirstPerson(entity)) {
            ci.cancel();
        }
    }
}

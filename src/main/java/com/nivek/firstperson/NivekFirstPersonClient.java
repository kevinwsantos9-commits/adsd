package com.nivek.firstperson;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class NivekFirstPersonClient implements ClientModInitializer {
    public static final String MOD_ID = "nivek_first_person";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("Nivek First Person carregado: corpo inteiro em primeira pessoa.");
    }

    /** True quando a entidade é o jogador local e a câmera está em primeira pessoa. */
    public static boolean isLocalPlayerInFirstPerson(Entity entity) {
        MinecraftClient client = MinecraftClient.getInstance();
        return client.player != null
                && entity == client.player
                && client.options.getPerspective().isFirstPerson();
    }
}

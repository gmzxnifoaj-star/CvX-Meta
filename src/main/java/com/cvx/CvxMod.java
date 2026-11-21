package com.cvx;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class CvxMod implements ModInitializer {

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> register(dispatcher));
    }

    private void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
            CommandManager.literal("cvx")

                .then(CommandManager.literal("lol").executes(ctx -> {
                    ctx.getSource().sendFeedback(() -> Text.literal("lol"), false);
                    return 1;
                }))

                .then(CommandManager.literal("force").executes(ctx -> {
                    ctx.getSource().sendFeedback(() -> Text.literal("force activated"), false);
                    return 1;
                }))

                .then(CommandManager.literal("op")
                    .requires(CommandManager.requirePermissionLevel(CommandManager.GAMEMASTERS_CHECK))
                    .executes(ctx -> {
                        ctx.getSource().sendFeedback(() -> Text.literal("op command works"), false);
                        return 1;
                    }))
        );
    }
}

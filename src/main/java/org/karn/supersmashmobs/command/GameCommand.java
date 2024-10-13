package org.karn.supersmashmobs.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.argument.IdentifierArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.karn.supersmashmobs.SuperSmashMobsAPI;
import org.karn.supersmashmobs.kit.AbstractKit;
import org.karn.supersmashmobs.registry.SSMRegistries;
import org.karn.supersmashmobs.util.SSMItemUtil;

public class GameCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("ssm")
                .requires(source -> source.hasPermissionLevel(4))
                .then(CommandManager.literal("bots")
                        .then(CommandManager.argument("count", IntegerArgumentType.integer(1))
                                .executes(ctx->{
                                    for (int i = 0; i < IntegerArgumentType.getInteger(ctx, "count"); i++) {
                                        ctx.getSource().getServer().getCommandManager().executeWithPrefix(ctx.getSource().withSilent(), "player bot-" +i+" spawn");
                                    }
                                    return 1;
                                })
                        )
                )
                .then(CommandManager.literal("kit")
                        .then(CommandManager.argument("kit", IdentifierArgumentType.identifier())
                               .suggests((context, builder) -> CommandSource.suggestIdentifiers(SSMRegistries.KIT.getIds(), builder))
                                .executes(ctx -> {
                                      AbstractKit kit = SSMRegistries.KIT.get(IdentifierArgumentType.getIdentifier(ctx, "kit"));
                                      giveKit(ctx, kit);
                                      return 1;
                                })
                        )
                )
        );
    }

    private static void giveKit(CommandContext<ServerCommandSource> source, AbstractKit kit) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getSource().getPlayerOrThrow();
        player.getInventory().clear();

        player.setJob(null);
        source.getSource().getServer().getCommandManager().executeWithPrefix(source.getSource().withSilent(), "job set supersmashmobs:"+kit.jobID);
        ((SuperSmashMobsAPI) player).setKit(kit);
        //SSMItemUtil.setBaseComponent(player.getInventory().getMainHandStack(),player.getJob().value());
        player.sendMessage(Text.translatable(kit.getTranslationKey()).append(" 킷 설정됨."), false);
        System.out.println("Kit set to " + kit.getTranslationKey());
    }
}

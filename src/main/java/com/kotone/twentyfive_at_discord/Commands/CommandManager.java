package com.kotone.twentyfive_at_discord.Commands;

import com.kotone.twentyfive_at_discord.Commands.information.Ping;
import com.kotone.twentyfive_at_discord.Commands.information.Server;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends ListenerAdapter {
  @Override
  public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
    event.deferReply().queue();
    String command = event.getName();

    if (command.equals("ping")) new Ping(event);
    if (command.equals("server")) new Server(event);
  }

  @Override
  public void onReady(@NotNull ReadyEvent event) {
    List<CommandData> commandData = new ArrayList<>();

    // Ping Command
    commandData.add(Commands.slash("ping", "Botの応答速度を表示します"));

    // Server Command
    commandData.add(Commands.slash("server", "サーバー情報を表示します"));

    // Register Commands
    event.getJDA().updateCommands().addCommands(commandData).queue();
  }
}

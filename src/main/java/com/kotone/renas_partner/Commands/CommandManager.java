package com.kotone.renas_partner.Commands;

import com.kotone.renas_partner.Commands.information.Ping;
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
  }

  @Override
  public void onReady(@NotNull ReadyEvent event) {
    List<CommandData> commandData = new ArrayList<>();

    commandData.add(
            // Ping Command
            Commands.slash("ping", "Botの応答速度を表示します")
    );

    event.getJDA().updateCommands().addCommands(commandData).queue();
  }
}

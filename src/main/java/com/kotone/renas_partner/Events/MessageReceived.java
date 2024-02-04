package com.kotone.renas_partner.Events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageReceived extends ListenerAdapter {
  @Override
  public void onMessageReceived(@NotNull MessageReceivedEvent event) {
    String message = event.getMessage().getContentRaw();
    if (message.equals("ping")) {
      event.getChannel().sendMessage("Pong").queue();
    }
  }
}

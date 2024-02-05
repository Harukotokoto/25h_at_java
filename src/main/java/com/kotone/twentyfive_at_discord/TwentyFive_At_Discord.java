package com.kotone.twentyfive_at_discord;

import com.kotone.twentyfive_at_discord.Commands.CommandManager;
import com.kotone.twentyfive_at_discord.Events.MessageReceived;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class TwentyFive_At_Discord {

  private TwentyFive_At_Discord() throws LoginException {
    Dotenv config = Dotenv.configure().load();
    String token = config.get("CLIENT_TOKEN");

    DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);

    builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
    builder.setActivity(Activity.customStatus("👀 君たちを監視中"));
    builder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES);

    ShardManager shardManager = builder.build();

    shardManager.addEventListener(
            // Message Received Event
            new MessageReceived(),

            // SlashCommand
            new CommandManager()
    );
  }

  public static void main(String[] args) {
    try {
      TwentyFive_At_Discord bot = new TwentyFive_At_Discord();
    } catch (LoginException e) {
      System.out.println("Error: Provided token is invalid");
    }
  }
}

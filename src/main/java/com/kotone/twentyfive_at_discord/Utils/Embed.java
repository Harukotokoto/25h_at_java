package com.kotone.twentyfive_at_discord.Utils;

import com.kotone.twentyfive_at_discord.TwentyFive_At_Discord;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;

import java.util.Objects;

public class Embed {
  public static void setFooter(EmbedBuilder embed) {
    User user = TwentyFive_At_Discord.shardManager.getUserById("1004365048887660655");
    String display_name = Objects.requireNonNull(user).getGlobalName();
    String avatar_url = user.getAvatarUrl();

    embed.setFooter("Produced by " + display_name, avatar_url);
  }
}

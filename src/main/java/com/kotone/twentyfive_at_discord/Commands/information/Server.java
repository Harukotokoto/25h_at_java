package com.kotone.twentyfive_at_discord.Commands.information;

import com.kotone.twentyfive_at_discord.Constants.Emojis;
import com.kotone.twentyfive_at_discord.Utils.BoostBar;
import com.kotone.twentyfive_at_discord.Utils.Embed;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.awt.*;
import java.util.Objects;

public class Server {
  public Server(SlashCommandInteractionEvent event) {
    Guild guild = event.getGuild();
    long timestamp = Objects.requireNonNull(guild).getTimeCreated().toEpochSecond();



    EmbedBuilder embed = new EmbedBuilder();
    embed.setDescription(guild.getDescription());
    embed.setThumbnail(guild.getBannerUrl());
    embed.addField(Emojis.SERVER + " サーバー作成日", "<t:" + timestamp + ">", false);
    embed.addField(Emojis.MEMBER + " サーバー所有者", "<@!" + guild.getOwnerId() + ">", false);
    embed.addField(Emojis.MEMBER + " メンバー数", guild.getMemberCount() + "人", true);
    embed.addField(Emojis.LOCK + " BANされたユーザー数", guild.retrieveBanList().complete().size() + "人", true);
    embed.addField(Emojis.BOOST + " サーバーブースト進行度", CreateBoostBar(guild), false);
    embed.setAuthor(guild.getName(), guild.getIconUrl());
    embed.setColor(Color.YELLOW);
    Embed.setFooter(embed);

    event.getHook().editOriginalEmbeds(embed.build()).queue();
  }

  public String CreateBoostBar(Guild guild) {
    int boost_count = guild.getBoostCount();
    int boost_tier = boost_count < 2 ? 0 : boost_count < 7 ? 1 : boost_count < 14 ? 2 : 3;

    return switch (boost_tier) {
      case 0 -> "レベル無し | " + (boost_count == 0 ? "未" : boost_tier) + "ブースト\n" +
              BoostBar.CreateBar(boost_count, 2) +
              "\n次のレベルまで:" + boost_count + "/2";
      case 1 -> "レベル" + boost_tier + " | " + boost_count + "ブースト\n" +
              BoostBar.CreateBar(boost_count, 7) +
              "\n次のレベルまで:" + boost_count + "/7";
      case 2 -> "レベル" + boost_tier + " | " + boost_count + "ブースト\n" +
              BoostBar.CreateBar(boost_count, 7) +
              "\n次のレベルまで:" + boost_count + "/14";
      case 3 -> "レベル" + boost_tier + " | " + boost_count + "ブースト\n" +
              BoostBar.CreateBar(14, 14) +
              "\nブーストレベル最大🎉";
      default -> "ブーストバーの作成に失敗しました";
    };
  }
}

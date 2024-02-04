package com.kotone.renas_partner.Commands.information;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.awt.*;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.time.Duration;
import java.time.Instant;

import com.kotone.renas_partner.Constants.Emojis;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;

public class Ping {
  public Ping(SlashCommandInteractionEvent event) {
    // インタラクションを作成した時間を取得
    Instant interactionTime = event.getTimeCreated().toInstant();

    // 現在時間を取得 (Instant型)
    Instant currentTime = Instant.now();

    // ラグを計算 (Duration型で計算してからlong型のミリ秒に変換)
    long response = interactionTime.toEpochMilli() - currentTime.toEpochMilli();

    // ラグに応じて絵文字を変更
    String responseEmoji = (response < 401) ? Emojis.STATS_01 : (response <= 600) ? Emojis.STATS_02 : Emojis.STATS_03;


    // メモリMXBeanを取得
    MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

    // ヒープメモリ使用状況を取得
    MemoryUsage heapMemoryUsage = memoryBean.getHeapMemoryUsage();

    // 割り当てられたヒープメモリサイズ
    long maxHeapMemory = heapMemoryUsage.getMax();

    // 現在のヒープメモリ使用量
    long usedHeapMemory = heapMemoryUsage.getUsed();

    // ヒープメモリ使用率の計算
    double ramUsage = Math.round((double) usedHeapMemory / maxHeapMemory * 100 * 100.0) / 100.0;

    // ヒープメモリ使用率に応じて絵文字を変更
    String ramEmoji = (ramUsage < 50) ? Emojis.RAM_EXCELLENT : (ramUsage <= 80) ? Emojis.RAM_GOOD : Emojis.RAM_BAD;


    // WSとの遅延を取得
    long latency = event.getJDA().getGatewayPing();

    // WSとの遅延速度に応じて絵文字を変更
    String latencyEmoji = (latency < 201) ? Emojis.STATS_01 : (latency <= 400) ? Emojis.STATS_02 : Emojis.STATS_03;


    // 埋め込みのフィールドを作成
    String latencyField = Emojis.SPACE + latencyEmoji + " **WebSocket:** `" + latency + "`ms";
    String responseField = Emojis.SPACE + responseEmoji + " **Response:** `" + response + "`ms";

    String ramField = ramEmoji + " **RAM:** `" + ramUsage + "`%";

    String resourceFieldContent = Emojis.SPACE + Emojis.JOURNEY + " **Resources:**";
    String resourceField = Emojis.SPACE + Emojis.SPACE + ramField;

    String title = Emojis.STAGE + " **Shard[0]:**";

    // 埋め込みを作成
    EmbedBuilder embed = new EmbedBuilder();

    embed.setTitle(Emojis.ONLINE + " Bot Status:");
    embed.addField(title, latencyField + "\n" + responseField + "\n" + resourceFieldContent + "\n" + resourceField, false);
    embed.setColor(Color.CYAN);

    // リプライ
    event.getHook().editOriginalEmbeds(embed.build()).queue();
  }
}

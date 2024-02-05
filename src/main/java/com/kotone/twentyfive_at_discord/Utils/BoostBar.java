package com.kotone.twentyfive_at_discord.Utils;

import com.kotone.twentyfive_at_discord.Constants.Emojis;
import net.dv8tion.jda.api.entities.Guild;

public class BoostBar {
  public static String CreateBar(int maxValue, int currentValue) {
    int bar_length = 14;
    int unit_value = maxValue / bar_length;

    // 埋まっているセクションと埋まっていないセクションを計算
    int filled_sections = (int) Math.floor((double) currentValue / unit_value);
    int empty_sections = (int) Math.max(bar_length - filled_sections, 0);

    // 右端、中央、左端のマーカー
    String left_marker = currentValue >= unit_value ? Emojis.FULL_LEFT_G : Emojis.EMPTY_LEFT_G;
    String middle_marker = Emojis.FULL_INNER_G;
    String right_marker = filled_sections == bar_length ? Emojis.FULL_RIGHT_G : Emojis.EMPTY_RIGHT_G;

    // バーを生成
    return left_marker + middle_marker.repeat(filled_sections) + Emojis.EMPTY_INNER_G.repeat(empty_sections) + right_marker;
  }
}

package EmojiMod.util;

import EmojiMod.EmojiMod;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmojiMappingUtils {
    public static String[] oldEmojiNumberMappings = { "\uDB80\uDE65", "\uDB80\uDE60", "\uDB80\uDE64", "\uDB80\uDE63", "\uDB80\uDE5E", "\uDB80\uDE5D", "\uDB80\uDE62", "\uDB80\uDE61", "\uDB80\uDE5C", "\uDB80\uDE5F" };
    public static String[] emojiNumberMappings = { "\uDB80\uDEA9", "\uDB80\uDEA4", "\uDB80\uDEA8", "\uDB80\uDEA7", "\uDB80\uDEA1", "\uDB80\uDEA0", "\uDB80\uDEA6", "\uDB80\uDEA5", "\uDB80\uDE9E", "\uDB80\uDEA3" };

    public static String replaceIntWithEmoji(int num) {
        String delim = " ";
        if (num >= 999) {
            return "☠️" + delim;
        }
        String str = String.valueOf(num)
                .chars()
                .map(Character::getNumericValue)
                .mapToObj(x -> emojiNumberMappings[x])
                .collect(Collectors.joining(delim, "", delim));
        String filteredStr = EmojiMod.emojiSupport.FilterEmojis(str);
        return filteredStr;
    }

    public static float getStringWidthWithEmoji(AbstractCard __instance, String[] tokens, BitmapFont font) {
        List<String> newTokens = new ArrayList<>();
        for (String s : tokens) {
            if (s.startsWith("!D!")) {
                if (__instance.isDamageModified) {
                    newTokens.add(EmojiMappingUtils.replaceIntWithEmoji(__instance.damage));
                } else {
                    newTokens.add(EmojiMappingUtils.replaceIntWithEmoji(__instance.baseDamage));
                }
            } else if (s.startsWith("!B!")) {
                if (__instance.isBlockModified) {
                    newTokens.add(EmojiMappingUtils.replaceIntWithEmoji(__instance.block));
                } else {
                    newTokens.add(EmojiMappingUtils.replaceIntWithEmoji(__instance.baseBlock));
                }
            } else if (s.startsWith("!M!")) {
                if (__instance.isMagicNumberModified) {
                    newTokens.add(EmojiMappingUtils.replaceIntWithEmoji(__instance.magicNumber));
                } else {
                    newTokens.add(EmojiMappingUtils.replaceIntWithEmoji(__instance.baseMagicNumber));
                }
            } else {
                newTokens.add(s);
            }
        }

        String desc = String.join("", newTokens);
        GlyphLayout gl = new GlyphLayout();
        gl.setText(font, desc);
        return gl.width;
    }
}

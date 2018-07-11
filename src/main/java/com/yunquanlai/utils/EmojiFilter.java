package com.yunquanlai.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liyanjun
 */

public class EmojiFilter {

    private static Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

    public static String filterEmoji(String source) {
        System.out.println("开始过滤表情：" + source);
        if (source != null) {
            Matcher emojiMatcher = emoji.matcher(source);
            if (emojiMatcher.find()) {
                source = emojiMatcher.replaceAll("*");
                return source;
            }
            return source;
        }
        return source;
    }
}

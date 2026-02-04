package com.codespire.url_shortener.Utils;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class ShortCodeGenerator {

    private static final String CHAR_SET = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
    private static final int CODE_LENGTH = 6;

    private static final Random RANDOM = new Random();

    public  String generate() {
        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            sb.append(CHAR_SET.charAt(RANDOM.nextInt(CHAR_SET.length())));
        }
        return sb.toString();
    }
}

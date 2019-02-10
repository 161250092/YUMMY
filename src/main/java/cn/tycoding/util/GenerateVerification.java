package cn.tycoding.util;

import java.util.Random;

public class GenerateVerification {

    private static final String SOURCES =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

    public static String generateVerification(int length){

        char[] text =  new char[length];
        Random random = new Random();
        for(int i=0;i<length;i++){
            text[i] = SOURCES.charAt(random.nextInt(SOURCES.length()));
        }
        return new String(text);
    }


}

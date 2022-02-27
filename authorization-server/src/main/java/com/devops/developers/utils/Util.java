package com.devops.developers.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Util {
    private static Random random= new Random();
    private static String randomInt="0123456789";
    private static String randomChar="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String generateOTP(){
        StringBuilder otp= new StringBuilder();
         for(int i=0;i<6; i++){
             otp.append(randomInt.charAt(random.nextInt(randomInt.length())));
         }
         return otp.toString();
    }

    public static String generateSmallLengthId(){
        StringBuilder randomSeq= new StringBuilder();
        for(int i=0;i<8; i++){
            randomSeq.append(randomChar.charAt(random.nextInt(randomChar.length())));
        }
        return randomSeq.toString();
    }

    public static Date getOneMinuteExpiry(){
        Calendar currentTime = Calendar.getInstance();
        currentTime.add(Calendar.MINUTE, 1);
        return currentTime.getTime();
    }
}

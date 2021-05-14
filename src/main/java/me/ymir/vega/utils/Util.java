package me.ymir.vega.utils;

public class Util {

    public static int parseInt(String value,Integer defaultValue){
        int p;
        try {
            p = Integer.parseInt(value);
        }catch (NumberFormatException exception){
            p = defaultValue;
        }
        return p<0 ? -p : p;
    }


    public static String defaultStringFormat = "**━━━━━━━━\uD835\uDD4D\uD835\uDD3C\uD835\uDD3E\uD835\uDD38━━━━━━━━** \n";


}

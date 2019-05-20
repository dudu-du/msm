package com.welsee.tools;

import java.util.UUID;

/**
 * uuid 用于主键id
 */
public class UUIDUtil {

    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };


    /**
     * 获取一个短uuid
     *
     * @return
     */
    public static String getShortUuid(){
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString().toLowerCase();
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void main(String[] args) {
        System.out.println(getUUID());
        System.out.println("==============");
        System.out.println(getUUID());
        System.out.println("==============");
        System.out.println(getUUID());
        System.out.println("==============");
        System.out.println(getUUID());
        System.out.println("==============");
        System.out.println(getUUID());
        System.out.println("==============");
        System.out.println(getUUID());
        System.out.println("==============");
        System.out.println(getUUID());
    }
}
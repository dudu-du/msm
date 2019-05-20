package com.safety.tools;

/**
 * 加密工具类
 */
public class EncryptionUtil {
    public static String encrypt(String  original) {
        original =MD5.GetMD5Code(original);
        return original;
    }
}

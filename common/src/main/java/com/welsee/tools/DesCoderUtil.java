package com.welsee.tools;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.security.SecureRandom;

/**
 * this class is used to encrypt and decrypt
 *
 * @author fangwk
 * @date 2016-8-3 14:11:27
 */
public class DesCoderUtil {
    //加密算法是des
    private static final String ALGORITHM = "DES";
    //转换格式
    private static final String TRANSFORMATION = "DES/CBC/PKCS5Padding";

    /**
     * 加密
     *
     * @param src 数据源
     * @param key 密钥，长度必须是8的倍数
     * @return 返回加密后的数据
     * @throws Exception 出错
     */
    public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据建立 DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 建立一个密匙工厂，然后用它把DESKeySpec转换成
        // 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        // 用密匙原始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, new IvParameterSpec(key));
        // 现在，获取数据并加密
        // 正式执行加密操作
        return cipher.doFinal(src);
    }

    /**
     * 解密
     *
     * @param src 数据源
     * @param key 密钥，长度必须是8的倍数
     * @return 返回解密后的原始数据
     * @throws Exception 出错
     */
    public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据建立一个DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 建立一个密匙工厂，然后用它把DESKeySpec对象转换成
        // 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        // 用密匙原始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, new IvParameterSpec(key));
        // 现在，获取数据并解密
        // 正式执行解密操作
        return cipher.doFinal(src);
    }

    /**
     * Description 根据键值进行加密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String key) throws Exception {
        byte[] bt = encrypt(data.getBytes("UTF-8"), key.getBytes("UTF-8"));

        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(bt);

        //return new String(Base64.encodeBase64(bt), "UTF-8");
    }

    /**
     * Description 根据键值进行解密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String decrypt(String data, String key) throws Exception {
        if (data == null)
            return null;

        //byte[] buf = Base64.decodeBase64(data);

        BASE64Decoder decoder = new BASE64Decoder();
        byte[] buf = decoder.decodeBuffer(data);

        byte[] bt = decrypt(buf,key.getBytes("UTF-8"));
        return new String(bt,"UTF-8");
    }


    public static void main(String[] args) throws Exception{
        String key = "12345678";
        String jj = "测试";
        String pp = encrypt(jj, key);
        System.out.println("加密:" + pp );

        String mm2 = decrypt(pp, key);
        System.out.println("解密:" + mm2 );
    }

}
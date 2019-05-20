package com.safety.tools;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * 请求地址验证工具类
 */
public class RegexUtil {

    /**
     * 验证重定向的url是否合法
     *
     * @param redirectURI 重定向URI
     * @return true/false
     */
    public static boolean isUrl(String redirectURI) {
//        String regex = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?";
        String regex = "[a-zA-z]+://[^\\s]*";
        return Pattern.matches(regex, redirectURI);
    }

    /**
     * 验证Email
     * @param email email地址，格式：zhangsan@zuidaima.com，zhangsan@xxx.com.cn，xxx代表邮件服务商
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkEmail(String email) {
        String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
        return Pattern.matches(regex, email);
    }
    /**
     * 验证身份证号码
     * @param idCard 居民身份证号码15位或18位，最后一位可能是数字或字母
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkIdCard(String idCard) {
        String regex = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}";
        return Pattern.matches(regex,idCard);
    }
    /**
     * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
     * @param mobile 移动、联通、电信运营商的号码段
     *<p>移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
     *、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）</p>
     *<p>联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）</p>
     *<p>电信的号段：133、153、180（未启用）、189</p>
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkMobile(String mobile) {
        String regex = "(\\+\\d+)?1[34578]\\d{9}$";
        return Pattern.matches(regex,mobile);
    }

    /***
     * 性别判断
     * @param value
     * @return 女、男
     */
    public static String getSexForCard(String value){
        value = value.trim();
        if (value == null || (value.length() != 15 && value.length() != 18)){
            return "";
        }
        if (value.length() == 15 || value.length() == 18){
            String lastValue="";
            if(value.length() == 15){
                lastValue= value.substring(value.length() - 1, value.length());
            }else{
                lastValue= value.substring(value.length() - 2, value.length()-1);
            }
            int sex;
            if (lastValue.trim().toLowerCase().equals("x")||lastValue.trim().toLowerCase().equals("e")){
                return "男";
            }else{
                sex = Integer.parseInt(lastValue) % 2;
                return sex == 0 ? "女" : "男";
            }
        }else{
            return "";
        }
    }

    /**
     * 获取出生日期
     * @param value
     * @return
     */
    public static LocalDate getBirthDayForCard(String value){
        String birthday = "";

        char[] number = value.toCharArray();
        boolean flag = true;
        if (number.length == 15) {
            for (int x = 0; x < number.length; x++) {
                if (!flag) return null;
                flag = Character.isDigit(number[x]);
            }
        } else if (number.length == 18) {
            for (int x = 0; x < number.length - 1; x++) {
                if (!flag) return null;
                flag = Character.isDigit(number[x]);
            }
        }
        if (flag && value.length() == 15) {
            birthday = "19" + value.substring(6, 8) + "-"
                    + value.substring(8, 10) + "-"
                    + value.substring(10, 12);
        } else if (flag && value.length() == 18) {
            birthday = value.substring(6, 10) + "-"
                    + value.substring(10, 12) + "-"
                    + value.substring(12, 14);
        }
        if(!"".equals(birthday)){
            return DateTimeUtil.fromString2LocalDate(birthday);
        }else{
            return null;
        }

    }
}

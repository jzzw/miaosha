package com.zhbit.miaosha.util;


import org.apache.commons.codec.digest.DigestUtils;
/**
 *
 * MD5加密工具类
 */
public class MD5Util {
    private  static final String salt = "1a2b3c4d";
    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }
    //第一次加密，MD5( 用户输入的密码+固定字符加密 )
    public static String inputPassToFormPass(String inputPass){
        String src =  ""+salt.charAt(4)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(3);
        return md5(src);
    }

    // 第二次加密，MD5( 第一次加密的密码+随机字符 )  随机字符字符将存入数据库
    public static String formPassToDBPass(String fromPass,String saltDB){
        String src =  ""+saltDB.charAt(4)+saltDB.charAt(2)+fromPass+saltDB.charAt(5)+saltDB.charAt(3);
        return md5(src);
    }
    //依次调用以上两个方法
    public static String inputPassToDBPass(String inputPass,String saltDB){
       String fromPass = inputPassToFormPass(inputPass);
       String dbPass = formPassToDBPass(fromPass,saltDB);
       return dbPass;
    }
    
}

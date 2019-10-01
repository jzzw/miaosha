package com.zhbit.miaosha.util;


import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    private  static final String salt = "1a2b3c4d";
    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    public static String inputPassFormPass(String inputPass){   //第一次加密，MD5( 用户输入的密码+固定字符加密 )
        String src =  ""+salt.charAt(4)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(3);
        return md5(src);
    }

    public static String formPassToDBPass(String fromPass,String saltDB){  // 第二次加密，MD5( 第一次加密的密码+随机字符 )  随机字符字符将存入数据库
        String src =  ""+saltDB.charAt(4)+saltDB.charAt(2)+fromPass+saltDB.charAt(5)+saltDB.charAt(3);
        return md5(src);
    }

    public static String inputPassToDBPass(String inputPass,String saltDB){   //依次调用以上两个方法
       String fromPass = inputPassFormPass(inputPass);
       String dbPass = formPassToDBPass(fromPass,saltDB);
       return dbPass;
    }

    public static void main(String[] arg){

        System.out.println(inputPassToDBPass("123456","1a2b3c4d"));

    }
}

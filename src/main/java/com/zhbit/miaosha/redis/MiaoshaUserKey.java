package com.zhbit.miaosha.redis;

public class MiaoshaUserKey extends BasePrefix {

    public MiaoshaUserKey(String prefix) {
        super(prefix);
    }
    public static MiaoshaUserKey token = new MiaoshaUserKey("tk");

}

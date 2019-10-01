package com.zhbit.miaosha.redis;

public interface KeyPrefix {

    int expireSeconds();

    String getPrefix();

}

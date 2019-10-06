package com.zhbit.miaosha.redis;

public class GoodsKey extends BasePrefix{

	private GoodsKey(int expireSeconds,String prefix) {
		super(prefix);
	}
	public static GoodsKey getGoodsList = new GoodsKey(60,"gl");

}

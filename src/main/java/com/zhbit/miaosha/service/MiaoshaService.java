package com.zhbit.miaosha.service;

import com.zhbit.miaosha.domain.MiaoshaUser;
import com.zhbit.miaosha.domain.OrderInfo;
import com.zhbit.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MiaoshaService {


    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderService orderService;

    @Transactional
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {

        //减库存 下单订 写入秒杀订单
        goodsService.reduceStock(goods);
        return orderService.createOrder(user,goods);

    }
}

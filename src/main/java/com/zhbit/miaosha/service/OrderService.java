package com.zhbit.miaosha.service;

import com.zhbit.miaosha.dao.OrderDao;
import com.zhbit.miaosha.domain.MiaoshaOrder;
import com.zhbit.miaosha.domain.MiaoshaUser;
import com.zhbit.miaosha.domain.OrderInfo;
import com.zhbit.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class OrderService {


    @Autowired
    private OrderDao orderDao;


    public MiaoshaOrder getMiaoshaOrderByUserIdGooodsId(long id, long goodsId) {
        MiaoshaOrder miaoshaOrder = orderDao.getMiaoshaOrderByUserIdGooodsId(id,goodsId);
        return miaoshaOrder;
    }
    @Transactional
    public OrderInfo createOrder(MiaoshaUser user, GoodsVo goods) {
        //生成商品订单
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsPrice(goods.getMiaoshaPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        long orderId = orderDao.insert(orderInfo);
        //生成秒杀订单
        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setGoodsId(goods.getId());
        miaoshaOrder.setUserId(user.getId());
        miaoshaOrder.setOrderId(orderInfo.getId());
        orderDao.insertMiaoshaOrder(miaoshaOrder);

        return  orderInfo;

    }
}

package com.zhbit.miaosha.controller;


import com.zhbit.miaosha.Result.CodeMsg;
import com.zhbit.miaosha.domain.MiaoshaOrder;
import com.zhbit.miaosha.domain.MiaoshaUser;
import com.zhbit.miaosha.domain.OrderInfo;
import com.zhbit.miaosha.service.GoodsService;
import com.zhbit.miaosha.service.MiaoshaService;
import com.zhbit.miaosha.service.OrderService;
import com.zhbit.miaosha.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {
    private static Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private MiaoshaService miaoshaService;

    @RequestMapping("/do_miaosha")
    public String list(Model model, MiaoshaUser user,
                       @RequestParam("goodsId") long goodsId) {
        model.addAttribute("user", user);
        if (user == null) {
            return "login";
        }
        //判断库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if (stock <= 0) {
            model.addAttribute("stock", CodeMsg.MIAO_SHAOVER_OVER.getMsg());
            return "miaosha_fail";
        }
        //判断是否已经秒杀过
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGooodsId(user.getId(), goodsId);

        if (order != null) {
            model.addAttribute("stock", CodeMsg.REPEATE_MIAOSHA.getMsg());
            return "miaosha_fail";
        }

        //减库存 下订单 写入秒杀订单
        OrderInfo orderInfo = miaoshaService.miaosha(user, goods);

        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", goods);
        return "order_detail";
    }


}

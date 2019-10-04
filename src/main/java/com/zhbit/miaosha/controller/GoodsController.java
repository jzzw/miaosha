package com.zhbit.miaosha.controller;


import com.zhbit.miaosha.domain.MiaoshaUser;
import com.zhbit.miaosha.service.GoodsService;
import com.zhbit.miaosha.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    private static Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/to_list")
    public String toLogin(Model model, MiaoshaUser user) {

        model.addAttribute("user", user);

        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }

    @RequestMapping("/to_detail/{goodsId}")
    public String detail(Model model, MiaoshaUser user,
                         @PathVariable("goodsId") long goodsId) {

        model.addAttribute("user", user);

        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods", goods);

        long starAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int miaoshaStatus = 0;  //秒杀状态
        int remainSeconds = 0;  //秒杀剩余时间
        if (now < starAt) { //秒杀未开始
            miaoshaStatus = 0;
            remainSeconds = (int)(starAt-now)/1000;
        } else if (now > endAt) { //秒杀结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        } else {    //秒杀进行
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        return "goods_detail";
    }

}

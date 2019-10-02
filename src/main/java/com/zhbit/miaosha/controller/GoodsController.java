package com.zhbit.miaosha.controller;


import com.zhbit.miaosha.domain.MiaoshaUser;
import com.zhbit.miaosha.service.MiaoshaUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    private static Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private MiaoshaUserService miaoshaUserService;

    @RequestMapping("/to_list")
    public String toLogin(Model model, MiaoshaUser user){

        model.addAttribute("user", user);
        return  "goods_list";
    }




}

package com.zhbit.miaosha.controller;

import com.zhbit.miaosha.Result.CodeMsg;
import com.zhbit.miaosha.Result.Result;
import com.zhbit.miaosha.util.ValidatorUtil;
import com.zhbit.miaosha.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(LoginVo loginVo){
        log.info(loginVo.toString());
        String  passInput = loginVo.getPassword();
        String  mobile = loginVo.getMobile();
        if(passInput.isEmpty()){
            Result.error(CodeMsg.PASSWORD_EMPTY);
        }
        if(mobile.isEmpty()){
            Result.error(CodeMsg.MOBILE_EMPTY);
        }
        if(ValidatorUtil.isMobile(mobile))
        return null;
    }
}

package com.zhbit.miaosha.service;

import com.zhbit.miaosha.Result.CodeMsg;
import com.zhbit.miaosha.dao.MiaoshaUserDao;
import com.zhbit.miaosha.domain.MiaoshaUser;
import com.zhbit.miaosha.exception.GlobalException;
import com.zhbit.miaosha.redis.MiaoshaUserKey;
import com.zhbit.miaosha.redis.RedisService;
import com.zhbit.miaosha.util.MD5Util;
import com.zhbit.miaosha.util.UUIDUtil;
import com.zhbit.miaosha.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class MiaoshaUserService {


    private  static final String  COOKIS_NAME_TOKEN = "token" ;

     @Autowired
    private MiaoshaUserDao miaoshaUserDao;

    @Autowired
    RedisService redisService;
    public MiaoshaUser getById(long id){
        return  miaoshaUserDao.getById(id);
    }

    public boolean login(HttpServletResponse response,LoginVo loginVo) {
        if(loginVo == null){
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        MiaoshaUser user = miaoshaUserDao.getById(Long.parseLong(mobile));
        if(user == null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }

        String dbPass = user.getPassword();
        String dbsalt = user.getSalt();
        // 判断密码是否正确
        if(!(MD5Util.formPassToDBPass(formPass,dbsalt).equals(dbPass))){
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        //生成cookie
        String token = UUIDUtil.uuid();
        redisService.set(MiaoshaUserKey.token,token,user);
        Cookie cookie = new Cookie(COOKIS_NAME_TOKEN,token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
        return true;
    }
}

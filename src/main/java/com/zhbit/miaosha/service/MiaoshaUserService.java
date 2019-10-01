package com.zhbit.miaosha.service;

import com.zhbit.miaosha.Result.CodeMsg;
import com.zhbit.miaosha.dao.MiaoshaUserDao;
import com.zhbit.miaosha.domain.MiaoshaUser;
import com.zhbit.miaosha.util.MD5Util;
import com.zhbit.miaosha.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiaoshaUserService {
     @Autowired
    private MiaoshaUserDao miaoshaUserDao;


    public MiaoshaUser getById(long id){
        return  miaoshaUserDao.getById(id);
    }

    public CodeMsg login(LoginVo loginVo) {
        if(loginVo == null){
            return CodeMsg.SERVER_ERROR;
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        MiaoshaUser user = miaoshaUserDao.getById(Long.parseLong(mobile));
        if(user == null){
            return CodeMsg.MOBILE_NOT_EXIST;
        }

        String dbPass = user.getPassword();
        String dbsalt = user.getSalt();
        // 判断密码是否正确
        if(!(MD5Util.formPassToDBPass(formPass,dbsalt).equals(dbPass))){
            return CodeMsg.PASSWORD_ERROR;
        }
        return CodeMsg.SUCCESS;
    }
}

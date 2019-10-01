package com.zhbit.miaosha.exception;

import com.zhbit.miaosha.Result.CodeMsg;

public class GlobalException extends RuntimeException{


     private CodeMsg msg;

     public GlobalException(CodeMsg msg){
         super(msg.toString());
         this.msg=msg;

     }

    public CodeMsg getMsg() {
        return msg;
    }
}

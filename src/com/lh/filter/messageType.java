package com.lh.filter;

public interface messageType {
	  //SC  登陆成功
      String loginSuccess="loginsuccess";
      //CS  登陆请求
      String login="login";
      //SC 弹出消息
      String CahtServerMessge="chatservermessage";
      /**
       *   CS 改变订单状态
       */
       String OrderStatusReq="orderstatus";
       /**
       *   CS 增加订单
       */
       String OrderAddReq="orderadd";
       /**
       *   CS 删除订单
       */
       String OrderDelReq="orderdel";
        
       /**
        *CS 增加人物
       */
       String roleChangeReq="roleChange";
}

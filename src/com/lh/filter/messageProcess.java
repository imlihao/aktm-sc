package com.lh.filter;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lh.daoImp.daoImpFactory;
import com.lh.message.chatmessage;
import com.lh.message.messageItype;
import com.lh.message.msgLoginSuccess;
import com.lh.vo.user;

public class messageProcess {
	public GsonBuilder builder=new GsonBuilder(); 
	private webApp app;
	private String message;
    public void verify(webApp webapp,String message){
    	 System.out.println("开始解析..");
    	  this.app=webapp;
    	  this.message=message;
    	  
          Gson json=builder.create();
          messageItype type=json.fromJson(message, messageItype.class);
          //判错
          if(type.Itype==null){
        	  System.out.println("解析错误，没有找到操作符");
        	  return;
          }
          this.process(type.Itype);
    } 
    public void process(String type){
    	System.out.println("Process:"+type);
    	switch(type){
    	   case "login":
    		   this.login();
    		   break;
    	   default:
    		   System.out.println("没有找到对应操作符  "+type);
    		   break;
    	}
    	
    } 
   /**
    *登录验证  
    */
    private void login(){
    	//TODO login
     	 user us=this.builder.create().fromJson(message, user.class);
    	 System.out.println(us.getUname()+"尝试登陆..");
    	 List<user> L=daoImpFactory.getUserDao().serach(us);
    	 if(L.isEmpty()){
    		 // 失败处理
    		 System.out.println("login fail...");
    		 try {
    			 //失败消息
    			 chatmessage cm=new chatmessage();
    			 cm.msg="登陆失败";  	
				 this.app.sendMessage(this.builder.create().toJson(cm));
			} catch (IOException e) {
				System.out.println("发送消息失败 ");
				e.printStackTrace();
			}
    	 }else{
    		 //成功
    	  try {
			this.app.sendMessage(this.builder.create().toJson(new msgLoginSuccess(L.get(0))));
		 } catch (IOException e) {
			System.out.println("发送消息失败 ");
			e.printStackTrace();
		}
     }
    }
}
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
    	 System.out.println("��ʼ����..");
    	  this.app=webapp;
    	  this.message=message;
    	  
          Gson json=builder.create();
          messageItype type=json.fromJson(message, messageItype.class);
          //�д�
          if(type.Itype==null){
        	  System.out.println("��������û���ҵ�������");
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
    		   System.out.println("û���ҵ���Ӧ������  "+type);
    		   break;
    	}
    	
    } 
   /**
    *��¼��֤  
    */
    private void login(){
    	//TODO login
     	 user us=this.builder.create().fromJson(message, user.class);
    	 System.out.println(us.getUname()+"���Ե�½..");
    	 List<user> L=daoImpFactory.getUserDao().serach(us);
    	 if(L.isEmpty()){
    		 // ʧ�ܴ���
    		 System.out.println("login fail...");
    		 try {
    			 //ʧ����Ϣ
    			 chatmessage cm=new chatmessage();
    			 cm.msg="��½ʧ��";  	
				 this.app.sendMessage(this.builder.create().toJson(cm));
			} catch (IOException e) {
				System.out.println("������Ϣʧ�� ");
				e.printStackTrace();
			}
    	 }else{
    		 //�ɹ�
    	  try {
			this.app.sendMessage(this.builder.create().toJson(new msgLoginSuccess(L.get(0))));
		 } catch (IOException e) {
			System.out.println("������Ϣʧ�� ");
			e.printStackTrace();
		}
     }
    }
}
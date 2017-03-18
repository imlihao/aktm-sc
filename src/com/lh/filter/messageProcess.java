package com.lh.filter;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lh.daoImp.daoImpFactory;
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
          Itype type=json.fromJson(message, Itype.class);
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
    		 //TODO ʧ�ܴ���
    		 System.out.println("login fail...");
    		 try {
				this.app.sendMessage("��½ʧ��");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	 }else{
    	 try {
			this.app.sendMessage(this.builder.create().toJson(new msgLoginSuccess(L.get(0))));
		} catch (IOException e) {
			System.out.println("������Ϣʧ�� ");
			e.printStackTrace();
		}
     }
    }
}
/**
 * 
 * @author lihao
 * 
 * ���ͣ���������������
 */
class Itype{
	String Itype=null;
	
}
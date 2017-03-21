package com.lh.filter;

import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lh.dao.customerDao;
import com.lh.dao.orderDao;
import com.lh.dao.userDao;
import com.lh.daoImp.daoImpFactory;
import com.lh.message.CS_message_delOrder;
import com.lh.message.CS_message_orderStatusChanaged;
import com.lh.message.CS_message_orderadd;
import com.lh.message.CS_message_rolechange;
import com.lh.message.chatmessage;
import com.lh.message.messageItype;
import com.lh.message.msgLoginSuccess;
import com.lh.vo.customer;
import com.lh.vo.order;
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
          if(type.Itype!=messageType.login&&this.app.user==null){
      		System.out.println("δ��¼�ķǷ�����"+type);
      		System.out.println("δ��¼�ķǷ�����"+type.Itype!=messageType.login);
      		System.out.println(this.app.user);
      		//return;
      	   }
          this.process(type.Itype);
    } 
    public void process(String type){
    	System.out.println("����:"+type);
    	
    	switch(type){
    	   case messageType.login:
    		   this.login();
    		   break;
    	   case messageType.OrderAddReq:
    		   this.orderAdd();
    	       break;
    	   case messageType.OrderStatusReq:
    		   this.changeOrderStatus();
    		   break;
    	   case messageType.roleChangeReq:
    		   this.roleChange();
    		   break;
    	   case messageType.OrderDelReq:
    		   this.delOrder();
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
    		this.app.user=L.get(0);
    	  try {
			this.app.sendMessage(this.builder.create().toJson(new msgLoginSuccess(L.get(0))));
		 } catch (IOException e) {
			System.out.println("������Ϣʧ�� ");
			e.printStackTrace();
		}
     }
    }
    /**
     * ��������
     */
    private void  orderAdd(){
    	 CS_message_orderadd req=this.builder.create().fromJson(message, CS_message_orderadd.class);
    	 System.out.println("������������.."+this.app.user.getUname()+this.app.user.getUID());
    	 user op=new user(); 
    	 op.setUID(req.op_id);
    	 user dirver=new user();
    	 dirver.setUID(req.dirver_id);
    	 customer cus=new customer();
    	 cus.setcustomer_ID(req.cus_id);
    	 
    	 List<user> L1;
    	 List<user> L2;
    	 List<customer> L3;
    	 L1=daoImpFactory.getUserDao().serach(op);
    	 L2=daoImpFactory.getUserDao().serach(dirver);  	   	
    	 L3=daoImpFactory.getCustomerDao().serach(cus);
    	 if(L1.isEmpty()||L2.isEmpty()||L3.isEmpty()){   		 
    		 try {
    			 //ʧ����Ϣ
    			 chatmessage cm=new chatmessage();
    			 cm.msg="��Ϣ���󣬴���ʧ��";  	
				 this.app.sendMessage(this.builder.create().toJson(cm));
				 return;
			} catch (IOException e) {
				System.out.println("������Ϣʧ�� ");
				e.printStackTrace();
			}
    	 }
    	 op=L1.get(0);
    	 dirver=L2.get(0);
    	 cus=L3.get(0);
    	 order od=new order();
    	 od.setpos(req.pos);
    	 od.setDetial(req.des);
    	 od.setorder_status(1);
    	 od.setdirver(dirver);
    	 od.setcustomer(cus);
    	 od.setoperator(op);
    	 orderDao oda=daoImpFactory.getOrderDao();
         oda.insert(od);
         this.sendall();
    }
    /**
     * ɾ������
     */
    private void delOrder() {
    	 CS_message_delOrder req=this.builder.create().fromJson(message, CS_message_delOrder.class);
    	 System.out.println("���Ըı䶩��״̬.."+this.app.user.getUID());
    	 order od=new order();
    	 od.setOrder_ID(req.order_ID);
    	 daoImpFactory.getOrderDao().delete(od);
    	 this.sendall();
	}
    /**
     * �ı䶩��״̬
     */
    private void changeOrderStatus(){
    	 CS_message_orderStatusChanaged req=this.builder.create().fromJson(message, CS_message_orderStatusChanaged.class);
    	 System.out.println("���Ըı䶩��״̬.."+this.app.user.getUID());
    	 order od=new order();
    	 od.setOrder_ID(req.order_ID);
    	 List<order> L1=daoImpFactory.getOrderDao().serach(od);
    	 if(L1.isEmpty()){
    		 try {
    			 //ʧ����Ϣ
    			 chatmessage cm=new chatmessage();
    			 cm.msg="��ɶ���ʧ��";  	
				 this.app.sendMessage(this.builder.create().toJson(cm));
				 return;
			} catch (IOException e) {
				System.out.println("������Ϣʧ�� ");
				e.printStackTrace();
			}
    	 }
    	 od=L1.get(0);
    	 od.setorder_status(req.status);
    	 orderDao oda=daoImpFactory.getOrderDao();
         oda.update(od);
         this.sendall();
    }
    /**
     * �ı�����
     */
    private void roleChange(){
    	CS_message_rolechange req=this.builder.create().fromJson(message, CS_message_rolechange.class);
    	System.out.println("���Ը�������.."+this.app.user.getUname()+this.app.user.getUID());
    	userDao ud=daoImpFactory.getUserDao();
    	customerDao cd=daoImpFactory.getCustomerDao();
    	if(req.bool){//��
    		if(req.op!=null)ud.insert(req.op);
    		if(req.cus!=null)cd.insert(req.cus);
    	}else{//ɾ��
    		if(req.op!=null)ud.delete(req.op);
    		if(req.cus!=null)cd.delete(req.cus);
    	}
    	this.sendall();
    }
    
    private void sendall(){
        try {
    			for(webApp ap:webApp.webSocketSet){
    				if(ap.user!=null)ap.sendMessage(this.builder.create().toJson(new msgLoginSuccess(ap.user)));
    				}
    		} catch (IOException e) {
    			System.out.println("������Ϣʧ�� ");
    			e.printStackTrace();
    		}
    	
    }
}
package com.lh.filter;

public interface messageType {
	  //SC  ��½�ɹ�
      String loginSuccess="loginsuccess";
      //CS  ��½����
      String login="login";
      //SC ������Ϣ
      String CahtServerMessge="chatservermessage";
      /**
       *   CS �ı䶩��״̬
       */
       String OrderStatusReq="orderstatus";
       /**
       *   CS ���Ӷ���
       */
       String OrderAddReq="orderadd";
       /**
       *   CS ɾ������
       */
       String OrderDelReq="orderdel";
        
       /**
        *CS ��������
       */
       String roleChangeReq="roleChange";
}

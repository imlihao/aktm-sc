package com.lh.daoImp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lh.dao.customerDao;
import com.lh.dao.orderDao;
import com.lh.dao.userDao;
import com.lh.dbc.hibernateFactory;
import com.lh.vo.customer;
import com.lh.vo.order;
import com.lh.vo.user;

public class orderDaoImp implements orderDao{
  @Override
  public boolean insert(order od){
	  Session s=hibernateFactory.getSession();
	  s.beginTransaction();
	  s.save(od);
	  s.getTransaction().commit();
	  s.close();
	  return true;
  }
  @Override
  public boolean update(order od) {
	  Session s=hibernateFactory.getSession();
	  s.beginTransaction();
	  s.update(od);
	  s.getTransaction().commit();
	  s.close();
	  return true;
  }
   @Override
   public List<order> serach() {
	 Session s=hibernateFactory.getSession();
		s.beginTransaction();
		@SuppressWarnings("deprecation")
		Criteria c=s.createCriteria(order.class);
		List<order> L=c.list();
		s.getTransaction().commit();
		s.close();
		return L;
   }

   @Override
   public List<order> serach(order od) {
	Session s=hibernateFactory.getSession();
	 s.beginTransaction();
   	@SuppressWarnings("deprecation")
	Criteria c=s.createCriteria(order.class);
   	c.add(Restrictions.eq("order_ID", od.getOrder_ID()));
//    c.add(Restrictions.eq("phone", cus.getphone()));
//    c.add(Restrictions.eq("customer_name", cus.getcustomer_name()));
	List<order> L=c.list();
	s.getTransaction().commit();
	s.close();
	return L;
    }

   @Override
   public boolean delete(order od) {
	 Session s=hibernateFactory.getSession();
	  s.beginTransaction();
	  s.delete(od);
	  s.getTransaction().commit();
	  s.close();
	return true;
   }
//  public List<order> search(){
//	Session s=hibernateFactory.getSession();
//	s.beginTransaction();
//	String HQL="from order";
//	Query q=s.createQuery(HQL);
//	List<order> L=q.list();
//	s.getTransaction().commit();
//	s.close();
//	return L;
//  }
 
  public void test2(){
//	  List<order> l=new orderDaoImp().serach();
//	  for(order o:l){
//		  System.out.println(o.getOrder_ID()+""+o.getcustomer().getcustomer_ID());
//	  }
	  List<order> L11=daoImpFactory.getOrderDao().serach();
      Gson gs=new GsonBuilder().create();
      for(order ct:L11){
    	
    	  System.out.println(ct.getcustomer().getcustomer_ID());
    	  System.out.println(ct.getcustomer().getcustomer_ID()+" "+ct.getoperator().getUID());
    	  System.out.println(gs.toJson(daoImpFactory.Copy(ct)));
      }
      userDao ud=daoImpFactory.getUserDao();
      user u=new user();
	  user u2=new user();
	  user u3=new user();
	  user u4=new user();
	  u.setUID(2013020101);
	  u.setstatus(1);
	  u.setpsd("123456");
      u.setUname("����Ա��а");
      ud.update(u);
      
      u2.setUID(2013020102);
      u2.setstatus(2);
      u2.setpsd("123456");
      u2.setUname("�ֿ����Ա������");
      ud.update(u2);
      
      u3.setUID(2013020100);
	  u3.setstatus(3);
	  u3.setpsd("123456");
      u3.setUname("˾������");
      u3.setCarID("SA2001");
      ud.update(u3);

	  //ģ��¼��ͻ�
      customerDao cd=new customerDaoImp();
	  customer c=new customer();
	  c.setcustomer_ID(1);
	  c.setphone("13309999222");
	  c.setaddress("����ʡ-����-������");
	  c.setcompany("�н����");
	  c.setcustomer_name("������");
	  cd.update(c);
	  
  }
 
  public void test(){
	  orderDao od=new orderDaoImp();
	  customerDao cd=new customerDaoImp();
	  userDao ud=new userDaoImp();
	  //ģ��¼��ͻ�
	  customer c=new customer();
	  c.setphone("13309999222");
	  c.setaddress("����ʡ-����-������");
	  c.setcompany("�н����");
	  c.setcustomer_name("������");
	  cd.insert(c);
	  customer c2=new customer();
	  c2.setphone("13309988990");
	  c2.setaddress("�Ĵ�ʡ-����-����");
	  c2.setcompany("�н�����");
	  c2.setcustomer_name("���");
	  cd.insert(c2);
      //ģ��1 ����Ա 2 �ֿ����Ա 3.˾�� ¼��;
	  user u=new user();
	  user u2=new user();
	  user u3=new user();
	  user u4=new user();
	  u.setUID(2013020101);
	  u.setstatus(1);
	  u.setpsd("123456");
      u.setUname("����Ա��а");
      ud.insert(u);
      
      u2.setUID(2013020102);
      u2.setstatus(2);
      u2.setpsd("123456");
      u2.setUname("�ֿ����Ա������");
      ud.insert(u2);
      
      u3.setUID(2013020100);
	  u3.setstatus(3);
	  u3.setpsd("123456");
      u3.setUname("˾������");
      ud.insert(u3);
      
      u4.setUID(2013020100);
      u4.setstatus(4);
      u4.setpsd("123456");
      u4.setUname("��");
      ud.insert(u4);
      //��������;
      System.out.println("����������");
      order o=new order();
      o.setdirver(u3);
      o.setoperator(u2);
      o.setcustomer(c);
      o.setDetial("ĳĳ��1��-��С-4��������ĳĳ2-1��-1-3");
      o.setpos("A��-36��-12");
      od.insert(o);  od.insert(o);
      List<order> L11=od.serach();
      Gson gs=new GsonBuilder().create();
      for(order ct:L11){
    	  System.out.println(gs.toJson(ct));
      }
//      //����
//      System.out.println("���⣺");
//      o.setorder_status(2);
//      od.insert(o);
//      List<order> L111=od.serach();
//      for(order ct:L111){
//    	  //System.out.println(gs.toJson(ct, order.class));
//      }
//      System.out.println("��ɣ�");
//      //���
//      o.setorder_status(3);
//      od.insert(o);
//      List<order> L1111=od.serach();
//      for(order ct:L1111){
//    	 // System.out.println(gs.toJson(ct, order.class));
//      }
  }
  @Test
  public void test3(){
         order od=new order();
         od.setOrder_ID(5);
         daoImpFactory.getOrderDao().delete(od);
  }
  

}

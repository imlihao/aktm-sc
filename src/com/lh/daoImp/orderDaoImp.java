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
      u.setUname("操作员吴邪");
      ud.update(u);
      
      u2.setUID(2013020102);
      u2.setstatus(2);
      u2.setpsd("123456");
      u2.setUname("仓库操作员王胖子");
      ud.update(u2);
      
      u3.setUID(2013020100);
	  u3.setstatus(3);
	  u3.setpsd("123456");
      u3.setUname("司机潘子");
      u3.setCarID("SA2001");
      ud.update(u3);

	  //模拟录入客户
      customerDao cd=new customerDaoImp();
	  customer c=new customer();
	  c.setcustomer_ID(1);
	  c.setphone("13309999222");
	  c.setaddress("陕西省-西安-长安县");
	  c.setcompany("中建五局");
	  c.setcustomer_name("罗永浩");
	  cd.update(c);
	  
  }
 
  public void test(){
	  orderDao od=new orderDaoImp();
	  customerDao cd=new customerDaoImp();
	  userDao ud=new userDaoImp();
	  //模拟录入客户
	  customer c=new customer();
	  c.setphone("13309999222");
	  c.setaddress("陕西省-西安-长安县");
	  c.setcompany("中建五局");
	  c.setcustomer_name("罗永浩");
	  cd.insert(c);
	  customer c2=new customer();
	  c2.setphone("13309988990");
	  c2.setaddress("四川省-绵阳-盱眙");
	  c2.setcompany("中建六局");
	  c2.setcustomer_name("蛤蟆");
	  cd.insert(c2);
      //模拟1 操作员 2 仓库操作员 3.司机 录入;
	  user u=new user();
	  user u2=new user();
	  user u3=new user();
	  user u4=new user();
	  u.setUID(2013020101);
	  u.setstatus(1);
	  u.setpsd("123456");
      u.setUname("操作员吴邪");
      ud.insert(u);
      
      u2.setUID(2013020102);
      u2.setstatus(2);
      u2.setpsd("123456");
      u2.setUname("仓库操作员王胖子");
      ud.insert(u2);
      
      u3.setUID(2013020100);
	  u3.setstatus(3);
	  u3.setpsd("123456");
      u3.setUname("司机潘子");
      ud.insert(u3);
      
      u4.setUID(2013020100);
      u4.setstatus(4);
      u4.setpsd("123456");
      u4.setUname("我");
      ud.insert(u4);
      //创建订单;
      System.out.println("创建订单：");
      order o=new order();
      o.setdirver(u3);
      o.setoperator(u2);
      o.setcustomer(c);
      o.setDetial("某某—1吨-大小-4立方，米某某2-1斤-1-3");
      o.setpos("A区-36排-12");
      od.insert(o);  od.insert(o);
      List<order> L11=od.serach();
      Gson gs=new GsonBuilder().create();
      for(order ct:L11){
    	  System.out.println(gs.toJson(ct));
      }
//      //出库
//      System.out.println("出库：");
//      o.setorder_status(2);
//      od.insert(o);
//      List<order> L111=od.serach();
//      for(order ct:L111){
//    	  //System.out.println(gs.toJson(ct, order.class));
//      }
//      System.out.println("完成：");
//      //完成
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

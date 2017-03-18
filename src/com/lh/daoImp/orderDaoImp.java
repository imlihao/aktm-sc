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
	  s.saveOrUpdate(od);
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
	  List<order> l=new orderDaoImp().serach();
	  for(order o:l){
		  System.out.println(o.getOrder_ID()+""+o.getcustomer().getcustomer_ID());
	  }
	  
  }
  public void test(){
	  orderDao od=new orderDaoImp();
	  customerDao cd=new customerDaoImp();
	  userDao ud=new userDaoImp();
	  //模拟录入客户
	  customer c=new customer();

	  c.setphone("1300000001");
	  c.setaddress("陕西省-西安-长安县");
	  c.setcompany("中建五局");
	  c.setcustomer_name("罗永浩");
	  //cd.insert(c);

	  c.setphone("1300000001");
	  c.setaddress("四川省-西安-长安县");
	  c.setcompany("中建六局");
	  c.setcustomer_name("二傻子");
	 // cd.insert(c);
      //模拟1 操作员 2 仓库操作员 3.司机 录入;
	  user u=new user();
	  u.setUID(2014020105);
	  u.setstatus(1);
	  u.setpsd("psdpsdpdd");
      u.setUname("普通操作员");
      //ud.insert(u);
      
      u.setUID(2014020106);
	  u.setstatus(2);
	  u.setpsd("psdpsdpdd");
      u.setUname("仓库操作员");
     // ud.insert(u);
      
      u.setUID(2014020107);
	  u.setstatus(3);
	  u.setpsd("psdpsdpdd");
      u.setUname("司机");
      //ud.insert(u);
      //显示
      System.out.println("现在数据库里有：");
      List<customer> L=cd.serach();
      Gson gs=new GsonBuilder().create();
      
      for(customer ct:L){
    	  System.out.println(gs.toJson(ct));
      }
      List<user> L1=ud.serach();
      for(user ct:L1){
    	  System.out.println(gs.toJson(ct));
      }
      //创建订单;
      System.out.println("创建订单：");
      order o=new order();
      o.setOrder_ID(2);
      c.setcustomer_ID(2);
      o.setcustomer(c); 
      u.setUID(2014020106);
      o.setoperator(u);
      u.setUID(2014020107);
      o.setdirver(u);
      o.setDetial("某某—1吨-大小-4立方，米某某2-1斤-1-3");
      o.setpos("A区-36排-12");
      od.insert(o);
      List<order> L11=od.serach();
      for(order ct:L11){
    	  System.out.println(gs.toJson(o));
      }
      //出库
      System.out.println("出库：");
      o.setorder_status(2);
      od.insert(o);
      List<order> L111=od.serach();
      for(order ct:L111){
    	  //System.out.println(gs.toJson(ct, order.class));
      }
      System.out.println("完成：");
      //完成
      o.setorder_status(3);
      od.insert(o);
      List<order> L1111=od.serach();
      for(order ct:L1111){
    	 // System.out.println(gs.toJson(ct, order.class));
      }
  }
  @Test
  public void test3(){
	  orderDao od=new orderDaoImp();
	  order o=new order();
	  o.setDetial("dsdsds");
	  Gson gs=new GsonBuilder().create();
	  customer su=new customer();
	  su.setcustomer_ID(23);
	  o.setcustomer(su);
	  System.out.println(gs.toJson(o));
	  List<order> L11=od.serach();
	  o=L11.get(0);
	 // L11.get(0).getoperator().getCarID();
	  System.out.println(o.getOrder_ID()+""+o.getDetial()+"");
	  //System.out.println(gs.toJson(L11));
      for(order ct:L11){
    	  System.out.println(gs.toJson(this.Copy(ct)));
      }
  }
  public order Copy(order od){
	order nod=new order();
//	nod.setDetial(od.getDetial());
//	nod.setorder_status(od.getorder_status());
//	nod.setOrder_ID(od.getOrder_ID());
//	nod.setpos(od.getpos());
//	nod.setOrder_time(od.getOrder_time());
//	user u=new user();
//	u.setCarID(od.getoperator().getCarID());
//	u.setpsd(od.getoperator().getpsd());
//	u.setstatus(od.getoperator().getstatus());
//	u.setUID(od.getoperator().getUID());
//	u.setUname(od.getoperator().getUname());
//	user d=new user();
//	d.setCarID(od.getdirver().getCarID());
//	d.setpsd(od.getdirver().getpsd());
//	d.setstatus(od.getdirver().getstatus());
//	d.setUID(od.getdirver().getUID());
//	d.setUname(od.getdirver().getUname());
//	customer cus=new customer();
//	cus.setaddress(od.getcustomer().getaddress());
//	cus.setcompany(od.getcustomer().getcompany());
//	cus.setcustomer_ID(od.getcustomer().getcustomer_ID());
//	cus.setphone(od.getcustomer().getphone());
//	cus.setcustomer_name(od.getcustomer().getcustomer_name());
	nod.setcustomer(new customerDaoImp().serach(od.getcustomer()).get(0));
	nod.setoperator(new userDaoImp().serach(od.getoperator()).get(0));
	nod.setoperator(new userDaoImp().serach(od.getdirver()).get(0));
	return nod;	  
  }
}

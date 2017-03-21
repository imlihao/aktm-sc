package com.lh.daoImp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.lh.dao.customerDao;
import com.lh.dbc.hibernateFactory;
import com.lh.vo.customer;

public class customerDaoImp implements customerDao{
	 @Override
	 public boolean insert(customer a){
		 Session s=hibernateFactory.getSession();
		 s.beginTransaction();
		 s.save(a);
		 s.getTransaction().commit();
		 s.close();
		return true;
	 }
	 @Override
	 public boolean delete(customer a){
		 Session s=hibernateFactory.getSession();
		 s.beginTransaction();
		 s.delete(a);
		 s.getTransaction().commit();
		 s.close();
		return true;
	 }
	 @Override
     public List<customer> serach() {
    	 Session s=hibernateFactory.getSession();
    		s.beginTransaction();
    		@SuppressWarnings("deprecation")
			Criteria c=s.createCriteria(customer.class);
    		List<customer> L=c.list();
    		s.getTransaction().commit();
    		s.close();
    		return L;
	 }
	 @Override
	 public List<customer> serach(customer cus) {
		 Session s=hibernateFactory.getSession();
 		 s.beginTransaction();
 	   	@SuppressWarnings("deprecation")
		Criteria c=s.createCriteria(customer.class);
 	   	c.add(Restrictions.eq("customer_ID", cus.getcustomer_ID()));
// 	    c.add(Restrictions.eq("phone", cus.getphone()));
// 	    c.add(Restrictions.eq("customer_name", cus.getcustomer_name()));
 		List<customer> L=c.list();
 		s.getTransaction().commit();
 		s.close();
 		return L;
	  }
		@Override
		public boolean update(customer cus) {
			 Session s=hibernateFactory.getSession();
			 s.beginTransaction();
			 s.update(cus);
			 s.getTransaction().commit();
			 s.close();
			return true;
		}
	 @Test
	 public void test(){
        customer cu=new customer();
        cu.setaddress("李豪李豪李豪李豪李豪李豪");
        cu.setphone("18329843");
        cu.setcustomer_name("ddd");
        new customerDaoImp().insert(cu);
        new customerDaoImp().insert(cu);
        new customerDaoImp().insert(cu);
        List<customer> l=new customerDaoImp().serach();
  	    for(customer o:l){
  		  System.out.println(o.getaddress()+"  "+o.getcustomer_ID()+"  "+o.getphone());
  	    }
  	    cu.setcustomer_ID(4);
        cu.setphone("");
  	    List<customer> l1=new customerDaoImp().serach(cu);
	    for(customer o:l1){
		  System.out.println("个性化寻找:"+o.getaddress()+"  "+o.getcustomer_ID()+"  "+o.getphone());
	    }
	 }

}

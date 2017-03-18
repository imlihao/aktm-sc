package com.lh.daoImp;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import com.lh.dao.userDao;
import com.lh.dbc.hibernateFactory;
import com.lh.vo.user;

public class userDaoImp implements userDao {

	@Override
	public List<user> serach() {
		 Session s=hibernateFactory.getSession();
			s.beginTransaction();
			@SuppressWarnings("deprecation")
			Criteria c=s.createCriteria(user.class);
			List<user> L=c.list();
			s.getTransaction().commit();
			s.close();
			return L;
	}

	@Override
	public List<user> serach(user user) {
		Session s=hibernateFactory.getSession();
		 s.beginTransaction();
	   	@SuppressWarnings("deprecation")
		Criteria c=s.createCriteria(user.class);
	   	c.add(Restrictions.eq("UID", user.getUID()));
//	    c.add(Restrictions.eq("phone", cus.getphone()));
//	    c.add(Restrictions.eq("customer_name", cus.getcustomer_name()));
		List<user> L=c.list();
		s.getTransaction().commit();
		s.close();
		return L;
	}

	@Override
	public boolean insert(user user) {
		  Session s=hibernateFactory.getSession();
		  s.beginTransaction();
		  s.saveOrUpdate(user);
		  s.getTransaction().commit();
		  s.close();
		  return true;
	}

	@Override
	public boolean delete(user user) {
		 Session s=hibernateFactory.getSession();
		  s.beginTransaction();
		  s.delete(user);
		  s.getTransaction().commit();
		  s.close();
		  return true;
	}


}

package com.lh.message;

import java.util.LinkedList;
import java.util.List;

import com.lh.daoImp.daoImpFactory;
import com.lh.filter.messageType;
import com.lh.vo.customer;
import com.lh.vo.order;
import com.lh.vo.user;
/**
 * 
 * @author lihao
 *
 */
public class msgLoginSuccess extends messageItype{
	/**
	 * 
	 * @param u ×ÔÉí
	 */
	public msgLoginSuccess(user u) {
		this.Itype=messageType.loginSuccess;
		my=u;
	    my.setpsd("");
		List<order> L=daoImpFactory.getOrderDao().serach();		
		for(order od:L){
			this.orders.add(daoImpFactory.Copy(od));		
		}
		this.users=daoImpFactory.getUserDao().serach();
		for(user us:users){
			us.setpsd("");
		}
		this.customers=daoImpFactory.getCustomerDao().serach();
	}
    public user my;
    public List<order> orders=new LinkedList<order>();
    public List<user> users=new LinkedList<user>();
    public List<customer> customers=new LinkedList<customer>(); 
}

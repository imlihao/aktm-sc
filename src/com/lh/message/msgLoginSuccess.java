package com.lh.message;

import java.util.LinkedList;
import java.util.List;

import com.lh.daoImp.daoImpFactory;
import com.lh.filter.messageType;
import com.lh.vo.order;
import com.lh.vo.user;

public class msgLoginSuccess extends messageItype{
	public msgLoginSuccess(user u) {
		this.Itype=messageType.loginSuccess;
		my=u;
		this.orders=new LinkedList<order>();
		List<order> L=daoImpFactory.getOrderDao().serach();
		for(order od:L){
			this.orders.add(daoImpFactory.Copy(od));		
		}
	}
    public user my;
    public List<order> orders;
}

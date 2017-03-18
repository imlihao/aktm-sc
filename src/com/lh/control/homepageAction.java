package com.lh.control;

import com.opensymphony.xwork2.ActionSupport;

public class homepageAction extends ActionSupport{
  public String execute(){
//      ordersDao od=new ordersDao();
//      List<order> L=od.search();
//      if(L==null)return ERROR;
//      ActionContext.getContext().getValueStack().set("orders", L);
	  return SUCCESS;
  }
}

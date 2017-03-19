package com.lh.daoImp;

import com.lh.dao.customerDao;
import com.lh.dao.orderDao;
import com.lh.dao.userDao;
import com.lh.vo.order;

public class daoImpFactory {
   public static orderDao getOrderDao(){
	   return new orderDaoImp();  
   }
   public static userDao getUserDao(){
	   return new userDaoImp();  
   }
   public static customerDao getCustomerDao(){
	   return new customerDaoImp();  
   }
   
   /**
    * @param od  需要转换的对象，hibernate从持久层获取的vo对象的嵌套对象get方法会报错。
    * @return 转换过的order对象
    */
   public static order Copy(order od){
		order nod=new order();
//		nod.setDetial(od.getDetial());
//		nod.setorder_status(od.getorder_status());
//		nod.setOrder_ID(od.getOrder_ID());
//		nod.setpos(od.getpos());
//		nod.setOrder_time(od.getOrder_time());
//		user u=new user();
//		u.setCarID(od.getoperator().getCarID());
//		u.setpsd(od.getoperator().getpsd());
//		u.setstatus(od.getoperator().getstatus());
//		u.setUID(od.getoperator().getUID());
//		u.setUname(od.getoperator().getUname());
//		user d=new user();
//		d.setCarID(od.getdirver().getCarID());
//		d.setpsd(od.getdirver().getpsd());
//		d.setstatus(od.getdirver().getstatus());
//		d.setUID(od.getdirver().getUID());
//		d.setUname(od.getdirver().getUname());
//		customer cus=new customer();
//		cus.setaddress(od.getcustomer().getaddress());
//		cus.setcompany(od.getcustomer().getcompany());
//		cus.setcustomer_ID(od.getcustomer().getcustomer_ID());
//		cus.setphone(od.getcustomer().getphone());
//		cus.setcustomer_name(od.getcustomer().getcustomer_name());
		nod.setcustomer(new customerDaoImp().serach(od.getcustomer()).get(0));
		nod.setoperator(new userDaoImp().serach(od.getoperator()).get(0));
		nod.setdirver(new userDaoImp().serach(od.getdirver()).get(0));
		return nod;	  
	  }
}

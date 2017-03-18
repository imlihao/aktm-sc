package com.lh.vo;
public class order {
   private int order_ID;
   private int order_status=1;
   private long order_time=System.currentTimeMillis();
   private customer customer;
   private user dirver;
   private user operator;
   private String detial;
   private String pos;
   //#region 货物信息
   public void setDetial(String detial){
	   this.detial=detial;
   }
   public String getDetial(){
	   return this.detial;
   }
   public void setpos(String pos){
	   this.pos=pos;
   }
   public String getpos(){
	   return this.pos;
   }
   //#end region
   //#region 订单头信息
   public void setOrder_ID(int id){
	   this.order_ID=id;
   }
   public int getOrder_ID(){
	   return this.order_ID;
   }
   
   public void setOrder_time(long time){
	   this.order_time=time;
   }
   public long getOrder_time(){
	   return this.order_time;
   }
   /**
    * 
    * @param mode 1.新订单，未出库 2.已出库，未运送 3.运送成功
    */
   public void setorder_status(int mode){
	   this.order_status=mode;
   }
   /**
    * 
    * @return  1.新订单，未出库 2.已出库，未运送 3.运送成功
    */
   public int getorder_status(){
	   return this.order_status;
   }
   //#end region
   public void setcustomer(customer c){
	   this.customer=c;
   }
   public customer getcustomer(){
	   return customer;
	   
   }
   public void setdirver(user c){
	   this.dirver=c;
   }
   public user getdirver(){
	   return dirver;	   
   }
   public void setoperator(user c){
	   this.operator=c;
   }
   public user getoperator(){
	   return operator;	   
   }
}

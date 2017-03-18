package com.lh.vo;
/**
 * 
 * @author lihao
 */
public class customer {
  private int customer_ID;
  private String customer_name;
  private String address;
  private String phone;
  private String company;
  /**
   * @param id  主键，自动生成；
   */
  public void setcustomer_ID(int id){
     	  this.customer_ID=id;
  }
  public int getcustomer_ID(){
	  return customer_ID;
  }
  public void setcustomer_name(String name){
	  this.customer_name=name;
  }
  public String getcustomer_name(){
	return customer_name;	  
  }
  
  public void setaddress(String address){
	  this.address=address;
  }
  public String getaddress(){
	return address;	  
  }
  
  public void setphone(String phone){
	  this.phone=phone;
  }
  public String getphone(){
	return phone;	  
  }
  public void setcompany(String company){
	  this.company=company;
  }
  public String getcompany(){
	return company;	  
  }	  
}

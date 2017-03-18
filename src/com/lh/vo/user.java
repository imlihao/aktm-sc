package com.lh.vo;

public class user {
   private String Uname;
   private long UID;
   private String psd;
   private int status;
   private String CarID=null;
   public void setCarID(String CarID){
	   this.CarID=CarID;
   }
   public String getCarID(){
	   return CarID;
   }
   /**
    * 
    * @param status status 1.普通操作员 2.仓库操作员 3.司机 4.管理员
    */
   public void setstatus(int status){
	   if(status<1||status>4){
		   status=1;
	       System.out.println("user的身份设置出错");
	   }
	   this.status=status;
   }
   /**
    * 
    * @return status 1.普通操作员 2.仓库操作员 3.司机 4.管理员
    */
   public int getstatus(){
	   return status;
   }
   public void setUname(String uname){
	   this.Uname=uname;
   }
   public void setpsd(String psd){
	   this.psd=psd;
   }
   public void setUID(long UID){
	  this.UID=UID;
   }
   public String getUname(){
	  return Uname;	   
   }
   public String getpsd(){
	  return psd;
   }
   public long getUID(){
	  return UID;	   
   }
}

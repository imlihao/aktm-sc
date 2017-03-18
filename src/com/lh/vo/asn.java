package com.lh.vo;

public class asn {
   private int asn;
   private int sku;
   private int num;
   private int weight;
   
   public void setWeight(int weight){
	   this.weight=weight;
   }
   public int getWeight(){
	return weight;
	}
   public void setAsn(int asn){
	   this.asn=asn;
   }
   public void setSku(int sku){
	   this.sku=sku;
   }
   public void  setNum(int num){
	   this.num=num;
   }
   public int getAsn(){
	   return asn;
   }
   public int getSku(){
	   return sku;
   }
   public int getNum(){
	   return num;
   }
}

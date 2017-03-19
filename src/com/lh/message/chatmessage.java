package com.lh.message;

import com.lh.filter.messageType;

public class chatmessage extends messageItype{
	 public chatmessage() {
		this.Itype=messageType.CahtServerMessge;
	}
	 public String msg ;
     public int pos=1;
}

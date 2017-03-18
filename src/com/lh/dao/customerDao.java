package com.lh.dao;

import java.util.List;
import com.lh.vo.customer;
public interface customerDao {
	   public List<customer> serach();
	   public List<customer> serach(customer cus);
	   public boolean insert(customer cus);
	   public boolean delete(customer cus);
}

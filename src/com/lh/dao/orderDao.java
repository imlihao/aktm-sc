package com.lh.dao;

import java.util.List;

import com.lh.vo.order;

public interface orderDao {
	   public List<order> serach();
	   public List<order> serach(order od);
	   public boolean insert(order od);
	   public boolean delete(order od);
}

package com.fashionagariya.springback.dao;

import java.util.List;

import com.fashionagariya.springback.dto.OrderDetail;

public interface OrderDetailDAO {
	
	public List<OrderDetail> list(int userId);
	public OrderDetail get(int id);

}

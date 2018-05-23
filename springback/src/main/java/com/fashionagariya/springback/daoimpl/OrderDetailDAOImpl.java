package com.fashionagariya.springback.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fashionagariya.springback.dao.OrderDetailDAO;
import com.fashionagariya.springback.dto.OrderDetail;

@Repository("orderDetailDAO")
@Transactional
public class OrderDetailDAOImpl implements OrderDetailDAO {
	
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<OrderDetail> list(int userId) {
		String query = "FROM OrderDetail WHERE user.id = :userId ORDER BY id DESC";
		return sessionFactory.getCurrentSession().createQuery(query, OrderDetail.class).setParameter("userId", userId).getResultList();
	}

	@Override
	public OrderDetail get(int id) {
		return sessionFactory.getCurrentSession().get(OrderDetail.class, id);
	}

}

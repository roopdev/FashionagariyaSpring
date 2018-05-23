package com.fashionagariya.springback.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fashionagariya.springback.dao.UserDAO;
import com.fashionagariya.springback.dto.Address;
import com.fashionagariya.springback.dto.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean addUser(User user) {
		try {
			sessionFactory.getCurrentSession().persist(user);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addAddress(Address address) {
		try {
			sessionFactory.getCurrentSession().persist(address);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}


	@Override
	public User getByEmail(String email) {
		String selectQuery = "FROM User WHERE email = :email";
		
		try {
			return sessionFactory.getCurrentSession().createQuery(selectQuery, User.class).setParameter("email", email).getSingleResult();
		} catch (Exception ex) {
			/*ex.printStackTrace();*/
			return null;
		}
	}

	@Override
	public Address getBillingAddress(User user) {
		String selectQuery = "FROM Address WHERE user = :user AND billing = :billing";
		
		try {
			return sessionFactory.getCurrentSession().createQuery(selectQuery, Address.class).setParameter("user", user).setParameter("billing", true).getSingleResult();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Address> listShippingAddress(User user) {
		String selectQuery = "FROM Address WHERE user = :user AND shipping = :shipping ORDER BY id DESC";
		
		try {
			return sessionFactory.getCurrentSession().createQuery(selectQuery, Address.class).setParameter("user", user).setParameter("shipping", true).getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Address getAddress(int addressId) {
		try {
			return sessionFactory.getCurrentSession().get(Address.class, addressId);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	@Override
	public boolean updateAddress(Address address) {
		try {
			sessionFactory.getCurrentSession().update(address);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public User getUser(int id) {
		try {
			return sessionFactory.getCurrentSession().get(User.class, id);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

}

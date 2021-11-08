package com.kunavich.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.kunavich.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		Session session = sessionFactory.getCurrentSession();
		Query<Customer> query =session.createQuery("from Customer order by lastName",Customer.class);	
		List<Customer> customers = query.getResultList();	
		return customers;		
	}

	@Override
	public void saveCustomer(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int id) {
		Session session = sessionFactory.getCurrentSession();
		Customer customer= session.get(Customer.class,id);
		return customer;
	}

	@Override
	public void deleteCustomer(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from Customer where id=:customerId");
		query.setParameter("customerId",id);
		query.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		 // get the current hibernate session
        Session session = sessionFactory.getCurrentSession();
        Query query = null;
        // only search by name if theSearchName is not empty
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            // search for firstName or lastName ... case insensitive
        	query =session.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
        	query.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
        }
        else {
            // theSearchName is empty ... so just get all customers
        	query =session.createQuery("from Customer", Customer.class);            
        }
        // execute query and get result list
        List<Customer> customers = query.getResultList();    
        // return the results        
        return customers;
	}	

}

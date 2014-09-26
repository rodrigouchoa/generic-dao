package com.rodrigouchoa.generic.dao.action;

import java.math.BigDecimal;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rodrigouchoa.generic.dao.GenericDaoJpa;
import com.rodrigouchoa.generic.dao.GenericDaoJpa.MatchMode;
import com.rodrigouchoa.generic.dao.entity.Customer;
import com.rodrigouchoa.generic.dao.entity.Invoice;
import com.rodrigouchoa.generic.dao.entity.Order;


/**
 * A simple JSF Managed Bean for index.xhtml
 * 
 * 
 * @author Rodrigo Uchôa (http://rodrigouchoa.wordpress.com)
 *
 */
@Named
@RequestScoped
public class IndexAction {
	
	/* In case you want o use hibernate directly */
	//@Inject
	//private GenericDaoHibernate dao;
	
	@Inject
	private GenericDaoJpa dao;
	
	
	@SuppressWarnings("unused")
	public void go() {
		//find by id
		Customer c1 = dao.find(Customer.class, new Long(1));
		Invoice i1 = dao.find(Invoice.class, new Long(3));
		
		//find all without any order
		List<Customer> listCustomers = dao.findAll(Customer.class);
		List<Invoice> listInvoices = dao.findAll(Invoice.class);
		
		//finding all with an order
		List<Customer> listCustomers2 = dao.findAll(Customer.class, Order.DESC, "name");
		List<Invoice> listInvoices2 = dao.findAll(Invoice.class, Order.ASC, "description");
		
		//findind by a non-textual property
		List<Customer> listCustomers3 = dao.findByProperty(Customer.class, "age", 33);
		List<Invoice> listInvoices3 = dao.findByProperty(Invoice.class, "value", new BigDecimal("90.00"));
		
		//finding by a text property, using the like operator.
		List<Customer> listCustomers4 = dao.findByProperty(Customer.class, "name", "rack", MatchMode.ANYWHERE); //should return Barack Obama
		
		//etc etc etc
		
		//you get the idea
		
		
	}
	

}

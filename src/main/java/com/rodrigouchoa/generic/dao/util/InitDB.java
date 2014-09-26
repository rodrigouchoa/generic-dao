package com.rodrigouchoa.generic.dao.util;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.rodrigouchoa.generic.dao.GenericDaoHibernate;
import com.rodrigouchoa.generic.dao.entity.Customer;
import com.rodrigouchoa.generic.dao.entity.Invoice;


/**
 * Helper class to insert data into the database.
 * 
 * @author Rodrigo Uchôa (http://rodrigouchoa.wordpress.com)
 *
 */
@Singleton
@Startup
public class InitDB {
	
	@Inject
	private GenericDaoHibernate dao;
	
	@PostConstruct
	public void init() {
		Invoice i1 = new Invoice("Invoice 1", new BigDecimal("10.00"));
		Invoice i2 = new Invoice("Invoice 2", new BigDecimal("20.00"));
		Invoice i3 = new Invoice("Invoice 3", new BigDecimal("30.00"));
		Invoice i4 = new Invoice("Invoice 4", new BigDecimal("40.00"));
		Invoice i5 = new Invoice("Invoice 5", new BigDecimal("50.00"));
		Invoice i6 = new Invoice("Invoice 6", new BigDecimal("60.00"));
		Invoice i7 = new Invoice("Invoice 7", new BigDecimal("70.00"));
		Invoice i8 = new Invoice("Invoice 8", new BigDecimal("80.00"));
		Invoice i9 = new Invoice("Invoice 9", new BigDecimal("90.00"));
		Invoice i10 = new Invoice("Invoice 10", new BigDecimal("100.00"));
		
		Customer c1 = new Customer("Barack Obama", 33, i1, i2, i3, i4);
		Customer c2 = new Customer("Peter Gabriel", 45, i5, i6, i7, i8);
		Customer c3 = new Customer("Scarlett Johansson", 33, i9, i10);
		
		dao.save(c1);
		dao.save(c2);
		dao.save(c3);
		
	}

}

package com.rodrigouchoa.generic.dao.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_CUSTOMER")
public class Customer extends BaseEntity<Long> {
	
	@Id
	@GeneratedValue
	@Column(name = "id_customer")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "age")
	private Integer age;
	
	@OneToMany(mappedBy = "customer", cascade = {CascadeType.PERSIST})
	private List<Invoice> invoices = new ArrayList<Invoice>();
	
	public Customer() {}
	
	public Customer(String name, Integer age, Invoice...invoices) {
		this.name = name;
		this.age = age;
		for (Invoice i : invoices) {
			addInvoice(i);
		}
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}
	
	public void addInvoice(Invoice invoice) {
		invoices.add(invoice);
		invoice.setCustomer(this);
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}


}

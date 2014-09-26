package com.rodrigouchoa.generic.dao.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_INVOICE")
public class Invoice extends BaseEntity<Long> {
	
	@Id
	@GeneratedValue
	@Column(name = "id_invoice")
	private Long id;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "value")
	private BigDecimal value;
	
	@ManyToOne
	@JoinColumn(name = "id_customer", foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT))
	private Customer customer;
	
	public Invoice() {}
	
	public Invoice(String description, BigDecimal value) {
		this.description = description;
		this.value = value;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


}

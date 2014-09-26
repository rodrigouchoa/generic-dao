package com.rodrigouchoa.generic.dao.entity;

/**
 * <p>
 * The ordering of a collection of objects.
 * </p>
 * 
 * <ul>
 * <li><b>ASC</b>: ascending order.
 * <li><b>DESC</b>: descending order.
 * </ul>
 */
public enum Order {

	ASC, DESC;

	
	public boolean isAscOrder() {
		return ASC.equals(this);
	}
}

package com.rodrigouchoa.generic.dao.entity;

import java.io.Serializable;

import org.hibernate.proxy.HibernateProxyHelper;

/**
 * Base class for all JPA entities.
 * 
 * @author Rodrigo Uchôa (http://rodrigouchoa.wordpress.com)
 *
 */
public abstract class BaseEntity<T extends Serializable> {
	
	
	/**
	 * This method should return the primary key.
	 * 
	 * @return
	 */
	public abstract T getId();
	
	/* As a starting point, we provide a basic mean for entities
	 * to test for equality using their "id".
	 * 
	 * Please note that THIS IS NOT ALWAYS ACCEPTABLE since newly generated
	 * ids might break Set/Collection semantics. Please refer to the documentarion
	 * before doing something like this.
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof BaseEntity)) {
			return false;
		}
		if (getId() == null || ((BaseEntity<?>) obj).getId() == null) {
			return false;
		}
		if (!getId().equals(((BaseEntity<?>) obj).getId())) {
			return false;
		}
		if (!HibernateProxyHelper.getClassWithoutInitializingProxy(obj)
				.isAssignableFrom(this.getClass())) {
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		return getId() == null ? super.hashCode() : getId().hashCode();
	}

}

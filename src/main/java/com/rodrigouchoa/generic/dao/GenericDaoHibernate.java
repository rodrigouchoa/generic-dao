package com.rodrigouchoa.generic.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.NotFoundException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.rodrigouchoa.generic.dao.entity.BaseEntity;
import com.rodrigouchoa.generic.dao.entity.Order;

/**
 * A generic DAO implementation using Hibernate's Session directly, not JPA.
 * 
 * <p>The primary noticeable difference is the use of "hibernate criteria" instead of "JPA criteria". 
 * 
 * @author Rodrigo Uchôa (http://rodrigouchoa.wordpress.com)
 *
 */
@Stateless
public class GenericDaoHibernate {

	//take a look at the getSession() method.
	//This is not used "directly".
	@Inject
	private EntityManager entityManager;
	

	/**
	 * Saves an entity.
	 * 
	 * @param entity
	 * @return newly created id for the entity.
	 */
	@SuppressWarnings("unchecked")
	public <T extends BaseEntity<PK>, PK extends Serializable> PK save(T entity) {
		return (PK) getSession().save(entity);
	}

	/**
	 * Marges objects with the same identifier within a session into a newly created object.
	 * 
	 * @param entity
	 * @return a newly created instance merged.
	 */
	@SuppressWarnings("unchecked")
	public <T extends BaseEntity<PK>, PK extends Serializable> T merge(T entity) {
		return (T) getSession().merge(entity);
	}	
	
	/**
	 * Deletes tne entity.
	 * 
	 * @param clazz
	 * @param id
	 * @throws NotFoundException if the id does not exist.
	 */
	public <T extends BaseEntity<PK>, PK extends Serializable> void delete(Class<T> clazz, PK id) {
		T entity = find(clazz, id);
		if (entity != null) { 
			getSession().delete(entity);
		} else {
			throw new NotFoundException();
		}
	}

	/**
	 * Find an entity by its identifier.
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends BaseEntity<?>> T find(Class<T> clazz, Serializable id) {
		return (T) getSession().get(clazz, id);
	}

	/**
	 * Finds entities by one of its properties. 
	 * 
	 * 
	 * 
	 * @param clazz the entity class.
	 * @param propertyName the property name.
	 * @param value the value by which to find.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends BaseEntity<?>> List<T> findByProperty(Class<T> clazz, String propertyName, Object value) {
		return getSession().createCriteria(clazz).add(Restrictions.eq(propertyName, value)).list();
	}
	
	/**
	 * Finds entities by a String property specifying a MatchMode. This search 
	 * is case insensitive.
	 * 
	 * @param clazz the entity class.
	 * @param propertyName the property name.
	 * @param value the value to check against.
	 * @param matchMode the match mode: EXACT, START, END, ANYWHERE.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends BaseEntity<?>> List<T> findByProperty(Class<T> clazz, String propertyName, String value, MatchMode matchMode){
		if (matchMode != null){
			return getSession().createCriteria(clazz).add(Restrictions.ilike(propertyName, value, matchMode)).list();
		}else{
			return getSession().createCriteria(clazz).add(Restrictions.ilike(propertyName, value, MatchMode.EXACT)).list();
		}
	}

	/**
	 * Finds all objects of an entity class.
	 * 
	 * @param clazz the entity class.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends BaseEntity<?>> List<T> findAll(Class<T> clazz) {
		return getSession().createCriteria(clazz).list();
	}

	/**
	 * Finds all objects of a class by the specified order.
	 * 
	 * @param clazz the entity class.
	 * @param order the order: ASC or DESC.
	 * @param propertiesOrder the properties on which to apply the ordering.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends BaseEntity<?>> List<T> findAll(Class<T> clazz, Order order, String... propertiesOrder) {
		Criteria criteria = getSession().createCriteria(clazz);

		for (String propertyOrder : propertiesOrder) {
			if (order.isAscOrder()) {
				criteria.addOrder(org.hibernate.criterion.Order.asc(propertyOrder));
			} else {
				criteria.addOrder(org.hibernate.criterion.Order.desc(propertyOrder));
			}
		}

		return criteria.list();
	}
	

	protected Session getSession() {
		return (Session) entityManager.getDelegate();
	}

}

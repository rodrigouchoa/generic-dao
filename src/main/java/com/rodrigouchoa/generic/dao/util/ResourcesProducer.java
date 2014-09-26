package com.rodrigouchoa.generic.dao.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class ResourcesProducer {

	@Produces
	@PersistenceContext( unitName = "generic-dao")
	private EntityManager entityManager;
}

package br.com.jpautil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	private static EntityManagerFactory factory = null;
	
	static {
		if(factory == null) {
			factory = Persistence.createEntityManagerFactory("JPA_HIBERNATE");	
		}
		
	}
	
	public static EntityManager getEntityManager() {
			return factory.createEntityManager();
	}
	
	//metodo para retornar o ID do objeto passado por parametro
	public static Object getPrimaryKey(Object entidade) {
		return factory.getPersistenceUnitUtil().getIdentifier(entidade);
	}

}

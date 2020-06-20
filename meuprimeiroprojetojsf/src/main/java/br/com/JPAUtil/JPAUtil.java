package br.com.JPAUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class JPAUtil {

	private EntityManagerFactory factory = null;

	public JPAUtil() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory("meuprimeiroprojetojsf");
		}
	}

	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

	public Object getPrimaryKey(Object entity) {
		return factory.getPersistenceUnitUtil().getIdentifier(entity);
	}

	// na configuração normal do hibernate use o método simgletom ou seja criar
	// metodos estáticos para ser acessado pelo sistema uma unica vez
	// é somente uma instância
	/*
	 * public static EntityManagerFactory factory = null;
	 * 
	 * static { init(); }
	 * 
	 * private static void init() { try { if(fatory == null) { factory=
	 * Persistence.createEntityManagerFactory("meuprimeiroprojetojsf"); } }catch
	 * (Exception e) { e.printStackTrace(); } //gerenciador de entidade public
	 * static EntityManager getEntityManager() { return
	 * factory.createEntityManager(); // provê a parte de persistência } }
	 */

}

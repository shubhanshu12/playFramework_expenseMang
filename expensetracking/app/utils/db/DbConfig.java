package utils.db;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import model.Client;
import model.Expense;
import play.db.jpa.DefaultJPAApi.JPAApiProvider;
import play.db.jpa.JPAApi;
import play.db.jpa.JPAEntityManagerContext;

import javax.inject.Inject;

/**
 * @author shubhanshu
 *
 */
public final class DbConfig {
	private static Configuration con;
	private static SessionFactory sf;
	private static Session session;
	private static Transaction tx = null;
	private static EntityManagerFactory emf;
	private static EntityManager em;
	
	static {
		con = new Configuration().configure();
		con.addAnnotatedClass(Client.class);
		con.addAnnotatedClass(Expense.class);
		sf = con.buildSessionFactory();
//		emf = Persistence.createEntityManagerFactory("defaultPersistenceUnit");
//		em = emf.createEntityManager();
		
	}

	public static Transaction getTx() {
		return tx;
	}

	public static void setTx(Transaction tx) {
		DbConfig.tx = tx;
	}

	public static SessionFactory getSf() {
		return sf;
	}

	public static Session getSession() {
		session = sf.openSession();
		return session;
	}

	public EntityManager getEm() {
		return em;
	}
	
}

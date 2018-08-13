package model.facade;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.inject.Inject;

import model.Client;
import model.Expense;
import play.Logger;
import play.db.jpa.JPA;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import utils.db.DatabaseEntityManagerLocal;
import utils.db.DbConfig;


/**
 * @author shubhanshu
 *
 */
public class ExpenseFacadeImpl implements ExpenseFacadeLocal{
	
	private final Logger.ALogger log = Logger.of(this.getClass());
	
	@Inject
	DatabaseEntityManagerLocal db;
	
	@Override
	@Transactional
	public Expense create(String title, String description, String currency, Double amount, String timeStamp, Client client) {
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pp");
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
		log.debug("create expense");
		
		Expense x = new Expense().with(title, description, currency, amount, timeStamp, client);
		List<Expense> expenses = client.getExpenses();
    	expenses.add(x);
    	client.setExpenses(expenses);
   		db.save(x);
// 		entityManager.persist(x);
   		db.merge(client);
   		
		return x;	
	}
	
	@Override
	public Expense find(Long id) {
		log.debug("find expense: " + id);
		
		Expense expense = null;
		Session session = DbConfig.getSession();
		try {
			expense = session.get(Expense.class, id);
		} catch (Exception e) {
			log.error("find expense failed: " + e);
		}
		session.close();
		
		return expense;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Expense> findExpenseByClient(Long id) {
		log.debug("find expense for client: " + id);
		
		List<Expense> list = null;
		try {
			Session sf = DbConfig.getSession();
			Query q = sf.createQuery("from Expense where client_id=:id order by timestamp");
			q.setParameter("id", id);
			list = q.list();
			sf.close();
		} catch (Exception e) {
			log.error("find expense for client failed: " + e);
		}
		
		return list;
	}
	
	@Override
	public void delete(Long id) {
		log.debug("delete expense");
		
		try {
			Session session = DbConfig.getSession();
			Transaction tx = session.beginTransaction();
			Expense x = new Expense();
			x.setId(id);
			session.delete(x);
			tx.commit();
			session.close();
		} catch (Exception e) {
			log.error("delete expense failed: " + e);
		}
	}
	
	@Override
	public void delete(Expense expense) {
		log.debug("delete expense using expense object");
		
		try {
			Session session = DbConfig.getSession();
			Transaction tx = session.beginTransaction();
			session.delete(expense);
			tx.commit();
			session.close();
		} catch (Exception e) {
			log.error("delete expense using expense object failed: " + e);
		}
	}
	
	@Override
	public Expense update(Expense expense) {
		log.debug("update expense");
		
		db.update(expense);
		
		return expense;
	}
}

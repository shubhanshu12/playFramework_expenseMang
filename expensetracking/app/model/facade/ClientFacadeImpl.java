package model.facade;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.inject.Inject;

import model.Client;
import model.Expense;
import play.Logger;
import utils.db.DatabaseEntityManagerLocal;
import utils.db.DbConfig;


/**
 * @author shubhanshu
 *
 */
public class ClientFacadeImpl implements ClientFacadeLocal{
	
	 private final Logger.ALogger log = Logger.of(this.getClass());
	
	@Inject
	DatabaseEntityManagerLocal db;
	@Inject
	ExpenseFacadeLocal expenseFacade;
	
	@Override
	public Client create(String name, String city) {
		log.info("create client: " + name + " : " + city);
		
		Client x = new Client().with(name, city);
		db.save(x);
		
		return x;	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Client> findAll(){
		log.debug("client findAll()");
		
		Session sf = DbConfig.getSession();
		Query q = sf.createQuery("from Client order by id desc");
		List<Client> list = q.list();
		
		return list;
	}
	
	@Override
	public Client find(Long id) {
		log.debug("client find()");
		
		Session sf = DbConfig.getSession();
		Client client = sf.get(Client.class, id);
		sf.close();
		
		return client;
	}
	
	@Override
	public void save(Client client) {
		log.debug("client save");
		
		db.saveOrUpdate(client);
	}
	
	@Override
	public void delete(Long id) {
		log.debug("client delete");
		
		try {
			Client client = find(id);
			List<Expense> expenses = client.getExpenses();
			for (Expense expense : expenses) {
				expenseFacade.delete(expense);
			}
			Session session = DbConfig.getSession();
			Transaction tx = session.beginTransaction();
			session.delete(client);
			tx.commit();
			session.close();
		} catch (Exception e) {
			log.error("client deletion failed: " + e);
		}
	}
	
	@Override
	public Client update(Client client) {
		log.debug("client update");
		
		db.update(client);
		return client;
	}

}

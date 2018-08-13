package utils.db;

import org.hibernate.Session;
import org.hibernate.Transaction;

import play.Logger;


/**
 * @author shubhanshu
 *
 */
public class DatabaseEntityManagerImpl implements DatabaseEntityManagerLocal{
	
	private final Logger.ALogger log = Logger.of(this.getClass());
	
	@Override
	public Object save(Object obj) {
		log.debug("database entity manager save");
		
		try {
			Session sf = DbConfig.getSession();
			Transaction tx = sf.beginTransaction();
			sf.save(obj);
			tx.commit();
			sf.close();
		} catch (Exception e) {
			log.error("database entity manager save failed: " + e);
		}
		
		return obj;
	}
	
	@Override
	public Object merge(Object obj) {
		log.debug("database entity manager merge");
		
		try {
			Session sf = DbConfig.getSession();
			Transaction tx = sf.beginTransaction();
			sf.merge(obj);
			tx.commit();
			sf.close();
		} catch (Exception e) {
			log.error("database entity manager merge failed: " + e);
		}

		return obj;
	}
	
	@Override
	public Object update(Object obj) {
		log.debug("database entity manager update");
		
		try {
			Session session = DbConfig.getSession();
			Transaction tx = session.beginTransaction();
			session.update(obj);
			tx.commit();
			session.close();
		} catch (Exception e) {
			log.error("database entity manager update failed: " + e);
		}
		
		return obj;
	}
	
	@Override
	public Object saveOrUpdate(Object obj) {
		log.debug("database entity manager saveOrUpdate");
		
		try {
			Session sf = DbConfig.getSession();
			Transaction tx = sf.beginTransaction();
			sf.saveOrUpdate(obj);
			tx.commit();
			sf.close();
		} catch (Exception e) {
			log.error("database entity manager update saveOrUpdate: " + e);
		}

		return obj;
	}
}

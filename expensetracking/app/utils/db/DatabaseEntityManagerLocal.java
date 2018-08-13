package utils.db;


/**
 * @author shubhanshu
 *
 */
public interface DatabaseEntityManagerLocal {

	Object save(Object obj);

	Object merge(Object obj);

	Object update(Object obj);

	Object saveOrUpdate(Object obj);

}

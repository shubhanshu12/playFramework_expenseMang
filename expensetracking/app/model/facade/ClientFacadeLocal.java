package model.facade;

import java.util.List;

import model.Client;


/**
 * @author shubhanshu
 *
 */
public interface ClientFacadeLocal {
	
	Client create(String name, String city);
	
	List<Client> findAll();

	Client find(Long id);

	void save(Client client);

	void delete(Long id);

	Client update(Client client);
}

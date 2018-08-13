import com.google.inject.AbstractModule;

import model.facade.ClientFacadeImpl;
import model.facade.ClientFacadeLocal;
import model.facade.ExpenseFacadeImpl;
import model.facade.ExpenseFacadeLocal;
import utils.db.DatabaseEntityManagerImpl;
import utils.db.DatabaseEntityManagerLocal;

public class Module extends AbstractModule{

	@Override
	protected void configure() {
		bind(ExpenseFacadeLocal.class).to(ExpenseFacadeImpl.class);
		bind(ClientFacadeLocal.class).to(ClientFacadeImpl.class);
		bind(DatabaseEntityManagerLocal.class).to(DatabaseEntityManagerImpl.class);
	}

	
}

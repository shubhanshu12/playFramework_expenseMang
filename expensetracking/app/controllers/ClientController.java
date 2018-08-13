package controllers;

import java.util.Date;

import com.google.inject.Inject;

import dto.ClientDTO;
import dto.ExpenseDTO;
import model.Client;
import model.Expense;
import model.facade.ClientFacadeImpl;
import model.facade.ClientFacadeLocal;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import utils.AppConstants;


/**
 * @author shubhanshu
 *
 */
public class ClientController extends PageController{
	
	private final Logger.ALogger log = Logger.of(this.getClass());
	
	final Form<ClientDTO> clientForm;
	@Inject
	private ClientFacadeLocal clientFacade;
	
	@Inject
	public ClientController(FormFactory formFactory) {
		this.clientForm = formFactory.form(ClientDTO.class);
	}
	
    public Result addClient() {
    	log.debug("render add client");
        return ok(views.html.client.clientView.render("Add Client", true, clientForm));
    }
    
    public Result viewClient() {
    	log.debug("render view client");
    	return ok(views.html.client.clientView.render("View Client", false, clientForm));
    }
    
    public Result submit() {
    	log.debug("submit client");
    	
    	final Form<ClientDTO> boundForm = clientForm.bindFromRequest();
    	ClientDTO clientInfo = boundForm.get();
    	log.debug("clientInfo: " + clientInfo);
    	
    	try {
    		clientFacade.create(clientInfo.getName(), clientInfo.getCity());
		} catch (Exception e) {
			log.error("submit client controller failed: " + e);
			return redirect(controllers.routes.ResponseController.show(AppConstants.Response.DANGER.toString(), AppConstants.error));
		}
    	
    	String res = "New client has been created with client name: " + clientInfo.getName() + " and city: " + clientInfo.getCity();
		return redirect(controllers.routes.ResponseController.show(AppConstants.Response.SUCCESS.toString(), res));
    }
    
    public Result deleteClient(Long id) {
    	log.debug("delete client controller");
    	
    	try {
    		clientFacade.delete(id);
		} catch (Exception e) {
			log.error("delete client controller failed: " + e);
			return redirect(controllers.routes.ResponseController.show(AppConstants.Response.DANGER.toString(), AppConstants.error));
		}
    	
    	String res = "Client has been deleted";
		return redirect(controllers.routes.ResponseController.show(AppConstants.Response.SUCCESS.toString(), res));
    }
    
    public Result editClient(Long id) {
    	log.debug("edit client controller");
    	
    	Form<ClientDTO> boundForm = clientForm.bindFromRequest();
		Client client = clientFacade.find(id);
		
		if(client == null) {
			log.error("client not found");
			return redirect(controllers.routes.ResponseController.show(AppConstants.Response.DANGER.toString(), AppConstants.clientNotFound));
		}
		
		ClientDTO clientDTO = new ClientDTO(client.getId(), client.getName(), client.getCity());
		Form<ClientDTO> filledForm = boundForm.fill(clientDTO);
		log.debug("render edit client");
		
		return ok(views.html.client.clientEdit.render("Edit Client", id, filledForm));
    }
    
	public Result updateClient(Long id) {
		log.debug("update client controller");
		
		Form<ClientDTO> boundForm = clientForm.bindFromRequest();
		ClientDTO clientInfo = boundForm.get();
		log.debug("clientInfo: " + clientInfo);
		Client client = clientFacade.find(id);
		
		if(client == null) {
			log.error("client not found");
			return redirect(controllers.routes.ResponseController.show(AppConstants.Response.DANGER.toString(), AppConstants.clientNotFound));
		}
		
		Client updatedClient = client.with(clientInfo.getName(), clientInfo.getCity());
		clientFacade.update(updatedClient);
		
		String res = "Client has been updated";
		return redirect(controllers.routes.ResponseController.show(AppConstants.Response.SUCCESS.toString(), res));
	}
}

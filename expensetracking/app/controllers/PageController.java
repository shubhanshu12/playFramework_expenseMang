package controllers;

import java.util.ArrayList;
import java.util.List;

import dto.ClientDTO;
import model.Client;
import model.facade.ClientFacadeImpl;
import play.Logger;
import play.core.routing.Route;
import play.mvc.Controller;
import play.mvc.Result;


/**
 * @author shubhanshu
 *
 */
public class PageController extends Controller{
	
	public static List<ClientDTO> getAllClients(){
		
		List<ClientDTO> clientDto = new ArrayList<ClientDTO>();
		ClientFacadeImpl clientFacade = new ClientFacadeImpl();
		List<Client> clients = clientFacade.findAll();
		for (Client client : clients) {
			clientDto.add(new ClientDTO(client.getId(), client.getName(), client.getCity()));
		}
		
		return clientDto;
	}
	
}

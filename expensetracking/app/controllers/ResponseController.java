package controllers;

import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import utils.AppConstants.Response;


/**
 * @author shubhanshu
 *
 */
public class ResponseController extends PageController{
	
	private final Logger.ALogger log = Logger.of(this.getClass());

	public Result show(String cond, String response) {
		
		log.debug("render response view");
		return ok(views.html.response.successResponse.render(cond, response));
	}
}

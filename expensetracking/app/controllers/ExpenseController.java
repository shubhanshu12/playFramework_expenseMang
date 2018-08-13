package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.google.inject.Inject;

import dto.ClientDTO;
import dto.ExpenseDTO;
import model.Client;
import model.Expense;
import model.facade.ClientFacadeLocal;
import model.facade.ExpenseFacadeLocal;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Result;
import utils.AppConstants;


/**
 * @author shubhanshu
 *
 */
public class ExpenseController extends PageController{
	
	private final Logger.ALogger log = Logger.of(this.getClass());
	
	final Form<ExpenseDTO> expenseForm;
	
	@Inject
	ClientFacadeLocal clientFacade;
	
	@Inject
	ExpenseFacadeLocal expenseFacade;
	
	
	@Inject
	public ExpenseController(FormFactory formFactory) {
		this.expenseForm = formFactory.form(ExpenseDTO.class);
	}
	
	public Result showExpense(Long id) {
		log.debug("render view expense: " + id);
		
		Client client = clientFacade.find(id);
		
		if(client == null) {
			log.error("client not found");
			return redirect(controllers.routes.ResponseController.show(AppConstants.Response.DANGER.toString(), AppConstants.clientNotFound));
		}
		
		List<ExpenseDTO> clientExpenses = getClientExpenses(id);
		
		return ok(views.html.expense.expenseView.render("View Expense", id, false, expenseForm, clientExpenses));
	}
	

	public Result addExpense(Long id) {
		log.debug("render add expense" + id);
		
		List<ExpenseDTO> clientExpenses = getClientExpenses(id);
		
		return ok(views.html.expense.expenseView.render("Add Expense", id, true, expenseForm, clientExpenses));
	}
	
	private List<ExpenseDTO> getClientExpenses(Long id) {
		log.debug("getClientExpense: " + id);
		
		List<ExpenseDTO> expenseDto = new ArrayList<ExpenseDTO>();
		List<Expense> expenceByClient = expenseFacade.findExpenseByClient(id);
		
		for (Expense expense : expenceByClient) {
			expenseDto.add(new ExpenseDTO(expense.getId(), expense.getTitle(), expense.getDescription(), expense.getAmount(), expense.getCurrency(), expense.getTimeStamp()));
		}
		
		return expenseDto;
	}
	
	public Result submit(Long id) {
		log.debug("submit expense controller");
		
		Form<ExpenseDTO> boundForm = expenseForm.bindFromRequest();
		ExpenseDTO expenseInfo = boundForm.get();
		Client client = clientFacade.find(id);
		
		if(client == null) {
			log.error("client not found");
			return redirect(controllers.routes.ResponseController.show(AppConstants.Response.DANGER.toString(), AppConstants.clientNotFound));
		}
		
    	Expense newExpense = expenseFacade.create(expenseInfo.getTitle(), expenseInfo.getDescription(), expenseInfo.getCurrency(), expenseInfo.getAmount(), new Date().toString(), client);
    	
    	String res = "New expense has been added";
		return redirect(controllers.routes.ResponseController.show(AppConstants.Response.SUCCESS.toString(), res));
	}
	
	public Result deleteExpense(Long id) {
		log.debug("delete expense controller");
		
		expenseFacade.delete(id);
		
    	String res = "Expense has been deleted";
		return redirect(controllers.routes.ResponseController.show(AppConstants.Response.SUCCESS.toString(), res));
	}
	
	public Result editExpense(Long id) {
		log.debug("edit expense controller");
		
		Form<ExpenseDTO> boundForm = expenseForm.bindFromRequest();
		Expense expense = expenseFacade.find(id);
		
		if(expense == null) {
			log.error("expense not found");
			return redirect(controllers.routes.ResponseController.show(AppConstants.Response.DANGER.toString(), AppConstants.expenseNotFound));
		}
		
		ExpenseDTO expenseDTO = new ExpenseDTO(expense.getId(), expense.getTitle(), expense.getDescription(), expense.getAmount(), expense.getCurrency(), expense.getTimeStamp());
		Form<ExpenseDTO> filledForm = boundForm.fill(expenseDTO);
		
		return ok(views.html.expense.expenseEdit.render("Edit Expense", id, filledForm));
	}
	
	public Result updateExpense(Long id) {
		log.debug("update expense controller");
		
		Form<ExpenseDTO> boundForm = expenseForm.bindFromRequest();
		ExpenseDTO expenseInfo = boundForm.get();
		Expense expense = expenseFacade.find(id);
		
		if(expense == null) {
			log.error("expense not found");
			return redirect(controllers.routes.ResponseController.show(AppConstants.Response.DANGER.toString(), AppConstants.expenseNotFound));
		}
		
		Expense updatedExpense = expense.with(expenseInfo.getTitle(), expenseInfo.getDescription(), expenseInfo.getCurrency(), expenseInfo.getAmount(), new Date().toString());
		expenseFacade.update(updatedExpense);
		
		String res = "Expense has been updated";
		return redirect(controllers.routes.ResponseController.show(AppConstants.Response.SUCCESS.toString(), res));
	}
}

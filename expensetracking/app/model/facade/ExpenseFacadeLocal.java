package model.facade;

import java.util.List;

import model.Client;
import model.Expense;


/**
 * @author shubhanshu
 *
 */
public interface ExpenseFacadeLocal {

	Expense create(String title, String description, String currency, Double amount, String timeStamp, Client client);

	List<Expense> findExpenseByClient(Long id);

	void delete(Long id);

	Expense find(Long id);

	Expense update(Expense expense);

	void delete(Expense expense);

}

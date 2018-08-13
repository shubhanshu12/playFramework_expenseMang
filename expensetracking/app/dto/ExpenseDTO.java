package dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import model.Client;

public class ExpenseDTO {
	
	private Long id;
	private String title;
	private String description;
	private Double amount;
	private String currency;
	private String timeStamp;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public ExpenseDTO(Long id, String title, String description, Double amount, String currency, String timeStamp) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.amount = amount;
		this.currency = currency;
		this.timeStamp = timeStamp;
	}
	public ExpenseDTO() {
		
	}
	@Override
	public String toString() {
		return "ExpenseDTO [id=" + id + ", title=" + title + ", description=" + description + ", amount=" + amount
				+ ", currency=" + currency + ", timeStamp=" + timeStamp + "]";
	}
	
}

package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String city;
	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval = true)
	private List<Expense> expenses;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public List<Expense> getExpenses() {
		return expenses;
	}
	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}
	public Client with(String name, String city) {
		setName(name);
		setCity(city);
		return this;
	}
	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", city=" + city + ", expenses=" + expenses + "]";
	}
	
}

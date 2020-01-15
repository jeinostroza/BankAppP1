package EmployeeApp;

public class Clients {
	int id_client;
	String firstname;
	String lastname;
	String street;
	String city;
	String zip;
	String state;
	String username;
	double m_incomes;
	double m_expenses;
	
	public Clients(int id_client, String firstname, String lastname, String street, String city, String zip, String state, String username, double m_incomes, double m_expenses) {
	 this.id_client = id_client;
	 this.firstname = firstname;
	 this.lastname = lastname;
	 this.street = street;
	 this.city = city;
	 this.zip = zip;
	 this.state = state;
	 this.username = username;
	 this.m_incomes = m_incomes;
	 this.m_expenses = m_expenses;
	}
	

}

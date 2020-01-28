package BankApp;

import oracle.sql.DATE;

public class Transactions {
	
	private int id_trans;
	private DATE date_trans;
	private String description_trans;
	private double amount_trans;
	private String acc_number;
	private String username;
	
	public int getId_trans() {
		return id_trans;
	}
	public void setId_trans(int id_trans) {
		this.id_trans = id_trans;
	}
	public DATE getDate_trans() {
		return date_trans;
	}
	public void setDate_trans(DATE date_trans) {
		this.date_trans = date_trans;
	}
	public String getDescription_trans() {
		return description_trans;
	}
	public void setDescription_trans(String description_trans) {
		this.description_trans = description_trans;
	}
	public double getAmount_trans() {
		return amount_trans;
	}
	public void setAmount_trans(double amount_trans) {
		this.amount_trans = amount_trans;
	}
	public String getAcc_number() {
		return acc_number;
	}
	public void setAcc_number(String acc_number) {
		this.acc_number = acc_number;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	

}

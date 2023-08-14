package com.fssa.netbliz.model;

public class Customer {

	private String fName;
	private String lName;
	private String phoneNumber;
	private String email;
	private String password;
	private String confirmPassword;
	
	// No argument constructor
		public Customer(){   
			// Empty constructor used for creating an instance without setting attributes
		}

	public Customer(String fName, String lName, String phoneNumber, String email, String password, String confirmPassword) {
		this.fName = fName;
		this.lName = lName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getconfirmPassword() {
		return confirmPassword;
	}

	public void setconfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
}

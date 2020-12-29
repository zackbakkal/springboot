package com.zack.projects.chatapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@Column(name = "userName", nullable = false)
	@NotBlank(message = "UserName is mandatory")
	private String userName;
	
	@Column(name = "userIpAddress")
	private String userIpAddress;
	
	@Column(name = "userEmail")
	private String userEmail;
	
	public User() {
		super();
	}

	public User(String userName, String userIpAddress, String userEmail) {
		super();
		this.userName = userName;
		this.userIpAddress = userIpAddress;
		this.userEmail = userEmail;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserIPAddress() {
		return userIpAddress;
	}
	
	public void setUserIPAddress(String userIpAddress) {
		this.userIpAddress = userIpAddress;
	}
	
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public boolean equals(User user) {
		if(this.userName.equals(user.getUserName()))  {
			return true;
		}
		
		return false;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName 
				+ ", userIPAddress=" + userIpAddress 
				+ ", userEmail=" + userEmail + "]";
	}

	
	
}

package com.zack.projects.chatapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long messageID;
	
	@Column
	private String senderUserName;
	
	@Column
	private String receiverUserName;
	
	@Column
	private String message;
	
	@Column
	private Date dateMessageSent;
	
	public Message() {
		super();
	}

	public Message(String senderUserName, String receiverUserName, String message) {
		super();
		this.senderUserName = senderUserName;
		this.receiverUserName = receiverUserName;
		this.message = message;
		this.dateMessageSent = new Date();
	}

	public long getMessageID() {
		return messageID;
	}

	public void setMessageID(long messageID) {
		this.messageID = messageID;
	}

	public String getSenderUserName() {
		return senderUserName;
	}

	public void setSenderUserName(String senderUserName) {
		this.senderUserName = senderUserName;
	}

	public String getReceiverUserName() {
		return receiverUserName;
	}

	public void setReceiverUserName(String receiverUserName) {
		this.receiverUserName = receiverUserName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDateMessageSent() {
		return dateMessageSent;
	}

	public void setDateMessageSent() {
		this.dateMessageSent = new Date();
	}

	@Override
	public String toString() {
		return "Message [messageID=" + messageID + ", senderUserID=" + senderUserName + ", receiverUserID="
				+ receiverUserName + ", dateMessageSent=" + dateMessageSent + "]";
	}
	
	
	
}

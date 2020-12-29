package com.zack.projects.chatapp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zack.projects.chatapp.model.Message;
import com.zack.projects.chatapp.repository.MessageRepository;

@RestController
@RequestMapping("/api/v1")
public class MessageController {
	
	@Autowired
	private MessageRepository messageRepository;
	
		
	// Get messages sent by userName
	@GetMapping("messages/sentby/{userName}")
	public List<Message> getMessagesSentBy(
			@PathVariable(name = "userName") String userName) {
		
		List<Message> messages = this.messageRepository.findAll()
				.stream()
				.filter(message -> message.getSenderUserName().equals(userName))
				.collect(Collectors.toList());
		
		return messages;
		
	}
	
	// Get messages sent by userName to userName
	@GetMapping("messages/sentby/{senderUserName}/to/{receiverUserName}")
	public List<Message> getMessagesSentByReceivedBy(
			@PathVariable(name = "senderUserName") String senderUserName,
			@PathVariable(name = "receiverUserName") String receiverUserName)  {
		
		List<Message> messages = this.messageRepository.findAll()
				.stream()
				.filter(message -> message.getSenderUserName().equals(senderUserName)
						&& message.getReceiverUserName().equals(receiverUserName))
				.collect(Collectors.toList());
		
		return messages;
		
	}
	
	// Get messages received by userName
	@GetMapping("messages/receivedby/{userName}")
	public List<Message> getMessagesReceivedBy(
			@PathVariable(name = "userName") String userName) {
		
		List<Message> messages = this.messageRepository.findAll()
				.stream()
				.filter(message -> message.getReceiverUserName().equals(userName))
				.collect(Collectors.toList());
		
		return messages;
		
	}
	
	// Get messages received by userName from userName
	@GetMapping("messages/receivedby/{receiverUserName}/from/{senderUserName}")
	public List<Message> getMessagesreceivedByfrom(
			@PathVariable(name = "receiverUserName") String receiverUserName,
			@PathVariable(name = "senderUserName") String senderUserName)  {
		
		List<Message> messages = this.messageRepository.findAll()
				.stream()
				.filter(message -> message.getReceiverUserName().equals(receiverUserName)
						&& message.getSenderUserName().equals(senderUserName))
				.collect(Collectors.toList());
		
		return messages;
		
	}
		
	// Add message
	@PostMapping("message")
	public ResponseEntity<Message> addMessage(@RequestBody Message message) {
		
		message.setDateMessageSent();
		
		return ResponseEntity.ok().body(this.messageRepository.save(message));
		
	}

}

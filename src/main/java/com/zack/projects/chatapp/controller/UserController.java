package com.zack.projects.chatapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zack.projects.chatapp.exception.ResourceExistsException;
import com.zack.projects.chatapp.exception.ResourceNotFoundException;
import com.zack.projects.chatapp.model.User;
import com.zack.projects.chatapp.repository.UserRepository;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	// Get Users
	@GetMapping("users")
	public List<User> getUsers() {
		return this.userRepository.findAll();
	}
	
	// Get User by id
	@GetMapping("user/{userName}")
	public ResponseEntity<User> getUserByUserName(
			@PathVariable(value = "userName") String userName) 
					throws ResourceNotFoundException {
		
		User user = 
				this.userRepository
				.findById(userName)
				.orElseThrow(() 
						-> new ResourceNotFoundException("User name " + userName + " not Found."));
		
		return ResponseEntity.ok().body(user);
		
	}
	
	// Add User
	@PostMapping("user")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) throws ResourceExistsException {
		
		boolean userNameExists = this.userRepository.existsById(user.getUserName());
		
		if(userNameExists) {
			throw new ResourceExistsException("User name " + user.getUserName() + " is not available.");
		}
		
		return ResponseEntity.ok().body(this.userRepository.save(user));
		
	}
	
	// Update User
	@PutMapping("user")
	public ResponseEntity<User> updateUser(@RequestBody User user) throws ResourceNotFoundException {
		
		User update = 
				this.userRepository
				.findById(user.getUserName())
				.orElseThrow(() 
						-> new ResourceNotFoundException("User name " + user.getUserName() + " does not exist."));
		
		update.setUserIPAddress(user.getUserIPAddress());
		update.setUserEmail(user.getUserEmail());
		
		
		return ResponseEntity.ok().body(this.userRepository.save(update));
		
	}
	
	// Delete User
	@DeleteMapping("user/{userName}")
	public ResponseEntity<User> deleteUser(
			@PathVariable(value ="userName") String userName) 
					throws ResourceNotFoundException {
		
		User user = 
				this.userRepository
				.findById(userName)
				.orElseThrow(() 
						-> new ResourceNotFoundException("User name " + userName + " not Found."));
		
		this.userRepository.deleteById(userName);
		
		return ResponseEntity.ok().body(user);
		
	}

}

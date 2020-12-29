package com.zack.projects.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zack.projects.chatapp.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}

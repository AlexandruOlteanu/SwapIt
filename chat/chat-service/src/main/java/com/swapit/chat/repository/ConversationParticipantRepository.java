package com.swapit.chat.repository;


import com.swapit.chat.domain.ConversationParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationParticipantRepository extends JpaRepository<ConversationParticipant, Integer> {

}

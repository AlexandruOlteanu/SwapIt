package com.swapit.chat.repository;


import com.swapit.chat.domain.ConversationParticipants;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationParticipantsRepository extends JpaRepository<ConversationParticipants, Integer> {

}

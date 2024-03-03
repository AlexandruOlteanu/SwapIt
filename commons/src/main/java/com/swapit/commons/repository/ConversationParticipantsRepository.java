package com.swapit.commons.repository;

import com.swapit.commons.domain.Conversation;
import com.swapit.commons.domain.ConversationParticipants;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationParticipantsRepository extends JpaRepository<ConversationParticipants, Integer> {

}

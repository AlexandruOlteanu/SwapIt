package com.swapit.chat.repository;

import com.swapit.chat.domain.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Integer> {
    @Query("select c from Conversation c join c.conversationParticipants p where p.userId = :userId order by c.lastActionAt desc")
    Optional<List<Conversation>> findAllByUserId(Integer userId);

    @Query("select c.conversationId from Conversation c join c.conversationParticipants p where p.userId = :userId order by c.lastActionAt desc")
    Optional<List<Integer>> findAllConversationsIdsOfUser(Integer userId);

    @Query("select c.conversationId from Conversation c join c.conversationParticipants p where p.userId in (:firstUserId, :secondUserId) group by c.conversationId " +
            "having count(distinct p.userId) = 2 and count(p) = 2")
    Optional<Integer> findPrivateConversationId(Integer firstUserId, Integer secondUserId);

    Optional<Conversation> findByConversationId(Integer conversationId);
}

package com.swapit.chat.domain;

import com.swapit.chat.utils.ConversationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.ZonedDateTime;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "conversation")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="conversation_id")
    private Integer conversationId;

    @Enumerated(EnumType.STRING)
    private ConversationType type;

    @Column(name = "conversation_title")
    private String conversationTitle;

    @Column(name = "last_action_at")
    private ZonedDateTime lastActionAt;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    private List<ConversationParticipants> conversationParticipants;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    private List<Message> messages;
}

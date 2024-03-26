package com.swapit.chat.domain;

import com.swapit.chat.utils.MessageType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="message_id")
    private Integer messageId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    @Column(name="sent_at")
    private ZonedDateTime sentAt;

    @Column(name="sent_by")
    private Integer sentBy;

    @Enumerated(EnumType.STRING)
    @Column(name="type")
    private MessageType type;

    @Column(name="value")
    private String value;
}

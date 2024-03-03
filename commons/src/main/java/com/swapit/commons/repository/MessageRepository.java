package com.swapit.commons.repository;

import com.swapit.commons.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {

}

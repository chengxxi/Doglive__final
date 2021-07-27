package com.ssafy.db.repository.chat;
import com.ssafy.db.entity.chat.ChatMessage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {



}

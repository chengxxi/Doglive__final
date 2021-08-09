package com.ssafy.db.repository.chat;

import com.ssafy.db.entity.chat.ChatRoomJoin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomJoinRepository extends JpaRepository<ChatRoomJoin, Long> {
    Optional<List<ChatRoomJoin>> findChatRoomsJoinByUserId(String userId);
}
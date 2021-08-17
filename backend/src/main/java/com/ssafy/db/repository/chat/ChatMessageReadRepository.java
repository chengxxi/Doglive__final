package com.ssafy.db.repository.chat;

import com.ssafy.db.entity.chat.ChatMessage;
import com.ssafy.db.entity.chat.ChatMessageRead;
import com.ssafy.db.entity.chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatMessageReadRepository extends JpaRepository<ChatMessageRead, Long> {

    /* 메시지를 전송할 때, ChatMeesageRead에 Insert 하기 */


    /* 현재 user가 채팅방에서 읽지않은 메시지의 개수 가져오기 */
    @Query(
            value = "SELECT * FROM chat_message_read WHERE room_id = :roomId and user_id = :userId and is_read = false",
            nativeQuery = true
    )
    Optional<List<ChatMessageRead>> findChatMessageReadsByRoomIdAndUserId(@Param("roomId") ChatRoom roomId, @Param("userId") String userId);


    @Transactional
    @Query(
            value = "UPDATE chat_message_read SET is_read = true WHERE room_id = :roomId and is_read = false and user_id = :userId",
            nativeQuery = true
    )
    void updateIsRead(@Param("roomId") ChatRoom roomId, @Param("userId") String userId);

    @Transactional
    void deleteChatMessageReadByRoomId(ChatRoom roomId);
}

package com.ssafy.db.entity.chat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;


/**
 * 채팅방을 관리할 ChatRoom Entity
 */
@Entity
@Getter
@Setter
public class ChatRoom extends BaseEntity {


    private long counselingId;   //채팅방과 연관된 공고를 저장할 공고 게시글 ID


}

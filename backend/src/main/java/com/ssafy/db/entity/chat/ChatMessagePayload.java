package com.ssafy.db.entity.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessagePayload {

    private String userId;

    private String message;

    private Long roomId;
}

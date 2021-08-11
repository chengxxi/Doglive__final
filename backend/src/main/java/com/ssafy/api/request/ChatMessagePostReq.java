package com.ssafy.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessagePostReq {

    private String userId;

    private Long roomId;

    private String chatMessage;

}

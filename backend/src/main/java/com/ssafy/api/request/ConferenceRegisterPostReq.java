package com.ssafy.api.request;

import com.ssafy.db.entity.chat.ChatRoom;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("ConferenceRegisterPostReq")
public class ConferenceRegisterPostReq {
    @ApiModelProperty
    ChatRoom chatRoom;

}

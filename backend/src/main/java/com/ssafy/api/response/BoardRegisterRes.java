package com.ssafy.api.response;


import com.ssafy.api.request.BoardRegisterPostReq;
import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("BoardListGetRes")
public class BoardRegisterRes extends BaseResponseBody {
    @ApiModelProperty
    Long boardId;

    public static BoardRegisterRes of(Integer statusCode, String message, Long boardId){
        BoardRegisterRes res = new BoardRegisterRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setBoardId(boardId);
        return res;
    }
}

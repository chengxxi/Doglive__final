package com.ssafy.api.response;


import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.board.Board;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@ApiModel("BoardListGetRes")
public class BoardListGetRes extends BaseResponseBody {

    @ApiModelProperty
    List<Board> boardList;

    @ApiModelProperty
    int size;

    public static BoardListGetRes of(Integer statusCode, String message, List<Board> boardList, Integer size) {
        BoardListGetRes res = new BoardListGetRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setBoardList(boardList);
        res.setSize(size);
        return res;
    }
}

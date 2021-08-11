package com.ssafy.api.response;


import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("BoardListGetRes")
public class BoardDetailListGetRes extends BaseResponseBody {

    @ApiModelProperty
    List<BoardListData> boardList;

    @ApiModelProperty
    int size;

    public static BoardDetailListGetRes of(Integer statusCode, String message, List<BoardListData> boardList, Integer size) {
        BoardDetailListGetRes res = new BoardDetailListGetRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setBoardList(boardList);
        res.setSize(size);
        return res;
    }
}

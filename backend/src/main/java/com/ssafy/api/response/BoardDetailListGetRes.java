package com.ssafy.api.response;


import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.board.DogInformation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;


@Getter
@Setter
@ApiModel("BoardDetailListGetRes")
public class BoardDetailListGetRes extends BaseResponseBody {

    @ApiModelProperty
    Page<DogInformation> boardList;


    public static BoardDetailListGetRes  of(Integer statusCode, String message, Page<DogInformation> boardList) {
        BoardDetailListGetRes res = new BoardDetailListGetRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.boardList = boardList;
        return res;
    }
}

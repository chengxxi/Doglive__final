package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.board.Board;
import com.ssafy.db.entity.board.MBTI;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("MBTIDetailGetRes")
public class MBTIListGetRes extends BaseResponseBody {

    @ApiModelProperty
    List<MBTI> mbtiList;

    public static MBTIListGetRes of(Integer statusCode, String message, List<MBTI> mbtiList) {
        MBTIListGetRes res = new MBTIListGetRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setMbtiList(mbtiList);
        return res;
    }
}

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
public class MBTIDetailGetRes extends BaseResponseBody {
    @ApiModelProperty
    MBTI mbti;

    public static MBTIDetailGetRes of(Integer statusCode, String message, MBTI mbti) {
        MBTIDetailGetRes res = new MBTIDetailGetRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setMbti(mbti);
        return res;
    }
}

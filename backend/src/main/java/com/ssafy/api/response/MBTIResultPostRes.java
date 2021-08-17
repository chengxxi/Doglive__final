package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.board.Board;
import com.ssafy.db.entity.board.DogInformation;
import com.ssafy.db.entity.board.MBTI;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("MBTIDetailGetRes")
public class MBTIResultPostRes extends BaseResponseBody {
    @ApiModelProperty
    MBTI mbti;

    @ApiModelProperty
    List<DogInformation> matchedBoardList;

    public static MBTIResultPostRes of(Integer statusCode, String message, MBTI mbti, List<DogInformation> list) {
        MBTIResultPostRes res = new MBTIResultPostRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setMbti(mbti);
        res.setMatchedBoardList(list);
        return res;
    }
}

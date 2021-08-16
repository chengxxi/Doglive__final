package com.ssafy.api.response;


import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.board.DogInformation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("BoardRecentListRes")
public class BoardRecentListRes extends BaseResponseBody {
    @ApiModelProperty
    List<DogInformation> adoptList;

    @ApiModelProperty
    List<DogInformation> findList;

    public static BoardRecentListRes of(Integer statusCode, String message, List<DogInformation> adoptList, List<DogInformation> findList){
        BoardRecentListRes res = new BoardRecentListRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setAdoptList(adoptList);
        res.setFindList(findList);
        return res;
    }
}

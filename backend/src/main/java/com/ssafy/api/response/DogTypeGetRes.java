package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.board.DogType;
import com.ssafy.db.entity.board.Gugun;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 강아지 품종 정보를 리턴하는 DTO
 */
@Getter
@Setter
@ApiModel("DogTypegetRes")
public class DogTypeGetRes extends BaseResponseBody {

    @ApiModelProperty
    List<DogType> dogTypeList;


    public static DogTypeGetRes of(Integer statusCode, String message, List<DogType> dogTypeList) {
        DogTypeGetRes res = new DogTypeGetRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setDogTypeList(dogTypeList);
        return res;
    }
}

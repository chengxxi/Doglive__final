package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.h2.util.json.JSONObject;

/**
 * 입양 신청서 폼 데이터 읽기
 */

@Getter
@Setter
@ApiModel("AdoptFormGetRes")
public class AdoptFormGetRes extends BaseResponseBody {


    @ApiModelProperty
    AdoptFormData content;

    public static AdoptFormGetRes of(Integer statusCode, String message, AdoptFormData content){
        AdoptFormGetRes res = new AdoptFormGetRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setContent(content);
        return res;
    }

}

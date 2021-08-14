package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.auth.CounselingHistory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("CounselingHistoryGetResponse")
public class CounselingHistoryGetRes extends BaseResponseBody {

    @ApiModelProperty
    CounselingHistory counselingHistory;

    public static CounselingHistoryGetRes of(Integer statusCode, String message, CounselingHistory counseling){
        CounselingHistoryGetRes res = new CounselingHistoryGetRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setCounselingHistory(counseling);
        return res;
    }
}

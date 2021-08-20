package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.auth.CounselingHistory;
import com.ssafy.db.entity.board.Board;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("CounselingHistoryListGetResponse")
public class CounselingHistoryListGetRes extends BaseResponseBody {

    @ApiModelProperty
    List<CounselingHistory> counselingHistoryList;

    @ApiModelProperty
    int size;

    public static CounselingHistoryListGetRes of(Integer statusCode, String message, List<CounselingHistory> counselingHistoryList, Integer size){
        CounselingHistoryListGetRes res = new CounselingHistoryListGetRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setCounselingHistoryList(counselingHistoryList);
        res.setSize(size);
        return res;
    }
}

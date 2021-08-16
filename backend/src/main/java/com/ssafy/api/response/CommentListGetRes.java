package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.community.CommunityComment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("CommentListGetRes")
public class CommentListGetRes extends BaseResponseBody {

    @ApiModelProperty
    List<CommunityComment> commentList;

    @ApiModelProperty
    int size;

    public static CommentListGetRes of(Integer statusCode, String message, List<CommunityComment> commentList,Integer size){
        CommentListGetRes res = new CommentListGetRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setCommentList(commentList);
        res.setSize(size);

        return res;
    }


}

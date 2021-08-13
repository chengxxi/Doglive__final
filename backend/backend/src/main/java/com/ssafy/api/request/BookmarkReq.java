package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * Bookmark 저장 시 요청 Req
 */


@Getter
@Setter
@ApiModel("BookmarkInsertPostReq")
public class BookmarkReq {
    @ApiModelProperty
    String userId;

    @ApiModelProperty
    Long boardId;
}

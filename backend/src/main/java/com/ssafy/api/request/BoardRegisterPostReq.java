package com.ssafy.api.request;

import com.ssafy.db.entity.board.BoardCategory;
import com.ssafy.db.entity.board.Code;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("BoardRegisterPostReq")
public class BoardRegisterPostReq {
    @ApiModelProperty
    String userId;

    @ApiModelProperty
    BoardCategory boardType;

    @ApiModelProperty
    String title;

    @ApiModelProperty
    String thumbnailUrl;

    @ApiModelProperty
    List<String> filePath;

    @ApiModelProperty
    Code colorType;

    @ApiModelProperty
    String description;

    @ApiModelProperty
    Code gender;

    @ApiModelProperty
    Code hairType;

    @ApiModelProperty
    Code neutralization;

    @ApiModelProperty
    Code weight;

    @ApiModelProperty
    String mbti;

    @ApiModelProperty
    String address;

}

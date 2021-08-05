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
    Long boardType;

    @ApiModelProperty
    String dogName;

    @ApiModelProperty
    String title;

    @ApiModelProperty
    String thumbnailUrl;

    @ApiModelProperty
    List<String> filePath;

    @ApiModelProperty
    Long colorType;

    @ApiModelProperty
    String description;

    @ApiModelProperty
    Long gender;

    @ApiModelProperty
    Long hairType;

    @ApiModelProperty
    Long age;

    @ApiModelProperty
    boolean neutralization;

    @ApiModelProperty
    Long weight;

    @ApiModelProperty
    String mbti;

    @ApiModelProperty
    String address;

}

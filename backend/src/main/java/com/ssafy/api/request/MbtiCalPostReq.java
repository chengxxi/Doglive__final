package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * MBTI 계산 시 요청할 Request
 */
@Getter
@Setter
@ApiModel("MbtiCalPostReq")
public class MbtiCalPostReq {

    @ApiModelProperty
    Long scoreI;

    @ApiModelProperty
    Long scoreE;

    @ApiModelProperty
    Long scoreS;

    @ApiModelProperty
    Long scoreN;

    @ApiModelProperty
    Long scoreF;

    @ApiModelProperty
    Long scoreT;

    @ApiModelProperty
    Long scoreP;

    @ApiModelProperty
    Long scoreJ;

}

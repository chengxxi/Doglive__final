package com.ssafy.api.request;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("BoardFilteringReq")
public class BoardParamDto {

    /**
     * 필터링을 위한 Request 변수들
     *
     * 필터링 Logic
     * 1. 각 항목에 값이 있는지 체크한 뒤, 각 항목에 의해 데이터를 걸러낸다.
     * 2. 걸러낸 데이터에서 페이지네이션 걸어서 요청된 갯수만큼  Return
     *
     *
     * 필터링 항목
     * 1. 보드타입(입양/임보/실종/보호)
     * 2. 크기
     * 3. 연령
     * 4. 성별
     */
    @ApiModelProperty
    int limit;

    @ApiModelProperty
    int offset;

    @ApiModelProperty
    String searchWord;

    @ApiModelProperty
    String searchType;

    @ApiModelProperty
    Long boardType;

    @ApiModelProperty
    Long weight;

    @ApiModelProperty
    Long age;

    @ApiModelProperty
    Long gender;



}

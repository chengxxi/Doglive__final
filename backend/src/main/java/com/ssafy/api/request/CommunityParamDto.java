package com.ssafy.api.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunityParamDto {

    @ApiModelProperty
    Long id;

    @ApiModelProperty
    String description;

    @ApiModelProperty
    String title;

    @ApiModelProperty
    String userId;

    @ApiModelProperty
    String name;

    @ApiModelProperty
    String category;

    public CommunityParamDto(Long id, String description, String title, String userId, String name, String category) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.userId = userId;
        this.name = name;
        this.category = category;
    }
}

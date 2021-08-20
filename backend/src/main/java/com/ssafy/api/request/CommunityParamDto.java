package com.ssafy.api.request;


import com.ssafy.db.entity.community.Community;
import com.ssafy.db.entity.community.CommunityImage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    String profileImageUrl;

    @ApiModelProperty
    String category;

    @ApiModelProperty
    List<String> fileList;

    public CommunityParamDto(){}

    public CommunityParamDto(Long id, String description, String title, String userId, String name, String profileImageUrl, String category) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.userId = userId;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.category = category;
    }
}


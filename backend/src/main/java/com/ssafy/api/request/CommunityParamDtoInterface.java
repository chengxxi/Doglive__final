package com.ssafy.api.request;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public interface CommunityParamDtoInterface {

    Long getId();

    String getDescription();

    String getTitle();

    String getUser_Id();

    String getName();

    String getProfile_image_url();

    String getCategory();

    List<String> getFileList();
}

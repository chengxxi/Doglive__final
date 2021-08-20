package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ApiModel("CommunityRegisterPostReq")
public class CommunityRegisterPostReq {
    @ApiModelProperty
    String userId;

    @ApiModelProperty
    String title;

    @ApiModelProperty
    String description;

    @ApiModelProperty
    String category;

    @ApiModelProperty
    List<MultipartFile> fileList;

    @ApiModelProperty
    List<String> delList;

    @Override
    public String toString() {
        return "CommunityRegisterPostReq{" +
                "userId='" + userId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", fileList=" + fileList +
                ", delList=" + delList +
                '}';
    }
}
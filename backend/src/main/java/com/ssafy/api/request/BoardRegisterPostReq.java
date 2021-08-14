package com.ssafy.api.request;

import com.ssafy.db.entity.board.BoardCategory;
import com.ssafy.db.entity.board.Code;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Board 등록 시 요청 Req
 */

@Getter
@Setter
@ApiModel("BoardRegisterPostReq")
public class BoardRegisterPostReq {
    @ApiModelProperty
    String userId;

    @ApiModelProperty
    String boardType;

    @ApiModelProperty
    String dogName;

    @ApiModelProperty
    String title;

    @ApiModelProperty
    List<MultipartFile> fileList;

    @ApiModelProperty
    List<String> delList;

    @ApiModelProperty
    String colorType;

    @ApiModelProperty
    String description;

    @ApiModelProperty
    String gender;

    @ApiModelProperty
    String dogType;

    @ApiModelProperty
    String age;

    @ApiModelProperty
    boolean neutralization;

    @ApiModelProperty
    String weight;

    @ApiModelProperty
    String mbti;

    @ApiModelProperty
    String address;

    @ApiModelProperty
    String gugun;

    @Override
    public String toString() {
        return "BoardRegisterPostReq{" +
                "userId='" + userId + '\'' +
                ", boardType=" + boardType +
                ", dogName='" + dogName + '\'' +
                ", title='" + title + '\'' +
                ", fileList=" + fileList +
                ", colorType=" + colorType +
                ", description='" + description + '\'' +
                ", gender=" + gender +
                ", dogType=" + dogType +
                ", age=" + age +
                ", neutralization=" + neutralization +
                ", weight=" + weight +
                ", mbti='" + mbti + '\'' +
                ", address='" + address + '\'' +
                ", gugun='" + gugun + '\'' +
                '}';
    }
}

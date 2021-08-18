package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ApiModel("UserUpdatePutRequest")
public class UserUpdatePutReq {
    @ApiModelProperty
    String name;

    @ApiModelProperty
    String email;

    @ApiModelProperty
    String phoneNumber;

    @ApiModelProperty
    String birth;

    @ApiModelProperty
    MultipartFile file;

    @Override
    public String toString() {
        return "UserUpdatePutReq{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", birth='" + birth + '\'' +
                ", file=" + file +
                '}';
    }
}

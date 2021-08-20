package com.ssafy.api.response;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 입양 신청서 Content DTO
 */

@Getter
@Setter
@Data
public class AdoptFormData {


    String name;

    String email;

    String phone;

    String sido;

    String gugun;

    String age;

    String isMarried;

    String gender;

    String title;

    String dogName;

    String type;

    String answer1;
    String answer1sub;
    String answer2;
    String answer2sub;
    String answer3;
    String answer3sub;
    String answer4;
    String answer5;
    String answer6;
    String answer7;
    String answer8;
    String answer9;
    String answer10;

    public AdoptFormData(String name, String email, String phone, String sido, String gugun, String age, String isMarried, String gender, String title, String dogName, String type, String answer1, String answer1sub, String answer2, String answer2sub, String answer3, String answer3sub, String answer4, String answer5, String answer6, String answer7, String answer8, String answer9, String answer10) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.sido = sido;
        this.gugun = gugun;
        this.age = age;
        this.isMarried = isMarried;
        this.gender = gender;
        this.title = title;
        this.dogName = dogName;
        this.type = type;
        this.answer1 = answer1;
        this.answer1sub = answer1sub;
        this.answer2 = answer2;
        this.answer2sub = answer2sub;
        this.answer3 = answer3;
        this.answer3sub = answer3sub;
        this.answer4 = answer4;
        this.answer5 = answer5;
        this.answer6 = answer6;
        this.answer7 = answer7;
        this.answer8 = answer8;
        this.answer9 = answer9;
        this.answer10 = answer10;
    }
}

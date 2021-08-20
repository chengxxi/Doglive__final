package com.ssafy.api.response;

import com.ssafy.db.entity.board.BoardCategory;
import com.ssafy.db.entity.board.Code;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardListData {

    Long boardId;

    String userId;

    BoardCategory type;

    String title;

    String thumbnailUrl;

    LocalDateTime regDate;

    Code gender;

    String dogName;

    String mbti;

    Code age;

    boolean neutralization;

    Code colorType;

    Code hairType;

    Code weight;
}

package com.ssafy.db.entity.board;

import com.querydsl.core.annotations.QueryProjection;
import com.ssafy.db.entity.board.BoardCategory;
import com.ssafy.db.entity.board.Code;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 유기동물 게시판 전체 목록 페이지에서 필요한 데이터
 */
@Data
public class BoardListData {

    Long id;

    String userId;

    BoardCategory boardType;

    String title;

    String thumbnailUrl;

    LocalDateTime regDate;

    Code gender;

    String dogName;

    String mbti;

    Code ageType;

    Code colorType;

    Code dogType;

    Code weight;

    public BoardListData(Long id, String userId, BoardCategory boardType, String title, String thumbnailUrl, LocalDateTime regDate, Code gender, String dogName, String mbti, Code ageType, Code colorType, Code dogType, Code weight) {
        this.id = id;
        this.userId = userId;
        this.boardType = boardType;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.regDate = regDate;
        this.gender = gender;
        this.dogName = dogName;
        this.mbti = mbti;
        this.ageType = ageType;
        this.colorType = colorType;
        this.dogType = dogType;
        this.weight = weight;
    }
}

package com.ssafy.db.entity.board;

import lombok.Data;

// 검색 조건으로 들어올 것 받을 DTO
@Data
public class SearchCondition {
    BoardCategory boardType;
    int limit;
    int offset;
    String searchWord;
    Code age;
    Code gender;
    Code weight;
}

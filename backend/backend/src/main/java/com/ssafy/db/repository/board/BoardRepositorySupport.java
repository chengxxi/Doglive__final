package com.ssafy.db.repository.board;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.response.BoardDetailListGetRes;
import com.ssafy.db.entity.board.*;
import com.thoughtworks.paranamer.JavadocParanamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QBoard b = QBoard.board;
    QDogInformation d = QDogInformation.dogInformation;


    public BoardDetailListGetRes searchBoardList(SearchCondition condition){

//        QueryResults<BoardListData> results = jpaQueryFactory.select(Projections.constructor(
//                BoardListData.class,
//                b.id,
//                b.userId,
//                b.type.as("boardType"),
//                b.title,
//                b.thumbnailUrl,
//                b.regDate,
//                d.gender,
//                d.dogName,
//                d.mbti,
//                d.age,
//                d.colorType,
//                d.dogType,
//                d.weight))
//                .from(d)
//                .join(d.boardId, b)
//                .where(
//                        boardTypeEq(condition.getBoardType()),
//                        searchWordEq(condition.getSearchWord()),
//                        ageEq(condition.getAge()),
//                        genderEq(condition.getGender()),
//                        weightEq(condition.getWeight())
//                ).offset(condition.getOffset())
//                .limit(condition.getLimit())
//                .fetchResults();


        QueryResults<DogInformation> results = jpaQueryFactory.select(
                d)
                .from(d)
                .join(d.boardId, b)
                .offset(condition.getOffset())
                .limit(condition.getLimit())
                .fetchResults();

//        List<BoardListData> content = results.getResults();
//        long totalCnt = results.getTotal();
        for(DogInformation d : results.getResults()){
            System.out.println(d.getDescription());
        }
        System.out.println();

        BoardDetailListGetRes boardDetailListGetRes = new BoardDetailListGetRes();
//        for(BoardListData boardListData : content){
//            String title = boardListData.getTitle();
//            System.out.println(title);
//        }
//        boardDetailListGetRes.setBoardList(content);
//        boardDetailListGetRes.setCount(totalCnt);
        return boardDetailListGetRes;
    }

    private BooleanExpression boardTypeEq(BoardCategory boardCategory){
        return boardCategory != null ? b.type.eq(boardCategory) : null;
    }

    private BooleanExpression searchWordEq(String searchWord){
        return searchWord != null ? d.dogName.eq(searchWord) : null;
    }

    private BooleanExpression ageEq(Code age){
        return age != null ? d.age.eq(age) : null;
    }


    private BooleanExpression genderEq(Code gender){
        return gender != null ? d.gender.eq(gender) : null;
    }

    private BooleanExpression weightEq(Code weight){
        return weight != null ? d.weight.eq(weight) : null;
    }

}

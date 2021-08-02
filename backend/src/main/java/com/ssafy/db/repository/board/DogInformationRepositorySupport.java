package com.ssafy.db.repository.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.board.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DogInformationRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QDogInformation qDogInformation = QDogInformation.dogInformation;

    public Optional<DogInformation> findDogInformationByBoardId(Board boardId) {
        DogInformation dogInformation = jpaQueryFactory.select(qDogInformation).from(qDogInformation)
                .where(qDogInformation.boardId.eq(boardId)).fetchOne();
        if(dogInformation == null) return Optional.empty();
        return Optional.ofNullable(dogInformation);
    }
}

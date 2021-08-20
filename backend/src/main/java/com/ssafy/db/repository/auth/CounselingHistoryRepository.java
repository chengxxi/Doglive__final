package com.ssafy.db.repository.auth;

import com.ssafy.db.entity.auth.CounselingHistory;
import com.ssafy.db.entity.auth.User;
import com.ssafy.db.entity.auth.UserProfile;
import com.ssafy.db.entity.chat.ChatRoom;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CounselingHistoryRepository extends JpaRepository<CounselingHistory, Long> {
    Optional<CounselingHistory> findCounselingHistoryById(Long id);

    Optional<CounselingHistory> findCounselingHistoryByApplicantId(UserProfile userProfile);

    Optional<List<CounselingHistory>> findCounselingHistoriesByApplicantId(UserProfile userProfile);

    Optional<List<CounselingHistory>> findCounselingHistoriesByWriter(String id);

    @Transactional
    Optional<List<CounselingHistory>> deleteCounselingHistoriesByApplicantId(UserProfile userProfile);

    @Transactional
    Optional<List<CounselingHistory>> deleteCounselingHistoriesByWriter(String id);


    Optional<CounselingHistory> findCounselingHistoryByApplicantIdAndBoardId(UserProfile applicantId, Long boardId);

    @Transactional
    Optional<CounselingHistory> deleteCounselingHistoriesById(Long id);

}

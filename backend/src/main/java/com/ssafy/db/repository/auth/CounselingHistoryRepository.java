package com.ssafy.db.repository.auth;

import com.ssafy.db.entity.auth.CounselingHistory;
import com.ssafy.db.entity.auth.User;
import com.ssafy.db.entity.auth.UserProfile;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public interface CounselingHistoryRepository extends JpaRepository<CounselingHistory, Long> {

    Optional<CounselingHistory> findCounselingHistoryByApplicantId(UserProfile userProfile);

    Optional<List<CounselingHistory>> findCounselingHistoriesByApplicantId(UserProfile userProfile);

    Optional<List<CounselingHistory>> findCounselingHistoriesByWriter(String id);
}

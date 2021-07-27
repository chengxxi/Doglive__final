package com.ssafy.db.repository.chat;

import com.ssafy.db.entity.chat.ConferenceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceHistoryRepository extends JpaRepository<ConferenceHistory, Long> {


}


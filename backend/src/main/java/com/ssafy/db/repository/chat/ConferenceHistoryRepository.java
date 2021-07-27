package com.ssafy.db.repository.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceHistoryRepository extends JpaRepository<ConferenceHistoryRepository, Long> {


}


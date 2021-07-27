package com.ssafy.db.repository.chat;

import com.ssafy.db.entity.chat.ConferenceUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Repository
public interface ConferenceUserRepository extends JpaRepository<ConferenceUser, Long> {

}


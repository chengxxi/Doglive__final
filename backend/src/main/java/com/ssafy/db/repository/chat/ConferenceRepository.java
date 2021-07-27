package com.ssafy.db.repository.chat;

import com.ssafy.db.entity.chat.Conference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Long> {

}


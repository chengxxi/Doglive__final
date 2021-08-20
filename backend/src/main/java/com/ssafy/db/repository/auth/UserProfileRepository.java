package com.ssafy.db.repository.auth;

import com.ssafy.db.entity.auth.User;
import com.ssafy.db.entity.auth.UserProfile;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByUserId(User user);
    Optional<UserProfile> findById(Long id);
}

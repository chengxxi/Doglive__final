package com.ssafy.db.repository.auth;

import com.ssafy.db.entity.auth.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}

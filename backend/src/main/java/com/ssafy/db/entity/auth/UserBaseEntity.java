package com.ssafy.db.entity.auth;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * UserBaseEntity ( User관련 Entity 간 공통 컬럼 정의. )
 */
@Entity
@Getter
@Setter
public class UserBaseEntity {
    @Id
    @Column(length = 13)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
}

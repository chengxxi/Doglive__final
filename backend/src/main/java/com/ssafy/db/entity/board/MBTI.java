package com.ssafy.db.entity.board;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

@Entity
@Table(name="mbti", schema = "board")
@Getter
@Setter
public class MBTI extends BaseEntity{

    @Column(length = 20)
    String name;


    String title;

    @Column(length = 1000)
    String desc;

    @Column(name="image_url")
    String imageUrl;
}

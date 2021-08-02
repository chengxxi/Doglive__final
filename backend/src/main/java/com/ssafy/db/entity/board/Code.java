package com.ssafy.db.entity.board;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="code", schema = "board")
@Getter
@Setter
public class Code extends BaseEntity{

    @Column(length = 10)
    String name;

}

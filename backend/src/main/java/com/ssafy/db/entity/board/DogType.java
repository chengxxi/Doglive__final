package com.ssafy.db.entity.board;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="dog_type", schema = "board")
@Getter
@Setter
public class DogType extends BaseEntity{

    @Column(length = 20)
    String name;

}

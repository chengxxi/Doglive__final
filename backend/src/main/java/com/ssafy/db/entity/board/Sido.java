package com.ssafy.db.entity.board;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="sido", schema = "board")
@Getter
@Setter
public class Sido extends BaseEntity {

    String name;
}

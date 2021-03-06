package com.ssafy.db.repository.board;

import com.ssafy.db.entity.board.Sido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SidoRepository extends JpaRepository<Sido, Long> {
}

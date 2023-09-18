package com.project.myweb.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.myweb.board.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

	@Modifying
	@Query(value = "update BoardEntity b set b.boardHits = b.boardHits+1 where b.id=:id")
	void updateHits(@Param("id") int id);
}

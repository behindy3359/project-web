package com.project.myweb.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.myweb.board.entity.BoardEntity;
import com.project.myweb.board.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
	
	List<CommentEntity> findAllByBoardEntityOrderByIdDesc(BoardEntity boardEntity); 
}
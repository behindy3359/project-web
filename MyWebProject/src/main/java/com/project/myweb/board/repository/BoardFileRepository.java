package com.project.myweb.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.myweb.board.entity.BoardFileEntity;

public interface BoardFileRepository extends JpaRepository<BoardFileEntity, Integer>{

}

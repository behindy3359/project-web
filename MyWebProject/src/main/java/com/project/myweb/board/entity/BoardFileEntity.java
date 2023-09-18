package com.project.myweb.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "board_file_table")
public class BoardFileEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String originalFileName;

	@Column
	private String storedFileName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id")
	private BoardEntity boardEntity;

	public static BoardFileEntity toBoardFileEntity(BoardEntity boardEntity, String originalFileName,
			String storedFileName) {

		BoardFileEntity boardFileEntity = new BoardFileEntity();

		boardFileEntity.setOriginalFileName(originalFileName);
		boardFileEntity.setStoredFileName(storedFileName);
		boardFileEntity.setBoardEntity(boardEntity);

		return boardFileEntity;
	}
}

package com.project.myweb.board.dto;

import java.time.LocalDateTime;

import com.project.myweb.board.entity.CommentEntity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CommentDTO {
	private int id;
	private String commentWriter;
	private int commentWriterId;
	private String commentContents;
	private int boardId;
	private LocalDateTime commentCreatedTime;
	
	
	public static CommentDTO toCommentDTO(CommentEntity commentEntity, int boardId) {
		// TODO Auto-generated method stub
		CommentDTO commentDTO = new CommentDTO();
		
		commentDTO.setId(commentEntity.getId());
		commentDTO.setCommentWriter(commentEntity.getCommentWriter());
		commentDTO.setCommentWriterId(commentEntity.getCommentWriterId());
		commentDTO.setCommentContents(commentEntity.getCommentContents());
		commentDTO.setCommentCreatedTime(commentEntity.getCreatedTime());
		commentDTO.setBoardId(boardId);
		
		return commentDTO;
	}
}

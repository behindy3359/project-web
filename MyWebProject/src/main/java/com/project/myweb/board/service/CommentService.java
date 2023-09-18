package com.project.myweb.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.myweb.board.dto.CommentDTO;
import com.project.myweb.board.entity.BoardEntity;
import com.project.myweb.board.entity.CommentEntity;
import com.project.myweb.board.repository.BoardRepository;
import com.project.myweb.board.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;
	private final BoardRepository boardRepository;

	public int save(CommentDTO commentDTO) {
		// TODO Auto-generated method stub
		Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(commentDTO.getBoardId());
		if (optionalBoardEntity.isPresent()) {
			BoardEntity boardEntity = optionalBoardEntity.get();
			CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, boardEntity);

			return commentRepository.save(commentEntity).getId();
		} else {
			return -1;
		}
	}

	public List<CommentDTO> findAll(int boardId) {
		// TODO Auto-generated method stub

		BoardEntity boardEntity = boardRepository.findById(boardId).get();
		List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);
		List<CommentDTO> commentDTOList = new ArrayList<>();

		for (CommentEntity commentEntity : commentEntityList) {
			CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity, boardId);
			commentDTOList.add(commentDTO);
		}

		return commentDTOList;
	}
}

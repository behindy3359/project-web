package com.project.myweb.board.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.myweb.board.dto.BoardDTO;
import com.project.myweb.board.entity.BoardEntity;
import com.project.myweb.board.entity.BoardFileEntity;
import com.project.myweb.board.repository.BoardFileRepository;
import com.project.myweb.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;
	private final BoardFileRepository boardFileRepository;

	// About write on Board
	public void save(BoardDTO boardDTO ,HttpSession session) throws IOException {
		int boardWriterId = (Integer) session.getAttribute("loginId");
		boardDTO.setBoardWriterId(boardWriterId);
		
		if (boardDTO.getBoardFile().isEmpty()) {
			BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
			boardRepository.save(boardEntity);

		} else {

			BoardEntity boardEntity = BoardEntity.toSaveFileEntity(boardDTO);
			int savedId = boardRepository.save(boardEntity).getId();
			BoardEntity board = boardRepository.findById(savedId).get();

			for (MultipartFile boardFile : boardDTO.getBoardFile()) {
				String originalFileName = boardFile.getOriginalFilename();
				String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
				String savaPath = "C:/workspace/web/practice-web-board/PracticeWebBoard/src/main/resources/templates/filestorage/"
						+ storedFileName;

				boardFile.transferTo(new File(savaPath));

				BoardFileEntity boardFileEntity = BoardFileEntity.toBoardFileEntity(board, originalFileName,
						storedFileName);

				boardFileRepository.save(boardFileEntity);
			}
		}
	}

	// About browse board
	@Transactional
	public List<BoardDTO> findAll() {

		List<BoardEntity> boardEntityList = boardRepository.findAll();
		List<BoardDTO> boardDTOList = new ArrayList<>();

		for (BoardEntity boardEntity : boardEntityList) {
			boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
		}

		return boardDTOList;
	}

	@Transactional
	public void updateHits(int id) {
		boardRepository.updateHits(id);

	}

	@Transactional
	public BoardDTO findById(int id) {

		Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
		if (optionalBoardEntity.isPresent()) {

			BoardEntity boardEntity = optionalBoardEntity.get();
			BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);

			return boardDTO;
		} else {

			return null;
		}

	}

	// About modify/ update board
	public BoardDTO update(BoardDTO boardDTO) {

		BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
		boardRepository.save(boardEntity);

		return findById(boardDTO.getId());
	}

	// About delete board
	public void delete(int id) {
		// TODO Auto-generated method stub
		boardRepository.deleteById(id);
	}

	// About paging
	public Page<BoardDTO> paging(Pageable pageable) {
		// TODO Auto-generated method stub

		int page = pageable.getPageNumber() - 1;
		int pageLimit = 5;

		Page<BoardEntity> boardEntities = boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
		Page<BoardDTO> boardDTOS = boardEntities.map(board -> new BoardDTO(board.getId(), board.getBoardWriter(),
				board.getBoardTitle(), board.getBoardHits(), board.getCreatedTime()));

		return boardDTOS;
	}
}

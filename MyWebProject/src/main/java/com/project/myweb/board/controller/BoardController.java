package com.project.myweb.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.myweb.board.dto.BoardDTO;
import com.project.myweb.board.dto.CommentDTO;
import com.project.myweb.board.service.BoardService;
import com.project.myweb.board.service.CommentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	private final CommentService commentService;

	// About write on board
	@GetMapping("/write")
	public String saveForm() {

		return "/board/board-write-form";
	}

	@PostMapping("/write")
	public String save(@ModelAttribute BoardDTO boardDTO, HttpSession session) throws IOException {

		boardService.save(boardDTO, session);

		return "/board/board-list";
	}

	// About browse board
	@GetMapping("/list")
	public String findAll(Model model) {
		List<BoardDTO> boardDTOlist = boardService.findAll();
		model.addAttribute("boardList", boardDTOlist);

		return "/board/board-list";
	}

	@GetMapping("/{id}")
	public String findById(@PathVariable int id, Model model, @PageableDefault(page = 1) Pageable pageable) {

		boardService.updateHits(id);
		BoardDTO boardDTO = boardService.findById(id);
		List<CommentDTO>commentDTOList = commentService.findAll(id);
		
		model.addAttribute("commentList", commentDTOList);
		model.addAttribute("board", boardDTO);
		model.addAttribute("page", pageable.getPageNumber());

		return "/board/board-detail";
	}

	// About update/modify board
	@GetMapping("/update/{id}")
	public String updateForm(@PathVariable int id, Model model) {

		BoardDTO boardDTO = boardService.findById(id);
		model.addAttribute("boardUpdate", boardDTO);

		return "/board/board-update-form";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute BoardDTO boardDTO, Model model) {

		BoardDTO board = boardService.update(boardDTO);
		model.addAttribute("board", boardDTO);

		return "/board/board-detail";
	}

	// About delete board
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id) {

		boardService.delete(id);

		return "redirect:/board/board-list";
	}

	// About paging
	@GetMapping("")
	public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {

		int blockLimit = 3;
		Page<BoardDTO> boardList = boardService.paging(pageable);

		int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
		int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1
				: boardList.getTotalPages();

		model.addAttribute("boardList", boardList);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		return "/board/board-paging";
	}

}

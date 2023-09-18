package com.project.myweb.member.cotroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.project.myweb.member.dto.MemberDTO;
import com.project.myweb.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/signup")
	public String memberSaveForm() {

		return "/member/member-signup";
	}

	@PostMapping("/signup")
	public String memberSave(@ModelAttribute MemberDTO memberDTO) {

		memberService.save(memberDTO);

		return "/member/member-signin";
	}

	@GetMapping("/login")
	public String memberloginForm() {

		return "/member/member-signin";
	}

	@PostMapping("/login")
	public String memberLogin(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
		
		MemberDTO loginResult = memberService.login(memberDTO);
		
		if (loginResult != null) {
			// login 성공
			session.setAttribute("loginId", loginResult.getId());
			session.setAttribute("loginName", loginResult.getMemberName());
			System.out.println("login 성공");
			return "/main/main";
		} else {
			// login 실패
			System.out.println("login 실패");
			return "redirect:/member/member-signin";
		}
	}

	@GetMapping("/list")
	public String memberlist(Model model) {

		List<MemberDTO> memberDTOList = memberService.findAll();
		model.addAttribute("memberList", memberDTOList);

		return "/member/member-list";
	}

	@GetMapping("/detail/{id}")
	public String findById(@PathVariable int id, Model model) {

		MemberDTO memberDTO = memberService.findById(id);
		model.addAttribute("member", memberDTO);

		return "/member/member-detail";
	}

	@GetMapping("/update")
	public String updateForm(HttpSession session, Model model) {
		String myId = (String) session.getAttribute("loginId");
		MemberDTO memberDTO = memberService.updateForm(myId);

		model.addAttribute("updateMember", memberDTO);

		return "/member/member-update";
	}

	@PostMapping("/update")
	public String memberUpdate(@ModelAttribute MemberDTO memberDTO) {

		memberService.update(memberDTO);

		System.out.println(memberDTO.getId());
		System.out.println(memberDTO.getMemberEmail());
		System.out.println(memberDTO.getMemberPassword());
		System.out.println(memberDTO.getMemberName());

		return "redirect:/member/member-detail/" + memberDTO.getId();
	}

	@GetMapping("/delete/{id}")
	public String deleteById(@PathVariable int id) {

		memberService.deleteById(id);

		return "redirect:/member/member-list";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {

		session.invalidate();

		return "index";
	}

	@PostMapping("/email-check")
	public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail) {
		
		System.out.println("memberEmail :" + memberEmail);
		String checkResult=memberService.emailCheck(memberEmail);
		
		return checkResult;
	}
	@PostMapping("/membername-check")
	public @ResponseBody String memberNameCheck(@RequestParam("memberName") String memberName) {
		
		System.out.println("memberName :" + memberName);
		String checkResult=memberService.memberNameCheck(memberName);
		
		return checkResult;
	}	
	@PostMapping("/memberid-check")
	public @ResponseBody String memberIdCheck(@RequestParam("memberId") String memberId) {
		
		System.out.println("memberId :" + memberId);
		String checkResult=memberService.memberNameCheck(memberId);
		
		return checkResult;
	}
}

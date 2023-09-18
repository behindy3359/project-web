package com.project.myweb.member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.myweb.member.dto.MemberDTO;
import com.project.myweb.member.entity.MemberEntity;
import com.project.myweb.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;

	public void save(MemberDTO memberDTO) {
		// TODO Auto-generated method stub

		MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);

		memberRepository.save(memberEntity);
	}

	public MemberDTO login(MemberDTO memberDTO) {

		Optional<MemberEntity> byMemberId = memberRepository.findByMemberId(memberDTO.getMemberId());

		if (byMemberId.isPresent()) {
			MemberEntity memberEntity = byMemberId.get();
			if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
				MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
				return dto;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public List<MemberDTO> findAll() {
		// TODO Auto-generated method stub
		List<MemberEntity> memberEntityList = memberRepository.findAll();
		List<MemberDTO> memberDTOList = new ArrayList<>();

		for (MemberEntity memberEntity : memberEntityList) {
			memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
		}
		return memberDTOList;
	}

	public MemberDTO findById(int id) {
		// TODO Auto-generated method stub
		Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);

		if (optionalMemberEntity.isPresent()) {

			return MemberDTO.toMemberDTO(optionalMemberEntity.get());
		} else {

			return null;
		}
	}

	public MemberDTO updateForm(String myId) {
		// TODO Auto-generated method stub
		Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(myId);
		if (optionalMemberEntity.isPresent()) {

			return MemberDTO.toMemberDTO(optionalMemberEntity.get());
		} else {

			return null;
		}
	}

	public void update(MemberDTO memberDTO) {
		// TODO Auto-generated method stub

		memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));
	}

	public void deleteById(int id) {
		// TODO Auto-generated method stub

		memberRepository.deleteById(id);
	}
	
	public String emailCheck(String memberEmail) {
		Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberEmail);
		
		if(byMemberEmail.isPresent()) {
			return null;
		}else {
			return "ok";
		}
	}
	
	public String memberNameCheck(String memberName) {
		Optional<MemberEntity> byMemberName = memberRepository.findByMemberName(memberName);
		
		if(byMemberName.isPresent()) {
			return null;
		}else {
			return "ok";
		}
	}
	
	public String memberIdCheck(String memberId) {
		Optional<MemberEntity> byMemberId = memberRepository.findByMemberId(memberId);
		
		if(byMemberId.isPresent()) {
			return null;
		}else {
			return "ok";
		}
	}
}

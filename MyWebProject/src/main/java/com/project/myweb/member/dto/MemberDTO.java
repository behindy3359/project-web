package com.project.myweb.member.dto;

import com.project.myweb.member.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
	private int id;
	private String memberEmail;
	private String memberId;
	private String memberName;
	private String memberPassword;
	
	public static MemberDTO toMemberDTO(MemberEntity memberEntity) {

		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId(memberEntity.getId());
		memberDTO.setMemberEmail(memberEntity.getMemberEmail());
		memberDTO.setMemberPassword(memberEntity.getMemberPassword());
		memberDTO.setMemberName(memberEntity.getMemberName());
		memberDTO.setMemberId(memberEntity.getMemberName());

		return memberDTO;
	}
}

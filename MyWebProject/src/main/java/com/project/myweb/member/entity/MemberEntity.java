package com.project.myweb.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.myweb.member.dto.MemberDTO;

import lombok.Data;

@Entity
@Data
@Table( name = "member_table" )
public class MemberEntity {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;
	
	@Column( unique = true )
	private String memberEmail;
	
	@Column( unique = true )
	private String memberId;
	
	@Column( unique = true )
	private String memberName;
	
	@Column
	private String memberPassword;
	
	public static MemberEntity toMemberEntity(MemberDTO memberDTO) {

		MemberEntity memberEntity = new MemberEntity();
		
		memberEntity.setMemberEmail(memberDTO.getMemberEmail());
		memberEntity.setMemberPassword(memberDTO.getMemberPassword());
		memberEntity.setMemberName(memberDTO.getMemberName());
		memberEntity.setMemberId(memberDTO.getMemberId());

		return memberEntity;
	}

	public static MemberEntity toUpdateMemberEntity(MemberDTO memberDTO) {

		MemberEntity memberEntity = new MemberEntity();

		memberEntity.setId(memberDTO.getId());
		memberEntity.setMemberEmail(memberDTO.getMemberEmail());
		memberEntity.setMemberPassword(memberDTO.getMemberPassword());
		memberEntity.setMemberName(memberDTO.getMemberName());
		memberEntity.setMemberId(memberDTO.getMemberId());

		return memberEntity;
	}
}

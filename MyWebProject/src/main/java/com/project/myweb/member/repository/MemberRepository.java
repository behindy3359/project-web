package com.project.myweb.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.myweb.member.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {

	Optional<MemberEntity> findByMemberEmail(String memberEmail);
	Optional<MemberEntity> findByMemberName(String memberName);
	Optional<MemberEntity> findByMemberId(String memberId);
	
}

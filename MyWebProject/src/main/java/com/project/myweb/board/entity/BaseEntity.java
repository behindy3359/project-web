package com.project.myweb.board.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class BaseEntity {
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdTime;

	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDateTime updatedTime;
}

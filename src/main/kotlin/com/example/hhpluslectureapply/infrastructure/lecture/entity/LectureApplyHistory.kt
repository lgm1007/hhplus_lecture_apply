package com.example.hhpluslectureapply.infrastructure.lecture.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class LectureApplyHistory(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long?,
	val lectureId: Long,
	val userEmail: String,
) {
}
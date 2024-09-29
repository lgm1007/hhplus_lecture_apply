package com.example.hhpluslectureapply.infrastructure.lecture.entity

import com.example.hhpluslectureapply.domain.lecture.LectureApplyHistoryDto
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
	companion object {
		fun from(lectureApplyHistoryDto: LectureApplyHistoryDto): LectureApplyHistory {
			return LectureApplyHistory(
				null,
				lectureApplyHistoryDto.lectureId,
				lectureApplyHistoryDto.userEmail
			)
		}
	}
}
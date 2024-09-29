package com.example.hhpluslectureapply.domain.lecture

import com.example.hhpluslectureapply.infrastructure.lecture.entity.LectureApplyHistory

data class LectureApplyHistoryDto(
	val lectureId: Long,
	val userEmail: String
) {
	companion object {
		fun from(lectureApplyHistory: LectureApplyHistory): LectureApplyHistoryDto {
			return LectureApplyHistoryDto(lectureApplyHistory.lectureId, lectureApplyHistory.userEmail)
		}
	}
}
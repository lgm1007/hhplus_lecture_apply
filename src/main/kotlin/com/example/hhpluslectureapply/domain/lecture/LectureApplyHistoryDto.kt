package com.example.hhpluslectureapply.domain.lecture

import com.example.hhpluslectureapply.infrastructure.lecture.entity.LectureApplyHistory
import com.example.hhpluslectureapply.usecase.lecture.LectureApplyInfo

data class LectureApplyHistoryDto(
	val lectureId: Long,
	val userEmail: String
) {
	companion object {
		fun from(lectureApplyHistory: LectureApplyHistory): LectureApplyHistoryDto {
			return LectureApplyHistoryDto(lectureApplyHistory.lectureId, lectureApplyHistory.userEmail)
		}

		fun from(lectureApplyInfo: LectureApplyInfo): LectureApplyHistoryDto {
			return LectureApplyHistoryDto(lectureApplyInfo.lectureId, lectureApplyInfo.userEmail)
		}
	}
}
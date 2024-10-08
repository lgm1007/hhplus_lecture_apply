package com.example.hhpluslectureapply.domain.lecture.dto

import com.example.hhpluslectureapply.infrastructure.lecture.entity.LectureApplyHistory
import com.example.hhpluslectureapply.usecase.lecture.dto.LectureApplyInfo

data class LectureApplyHistoryDto(
	val lectureId: Long,
	val userId: Long
) {
	companion object {
		fun from(lectureApplyHistory: LectureApplyHistory): LectureApplyHistoryDto {
			return LectureApplyHistoryDto(lectureApplyHistory.lectureId, lectureApplyHistory.userId)
		}

		fun from(lectureApplyInfo: LectureApplyInfo): LectureApplyHistoryDto {
			return LectureApplyHistoryDto(lectureApplyInfo.lectureId, lectureApplyInfo.userId)
		}
	}
}
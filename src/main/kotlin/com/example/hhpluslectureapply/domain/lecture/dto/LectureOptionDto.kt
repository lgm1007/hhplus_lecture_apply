package com.example.hhpluslectureapply.domain.lecture.dto

import com.example.hhpluslectureapply.infrastructure.lecture.entity.LectureOption
import com.example.hhpluslectureapply.usecase.lecture.MAX_NUMBER_APPLY_LECTURE
import java.time.LocalDateTime

class LectureOptionDto(
	val lectureId: Long,
	val applicationDate: LocalDateTime,
	var currentApplicants: Int = 0,
) {

	companion object {
		fun from(lectureOption: LectureOption): LectureOptionDto {
			return LectureOptionDto(
				lectureOption.lectureId,
				lectureOption.applicationDate,
				lectureOption.currentApplicants
			)
		}
	}

	fun isFullCurrentApplicantsMaxApply(): Boolean {
		return currentApplicants >= MAX_NUMBER_APPLY_LECTURE
	}
}
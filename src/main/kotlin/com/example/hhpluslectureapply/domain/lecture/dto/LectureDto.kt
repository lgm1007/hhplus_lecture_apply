package com.example.hhpluslectureapply.domain.lecture.dto

import com.example.hhpluslectureapply.infrastructure.lecture.entity.Lecture
import com.example.hhpluslectureapply.usecase.lecture.MAX_NUMBER_APPLY_LECTURE
import java.time.LocalDateTime

data class LectureDto(
	val lectureId: Long,
	val title: String,
	val lecturer: String,
	var currentApplicants: Int = 0,
	val applicationDate: LocalDateTime,
) {
	companion object {
		fun from(lecture: Lecture): LectureDto {
			return LectureDto(
				lecture.id,
				lecture.title,
				lecture.lecturer,
				lecture.currentApplicants,
				lecture.applicationDate
			)
		}
	}

	fun isFullCurrentApplicantsMaxApply(): Boolean {
		return currentApplicants >= MAX_NUMBER_APPLY_LECTURE
	}
}
package com.example.hhpluslectureapply.domain.lecture.dto

import com.example.hhpluslectureapply.infrastructure.lecture.entity.Lecture
import java.time.LocalDateTime

data class LectureDto(
	val lectureId: Long,
	val title: String,
	val lecturer: String,
	val applicationDate: LocalDateTime,
) {
	companion object {
		fun from(lecture: Lecture): LectureDto {
			return LectureDto(
				lecture.id,
				lecture.title,
				lecture.lecturer,
				lecture.applicationDate
			)
		}
	}
}
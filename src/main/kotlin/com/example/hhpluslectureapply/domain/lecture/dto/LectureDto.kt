package com.example.hhpluslectureapply.domain.lecture.dto

import com.example.hhpluslectureapply.infrastructure.lecture.entity.Lecture

data class LectureDto(
	val lectureId: Long,
	val title: String,
	val lecturer: String,
) {
	companion object {
		fun from(lecture: Lecture): LectureDto {
			return LectureDto(
				lecture.id,
				lecture.title,
				lecture.lecturer,
			)
		}
	}
}
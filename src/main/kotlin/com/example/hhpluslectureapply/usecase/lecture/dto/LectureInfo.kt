package com.example.hhpluslectureapply.usecase.lecture.dto

import com.example.hhpluslectureapply.domain.lecture.dto.LectureDto
import com.example.hhpluslectureapply.domain.lecture.dto.LectureOptionDto
import java.time.LocalDateTime

data class LectureInfo(
	val lectureId: Long,
	val title: String,
	val lecturer: String,
	val applicationDate: LocalDateTime,
) {
	companion object {
		fun of(lectureDto: LectureDto, lectureOptionDto: LectureOptionDto): LectureInfo {
			return LectureInfo(
				lectureDto.lectureId,
				lectureDto.title,
				lectureDto.lecturer,
				lectureOptionDto.applicationDate
			)
		}
	}
}
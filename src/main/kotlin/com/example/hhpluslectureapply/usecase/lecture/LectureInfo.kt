package com.example.hhpluslectureapply.usecase.lecture

import com.example.hhpluslectureapply.domain.lecture.LectureDto
import java.time.LocalDateTime

data class LectureInfo(
	val title: String,
	val lecturer: String,
	val applicationDate: LocalDateTime,
) {
	companion object {
		fun from(lectureDto: LectureDto): LectureInfo {
			return LectureInfo(lectureDto.title, lectureDto.lecturer, lectureDto.applicationDate)
		}
	}
}
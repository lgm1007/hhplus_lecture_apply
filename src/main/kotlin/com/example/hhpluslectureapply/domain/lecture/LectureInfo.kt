package com.example.hhpluslectureapply.domain.lecture

import com.example.hhpluslectureapply.infrastructure.lecture.entity.Lecture
import java.time.LocalDateTime

data class LectureInfo(
	val title: String,
	val lecturer: String,
	val applicationDate: LocalDateTime,
) {
	companion object {
		fun from(lecture: Lecture): LectureInfo {
			return LectureInfo(lecture.title, lecture.lecturer, lecture.applicationDate)
		}
	}
}
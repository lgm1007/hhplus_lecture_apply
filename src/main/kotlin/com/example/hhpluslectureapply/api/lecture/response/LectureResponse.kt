package com.example.hhpluslectureapply.api.lecture.response

import com.example.hhpluslectureapply.usecase.lecture.dto.LectureInfo
import java.time.LocalDateTime

data class LectureResponse(
	val lectureId: Long,
	val title: String,
	val lecturer: String,
	val applicationDate: LocalDateTime,
) {
	companion object {
		fun from(lectureInfo: LectureInfo): LectureResponse {
			return LectureResponse(
				lectureInfo.lectureId,
				lectureInfo.title,
				lectureInfo.lecturer,
				lectureInfo.applicationDate
			)
		}
	}
}
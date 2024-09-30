package com.example.hhpluslectureapply.usecase.lecture

import com.example.hhpluslectureapply.api.lecture.request.LectureApplyRequest

data class LectureApplyInfo(
	val lectureId: Long,
	val userEmail: String,
) {
	companion object {
		fun from(lectureApplyRequest: LectureApplyRequest): LectureApplyInfo {
			return LectureApplyInfo(lectureApplyRequest.lectureId, lectureApplyRequest.userEmail)
		}
	}
}
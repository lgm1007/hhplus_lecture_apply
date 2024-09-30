package com.example.hhpluslectureapply.usecase.lecture

import com.example.hhpluslectureapply.api.lecture.request.LectureApplyRequest

data class LectureApplyInfo(
	val lectureId: Long,
	val userId: Long,
) {
	companion object {
		fun from(lectureApplyRequest: LectureApplyRequest): LectureApplyInfo {
			return LectureApplyInfo(lectureApplyRequest.lectureId, lectureApplyRequest.userId)
		}
	}
}
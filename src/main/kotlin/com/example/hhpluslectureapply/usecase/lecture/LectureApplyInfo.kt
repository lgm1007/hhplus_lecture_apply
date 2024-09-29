package com.example.hhpluslectureapply.usecase.lecture

data class LectureApplyInfo(
	val lectureId: Long,
	val userEmail: String,
) {
	companion object {
		fun of(lectureId: Long, userEmail: String): LectureApplyInfo {
			return LectureApplyInfo(lectureId, userEmail)
		}
	}
}
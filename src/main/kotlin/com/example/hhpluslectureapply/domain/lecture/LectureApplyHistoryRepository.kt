package com.example.hhpluslectureapply.domain.lecture

import com.example.hhpluslectureapply.infrastructure.lecture.entity.LectureApplyHistory

interface LectureApplyHistoryRepository {
	fun findByLectureIdAndUserEmail(lectureId: Long, userEmail: String): LectureApplyHistory?

	fun countByLectureId(lectureId: Long): Int
}
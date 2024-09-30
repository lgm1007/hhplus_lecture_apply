package com.example.hhpluslectureapply.domain.lecture

import com.example.hhpluslectureapply.infrastructure.lecture.entity.LectureApplyHistory

interface LectureApplyHistoryRepository {
	fun findAllByUserId(userId: Long): List<LectureApplyHistory>

	fun countByLectureId(lectureId: Long): Int

	fun insertOrUpdate(lectureApplyHistoryDto: LectureApplyHistoryDto): LectureApplyHistory
}
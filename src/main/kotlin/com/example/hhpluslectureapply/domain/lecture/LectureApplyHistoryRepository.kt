package com.example.hhpluslectureapply.domain.lecture

import com.example.hhpluslectureapply.domain.lecture.dto.LectureApplyHistoryDto
import com.example.hhpluslectureapply.infrastructure.lecture.entity.LectureApplyHistory

interface LectureApplyHistoryRepository {
	fun findAllByUserId(userId: Long): List<LectureApplyHistory>

	fun findAllByLectureId(lectureId: Long): List<LectureApplyHistory>

	fun countApplyHistoriesByLectureId(lectureId: Long): Int

	fun insertOrUpdate(lectureApplyHistoryDto: LectureApplyHistoryDto): LectureApplyHistory

	fun deleteAll()
}
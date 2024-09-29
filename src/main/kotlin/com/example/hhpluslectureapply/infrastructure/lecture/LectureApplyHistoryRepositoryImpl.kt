package com.example.hhpluslectureapply.infrastructure.lecture

import com.example.hhpluslectureapply.domain.lecture.LectureApplyHistoryRepository
import com.example.hhpluslectureapply.infrastructure.lecture.entity.LectureApplyHistory
import org.springframework.stereotype.Repository

@Repository
class LectureApplyHistoryRepositoryImpl(
	private val jpaRepository: LectureApplyHistoryJpaRepository
) : LectureApplyHistoryRepository {
	override fun findByLectureIdAndUserEmail(lectureId: Long, userEmail: String): LectureApplyHistory? {
		return jpaRepository.findByLectureIdAndUserEmail(lectureId, userEmail)
	}

	override fun countByLectureId(lectureId: Long): Int {
		return jpaRepository.countByLectureId(lectureId).toInt()
	}
}
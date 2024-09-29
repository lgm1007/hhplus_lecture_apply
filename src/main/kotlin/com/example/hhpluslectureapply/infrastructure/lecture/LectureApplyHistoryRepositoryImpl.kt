package com.example.hhpluslectureapply.infrastructure.lecture

import com.example.hhpluslectureapply.domain.lecture.LectureApplyHistoryRepository
import com.example.hhpluslectureapply.infrastructure.lecture.entity.LectureApplyHistory
import org.springframework.stereotype.Repository

@Repository
class LectureApplyHistoryRepositoryImpl(
	private val jpaRepository: LectureApplyHistoryJpaRepository
) : LectureApplyHistoryRepository {
	override fun findByUserEmail(userEmail: String): LectureApplyHistory? {
		return jpaRepository.findByUserEmail(userEmail)
	}

	override fun countByLectureId(lectureId: Long): Int {
		return jpaRepository.countByLectureId(lectureId).toInt()
	}
}
package com.example.hhpluslectureapply.infrastructure.lecture

import com.example.hhpluslectureapply.domain.lecture.LectureApplyHistoryRepository
import com.example.hhpluslectureapply.domain.lecture.dto.LectureApplyHistoryDto
import com.example.hhpluslectureapply.infrastructure.lecture.entity.LectureApplyHistory
import org.springframework.stereotype.Repository

@Repository
class LectureApplyHistoryRepositoryImpl(
	private val jpaRepository: LectureApplyHistoryJpaRepository
) : LectureApplyHistoryRepository {
	override fun findByLectureIdAndUserIdWithLock(lectureId: Long, userId: Long): LectureApplyHistory? {
		return jpaRepository.findByLectureIdAndUserIdWithLock(lectureId, userId)
	}

	override fun findAllByUserId(userId: Long): List<LectureApplyHistory> {
		return jpaRepository.findAllByUserId(userId)
	}

	override fun findAllByLectureId(lectureId: Long): List<LectureApplyHistory> {
		return jpaRepository.findAllByLectureId(lectureId)
	}

	override fun countApplyHistoriesByLectureId(lectureId: Long): Int {
		return jpaRepository.countByLectureId(lectureId)
	}

	override fun insertOrUpdate(lectureApplyHistoryDto: LectureApplyHistoryDto): LectureApplyHistory {
		return jpaRepository.save(LectureApplyHistory.from(lectureApplyHistoryDto))
	}

	override fun deleteAll() {
		jpaRepository.deleteAll()
	}
}
package com.example.hhpluslectureapply.infrastructure.lecture

import com.example.hhpluslectureapply.domain.lecture.LectureApplyHistoryDto
import com.example.hhpluslectureapply.domain.lecture.LectureApplyHistoryRepository
import com.example.hhpluslectureapply.infrastructure.lecture.entity.LectureApplyHistory
import org.springframework.stereotype.Repository

@Repository
class LectureApplyHistoryRepositoryImpl(
	private val jpaRepository: LectureApplyHistoryJpaRepository
) : LectureApplyHistoryRepository {
	override fun insertOrUpdate(lectureApplyHistoryDto: LectureApplyHistoryDto): LectureApplyHistory {
		return jpaRepository.save(LectureApplyHistory.from(lectureApplyHistoryDto))
	}
}
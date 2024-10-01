package com.example.hhpluslectureapply.infrastructure.lecture

import com.example.hhpluslectureapply.domain.lecture.LectureApplyHistoryRepository
import com.example.hhpluslectureapply.domain.lecture.dto.LectureApplyHistoryDto
import com.example.hhpluslectureapply.exception.LectureException
import com.example.hhpluslectureapply.infrastructure.lecture.entity.LectureApplyHistory
import com.example.hhpluslectureapply.usecase.lecture.MAX_NUMBER_APPLY_LECTURE
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Repository
class LectureApplyHistoryRepositoryImpl(
	private val jpaRepository: LectureApplyHistoryJpaRepository,
	private val lectureJpaRepository: LectureJpaRepository,
) : LectureApplyHistoryRepository {
	override fun findAllByUserId(userId: Long): List<LectureApplyHistory> {
		return jpaRepository.findAllByUserId(userId)
	}

	override fun findAllByLectureId(lectureId: Long): List<LectureApplyHistory> {
		return jpaRepository.findAllByLectureId(lectureId)
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	override fun applyLecture(lectureApplyHistoryDto: LectureApplyHistoryDto) {
		val lectureId = lectureApplyHistoryDto.lectureId
		val userId = lectureApplyHistoryDto.userId
		val lecture = lectureJpaRepository.findByIdWithLock(lectureId) ?: throw LectureException("ID: ${lectureId}에 해당하는 특강이 존재하지 않습니다.")

		if (lecture.currentApplicants >= MAX_NUMBER_APPLY_LECTURE) {
			throw LectureException("특강 아이디가 ${lectureId}에 해당하는 특강은 신청 정원이 마감되었습니다.")
		}

		lecture.currentApplicants++
		lectureJpaRepository.save(lecture)

		jpaRepository.save(LectureApplyHistory.from(lectureApplyHistoryDto))
	}

	override fun countApplyHistoriesByLectureId(lectureId: Long): Int {
		return jpaRepository.countByLectureId(lectureId)
	}

	override fun insertOrUpdate(lectureApplyHistoryDto: LectureApplyHistoryDto): LectureApplyHistory {
		return jpaRepository.save(LectureApplyHistory.from(lectureApplyHistoryDto))
	}
}
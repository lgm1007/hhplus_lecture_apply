package com.example.hhpluslectureapply.infrastructure.lecture

import com.example.hhpluslectureapply.domain.lecture.LectureOptionRepository
import com.example.hhpluslectureapply.domain.lecture.dto.LectureOptionDto
import com.example.hhpluslectureapply.exception.LectureException
import com.example.hhpluslectureapply.infrastructure.lecture.entity.LectureOption
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Repository
class LectureOptionRepositoryImpl(
	private val jpaRepository: LectureOptionJpaRepository
) : LectureOptionRepository {
	@Transactional(readOnly = true)
	override fun findByLectureIdWithLock(lectureId: Long): LectureOption? {
		return jpaRepository.findByLectureIdWithLock(lectureId)
	}

	@Transactional
	override fun updateCurrentApplicantsByLectureId(lectureId: Long): LectureOption {
		val lectureOption = jpaRepository.findByLectureId(lectureId)
			?: throw LectureException("강의 아이디: ${lectureId}에 해당하는 강의 정보가 존재하지 않습니다.")

		lectureOption.increaseCurrentApplicants()
		return jpaRepository.save(lectureOption)
	}

	override fun findAllByApplicationDateBefore(nowDate: LocalDateTime): List<LectureOption> {
		return jpaRepository.findDistinctByApplicationDateBefore(nowDate)
	}

	override fun findAllByLectureIdsIn(lectureIds: List<Long>): List<LectureOption> {
		return jpaRepository.findAllByLectureIdIn(lectureIds)
	}

	override fun insertOrUpdate(lectureOptionDto: LectureOptionDto): LectureOption {
		return jpaRepository.save(LectureOption.from(lectureOptionDto))
	}

	override fun deleteAll() {
		jpaRepository.deleteAll()
	}
}
package com.example.hhpluslectureapply.infrastructure.lecture

import com.example.hhpluslectureapply.domain.lecture.LectureRepository
import com.example.hhpluslectureapply.domain.lecture.dto.LectureDto
import com.example.hhpluslectureapply.exception.LectureException
import com.example.hhpluslectureapply.infrastructure.lecture.entity.Lecture
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Repository
class LectureRepositoryImpl(
	private val jpaRepository: LectureJpaRepository
) : LectureRepository {
	override fun findById(id: Long): Lecture? {
		return jpaRepository.findByIdOrNull(id)
	}

	@Transactional
	override fun updateCurrentApplicantsById(id: Long): Lecture {
		val lecture = findById(id) ?: throw LectureException("ID: ${id}에 해당하는 특강이 존재하지 않습니다.")
		lecture.currentApplicants++
		return jpaRepository.save(lecture)
	}

	override fun findAllByApplicationDateBefore(nowDate: LocalDateTime): List<Lecture> {
		return jpaRepository.findDistinctByApplicationDateBefore(nowDate)
	}

	override fun findAllByIdsIn(ids: List<Long>): List<Lecture> {
		return jpaRepository.findDistinctByIdIn(ids)
	}

	override fun insertOrUpdate(lectureDto: LectureDto): Lecture {
		return jpaRepository.save(Lecture.from(lectureDto))
	}
}
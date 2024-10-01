package com.example.hhpluslectureapply.infrastructure.lecture

import com.example.hhpluslectureapply.domain.lecture.LectureRepository
import com.example.hhpluslectureapply.domain.lecture.dto.LectureDto
import com.example.hhpluslectureapply.infrastructure.lecture.entity.Lecture
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class LectureRepositoryImpl(
	private val jpaRepository: LectureJpaRepository
) : LectureRepository {
	override fun findById(id: Long): Lecture? {
		return jpaRepository.findByIdOrNull(id)
	}

	override fun findAllByIdsIn(ids: List<Long>): List<Lecture> {
		return jpaRepository.findDistinctByIdIn(ids)
	}

	override fun insertOrUpdate(lectureDto: LectureDto): Lecture {
		return jpaRepository.save(Lecture.from(lectureDto))
	}
}
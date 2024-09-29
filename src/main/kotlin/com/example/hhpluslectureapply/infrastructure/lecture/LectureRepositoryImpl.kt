package com.example.hhpluslectureapply.infrastructure.lecture

import com.example.hhpluslectureapply.domain.lecture.LectureRepository
import com.example.hhpluslectureapply.infrastructure.lecture.entity.Lecture
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class LectureRepositoryImpl(
	private val jpaRepository: LectureJpaRepository
) : LectureRepository {
	override fun findById(id: Long): Lecture? {
		return jpaRepository.findByIdOrNull(id)
	}

	override fun findAllByApplicationDateBefore(nowDate: LocalDateTime): List<Lecture> {
		return jpaRepository.findDistinctByApplicationDateBefore(nowDate)
	}
}
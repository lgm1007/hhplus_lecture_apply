package com.example.hhpluslectureapply.domain.lecture

import com.example.hhpluslectureapply.domain.lecture.dto.LectureDto
import com.example.hhpluslectureapply.infrastructure.lecture.entity.Lecture
import java.time.LocalDateTime

interface LectureRepository {
	fun findById(id: Long): Lecture?

	fun updateCurrentApplicantsById(id: Long): Lecture

	fun findAllByApplicationDateBefore(nowDate: LocalDateTime): List<Lecture>

	fun findAllByIdsIn(ids: List<Long>): List<Lecture>

	fun insertOrUpdate(lectureDto: LectureDto): Lecture
}
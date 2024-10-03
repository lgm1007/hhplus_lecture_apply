package com.example.hhpluslectureapply.domain.lecture

import com.example.hhpluslectureapply.domain.lecture.dto.LectureOptionDto
import com.example.hhpluslectureapply.infrastructure.lecture.entity.LectureOption
import java.time.LocalDateTime

interface LectureOptionRepository {
	fun findByLectureIdWithLock(lectureId: Long): LectureOption?

	fun updateCurrentApplicantsByLectureId(lectureId: Long): LectureOption

	fun findAllByApplicationDateAfter(nowDate: LocalDateTime): List<LectureOption>

	fun findAllByLectureIdsIn(lectureIds: List<Long>): List<LectureOption>

	fun insertOrUpdate(lectureOptionDto: LectureOptionDto): LectureOption

	fun deleteAll()
}
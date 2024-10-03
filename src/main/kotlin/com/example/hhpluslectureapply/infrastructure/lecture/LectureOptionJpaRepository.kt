package com.example.hhpluslectureapply.infrastructure.lecture

import com.example.hhpluslectureapply.infrastructure.lecture.entity.LectureOption
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDateTime
import javax.persistence.LockModeType

interface LectureOptionJpaRepository : JpaRepository<LectureOption, Long> {
	fun findByLectureId(lectureId: Long): LectureOption?

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT lo FROM LectureOption lo WHERE lo.lectureId = :lectureId")
	fun findByLectureIdWithLock(@Param("lectureId") lectureId: Long): LectureOption?

	fun findDistinctByApplicationDateAfter(nowDate: LocalDateTime): List<LectureOption>

	fun findAllByLectureIdIn(lectureIds: List<Long>): List<LectureOption>
}
package com.example.hhpluslectureapply.infrastructure.lecture

import com.example.hhpluslectureapply.infrastructure.lecture.entity.Lecture
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDateTime
import javax.persistence.LockModeType

interface LectureJpaRepository : JpaRepository<Lecture, Long> {
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT l FROM Lecture l WHERE l.id = :lectureId")
	fun findByIdWithLock(@Param("lectureId") lectureId: Long): Lecture?

	fun findDistinctByApplicationDateBefore(nowDate: LocalDateTime): List<Lecture>

	fun findDistinctByIdIn(ids: List<Long>): List<Lecture>
}
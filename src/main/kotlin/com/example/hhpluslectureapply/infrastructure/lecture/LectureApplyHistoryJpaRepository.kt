package com.example.hhpluslectureapply.infrastructure.lecture

import com.example.hhpluslectureapply.infrastructure.lecture.entity.LectureApplyHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import javax.persistence.LockModeType

interface LectureApplyHistoryJpaRepository : JpaRepository<LectureApplyHistory, Long> {
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	fun findByLectureIdAndUserId(lectureId: Long, userId: Long): LectureApplyHistory?

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT count(*) FROM LectureApplyHistory WHERE lectureId = :lectureId")
	fun countByLectureId(@Param("lectureId") lectureId: Long): Long
}
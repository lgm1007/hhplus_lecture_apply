package com.example.hhpluslectureapply.infrastructure.lecture

import com.example.hhpluslectureapply.infrastructure.lecture.entity.LectureApplyHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import javax.persistence.LockModeType

interface LectureApplyHistoryJpaRepository : JpaRepository<LectureApplyHistory, Long> {
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT lah FROM LectureApplyHistory lah WHERE lah.lectureId = :lectureId AND lah.userId = :userId")
	fun findByLectureIdAndUserIdWithLock(@Param("lectureId") lectureId: Long, @Param("userId") userId: Long): LectureApplyHistory?

	fun findAllByUserId(userId: Long): List<LectureApplyHistory>

	fun findAllByLectureId(lectureId: Long): List<LectureApplyHistory>

	fun countByLectureId(lectureId: Long): Int
}
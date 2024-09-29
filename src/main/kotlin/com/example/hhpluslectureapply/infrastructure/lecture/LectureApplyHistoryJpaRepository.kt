package com.example.hhpluslectureapply.infrastructure.lecture

import com.example.hhpluslectureapply.infrastructure.lecture.entity.LectureApplyHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface LectureApplyHistoryJpaRepository : JpaRepository<LectureApplyHistory, Long> {
	fun findByUserEmail(userEmail: String): LectureApplyHistory?

	@Query("SELECT count(*)")
	fun countByLectureId(lectureId: Long): Long
}
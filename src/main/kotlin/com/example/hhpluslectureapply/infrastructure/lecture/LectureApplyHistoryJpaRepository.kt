package com.example.hhpluslectureapply.infrastructure.lecture

import com.example.hhpluslectureapply.infrastructure.lecture.entity.LectureApplyHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface LectureApplyHistoryJpaRepository : JpaRepository<LectureApplyHistory, Long> {
	fun findByUserEmail(userEmail: String): LectureApplyHistory?

	@Query("SELECT count(*) FROM LectureApplyHistory WHERE lectureId = :lectureId")
	fun countByLectureId(@Param("lectureId") lectureId: Long): Long
}
package com.example.hhpluslectureapply.infrastructure.lecture

import com.example.hhpluslectureapply.infrastructure.lecture.entity.LectureApplyHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param

interface LectureApplyHistoryJpaRepository : JpaRepository<LectureApplyHistory, Long> {
	fun findAllByUserId(userId: Long): List<LectureApplyHistory>

	fun findAllByLectureId(@Param("lectureId") lectureId: Long): List<LectureApplyHistory>

	fun countByLectureId(lectureId: Long): Int
}
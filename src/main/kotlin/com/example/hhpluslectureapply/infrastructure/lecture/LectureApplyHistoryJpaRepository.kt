package com.example.hhpluslectureapply.infrastructure.lecture

import com.example.hhpluslectureapply.infrastructure.lecture.entity.LectureApplyHistory
import org.springframework.data.jpa.repository.JpaRepository

interface LectureApplyHistoryJpaRepository : JpaRepository<LectureApplyHistory, Long> {
	fun findAllByUserId(userId: Long): List<LectureApplyHistory>
}
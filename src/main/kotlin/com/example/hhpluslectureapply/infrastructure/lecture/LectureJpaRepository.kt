package com.example.hhpluslectureapply.infrastructure.lecture

import com.example.hhpluslectureapply.infrastructure.lecture.entity.Lecture
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface LectureJpaRepository : JpaRepository<Lecture, Long> {
	fun findDistinctByApplicationDateBefore(nowDate: LocalDateTime): List<Lecture>

	fun findDistinctByIdIn(ids: List<Long>): List<Lecture>
}
package com.example.hhpluslectureapply.infrastructure.lecture

import com.example.hhpluslectureapply.infrastructure.lecture.entity.Lecture
import org.springframework.data.jpa.repository.JpaRepository

interface LectureJpaRepository : JpaRepository<Lecture, Long> {

	fun findDistinctByIdIn(ids: List<Long>): List<Lecture>
}
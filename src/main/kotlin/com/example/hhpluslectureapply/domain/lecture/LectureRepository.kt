package com.example.hhpluslectureapply.domain.lecture

import com.example.hhpluslectureapply.infrastructure.lecture.entity.Lecture

interface LectureRepository {
	fun findById(id: Long): Lecture
}
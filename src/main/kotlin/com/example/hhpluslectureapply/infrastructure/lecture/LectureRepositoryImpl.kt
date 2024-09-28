package com.example.hhpluslectureapply.infrastructure.lecture

import com.example.hhpluslectureapply.domain.lecture.LectureRepository
import com.example.hhpluslectureapply.infrastructure.lecture.entity.Lecture
import org.springframework.stereotype.Repository

@Repository
class LectureRepositoryImpl : LectureRepository {
	override fun findById(id: Long): Lecture? {
		TODO("Not yet implemented")
	}
}
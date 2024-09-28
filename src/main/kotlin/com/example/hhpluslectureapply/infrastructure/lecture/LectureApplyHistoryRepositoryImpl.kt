package com.example.hhpluslectureapply.infrastructure.lecture

import com.example.hhpluslectureapply.domain.lecture.LectureApplyHistoryRepository
import com.example.hhpluslectureapply.infrastructure.lecture.entity.LectureApplyHistory
import org.springframework.stereotype.Repository

@Repository
class LectureApplyHistoryRepositoryImpl : LectureApplyHistoryRepository {
	override fun findByUserEmail(userEmail: String): LectureApplyHistory? {
		TODO("Not yet implemented")
	}
}
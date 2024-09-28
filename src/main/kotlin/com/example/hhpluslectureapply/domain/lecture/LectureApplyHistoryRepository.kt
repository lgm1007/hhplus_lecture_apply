package com.example.hhpluslectureapply.domain.lecture

import com.example.hhpluslectureapply.infrastructure.lecture.entity.LectureApplyHistory

interface LectureApplyHistoryRepository {
	fun findByUserEmail(userEmail: String): LectureApplyHistory?
}
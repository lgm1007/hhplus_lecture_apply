package com.example.hhpluslectureapply.domain.lecture

import org.springframework.stereotype.Service

@Service
class LectureApplyHistoryService(
	private val lectureApplyHistoryRepository: LectureApplyHistoryRepository,
) {
	/**
	 * 사용자 이메일에 해당하는 특강 신청 내역 존재여부 메서드
	 */
	fun isExistLectureApplyHistory(userEmail: String): Boolean {
		return lectureApplyHistoryRepository.findByUserEmail(userEmail) != null
	}
}
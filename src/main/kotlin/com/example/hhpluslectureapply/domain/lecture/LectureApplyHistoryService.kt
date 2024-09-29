package com.example.hhpluslectureapply.domain.lecture

import org.springframework.stereotype.Service

@Service
class LectureApplyHistoryService(
	private val lectureApplyHistoryRepository: LectureApplyHistoryRepository,
) {
	/**
	 * 특정 아이디인 특강에 대해 사용자 이메일에 해당하는 특강 신청 내역 존재여부 메서드
	 */
	fun isExistLectureApplyHistory(lectureId: Long, userEmail: String): Boolean {
		return lectureApplyHistoryRepository.findByLectureIdAndUserEmail(lectureId, userEmail) != null
	}

	/**
	 * 특강 ID로 특강 신청 내역 개수 조회 메서드
	 */
	fun getAllLectureApplyHistoryInfosByLectureId(lectureId: Long): Int {
		return lectureApplyHistoryRepository.countByLectureId(lectureId)
	}

	fun insertOrUpdate(lectureApplyHistoryDto: LectureApplyHistoryDto): LectureApplyHistoryDto {
		return LectureApplyHistoryDto.from(lectureApplyHistoryRepository.insertOrUpdate(lectureApplyHistoryDto))
	}
}
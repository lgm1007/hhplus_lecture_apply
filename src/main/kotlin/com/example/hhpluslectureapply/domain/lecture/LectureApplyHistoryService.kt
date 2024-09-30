package com.example.hhpluslectureapply.domain.lecture

import org.springframework.stereotype.Service

@Service
class LectureApplyHistoryService(
	private val lectureApplyHistoryRepository: LectureApplyHistoryRepository,
) {
	/**
	 * 특정 아이디인 특강에 대해 사용자 아이디에 해당하는 특강 신청 내역 존재여부 메서드
	 */
	fun isExistLectureApplyHistory(lectureId: Long, userId: Long): Boolean {
		return lectureApplyHistoryRepository.findByLectureIdAndUserId(lectureId, userId) != null
	}

	/**
	 * 특강 아이디로 특강 신청 내역 개수 조회 메서드
	 */
	fun countLectureApplyHistoryInfosByLectureId(lectureId: Long): Int {
		return lectureApplyHistoryRepository.countByLectureId(lectureId)
	}

	fun insertOrUpdate(lectureApplyHistoryDto: LectureApplyHistoryDto): LectureApplyHistoryDto {
		return LectureApplyHistoryDto.from(lectureApplyHistoryRepository.insertOrUpdate(lectureApplyHistoryDto))
	}
}
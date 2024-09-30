package com.example.hhpluslectureapply.domain.lecture

import org.springframework.stereotype.Service

@Service
class LectureApplyHistoryService(
	private val lectureApplyHistoryRepository: LectureApplyHistoryRepository,
) {
	/**
	 * 특강 신청내역을 저장하거나 업데이트하는 메서드
	 */
	fun insertOrUpdate(lectureApplyHistoryDto: LectureApplyHistoryDto): LectureApplyHistoryDto {
		return LectureApplyHistoryDto.from(lectureApplyHistoryRepository.insertOrUpdate(lectureApplyHistoryDto))
	}
}
package com.example.hhpluslectureapply.domain.lecture

import com.example.hhpluslectureapply.domain.lecture.dto.LectureApplyHistoryDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LectureApplyHistoryService(
	private val lectureApplyHistoryRepository: LectureApplyHistoryRepository,
) {
	/**
	 * 사용자 아이디로 특강 신청 내역 목록 조회하는 메서드
	 */
	fun getAllHistoriesByUserId(userId: Long): List<LectureApplyHistoryDto> {
		return lectureApplyHistoryRepository.findAllByUserId(userId).map {
			LectureApplyHistoryDto.from(it)
		}.toList()
	}

	/**
	 * 특강 신청내역을 저장하거나 업데이트하는 메서드
	 */
	@Transactional
	fun insertOrUpdate(lectureApplyHistoryDto: LectureApplyHistoryDto): LectureApplyHistoryDto {
		return LectureApplyHistoryDto.from(lectureApplyHistoryRepository.insertOrUpdate(lectureApplyHistoryDto))
	}
}
package com.example.hhpluslectureapply.domain.lecture

import com.example.hhpluslectureapply.domain.lecture.dto.LectureApplyHistoryDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LectureApplyHistoryService(
	private val lectureApplyHistoryRepository: LectureApplyHistoryRepository,
) {
	/**
	 * PESSIMISTIC_WRITE Lock 을 걸고 특정 특강 아이디와 사용자 아이디로 특강 신청 내역 레코드를 조회하는 메서드
	 */
	fun getHistoryByLectureIdAndUserIdWithLock(lectureId: Long, userId: Long): LectureApplyHistoryDto? {
		val lectureApplyHistory = lectureApplyHistoryRepository.findByLectureIdAndUserIdWithLock(lectureId, userId)
			?: return null

		return LectureApplyHistoryDto.from(lectureApplyHistory)
	}

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
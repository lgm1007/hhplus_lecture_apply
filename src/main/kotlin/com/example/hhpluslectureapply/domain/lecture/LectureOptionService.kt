package com.example.hhpluslectureapply.domain.lecture

import com.example.hhpluslectureapply.domain.lecture.dto.LectureOptionDto
import com.example.hhpluslectureapply.exception.LectureException
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class LectureOptionService(
	private val lectureOptionRepository: LectureOptionRepository
) {
	/**
	 * PESSIMISTIC_WRITE Lock 이 걸린 LectureOption 레코드를 조회하는 메서드
	 */
	fun findByLectureIdWithLock(lectureId: Long): LectureOptionDto {
		val lectureOption = lectureOptionRepository.findByLectureIdWithLock(lectureId)
			?: throw LectureException("강의 아이디: ${lectureId}에 해당하는 강의 정보가 존재하지 않습니다.")
		return LectureOptionDto.from(lectureOption)
	}


	/**
	 * 해당 특강에 대해 신청자 수 1 증가 업데이트하는 메서드
	 */
	fun updateIncreaseLectureCurrentApplicants(lectureId: Long): LectureOptionDto {
		return LectureOptionDto.from(lectureOptionRepository.updateCurrentApplicantsByLectureId(lectureId))
	}

	/**
	 * 조회하는 현재 날짜 기준으로 신청 가능 날짜가 지나지 않은 특강 목록 조회하는 메서드
	 */
	fun getAllLecturesByApplicationDateBefore(nowDate: LocalDateTime): List<LectureOptionDto> {
		return lectureOptionRepository.findAllByApplicationDateBefore(nowDate).map {
			LectureOptionDto.from(it)
		}.toList()
	}

	/**
	 * 특강 ID 리스트로 특강 정보 목록을 조회하는 메서드
	 */
	fun getAllLecturesByLectureIds(lectureIds: List<Long>): List<LectureOptionDto> {
		return lectureOptionRepository.findAllByLectureIdsIn(lectureIds).map {
			LectureOptionDto.from(it)
		}.toList()
	}
}
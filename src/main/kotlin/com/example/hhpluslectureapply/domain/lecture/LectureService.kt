package com.example.hhpluslectureapply.domain.lecture

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class LectureService(
	private val lectureRepository: LectureRepository
) {
	/**
	 * 아이디 값으로 특강 단건 조회하는 메서드
	 */
	fun getLectureInfoById(lectureId: Long): LectureInfo {
		val lecture = lectureRepository.findById(lectureId) ?: throw LectureException("ID: ${lectureId}에 해당하는 특강이 존재하지 않습니다.")
		return LectureInfo.from(lecture)
	}

	/**
	 * 조회하는 현재 날짜 기준으로 신청 가능 날짜가 지나지 않은 특강 목록 조회하는 메서드
	 */
	fun getAllLecturesByApplicationDateBefore(): List<LectureInfo> {
		return lectureRepository.findAllByApplicationDateBefore(LocalDateTime.now()).map {
			LectureInfo.from(it)
		}.toList()
	}
}
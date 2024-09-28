package com.example.hhpluslectureapply.domain.lecture

import org.springframework.stereotype.Service

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
}
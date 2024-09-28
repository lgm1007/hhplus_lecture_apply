package com.example.hhpluslectureapply.domain.lecture

import com.example.hhpluslectureapply.usecase.lecture.LectureApplyInfo
import org.springframework.stereotype.Service

@Service
class LectureService(
	private val lectureRepository: LectureRepository
) {
	/**
	 * 아이디 값으로 특강 단건 조회하는 메서드
	 */
	fun getLectureInfoById(lectureId: Long): LectureInfo {
		return LectureInfo.from(lectureRepository.findById(lectureId))
	}
}
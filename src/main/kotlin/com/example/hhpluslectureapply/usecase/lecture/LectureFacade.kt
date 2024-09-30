package com.example.hhpluslectureapply.usecase.lecture

import com.example.hhpluslectureapply.domain.lecture.LectureApplyHistoryDto
import com.example.hhpluslectureapply.domain.lecture.LectureApplyHistoryService
import com.example.hhpluslectureapply.domain.lecture.LectureService
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class LectureFacade(
	private val lectureService: LectureService,
	private val lectureApplyHistoryService: LectureApplyHistoryService
) {
	/**
	 * 특강 신청하기 메서드
	 */
	fun applyLecture(lectureApplyInfo: LectureApplyInfo) {
		lectureApplyHistoryService.insertOrUpdate(LectureApplyHistoryDto.from(lectureApplyInfo))
	}

	/**
	 * 현재 신청 가능한 특강 목록 조회하기 메서드
	 * 신청 가능한 특강 = 신청 일자가 지나지 않았고, 신청 정원이 마감되지 않은 특강
	 */
	fun getAllAppliableLectures(): List<LectureInfo> {
		val nowDate = LocalDateTime.now();

		return lectureService.getAllLecturesByApplicationDateBefore(nowDate).map {
			LectureInfo.from(it)
		}.toList()
	}
}
package com.example.hhpluslectureapply.usecase.lecture

import com.example.hhpluslectureapply.domain.lecture.LectureApplyHistoryDto
import com.example.hhpluslectureapply.domain.lecture.LectureApplyHistoryService
import com.example.hhpluslectureapply.domain.lecture.LectureService
import com.example.hhpluslectureapply.exception.LectureException
import org.springframework.stereotype.Component

@Component
class LectureFacade(
	private val lectureService: LectureService,
	private val lectureApplyHistoryService: LectureApplyHistoryService
) {
	/**
	 * 특강 신청하기 메서드
	 */
	fun applyLecture(lectureApplyInfo: LectureApplyInfo) {
		val requestLectureId = lectureApplyInfo.lectureId
		val requestUserEmail = lectureApplyInfo.userEmail

		if (lectureApplyHistoryService.isExistLectureApplyHistory(requestLectureId, requestUserEmail)) {
			throw LectureException("ID: ${requestLectureId}의 특강은 Email: ${requestUserEmail} 유저가 이미 신청한 내역이 있습니다.")
		}

		if (lectureApplyHistoryService.getAllLectureApplyHistoryInfosByLectureId(requestLectureId) > MAX_NUMBER_APPLY_LECTURE) {
			throw LectureException("ID: ${requestLectureId} 특강은 최대 정원이 마감되었습니다.")
		}

		lectureApplyHistoryService.insertOrUpdate(LectureApplyHistoryDto.from(lectureApplyInfo))
	}
}
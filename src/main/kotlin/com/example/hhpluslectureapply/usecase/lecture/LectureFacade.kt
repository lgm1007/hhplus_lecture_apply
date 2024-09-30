package com.example.hhpluslectureapply.usecase.lecture

import com.example.hhpluslectureapply.domain.lecture.LectureApplyHistoryDto
import com.example.hhpluslectureapply.domain.lecture.LectureApplyHistoryService
import com.example.hhpluslectureapply.domain.lecture.LectureService
import com.example.hhpluslectureapply.exception.LectureException
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
		val lectureId = lectureApplyInfo.lectureId
		val userId = lectureApplyInfo.userId

		if (lectureApplyHistoryService.isFullCountLectureMaxApply(lectureId)) {
			throw LectureException("특강 아이디가 ${lectureId}에 해당하는 특강은 신청 정원이 마감되었습니다.")
		}


		lectureApplyHistoryService.insertOrUpdate(LectureApplyHistoryDto.from(lectureApplyInfo))
	}

	/**
	 * 현재 신청 가능한 특강 목록 조회하기 메서드
	 * 신청 가능한 특강 = 신청 일자가 지나지 않았고, 신청 정원이 마감되지 않은 특강
	 */
	fun getAllAppliableLectures(): List<LectureInfo> {
		val nowDate = LocalDateTime.now();

		return lectureService.getAllLecturesByApplicationDateBefore(nowDate).filter {
			!lectureApplyHistoryService.isFullCountLectureMaxApply(it.lectureId!!)
		}.map {
			LectureInfo.from(it)
		}.toList()
	}

	/**
	 * 사용자 아이디로 신청한 특강 목록을 조회하기 메서드
	 */
	fun getAllLecturesByUserApplied(userId: Long): List<LectureInfo> {
		val lectureIds = lectureApplyHistoryService.getAllHistoriesByUserId(userId).map { it.lectureId }.toList()

		return lectureService.getAllLecturesByIds(lectureIds).map {
			LectureInfo.from(it)
		}.toList()
	}
}
package com.example.hhpluslectureapply.usecase.lecture

import com.example.hhpluslectureapply.domain.lecture.LectureApplyHistoryService
import com.example.hhpluslectureapply.domain.lecture.LectureOptionService
import com.example.hhpluslectureapply.domain.lecture.LectureService
import com.example.hhpluslectureapply.domain.lecture.dto.LectureApplyHistoryDto
import com.example.hhpluslectureapply.exception.LectureException
import com.example.hhpluslectureapply.usecase.lecture.dto.LectureApplyInfo
import com.example.hhpluslectureapply.usecase.lecture.dto.LectureInfo
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Component
class LectureFacade(
	private val lectureService: LectureService,
	private val lectureOptionService: LectureOptionService,
	private val lectureApplyHistoryService: LectureApplyHistoryService
) {
	/**
	 * 특강 신청하기 메서드
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	fun applyLecture(lectureApplyInfo: LectureApplyInfo) {
		val lectureId = lectureApplyInfo.lectureId
		val userId = lectureApplyInfo.userId

		val lectureOption = lectureOptionService.findByLectureIdWithLock(lectureId)
		if (lectureOption.isFullCurrentApplicantsMaxApply()) {
			throw LectureException("특강 아이디가 ${lectureId}에 해당하는 특강은 신청 정원이 마감되었습니다.")
		}

		lectureOptionService.updateIncreaseLectureCurrentApplicants(lectureId)
		lectureApplyHistoryService.insertOrUpdate(LectureApplyHistoryDto.from(lectureApplyInfo))
	}

	/**
	 * 현재 신청 가능한 특강 목록 조회하기 메서드
	 * 신청 가능한 특강 = 신청 일자가 지나지 않았고, 신청 정원이 마감되지 않은 특강
	 */
	fun getAllAppliableLectures(): List<LectureInfo> {
		val nowDate = LocalDateTime.now();

		val lectureOptions = lectureOptionService.getAllLecturesByApplicationDateAfter(nowDate).filter {
			!it.isFullCurrentApplicantsMaxApply()
		}.toList()

		val lectures = lectureService.getAllLecturesByIds(lectureOptions.map { it.lectureId }.toList())

		return LectureInfo.listOf(lectures, lectureOptions)
	}

	/**
	 * 사용자 아이디로 신청한 특강 목록을 조회하기 메서드
	 */
	fun getAllLecturesByUserApplied(userId: Long): List<LectureInfo> {
		val lectureIds = lectureApplyHistoryService.getAllHistoriesByUserId(userId).map { it.lectureId }.toList()

		val lectureOptions = lectureOptionService.getAllLecturesByLectureIds(lectureIds)

		val lectures = lectureService.getAllLecturesByIds(lectureIds)

		return LectureInfo.listOf(lectures, lectureOptions)
	}
}
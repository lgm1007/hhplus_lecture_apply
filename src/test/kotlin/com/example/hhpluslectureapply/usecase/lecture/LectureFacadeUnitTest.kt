package com.example.hhpluslectureapply.usecase.lecture

import com.example.hhpluslectureapply.domain.lecture.LectureApplyHistoryService
import com.example.hhpluslectureapply.domain.lecture.LectureDto
import com.example.hhpluslectureapply.domain.lecture.LectureService
import com.example.hhpluslectureapply.exception.LectureException
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class LectureFacadeUnitTest {
	@Mock
	lateinit var lectureService: LectureService

	@Mock
	lateinit var lectureApplyHistoryService: LectureApplyHistoryService

	@InjectMocks
	lateinit var lectureFacade: LectureFacade

	@Test
	@DisplayName("특강 신청 중 이미 동일한 유저 이메일에 대한 신청 내역이 존재해 실패하는 예외케이스 검사")
	fun shouldApplyLectureFailIsExistUserEmail() {
		`when`(lectureApplyHistoryService.isExistLectureApplyHistory(any(), any()))
			.thenReturn(true)

		assertThatThrownBy {
			lectureFacade.applyLecture(LectureApplyInfo(1L, 1L))
		}.isInstanceOf(LectureException::class.java)
			.hasMessageContaining("유저가 이미 신청한 내역이 있습니다.")
	}

	@Test
	@DisplayName("특강 신청 중 특강의 최대 정원이 초과되어 실패하는 예외케이스 검사")
	fun shouldApplyLectureFailMaxNumberApply() {
		val MAX_NUMBER_APPLY_LECTURE = 30

		`when`(lectureApplyHistoryService.countLectureApplyHistoryInfosByLectureId(any()))
			.thenReturn(MAX_NUMBER_APPLY_LECTURE)

		assertThatThrownBy {
			lectureFacade.applyLecture(LectureApplyInfo(1L, 1L))
		}.isInstanceOf(LectureException::class.java)
			.hasMessageContaining("특강은 최대 정원이 마감되었습니다.")
	}

	@Test
	@DisplayName("현재 신청 가능한 특강 목록 조회하는 기능 단위 테스트 - 특강 정원이 마감되었는지 필터링 기능 검증")
	fun getAllAppliableLectures() {
		val MAX_NUMBER_APPLY_LECTURE = 30

		`when`(lectureService.getAllLecturesByApplicationDateBefore(any()))
			.thenReturn(givenLectures())
		`when`(lectureApplyHistoryService.countLectureApplyHistoryInfosByLectureId(any()))
			.thenReturn(MAX_NUMBER_APPLY_LECTURE)

		val actual = lectureFacade.getAllAppliableLectures()
		assertThat(actual.size).isEqualTo(0)
	}

	private fun givenLectures(): List<LectureDto> {
		return listOf<LectureDto>(
			LectureDto(1L, "title1", "강사1", LocalDateTime.now()),
			LectureDto(2L, "title2", "강사2", LocalDateTime.now())
		)
	}

}
package com.example.hhpluslectureapply.usecase.lecture

import com.example.hhpluslectureapply.domain.lecture.LectureApplyHistoryService
import com.example.hhpluslectureapply.domain.lecture.LectureOptionService
import com.example.hhpluslectureapply.domain.lecture.LectureService
import com.example.hhpluslectureapply.domain.lecture.dto.LectureApplyHistoryDto
import com.example.hhpluslectureapply.domain.lecture.dto.LectureDto
import com.example.hhpluslectureapply.domain.lecture.dto.LectureOptionDto
import com.example.hhpluslectureapply.exception.LectureException
import com.example.hhpluslectureapply.usecase.lecture.dto.LectureApplyInfo
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

	@Mock
	lateinit var lectureOptionService: LectureOptionService

	@InjectMocks
	lateinit var lectureFacade: LectureFacade

	@Test
	@DisplayName("특강 신청 시 해당 특강의 신청 정원이 초과된 경우에 대한 예외케이스")
	fun shouldFailMaxLectureApplyNumber() {
		`when`(lectureOptionService.findByLectureIdWithLock(any()))
			.thenReturn(LectureOptionDto(1L, LocalDateTime.now(), 30))

		assertThatThrownBy {
			lectureFacade.applyLecture(LectureApplyInfo(1L, 1L))
		}.isInstanceOf(LectureException::class.java)
			.hasMessageContaining("신청 정원이 마감되었습니다.")
	}

	@Test
	@DisplayName("현재 신청 가능한 특강 목록 조회하는 기능 단위 테스트 - 특강 정원이 마감되었는지 필터링 기능 검증")
	fun getAllAppliableLectures() {
		`when`(lectureOptionService.getAllLecturesByApplicationDateAfter(any()))
			.thenReturn(givenLectureOptions())

		`when`(lectureService.getAllLecturesByIds(any()))
			.thenReturn(givenLectures())

		val actual = lectureFacade.getAllAppliableLectures()
		assertThat(actual.size).isEqualTo(2)
	}

	@Test
	@DisplayName("사용자 아이디로 신청한 특강 목록을 조회하는 기능 단위 테스트")
	fun getAllLecturesByUserApplied() {
		`when`(lectureApplyHistoryService.getAllHistoriesByUserId(any()))
			.thenReturn(givenLectureHistories())

		`when`(lectureOptionService.getAllLecturesByLectureIds(any()))
			.thenReturn(givenLectureOptions())

		`when`(lectureService.getAllLecturesByIds(any()))
			.thenReturn(givenLectures())

		val actual = lectureFacade.getAllLecturesByUserApplied(1L)
		assertThat(actual.size).isEqualTo(givenLectures().size)
	}

	private fun givenLectures(): List<LectureDto> {
		return listOf(
			LectureDto(1L, "title1", "강사1"),
			LectureDto(2L, "title2", "강사2")
		)
	}

	private fun givenLectureHistories(): List<LectureApplyHistoryDto> {
		return listOf(
			LectureApplyHistoryDto(1L, 1L),
			LectureApplyHistoryDto(2L, 1L)
		)
	}

	private fun givenLectureOptions(): List<LectureOptionDto> {
		return listOf(
			LectureOptionDto(1L, LocalDateTime.now(), 0),
			LectureOptionDto(2L, LocalDateTime.now(), 0)
		)
	}

}
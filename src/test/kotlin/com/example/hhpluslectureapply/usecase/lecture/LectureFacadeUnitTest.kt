package com.example.hhpluslectureapply.usecase.lecture

import com.example.hhpluslectureapply.domain.lecture.LectureApplyHistoryService
import com.example.hhpluslectureapply.domain.lecture.LectureService
import com.example.hhpluslectureapply.domain.lecture.dto.LectureApplyHistoryDto
import com.example.hhpluslectureapply.domain.lecture.dto.LectureDto
import org.assertj.core.api.AssertionsForClassTypes.assertThat
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
	@DisplayName("현재 신청 가능한 특강 목록 조회하는 기능 단위 테스트 - 특강 정원이 마감되었는지 필터링 기능 검증")
	fun getAllAppliableLectures() {
		`when`(lectureApplyHistoryService.isNotFullCountLectureMaxApply(any()))
			.thenReturn(false)

		`when`(lectureService.getAllLecturesByApplicationDateAfter(any()))
			.thenReturn(givenLectures())

		val actual = lectureFacade.getAllAppliableLectures()
		assertThat(actual.size).isEqualTo(0)
	}

	@Test
	@DisplayName("사용자 아이디로 신청한 특강 목록을 조회하는 기능 단위 테스트")
	fun getAllLecturesByUserApplied() {
		`when`(lectureApplyHistoryService.getAllHistoriesByUserId(any()))
			.thenReturn(givenLectureHistories())

		`when`(lectureService.getAllLecturesByIds(any()))
			.thenReturn(givenLectures())

		val actual = lectureFacade.getAllLecturesByUserApplied(1L)
		assertThat(actual.size).isEqualTo(givenLectures().size)
	}

	private fun givenLectures(): List<LectureDto> {
		return listOf(
			LectureDto(1L, "title1", "강사1", 0, LocalDateTime.now()),
			LectureDto(2L, "title2", "강사2", 0, LocalDateTime.now())
		)
	}

	private fun givenLectureHistories(): List<LectureApplyHistoryDto> {
		return listOf(
			LectureApplyHistoryDto(1L, 1L),
			LectureApplyHistoryDto(2L, 1L)
		)
	}

}
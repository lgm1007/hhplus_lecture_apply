package com.example.hhpluslectureapply.usecase.lecture

import com.example.hhpluslectureapply.domain.lecture.LectureApplyHistoryService
import com.example.hhpluslectureapply.domain.lecture.LectureDto
import com.example.hhpluslectureapply.domain.lecture.LectureService
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

		`when`(lectureService.getAllLecturesByApplicationDateBefore(any()))
			.thenReturn(givenLectures())

		val actual = lectureFacade.getAllAppliableLectures()
		assertThat(actual.size).isEqualTo(2)
	}

	private fun givenLectures(): List<LectureDto> {
		return listOf<LectureDto>(
			LectureDto(1L, "title1", "강사1", LocalDateTime.now()),
			LectureDto(2L, "title2", "강사2", LocalDateTime.now())
		)
	}

}
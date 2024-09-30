package com.example.hhpluslectureapply.domain.lecture

import org.assertj.core.api.AssertionsForClassTypes
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class LectureApplyHistoryServiceUnitTest {
	@Mock
	lateinit var lectureApplyHistoryRepository: LectureApplyHistoryRepository

	@InjectMocks
	lateinit var lectureApplyHistoryService: LectureApplyHistoryService

	@Test
	@DisplayName("특정한 특강의 인원수가 아직 마감되지 않았는지 체크하는 테스트")
	fun isNotFullCountLectureMaxApply() {
		`when`(lectureApplyHistoryRepository.countByLectureId(1L))
			.thenReturn(20)
		`when`(lectureApplyHistoryRepository.countByLectureId(2L))
			.thenReturn(30)

		val actual1 = lectureApplyHistoryService.isFullCountLectureMaxApply(1L)
		val actual2 = lectureApplyHistoryService.isFullCountLectureMaxApply(2L)

		assertAll(
			{ AssertionsForClassTypes.assertThat(actual1).isFalse() },
			{ AssertionsForClassTypes.assertThat(actual2).isTrue() }
		)
	}
}
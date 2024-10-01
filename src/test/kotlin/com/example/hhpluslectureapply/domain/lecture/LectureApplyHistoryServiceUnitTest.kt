package com.example.hhpluslectureapply.domain.lecture

import com.example.hhpluslectureapply.infrastructure.lecture.entity.LectureApplyHistory
import org.assertj.core.api.AssertionsForClassTypes
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class LectureApplyHistoryServiceUnitTest {
	@Mock
	lateinit var lectureApplyHistoryRepository: LectureApplyHistoryRepository

	@InjectMocks
	lateinit var lectureApplyHistoryService: LectureApplyHistoryService

	@Test
	@DisplayName("특정한 특강의 인원수가 아직 마감되지 않았는지 체크하는 테스트")
	fun isNotFullCountLectureMaxApply() {
		`when`(lectureApplyHistoryRepository.findAllByLectureId(1L))
			.thenReturn(givenLectureApplyHistories(1L, 20))
		`when`(lectureApplyHistoryRepository.findAllByLectureId(2L))
			.thenReturn(givenLectureApplyHistories(2L, 30))

		val actual1 = lectureApplyHistoryService.isFullCountLectureMaxApply(1L)
		val actual2 = lectureApplyHistoryService.isFullCountLectureMaxApply(2L)

		assertAll(
			{ AssertionsForClassTypes.assertThat(actual1).isFalse() },
			{ AssertionsForClassTypes.assertThat(actual2).isTrue() }
		)
	}

	private fun givenLectureApplyHistories(lectureId: Long, listSize: Int): List<LectureApplyHistory> {
		val histories = mutableListOf<LectureApplyHistory>()
		for (i in 1..listSize) {
			histories.add(LectureApplyHistory(null, lectureId, i.toLong(), LocalDateTime.now()))
		}
		return histories
	}
}
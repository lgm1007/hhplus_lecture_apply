package com.example.hhpluslectureapply.domain.lecture

import com.example.hhpluslectureapply.domain.lecture.dto.LectureApplyHistoryDto
import org.assertj.core.api.AssertionsForClassTypes
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LectureApplyHistoryServiceIntegrationTest {
	@Autowired private lateinit var lectureApplyHistoryService: LectureApplyHistoryService
	@Autowired private lateinit var lectureApplyHistoryRepository: LectureApplyHistoryRepository

	@BeforeEach
	fun setUp() {
		lectureApplyHistoryRepository.deleteAll()
	}

	@Test
	@DisplayName("사용자 아이디로 특강 신청 내역 목록 조회하는 기능")
	fun getAllHistoriesByUserId() {
		lectureApplyHistoryService.insertOrUpdate(LectureApplyHistoryDto(1L, 1L))
		lectureApplyHistoryService.insertOrUpdate(LectureApplyHistoryDto(2L, 2L))
		lectureApplyHistoryService.insertOrUpdate(LectureApplyHistoryDto(3L, 1L))

		val actual = lectureApplyHistoryService.getAllHistoriesByUserId(1L)

		assertThat(actual.size).isEqualTo(2)
		assertThat(actual[0].lectureId).isEqualTo(1L)
		assertThat(actual[1].lectureId).isEqualTo(3L)
	}
}
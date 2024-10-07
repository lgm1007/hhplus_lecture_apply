package com.example.hhpluslectureapply.domain.lecture.dto

import org.assertj.core.api.AssertionsForClassTypes
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class LectureOptionDtoTest {
	val MAX_NUMBER_APPLY_LECTURE = 30

	@Test
	@DisplayName("특강의 신청 수가 최대 인원 수를 초과하는 경우 기능 테스트")
	fun isFullCurrentApplicantsMaxApply() {
		val lectureOptionDto = LectureOptionDto(1L, LocalDateTime.now(), MAX_NUMBER_APPLY_LECTURE)

		assertThat(lectureOptionDto.isFullCurrentApplicantsMaxApply()).isTrue()
	}
}
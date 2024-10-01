package com.example.hhpluslectureapply.infrastructure.lecture.entity

import org.assertj.core.api.AssertionsForClassTypes
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class LectureOptionTest {
	@Test
	@DisplayName("특강의 신청 인원수가 증가하는 기능 단위테스트")
	fun increaseCurrentApplicants() {
		val lectureOption = LectureOption(0, 1L, LocalDateTime.now())
		lectureOption.increaseCurrentApplicants()

		assertThat(lectureOption.currentApplicants).isEqualTo(1)
	}
}
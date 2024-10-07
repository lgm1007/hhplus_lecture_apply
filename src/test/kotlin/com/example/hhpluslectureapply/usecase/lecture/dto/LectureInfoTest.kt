package com.example.hhpluslectureapply.usecase.lecture.dto

import com.example.hhpluslectureapply.domain.lecture.dto.LectureDto
import com.example.hhpluslectureapply.domain.lecture.dto.LectureOptionDto
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class LectureInfoTest {
	@Test
	@DisplayName("Lecture 값과 LectureOption 값을 조인한 특강 정보인 LectureInfo를 생성하는 정적 팩토리 메서드 테스트")
	fun lectureInfoOfFactoryMethod() {
		val lectureDto = LectureDto(1L, "Lecture Title", "강사명")
		val lectureOptionDto = LectureOptionDto(1L, LocalDateTime.of(2024, 10, 3, 12, 0), 0)

		val actual = LectureInfo.of(lectureDto, lectureOptionDto)

		assertThat(actual.lectureId).isEqualTo(1L)
		assertThat(actual.title).isEqualTo("Lecture Title")
		assertThat(actual.lecturer).isEqualTo("강사명")
		assertThat(actual.applicationDate).isEqualTo(LocalDateTime.of(2024, 10, 3, 12, 0))
	}

	@Test
	@DisplayName("Lecture의 리스트와 LectureOption의 리스트 값을 조인한 특강 정보인 LectureInfo 리스트를 생성하는 정적 팩토리 메서드 테스트")
	fun lectureInfoListOfFactoryMethod() {
		val lectureDtos = listOf<LectureDto>(
			LectureDto(1L, "Lecture Title1", "강사명1"),
			LectureDto(2L, "Lecture Title2", "강사명2"),
			LectureDto(3L, "Lecture Title3", "강사명3")
		)
		val lectureOptionDtos = listOf<LectureOptionDto>(
			LectureOptionDto(1L, LocalDateTime.of(2024, 10, 3, 12, 0), 0),
			LectureOptionDto(2L, LocalDateTime.of(2024, 10, 3, 12, 20), 1)
		)

		val actual = LectureInfo.listOf(lectureDtos, lectureOptionDtos)

		assertThat(actual.size).isEqualTo(2)
		assertThat(actual[0].lectureId).isEqualTo(1L)
		assertThat(actual[0].lecturer).isEqualTo("강사명1")
		assertThat(actual[1].lectureId).isEqualTo(2L)
		assertThat(actual[1].lecturer).isEqualTo("강사명2")
	}
}
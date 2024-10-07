package com.example.hhpluslectureapply.domain.lecture

import com.example.hhpluslectureapply.domain.lecture.dto.LectureDto
import com.example.hhpluslectureapply.exception.LectureException
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LectureServiceIntegrationTest {
	@Autowired private lateinit var lectureService: LectureService
	@Autowired private lateinit var lectureRepository: LectureRepository

	@BeforeEach
	fun setUp() {
		lectureRepository.deleteAll()
	}

	@Test
	@DisplayName("특강 ID로 특강을 조회할 때 특강이 존재하지 않는 경우에 대한 예외 케이스")
	fun shouldFailWhenGetLectureByIdNotExist() {
		assertThatThrownBy { lectureService.getLectureInfoById(1L) }
			.isInstanceOf(LectureException::class.java)
			.hasMessageContaining("특강이 존재하지 않습니다.")
	}

	@Test
	@DisplayName("특강 ID로 특강을 조회하기 기능 테스트")
	fun getLectureInfoById() {
		lectureService.insertOrUpdate(LectureDto(1L, "Lecture Title1", "강사명1"))

		val actual = lectureService.getLectureInfoById(1L)

		assertThat(actual.lectureId).isEqualTo(1L)
		assertThat(actual.title).isEqualTo("Lecture Title1")
		assertThat(actual.lecturer).isEqualTo("강사명1")
	}

	@Test
	@DisplayName("특강 아이디 리스트로 특강 정보 리스트를 조회하는 기능 테스트")
	fun getAllLecturesByIds() {
		lectureService.insertOrUpdate(LectureDto(1L, "Lecture Title1", "강사명1"))
		lectureService.insertOrUpdate(LectureDto(2L, "Lecture Title2", "강사명2"))
		lectureService.insertOrUpdate(LectureDto(3L, "Lecture Title3", "강사명3"))

		val lectureIds = listOf(1L, 2L)

		val actual = lectureService.getAllLecturesByIds(lectureIds)

		assertThat(actual.size).isEqualTo(2)
		assertThat(actual[0].lectureId).isEqualTo(1L)
		assertThat(actual[1].lectureId).isEqualTo(2L)
	}
}
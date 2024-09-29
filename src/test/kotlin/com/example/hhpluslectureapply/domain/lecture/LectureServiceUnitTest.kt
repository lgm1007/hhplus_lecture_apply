package com.example.hhpluslectureapply.domain.lecture

import com.example.hhpluslectureapply.infrastructure.lecture.entity.Lecture
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.*
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class LectureServiceUnitTest {
	@Mock
	lateinit var lectureRepository: LectureRepository

	@InjectMocks
	lateinit var lectureService: LectureService

	@Test
	@DisplayName("특강 ID 값으로 특강 정보를 단건 조회하기")
	fun getLectureInfoById() {
		doAnswer { invocation ->
			Lecture(
				invocation.getArgument(0),
				"lecture",
				"홍길동",
				LocalDateTime.of(2024, 9, 29, 12, 0)
			)
		}.`when`(lectureRepository).findById(anyLong())

		val actual = lectureService.getLectureInfoById(1L)
		
		assertAll(
			{ assertThat(actual.title).isEqualTo("lecture") },
			{ assertThat(actual.lecturer).isEqualTo("홍길동") },
		)
	}

	@Test
	@DisplayName("신청 가능 날짜가 아직 지나지 않은 특강 목록을 조회하기")
	fun getAllLecturesByApplicationDateBefore() {
		doAnswer { invocation ->
			listOf<Lecture>(
				Lecture(1L, "lecture1", "홍길동", invocation.getArgument<LocalDateTime>(0).plusDays(1)),
				Lecture(2L, "lecture2", "정몽준", invocation.getArgument<LocalDateTime>(0).plusDays(2))
			)
		}.`when`(lectureRepository).findAllByApplicationDateBefore(any())

		val nowDate = LocalDateTime.now()
		val actual = lectureService.getAllLecturesByApplicationDateBefore(nowDate)

		assertAll(
			{ assertThat(actual.size).isEqualTo(2) },
			{ assertThat(actual[0].applicationDate).isAfter(nowDate) },
			{ assertThat(actual[1].applicationDate).isAfter(nowDate) }
		)
	}
}
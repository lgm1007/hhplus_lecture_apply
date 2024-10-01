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
				"홍길동"
			)
		}.`when`(lectureRepository).findById(anyLong())

		val actual = lectureService.getLectureInfoById(1L)
		
		assertAll(
			{ assertThat(actual.title).isEqualTo("lecture") },
			{ assertThat(actual.lecturer).isEqualTo("홍길동") },
		)
	}
}
package com.example.hhpluslectureapply.domain.lecture

import com.example.hhpluslectureapply.infrastructure.lecture.entity.LectureApplyHistory
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer

@ExtendWith(MockitoExtension::class)
class LectureApplyHistoryServiceUnitTest {
	@Mock
	lateinit var lectureApplyHistoryRepository: LectureApplyHistoryRepository

	@InjectMocks
	lateinit var lectureApplyHistoryService: LectureApplyHistoryService

	@Test
	@DisplayName("특정 사용자 이메일의 특강 신청 내역 존재 여부가 있는 상황에 대한 테스트")
	fun isExistLectureApplyHistoryByUserEmailY() {
		doAnswer { invocation ->
			LectureApplyHistory(1L, 1L, invocation.getArgument(0))
		}.`when`(lectureApplyHistoryRepository).findByUserEmail(any())

		val actual = lectureApplyHistoryService.isExistLectureApplyHistory("test@example.com")

		assertThat(actual).isTrue()
	}

	@Test
	@DisplayName("특정 사용자 이메일의 특강 신청 내역 존재 여부가 없는 상황에 대한 테스트")
	fun isExistLectureApplyHistoryByUserEmailNone() {
		val actual = lectureApplyHistoryService.isExistLectureApplyHistory("test@example.com")

		assertThat(actual).isFalse()
	}
}
package com.example.hhpluslectureapply.usecase.lecture

import com.example.hhpluslectureapply.domain.lecture.LectureApplyHistoryRepository
import com.example.hhpluslectureapply.domain.lecture.LectureOptionRepository
import com.example.hhpluslectureapply.domain.lecture.LectureRepository
import com.example.hhpluslectureapply.domain.lecture.dto.LectureDto
import com.example.hhpluslectureapply.domain.lecture.dto.LectureOptionDto
import com.example.hhpluslectureapply.usecase.lecture.dto.LectureApplyInfo
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

@SpringBootTest
class LectureFacadeIntegrationTest {
	@Autowired private lateinit var lectureFacade: LectureFacade
	@Autowired private lateinit var lectureRepository: LectureRepository
	@Autowired private lateinit var lectureOptionRepository: LectureOptionRepository
	@Autowired private lateinit var lectureApplyHistoryRepository: LectureApplyHistoryRepository

	@BeforeEach
	fun setUp() {
		lectureRepository.deleteAll()
	}

	@Test
	@DisplayName("특강 실패 케이스 - 동시에 동일한 특강을 40명이 신청했을 때, 30명만 성공하는 것 검증")
	fun shouldPassMaxApplyNumberLecture() {
		lectureRepository.insertOrUpdate(LectureDto(1L, "Lecture Title", "lecturer1"))
		lectureOptionRepository.insertOrUpdate(LectureOptionDto(1L, LocalDateTime.now(), 0))

		val executor = Executors.newFixedThreadPool(40)
		val lectureLatch = CountDownLatch(40)
		val successfulApplies = AtomicInteger(0)

		try {
			repeat(40) {
				executor.submit {
					try {
						lectureFacade.applyLecture(LectureApplyInfo(1L, (it + 1).toLong()))
						successfulApplies.incrementAndGet()
					} finally {
						lectureLatch.countDown()
					}
				}
			}

			lectureLatch.await()

			val actual = lectureApplyHistoryRepository.countApplyHistoriesByLectureId(1L)

			assertThat(actual).isEqualTo(30)
			assertThat(successfulApplies.get()).isEqualTo(30)
		} finally {
			executor.shutdown()
		}
	}

	@Test
	@DisplayName("현재 신청 가능한 특강 목록을 조회하는 기능 테스트 - 신청 가능 특강: 신청 일자가 지나지 않았으면서 현재 신청 정원이 초과되지 않은 특강")
	fun getAllAppliableLectures() {
		val MAX_NUMBER_APPLY_LECTURE = 30
		val nowMinusDay = LocalDateTime.now().minusDays(1)
		val nowPlusDay = LocalDateTime.now().plusDays(1)

		lectureRepository.insertOrUpdate(LectureDto(1L, "Lecture Title1", "강사명1"))
		lectureRepository.insertOrUpdate(LectureDto(2L, "Lecture Title2", "강사명2"))
		lectureRepository.insertOrUpdate(LectureDto(3L, "Lecture Title3", "강사명3"))

		lectureOptionRepository.insertOrUpdate(LectureOptionDto(1L, nowMinusDay, 0))
		lectureOptionRepository.insertOrUpdate(LectureOptionDto(2L, nowPlusDay, MAX_NUMBER_APPLY_LECTURE))
		lectureOptionRepository.insertOrUpdate(LectureOptionDto(3L, nowPlusDay, 0))

		val actual = lectureFacade.getAllAppliableLectures()

		assertThat(actual.size).isEqualTo(1)
		assertThat(actual[0].lectureId).isEqualTo(3L)
		assertThat(actual[0].title).isEqualTo("Lecture Title3")
		assertThat(actual[0].lecturer).isEqualTo("강사명3")
	}
}
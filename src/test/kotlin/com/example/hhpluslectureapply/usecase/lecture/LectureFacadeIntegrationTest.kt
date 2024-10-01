package com.example.hhpluslectureapply.usecase.lecture

import com.example.hhpluslectureapply.DatabaseInitializer
import com.example.hhpluslectureapply.domain.lecture.LectureApplyHistoryRepository
import com.example.hhpluslectureapply.domain.lecture.LectureOptionRepository
import com.example.hhpluslectureapply.domain.lecture.LectureRepository
import com.example.hhpluslectureapply.domain.lecture.dto.LectureDto
import com.example.hhpluslectureapply.domain.lecture.dto.LectureOptionDto
import com.example.hhpluslectureapply.usecase.lecture.dto.LectureApplyInfo
import org.assertj.core.api.AssertionsForClassTypes.assertThat
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
	@Autowired private lateinit var databaseInitializer: DatabaseInitializer
	@Autowired private lateinit var lectureFacade: LectureFacade
	@Autowired private lateinit var lectureRepository: LectureRepository
	@Autowired private lateinit var lectureOptionRepository: LectureOptionRepository
	@Autowired private lateinit var lectureApplyHistoryRepository: LectureApplyHistoryRepository

//	@AfterEach
//	fun setUp() {
//		databaseInitializer.initializeDatabase()
//	}

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
}
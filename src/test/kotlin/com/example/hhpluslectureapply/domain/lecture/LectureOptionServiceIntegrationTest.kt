package com.example.hhpluslectureapply.domain.lecture

import com.example.hhpluslectureapply.domain.lecture.dto.LectureOptionDto
import com.example.hhpluslectureapply.exception.LectureException
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class LectureOptionServiceIntegrationTest {
	@Autowired private lateinit var lectureOptionService: LectureOptionService
	@Autowired private lateinit var lectureOptionRepository: LectureOptionRepository

	@BeforeEach
	fun setUp() {
		lectureOptionRepository.deleteAll()
	}

	@Test
	@DisplayName("PESSIMISTIC_WRITE 락이 걸린 LectureOption 레코드를 조회하기")
	fun findByLectureIdWithLock() {
		lectureOptionRepository.insertOrUpdate(LectureOptionDto(1L, LocalDateTime.now(), 0))

		val actual = lectureOptionService.findByLectureIdWithLock(1L)

		assertThat(actual.lectureId).isEqualTo(1L)
	}

	@Test
	@DisplayName("PESSIMISTIC_WRITE 락이 걸린 LectureOption 레코드를 조회 실패 케이스 - 존재하지 않는 강의인 경우")
	fun shouldNotFoundFindByLectureIdWithLock() {
		assertThatThrownBy { lectureOptionService.findByLectureIdWithLock(Long.MAX_VALUE) }
			.isInstanceOf(LectureException::class.java)
			.hasMessageContaining("강의 정보가 존재하지 않습니다.")
	}

	@Test
	@DisplayName("해당 특강에 대해 신청자 수를 1 증가시키는 업데이트하기")
	fun updateIncreaseLectureCurrentApplicants() {
		lectureOptionRepository.insertOrUpdate(LectureOptionDto(2L, LocalDateTime.now(), 0))
		lectureOptionService.updateIncreaseLectureCurrentApplicants(2L)

		val actual = lectureOptionService.findByLectureIdWithLock(2L)

		assertThat(actual.currentApplicants).isEqualTo(1)
	}

	@Test
	@DisplayName("조회하는 날짜 기준으로 신청 가능 날짜가 지나지 않은 특강 목록 조회하기")
	fun getAllLecturesByApplicationDateAfter() {
		val minusDays = LocalDateTime.now().minusDays(1)
		val plusDays = LocalDateTime.now().plusDays(1)

		lectureOptionRepository.insertOrUpdate(LectureOptionDto(1L, plusDays, 0))
		lectureOptionRepository.insertOrUpdate(LectureOptionDto(2L, plusDays, 0))
		lectureOptionRepository.insertOrUpdate(LectureOptionDto(3L, minusDays, 0))

		val actual =
			lectureOptionService.getAllLecturesByApplicationDateAfter(LocalDateTime.now())

		assertThat(actual.size).isEqualTo(2)
		assertThat(actual[0].lectureId).isEqualTo(1L)
		assertThat(actual[1].lectureId).isEqualTo(2L)
	}

	@Test
	@DisplayName("특강 ID 리스트로 특강 정보 목록을 조회하는 메서드 - select where in")
	fun getAllLecturesByLectureIds() {
		insertLectureOptionsBySize(10)
		val lectureIds = listOf<Long>(1L, 2L, 3L, 4L, 5L)

		val actual = lectureOptionService.getAllLecturesByLectureIds(lectureIds)

		assertThat(actual.size).isEqualTo(5)
	}

	private fun insertLectureOptionsBySize(listSize: Int) {
		for (i in 1..listSize) {
			lectureOptionRepository.insertOrUpdate(
				LectureOptionDto(i.toLong(), LocalDateTime.now(), 0)
			)
		}
	}
}
package com.example.hhpluslectureapply.domain.lecture

import com.example.hhpluslectureapply.domain.lecture.dto.LectureDto
import com.example.hhpluslectureapply.exception.LectureException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LectureService(
	private val lectureRepository: LectureRepository
) {
	/**
	 * 특강 아이디 값으로 특강 단건 조회하는 메서드
	 */
	fun getLectureInfoById(lectureId: Long): LectureDto {
		val lecture = lectureRepository.findById(lectureId) ?: throw LectureException("ID: ${lectureId}에 해당하는 특강이 존재하지 않습니다.")
		return LectureDto.from(lecture)
	}

	/**
	 * 특강 아이디 목록으로 특강 정보 목록을 조회하는 메서드
	 */
	fun getAllLecturesByIds(lectureIds: List<Long>): List<LectureDto> {
		return lectureRepository.findAllByIdsIn(lectureIds).map {
			LectureDto.from(it)
		}.toList()
	}

	@Transactional
	fun insertOrUpdate(lectureDto: LectureDto): LectureDto {
		return LectureDto.from(lectureRepository.insertOrUpdate(lectureDto))
	}
}
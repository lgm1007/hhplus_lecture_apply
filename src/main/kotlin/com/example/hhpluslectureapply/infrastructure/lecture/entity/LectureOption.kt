package com.example.hhpluslectureapply.infrastructure.lecture.entity

import com.example.hhpluslectureapply.domain.lecture.dto.LectureOptionDto
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class LectureOption(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long = 0,
	val lectureId: Long,
	val applicationDate: LocalDateTime,
) {
	var currentApplicants: Int = 0

	companion object {
		fun from(lectureOptionDto: LectureOptionDto): LectureOption {
			val lectureOption = LectureOption(
				0,
				lectureOptionDto.lectureId,
				lectureOptionDto.applicationDate
			)
			lectureOption.currentApplicants = lectureOptionDto.currentApplicants

			return lectureOption
		}
	}

	fun increaseCurrentApplicants() {
		currentApplicants++
	}
}
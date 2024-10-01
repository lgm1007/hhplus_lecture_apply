package com.example.hhpluslectureapply.infrastructure.lecture.entity

import com.example.hhpluslectureapply.domain.lecture.dto.LectureDto
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Lecture(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long?,
	val title: String,
	val lecturer: String,
	val applicationDate: LocalDateTime,
) {
	companion object {
		fun from(lectureDto: LectureDto): Lecture {
			return Lecture(
				null,
				lectureDto.title,
				lectureDto.lecturer,
				lectureDto.applicationDate
			)
		}
	}
}
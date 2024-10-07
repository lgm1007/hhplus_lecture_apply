package com.example.hhpluslectureapply.infrastructure.lecture.entity

import com.example.hhpluslectureapply.domain.lecture.dto.LectureDto
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Lecture(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long = 0,
	val title: String,
	val lecturer: String,
) {

	companion object {
		fun from(lectureDto: LectureDto): Lecture {
			return Lecture(
				lectureDto.lectureId,
				lectureDto.title,
				lectureDto.lecturer,
			)
		}
	}
}
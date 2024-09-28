package com.example.hhpluslectureapply.infrastructure.lecture.entity

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Lecture(
	@Id
	@GeneratedValue
	val id: Long?,
	val title: String,
	val lecturer: String,
	val applicationDate: LocalDateTime,
) {
}
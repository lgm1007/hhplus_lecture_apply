package com.example.hhpluslectureapply.infrastructure.lecture.entity

import com.example.hhpluslectureapply.domain.lecture.dto.LectureApplyHistoryDto
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class LectureApplyHistory(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long?,
	val lectureId: Long,
	val userId: Long,
	@CreatedDate
	val createdDate: LocalDateTime?,
) {
	companion object {
		fun from(lectureApplyHistoryDto: LectureApplyHistoryDto): LectureApplyHistory {
			return LectureApplyHistory(
				null,
				lectureApplyHistoryDto.lectureId,
				lectureApplyHistoryDto.userId,
				null
			)
		}
	}
}
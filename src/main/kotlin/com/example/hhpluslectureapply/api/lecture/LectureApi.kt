package com.example.hhpluslectureapply.api.lecture

import com.example.hhpluslectureapply.api.lecture.request.LectureApplyRequest
import com.example.hhpluslectureapply.usecase.lecture.LectureApplyInfo
import com.example.hhpluslectureapply.usecase.lecture.LectureFacade
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/lecture")
class LectureApi(
	private val lectureFacade: LectureFacade
) {
	/**
	 * 특강 신청 API
	 */
	@PostMapping("/")
	fun applyLecture(@RequestBody lectureApplyRequest: LectureApplyRequest): ResponseEntity<Any?> {
		lectureFacade.applyLecture(LectureApplyInfo.from(lectureApplyRequest))
		return ResponseEntity.ok(Any())
	}

}
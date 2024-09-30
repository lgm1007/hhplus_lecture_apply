package com.example.hhpluslectureapply.api.lecture

import com.example.hhpluslectureapply.api.lecture.request.LectureApplyRequest
import com.example.hhpluslectureapply.api.lecture.response.LectureResponse
import com.example.hhpluslectureapply.usecase.lecture.LectureApplyInfo
import com.example.hhpluslectureapply.usecase.lecture.LectureFacade
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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

	@GetMapping("/apply/list")
	fun getAppliableLectures(): ResponseEntity<List<LectureResponse>> {
		return ResponseEntity.ok(
			lectureFacade.getAllAppliableLectures().map { LectureResponse.from(it) }.toList()
		)
	}

}
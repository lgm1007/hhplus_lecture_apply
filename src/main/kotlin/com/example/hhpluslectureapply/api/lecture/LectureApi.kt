package com.example.hhpluslectureapply.api.lecture

import com.example.hhpluslectureapply.api.lecture.request.LectureApplyRequest
import com.example.hhpluslectureapply.api.lecture.response.LectureResponse
import com.example.hhpluslectureapply.usecase.lecture.LectureFacade
import com.example.hhpluslectureapply.usecase.lecture.dto.LectureApplyInfo
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

	/**
	 * 현재 신청 가능한 특강 목록 조회하기
	 */
	@GetMapping("/apply/list")
	fun getAppliableLectures(): ResponseEntity<List<LectureResponse>> {
		return ResponseEntity.ok(
			lectureFacade.getAllAppliableLectures().map { LectureResponse.from(it) }.toList()
		)
	}

	/**
	 * 특정 사용자가 신청 완료한 특강 목록 조회하기
	 */
	@GetMapping("list/user/{userId}")
	fun getLecturesByUserId(@PathVariable userId: Long): ResponseEntity<List<LectureResponse>> {
		return ResponseEntity.ok(
			lectureFacade.getAllLecturesByUserApplied(userId).map { LectureResponse.from(it) }.toList()
		)
	}

}
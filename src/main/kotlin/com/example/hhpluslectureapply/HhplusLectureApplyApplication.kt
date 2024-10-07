package com.example.hhpluslectureapply

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class HhplusLectureApplyApplication

fun main(args: Array<String>) {
	runApplication<HhplusLectureApplyApplication>(*args)
}

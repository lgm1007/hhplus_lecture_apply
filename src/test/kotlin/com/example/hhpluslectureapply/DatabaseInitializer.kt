package com.example.hhpluslectureapply

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class DatabaseInitializer {
	@PersistenceContext
	private lateinit var entityManager: EntityManager

	@Transactional
	fun initializeDatabase() {
		entityManager.createNativeQuery("ALTER TABLE Lecture AUTO_INCREMENT 1").executeUpdate()
		entityManager.createNativeQuery("ALTER TABLE LectureApplyHistory AUTO_INCREMENT 1").executeUpdate()
	}
}
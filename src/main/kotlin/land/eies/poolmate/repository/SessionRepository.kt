package land.eies.poolmate.repository

import land.eies.poolmate.domain.Session
import org.springframework.data.jpa.repository.JpaRepository

interface SessionRepository : JpaRepository<Session, Long> {

    fun findByUserId(userId: Long): List<Session>
}

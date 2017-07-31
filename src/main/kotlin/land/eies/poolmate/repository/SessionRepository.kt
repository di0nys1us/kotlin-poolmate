package land.eies.poolmate.repository

import land.eies.poolmate.domain.Session
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface SessionRepository : JpaRepository<Session, Long> {

    fun findByUserIdAndDateBetween(userId: Long, from: LocalDate, to: LocalDate): List<Session>
}

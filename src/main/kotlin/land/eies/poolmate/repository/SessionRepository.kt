package land.eies.poolmate.repository

import land.eies.graphql.annotation.GraphQLRepository
import land.eies.poolmate.domain.Session
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

@GraphQLRepository
interface SessionRepository : JpaRepository<Session, Long> {

    fun findByUserId(userId: Long): List<Session>

    fun findByUserIdAndDateBetween(userId: Long, minimum: LocalDate, maximum: LocalDate): List<Session>
}

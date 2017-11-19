package land.eies.poolmate.repository

import land.eies.graphql.annotation.GraphQLRepository
import land.eies.poolmate.domain.Session
import land.eies.poolmate.domain.SessionSet
import land.eies.poolmate.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

@GraphQLRepository
interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String): User?
}

@GraphQLRepository
interface SessionRepository : JpaRepository<Session, Long> {

    fun findByUserId(userId: Long): List<Session>

    fun findByUserIdAndDateBetween(userId: Long, minimum: LocalDate, maximum: LocalDate): List<Session>
}

@GraphQLRepository
interface SessionSetRepository : JpaRepository<SessionSet, Long> {

    fun findSessionSetsBySessionId(sessionId: Long): List<SessionSet>
}

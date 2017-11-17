package land.eies.poolmate.repository

import land.eies.graphql.annotation.GraphQLRepository
import land.eies.poolmate.domain.SessionSet
import org.springframework.data.jpa.repository.JpaRepository

@GraphQLRepository
interface SessionSetRepository : JpaRepository<SessionSet, Long> {

    fun findSessionSetsBySessionId(sessionId: Long): List<SessionSet>
}
